package pl.kzadros.tictactoe.entities.factory;

import pl.kzadros.tictactoe.entities.GameBoard;
import pl.kzadros.tictactoe.entities.figures.Field;
import pl.kzadros.tictactoe.states.FieldStates;

/**
 *
 * @author kzadros
 */
public class GameBoardFactory {
    private static int counter = 0;
    private static GameBoard create(int size) {
        GameBoard game = new GameBoard();
        Field[][] board = new Field[size][size];
        for (int i=0 ; i<board.length ; i++) {
            for (int j=0 ; j<board[i].length ; j++) {
                board[i][j] = new Field(FieldStates.EMPTY, -1);
            }
        }
        game.setBoard(board);
        game.setId(Integer.toString(++counter));
        game.setCurrentPlayerNumber(0);
        return game;
    }
    
    public static GameBoard createTicTacToe() {
        return create(3);
    }
}
