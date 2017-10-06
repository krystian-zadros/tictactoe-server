package pl.kzadros.tictactoe.service.gameplaylogic;

import pl.kzadros.tictactoe.dto.MoveDto;
import pl.kzadros.tictactoe.entities.GameBoard;
import pl.kzadros.tictactoe.entities.User;
import pl.kzadros.tictactoe.exceptions.moves.MoveMatchedWithWrongGameIdException;
import pl.kzadros.tictactoe.states.FieldStates;

/**
 *
 * @author kzadros
 */
public class PlayerMoves {
    
    private int encodeUserNumber(int userNumber) {
        return userNumber + FieldStates.PLAYER.ordinal();
    }

    private int decodeUserNumber(int userNumber) {
        return userNumber - FieldStates.PLAYER.ordinal();
    }
    
    public void makeMove(GameBoard game, User user, MoveDto move) throws Exception {
        if (!game.getId().equals(move.getGameId()))
            throw new MoveMatchedWithWrongGameIdException();
        User actualUser = game.getPlayers()
                .get(game.getCurrentPlayerNumber());
        if (!actualUser.getId().equals(user.getId()))
            throw new Exception("User cannot make move.");
        
        Integer[][] board = game.getBoard();
        int boardHeight = board.length;
        int boardWidth = board[0].length;
        if (move.getRow() < 0 
                || move.getCol() < 0
                || move.getRow() > boardWidth 
                || move.getCol() > boardHeight)
            throw new Exception("Move is out of bound of board.");
        
        if (board[move.getRow()][move.getCol()] != FieldStates.EMPTY.ordinal())
            throw new Exception("Field isn't empty.");
        
        board[move.getRow()][move.getCol()] = encodeUserNumber(game.getCurrentPlayerNumber());
    }
}
