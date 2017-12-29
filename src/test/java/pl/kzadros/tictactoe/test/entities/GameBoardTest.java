package pl.kzadros.tictactoe.test.entities;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import pl.kzadros.tictactoe.entities.GameBoard;
import pl.kzadros.tictactoe.entities.factory.GameBoardFactory;
import pl.kzadros.tictactoe.entities.figures.Field;
import pl.kzadros.tictactoe.states.FieldStates;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author kzadros
 */
public class GameBoardTest {
    
    int colsNumberExpectedForTicTacToe; // = 3;
    int rowsNumberExpectedForTicTacToe; // = 3;
    FieldStates fieldEmptyExpected; // = FieldStates.EMPTY.ordinal();
    
    @Before
    public void init() {
        colsNumberExpectedForTicTacToe = 3;
        rowsNumberExpectedForTicTacToe = 3;
        fieldEmptyExpected = FieldStates.EMPTY;
    }
    
    @Test
    public void checkInitializedBoardSizes() {
        // Given
        GameBoard board = GameBoardFactory.createTicTacToe();
        // Do
        int colNumbers = board.getBoard().length;
        int rowNumbers = board.getBoard()[0].length;
        // Then
        assertEquals(colNumbers, colsNumberExpectedForTicTacToe);
        assertEquals(rowNumbers, rowsNumberExpectedForTicTacToe);
    }
    
    /**
     * @TODO It's not elegant way of testing array. Would creating new array and using assertArrayEquals() be better?
     */
    @Test
    public void boardElementsInitializedProperly() {
        // Given
        GameBoard board = GameBoardFactory.createTicTacToe();
        
        // Do and Then
        for (Field[] row : board.getBoard()) {
            for (Field currentField : row) {
                assertEquals(currentField.getState(), fieldEmptyExpected);
            }
        }
    }
    
    @Test
    public void makeMoveTest() {
        
    }
    
}
