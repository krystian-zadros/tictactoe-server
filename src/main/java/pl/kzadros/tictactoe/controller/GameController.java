package pl.kzadros.tictactoe.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.kzadros.tictactoe.dto.MoveDto;
import pl.kzadros.tictactoe.entities.GameBoard;
import pl.kzadros.tictactoe.entities.User;
import pl.kzadros.tictactoe.repository.GameBoardRepository;
import pl.kzadros.tictactoe.repository.UserRepository;
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
        GameBoard board = new GameBoard();
        board.init(3);
        
        List<User> users = new ArrayList();
        User user;
        for (String id : usersIds) {
            user = userRepo.find(id);
            users.add(user);
        }
        board.setPlayers(users);
        
        ParserJSON parserJson = new ParserJSON();
        String game;
        try {
            game = parserJson.toJSON(board);
            System.out.println(game);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return board.getId();
    }
    
    public void makeMove(MoveDto moveDto) throws Exception {
        // 1. Validate
        if (moveDto.getGameId().isEmpty())
            throw new Exception();
        // 2. Get
        GameBoard board = gameBoardRepository.find(moveDto.getGameId());
        // 3. Change
        board.makeMove(moveDto);
        // 4. Update in database
        // 5. Print
        ParserJSON parserJson = new ParserJSON();
        String json;
        json = parserJson.toJSON(board);
        System.out.println(json);
    }
    
    public void findFreeRooms() {
        
    }
}
