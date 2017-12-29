package pl.kzadros.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.kzadros.tictactoe.controller.GameController;
import pl.kzadros.tictactoe.dto.MoveDto;
import pl.kzadros.tictactoe.entities.GameBoard;
import pl.kzadros.tictactoe.entities.Role;
import pl.kzadros.tictactoe.entities.User;
import pl.kzadros.tictactoe.entities.factory.GameBoardFactory;
import pl.kzadros.tictactoe.entities.factory.UserFactory;
import pl.kzadros.tictactoe.repository.GameBoardRepository;
import pl.kzadros.tictactoe.repository.UserRepository;

public class TicTacToe {
    public static void initDatabase() throws Exception {
        User mistrz = UserFactory.create("Mistrz");
        User malgorzata = UserFactory.create("Malgorzata");

        mistrz.addRole(Role.createPlayer());
        malgorzata.addRole(Role.createPlayer());
        
        UserRepository userRepo = new UserRepository();
        userRepo.save(mistrz);
        userRepo.save(malgorzata);
        
        GameBoard game = GameBoardFactory.createTicTacToe();
        List<User> players = new ArrayList<>();
        players.add(mistrz);
        players.add(malgorzata);
        game.setPlayers(players);
        
        GameBoardRepository gameRepo = new GameBoardRepository();
        gameRepo.save(game);
    }
    
    public static void main(String[] args) {
        try {
            initDatabase();
        } catch (Exception ex) {
            Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
        }
        GameController controller = new GameController();
        
        
        /*try {
            //GameBoard game = GameBoardFactory.create(3);
            String gameId = controller.initGame("1", "2");
            MoveDto move = new MoveDto();
            move.setCol(1);
            move.setRow(2);
            move.setUser("1");
            move.setGameId(gameId);
            controller.makeMove(move);
        } catch (Exception ex) {
            //Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        */
        
        controller.checkMovesSeries();

        System.out.println("Koniec.");
    }
}
