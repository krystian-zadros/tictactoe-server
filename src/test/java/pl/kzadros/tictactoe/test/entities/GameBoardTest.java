package pl.kzadros.tictactoe.test.entities;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import pl.kzadros.tictactoe.entities.GameBoard;
import pl.kzadros.tictactoe.entities.factory.GameBoardFactory;
import pl.kzadros.tictactoe.states.FieldStates;

/**
 *
 * @author kzadros
 */
public class GameBoardTest {
    
    int colsNumberExpected; // = 3;
    int rowsNumberExpected; // = 3;
    int fieldEmptyExpected; // = FieldStates.EMPTY.ordinal();
    
    @Before
    public void init() {
        colsNumberExpected = 3;
        rowsNumberExpected = 3;
        fieldEmptyExpected = FieldStates.EMPTY.ordinal();
    }
    
    @Test
    public void checkInitializedBoardSizes() {
        // Given
        GameBoard board = GameBoardFactory.create(colsNumberExpected);
        // Do
        int colNumbers = board.getBoard().length;
        int rowNumbers = board.getBoard()[0].length;
        // Then
        assertEquals(colNumbers, colsNumberExpected);
        assertEquals(rowNumbers, rowsNumberExpected);
    }
    
    /**
     * @TODO It's not elegant way of testing array. Would creating new array and using assertArrayEquals() be better?
     */
    @Test
    public void boardElementsInitializedProperly() {
        // Given
        GameBoard board = GameBoardFactory.create(colsNumberExpected);
        
        // Do and Then
        for (Integer[] row : board.getBoard()) {
            for (int actualField : row) {
                assertEquals(actualField, fieldEmptyExpected);
            }
        }
    }
    
}
