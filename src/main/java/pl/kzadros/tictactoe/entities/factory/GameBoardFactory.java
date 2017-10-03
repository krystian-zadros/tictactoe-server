package pl.kzadros.tictactoe.entities.factory;

import pl.kzadros.tictactoe.entities.GameBoard;
import pl.kzadros.tictactoe.states.FieldStates;

/**
 *
 * @author kzadros
 */
public class GameBoardFactory {
    private static int count = 0;
    public static GameBoard create(int size) {
        GameBoard game = new GameBoard();
        Integer[][] board = new Integer[size][size];
        for (int i=0 ; i<board.length ; i++) {
            for (int j=0 ; j<board[i].length ; j++) {
                board[i][j] = FieldStates.EMPTY.ordinal();
            }
        }
        game.setBoard(board);
        game.setId(Integer.toString(++count));
        game.setCurrentPlayerNumber(0);
        return game;
    }
}
