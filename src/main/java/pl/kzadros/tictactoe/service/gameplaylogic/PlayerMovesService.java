package pl.kzadros.tictactoe.service.gameplaylogic;

import pl.kzadros.tictactoe.dto.MoveDto;
import pl.kzadros.tictactoe.entities.GameBoard;
import pl.kzadros.tictactoe.entities.User;
import pl.kzadros.tictactoe.entities.figures.Field;
import pl.kzadros.tictactoe.entities.notpersisted.Move;
import pl.kzadros.tictactoe.exceptions.moves.FieldIsAlreadyTakenException;
import pl.kzadros.tictactoe.exceptions.moves.MoveMatchedWithWrongGameIdException;
import pl.kzadros.tictactoe.exceptions.moves.MoveOutsideOfBoardException;
import pl.kzadros.tictactoe.exceptions.moves.WrongUserAttemptedToMakeMoveException;
import pl.kzadros.tictactoe.states.FieldStates;

/**
 *
 * @author kzadros
 */
public class PlayerMovesService {
    
    private int encodeUserNumber(int userNumber) {
        return userNumber + FieldStates.PLAYER.ordinal();
    }

    private int decodeUserNumber(int userNumber) {
        return userNumber - FieldStates.PLAYER.ordinal();
    }
    
    private boolean isMoveOutsideOfBoard(Move move) {
        Field[][] board = move.getGame().getBoard();
        int height = board.length;
        int width = board[0].length;
        return (move.getRow() < 0 
                || move.getCol() < 0
                || move.getRow() > width 
                || move.getCol() > height);
    }
    
    private boolean isItTheUserTurn(GameBoard game, User user) {
        User currentUser = game.getPlayers()
                .get(game.getCurrentPlayerNumber());
        return (currentUser.getId().equals(user.getId()));
    }
    
    private boolean isRequestedFieldEmpty(GameBoard game, Move move) {
        return (game
                .getBoard()[move.getRow()][move.getCol()]
                .getState()
                .equals(FieldStates.EMPTY));
    }
    
    public void makeMove(Move move) throws WrongUserAttemptedToMakeMoveException, MoveOutsideOfBoardException, FieldIsAlreadyTakenException {
        GameBoard game = move.getGame();
        User user = move.getUser();
        
        if (!isItTheUserTurn(game, user))
            throw new WrongUserAttemptedToMakeMoveException();
        if (isMoveOutsideOfBoard(move))
            throw new MoveOutsideOfBoardException();
        if (!isRequestedFieldEmpty(game, move))
            throw new FieldIsAlreadyTakenException();
        
        Field field = game.getBoard()[move.getRow()][move.getCol()];
        field.setState(FieldStates.PLAYER);
        field.setUserNumber(game.getCurrentPlayerNumber());
        game.nextPlayerCanMove();
    }
}
