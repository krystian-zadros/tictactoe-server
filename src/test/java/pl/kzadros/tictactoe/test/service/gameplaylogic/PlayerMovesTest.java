package pl.kzadros.tictactoe.test.service.gameplaylogic;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.kzadros.tictactoe.dto.MoveDto;
import pl.kzadros.tictactoe.entities.GameBoard;
import pl.kzadros.tictactoe.entities.User;
import pl.kzadros.tictactoe.entities.factory.GameBoardFactory;
import pl.kzadros.tictactoe.entities.factory.UserFactory;
import pl.kzadros.tictactoe.exceptions.moves.MoveMatchedWithWrongGameIdException;
import pl.kzadros.tictactoe.service.gameplaylogic.PlayerMoves;
import pl.kzadros.tictactoe.states.FieldStates;

/**
 *
 * @author kzadros
 */
public class PlayerMovesTest {
    private static  User mistrz;
    private static User malgorzata;
    private static GameBoard game;
    private static MoveDto move;
    
    @BeforeClass
    public static void init() {
        mistrz = UserFactory.create("mistrz");
        malgorzata = UserFactory.create("malgorzata");
        game = GameBoardFactory.create(3);
        
        List<User> players = new ArrayList<>();
        players.add(mistrz);
        players.add(malgorzata);
        game.setPlayers(players);
        
        move = new MoveDto();
        move.setUser(mistrz.getId());
        move.setGameId(game.getId());
        move.setCol(1);
        move.setRow(0);
    }
    
    @Before
    public void resetValues() {
        move.setGameId(game.getId());
    }
    
    @Test
    public void makeMove() throws Exception {
        // Given
        String idMistrz = mistrz.getId();
        String idMalgorzata = malgorzata.getId();
        
        // Do
        PlayerMoves playerMoves = new PlayerMoves();
        playerMoves.makeMove(game, mistrz, move);
        // Then
        Assert.assertNotSame(
                FieldStates.EMPTY, 
                game.getBoard()[move.getRow()][move.getCol()]);
    }
    
    @Test(expected = MoveMatchedWithWrongGameIdException.class)
    public void canMismatchedGameAndMoveBeDetected() throws Exception {
        // Given
        move.setGameId("wrongGameId");
        // Do
        PlayerMoves playerMoves = new PlayerMoves();
        playerMoves.makeMove(game, mistrz, move);
    }
}
