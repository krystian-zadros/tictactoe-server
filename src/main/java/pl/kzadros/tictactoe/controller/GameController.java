package pl.kzadros.tictactoe.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.kzadros.tictactoe.dto.MoveDto;
import pl.kzadros.tictactoe.entities.GameBoard;
import pl.kzadros.tictactoe.entities.User;
import pl.kzadros.tictactoe.entities.factory.GameBoardFactory;
import pl.kzadros.tictactoe.entities.factory.UserFactory;
import pl.kzadros.tictactoe.entities.notpersisted.Move;
import pl.kzadros.tictactoe.exceptions.moves.FieldIsAlreadyTakenException;
import pl.kzadros.tictactoe.exceptions.moves.MoveOutsideOfBoardException;
import pl.kzadros.tictactoe.exceptions.moves.WrongUserAttemptedToMakeMoveException;
import pl.kzadros.tictactoe.repository.GameBoardRepository;
import pl.kzadros.tictactoe.repository.UserRepository;
import pl.kzadros.tictactoe.service.gameplaylogic.PlayerMovesService;
import pl.kzadros.tictactoe.service.json.ParserJSON;

/**
 *
 * @author kzadros
 */
public class GameController {
    private UserRepository userRepo = new UserRepository();
    private GameBoardRepository gameBoardRepository = new GameBoardRepository();
    
    public String initGame(String... usersIds) throws Exception {
        if (usersIds.length != 2)
            throw new Exception("Wrong amont of players. Given " + usersIds.length + ".");
        GameBoard board = GameBoardFactory.createTicTacToe();
        
        List<User> users = new ArrayList();
        User user;
        for (String id : usersIds) {
            user = userRepo.find(id);
            users.add(user);
        }
        board.setPlayers(users);
        gameBoardRepository.save(board);
        return board.getId();
    }
    
    public void makeMove(MoveDto moveDto) throws Exception {
        // 1. Validate
        if (moveDto.getGameId().isEmpty())
            throw new Exception("Empty gameId in MoveDto.");
        // 2. Get
        GameBoard game = gameBoardRepository.find(moveDto.getGameId());
        if (game == null)
            throw new Exception("Game with id='" + moveDto.getGameId() + "' not found.");
        User user = userRepo.find(moveDto.getUserId());
        Move move = new Move(user, game);
        move.setCoordinates(moveDto);
        
        // 3. Change
        // board.makeMove(moveDto);
        PlayerMovesService playingLogic = new PlayerMovesService();
        playingLogic.makeMove(move);
        
        // 4. Update in database
        // 5. Print
        ParserJSON parserJson = new ParserJSON();
        String json;
        json = parserJson.toJSON(game);
        System.out.println(json);
    }
    
    public void findFreeRooms() {
        
    }
    
    public void checkMovesSeries() {
        // Init **************************************
        
        User mistrz = UserFactory.create("mistrz");
        User malgorzata = UserFactory.create("malgorzata");
        GameBoard game = GameBoardFactory.createTicTacToe();
        
        List<User> players = new ArrayList<>();
        players.add(mistrz);
        players.add(malgorzata);
        game.setPlayers(players);
        
        MoveDto[] moveDtos = new MoveDto[4];
        Move[] moves = new Move[moveDtos.length];
        
        MoveDto singleMove = new MoveDto();
        singleMove = new MoveDto();
        singleMove.setUserId(mistrz.getId());
        singleMove.setGameId(game.getId());
        singleMove.setRow(0);
        singleMove.setCol(1);
        moveDtos[0] = singleMove;
        moves[0] = new Move(mistrz, game);
        moves[0].setCoordinates(singleMove);
        
        singleMove = new MoveDto();
        singleMove.setUserId(malgorzata.getId());
        singleMove.setGameId(game.getId());
        singleMove.setRow(0);
        singleMove.setCol(0);
        moveDtos[1] = singleMove;
        moves[1] = new Move(malgorzata, game);
        moves[1].setCoordinates(singleMove);
        
        singleMove = new MoveDto();
        singleMove.setUserId(mistrz.getId());
        singleMove.setGameId(game.getId());
        singleMove.setRow(2);
        singleMove.setCol(2);
        moveDtos[2] = singleMove;
        moves[2] = new Move(mistrz, game);
        moves[2].setCoordinates(singleMove);
        
        singleMove = new MoveDto();
        singleMove.setUserId(malgorzata.getId());
        singleMove.setGameId(game.getId());
        singleMove.setRow(2);
        singleMove.setCol(1);
        moveDtos[3] = singleMove;
        moves[3] = new Move(malgorzata, game);
        moves[3].setCoordinates(singleMove);
        
        // Make ***********************************
        System.out.println("Amount of potential moves: " + moveDtos.length);
        // Given
        players = game.getPlayers();
        PlayerMovesService playingLogic = new PlayerMovesService();
        int[] expectedValues = {2, 1, 2, 1};
        int counter = 0;
        
        // Do
        int[] actualValues = new int[moveDtos.length];
        try {
            for (int i=0 ; i<moves.length ; i++) {
                System.out.println("\nMove " + i);
                Move move = moves[i];
                int userNumber = (counter)%2;
                System.out.println("Wait for user " + players.get(userNumber).getName());
                playingLogic.makeMove(move);
                actualValues[counter] = game.getCurrentPlayerNumber() + 1;
                ++counter;
                
                System.out.println("User " + players.get(userNumber).getName() + " made move.");
            }
        } catch (FieldIsAlreadyTakenException ex) {
            System.out.println(game.generateBoardAsString());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        } catch (MoveOutsideOfBoardException ex) {
            ex.printStackTrace();
        } catch (WrongUserAttemptedToMakeMoveException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        // Then
        System.out.println(Arrays.toString(actualValues));
        System.out.println(Arrays.toString(expectedValues));
    }
}
