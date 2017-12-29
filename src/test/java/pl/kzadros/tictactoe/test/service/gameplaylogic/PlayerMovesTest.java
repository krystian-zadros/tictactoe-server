package pl.kzadros.tictactoe.test.service.gameplaylogic;

import java.util.ArrayList;
import java.util.Arrays;
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
import pl.kzadros.tictactoe.entities.notpersisted.Move;
import pl.kzadros.tictactoe.exceptions.moves.FieldIsAlreadyTakenException;
import pl.kzadros.tictactoe.exceptions.moves.MoveMatchedWithWrongGameIdException;
import pl.kzadros.tictactoe.exceptions.moves.MoveOutsideOfBoardException;
import pl.kzadros.tictactoe.exceptions.moves.WrongUserAttemptedToMakeMoveException;
import pl.kzadros.tictactoe.service.gameplaylogic.PlayerMovesService;
import pl.kzadros.tictactoe.states.FieldStates;

/**
 *
 * @author kzadros
 */
public class PlayerMovesTest {
    private static  User mistrz;
    private static User malgorzata;
    private static GameBoard game;
    private static MoveDto[] moveDtos;
    private static Move[] movesList;
    private static List<User> players;
    
    @BeforeClass
    public static void init() {
        mistrz = UserFactory.create("mistrz");
        malgorzata = UserFactory.create("malgorzata");
        game = GameBoardFactory.createTicTacToe();
        
        players = new ArrayList<>();
        players.add(mistrz);
        players.add(malgorzata);
        game.setPlayers(players);
        
        moveDtos = new MoveDto[4];
        movesList = new Move[moveDtos.length];
        
        MoveDto singleMove = new MoveDto();
        singleMove = new MoveDto();
        singleMove.setUserId(mistrz.getId());
        singleMove.setGameId(game.getId());
        singleMove.setRow(0);
        singleMove.setCol(1);
        moveDtos[0] = singleMove;
        movesList[0] = new Move(mistrz, game);
        movesList[0].setCoordinates(singleMove);
        
        singleMove = new MoveDto();
        singleMove.setUserId(malgorzata.getId());
        singleMove.setGameId(game.getId());
        singleMove.setRow(0);
        singleMove.setCol(0);
        moveDtos[1] = singleMove;
        movesList[1] = new Move(malgorzata, game);
        movesList[1].setCoordinates(singleMove);
        
        singleMove = new MoveDto();
        singleMove.setUserId(mistrz.getId());
        singleMove.setGameId(game.getId());
        singleMove.setRow(2);
        singleMove.setCol(2);
        moveDtos[2] = singleMove;
        movesList[2] = new Move(mistrz, game);
        movesList[2].setCoordinates(singleMove);
        
        singleMove = new MoveDto();
        singleMove.setUserId(malgorzata.getId());
        singleMove.setGameId(game.getId());
        singleMove.setRow(2);
        singleMove.setCol(1);
        moveDtos[3] = singleMove;
        movesList[3] = new Move(malgorzata, game);
        movesList[3].setCoordinates(singleMove);
        
        
    }
    
    @Before
    public void resetValues() {
        moveDtos[0].setGameId(game.getId());
    }
    
    @Test
    public void makeMoveChangesFieldTest() throws Exception {
        // Given
        GameBoard gameCurrent = GameBoardFactory.createTicTacToe();
        gameCurrent.setPlayers(players);
        Move move = new Move(players.get(0), gameCurrent);
        
        // Do
        PlayerMovesService playerMoves = new PlayerMovesService();
        playerMoves.makeMove(move);
        // Then
        Assert.assertNotSame(
                FieldStates.EMPTY, 
                game.getBoard()[move.getRow()][move.getCol()]);
    }
    
    @Test
    public void makeMoveSetsFieldForUser() throws Exception {
        // Given
        List<User> players = game.getPlayers();
        PlayerMovesService playingLogic = new PlayerMovesService();
        int[] expectedValues = {2, 1, 2, 1};
        int counter = 0;
        
        // Do
        int currentPlayerNumber = game.getCurrentPlayerNumber();
        int expected = 0;
        int[] actualValues = new int[moveDtos.length];
        try {
            for (Move move : movesList) {
                int userNumber = (counter)%2;
                System.out.println("Wait for user " + players.get(userNumber).getName());
                playingLogic.makeMove(move);
                actualValues[counter] = game.getCurrentPlayerNumber() + 1;
                ++counter;
                System.out.println("User " + players.get(userNumber).getName() + " made move.");
                System.out.println(move.getGame().generateBoardAsString());
                System.out.println("");
            }
        } catch (FieldIsAlreadyTakenException ex) {
            System.out.println(game.generateBoardAsString());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
        // Then
        System.out.println(Arrays.toString(actualValues));
        System.out.println(Arrays.toString(expectedValues));
        
        Assert.assertArrayEquals(actualValues, expectedValues);
    }
    /*
    // Nieaktualne
    @Test(expected = MoveMatchedWithWrongGameIdException.class)
    public void canMismatchedGameAndMoveBeDetected() throws Exception {
        // Given
        Move move = moves[0];
        //move.setGameId("wrongGameId");
        move.getGame().setId("wrongGameId");
        // Do
        PlayerMovesService playerMoves = new PlayerMovesService();
        playerMoves.makeMove(move);
    }*/
    
    @Test(expected = WrongUserAttemptedToMakeMoveException.class)
    public void wrongUserAttemptedToMakeMoveTest() throws Exception {
        // Given
        GameBoard gameCurrent = GameBoardFactory.createTicTacToe();
        gameCurrent.setPlayers(players);
        Move move1 = new Move(gameCurrent.getPlayers().get(0), gameCurrent);
        move1.setRow(1);
        move1.setCol(1);
        Move move2 = new Move(gameCurrent.getPlayers().get(0), gameCurrent);
        move2.setRow(0);
        move2.setCol(1);
        // Do
        PlayerMovesService playingLogic = new PlayerMovesService();
        playingLogic.makeMove(move1);
        playingLogic.makeMove(move2);
    }
    
    @Test(expected = MoveOutsideOfBoardException.class)
    public void moveOutsideOfBoardTest() throws Exception {
        // Given
        GameBoard gameCurrent = GameBoardFactory.createTicTacToe();
        gameCurrent.setPlayers(players);
        Move move = new Move(gameCurrent.getPlayers().get(0), gameCurrent);
        move.setRow(7);
        move.setCol(-1);
        // Do
        PlayerMovesService playingLogic = new PlayerMovesService();
        playingLogic.makeMove(move);
    }
}
