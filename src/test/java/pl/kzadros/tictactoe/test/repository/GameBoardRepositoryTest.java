package pl.kzadros.tictactoe.test.repository;

import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.kzadros.tictactoe.entities.GameBoard;
import pl.kzadros.tictactoe.entities.Role;
import pl.kzadros.tictactoe.entities.User;
import pl.kzadros.tictactoe.entities.factory.GameBoardFactory;
import pl.kzadros.tictactoe.entities.factory.UserFactory;
import pl.kzadros.tictactoe.repository.GameBoardRepository;
//import pl.kzadros.tictactoe.repository.MockDatabase;
import pl.kzadros.tictactoe.repository.UserRepository;
import static pl.kzadros.tictactoe.test.repository.UserRepositoryTest.ids;
import static pl.kzadros.tictactoe.test.repository.UserRepositoryTest.userRepo;

/**
 *
 * @author kzadros
 */
public class GameBoardRepositoryTest {
    public static GameBoardRepository gameRepo;
    public static UserRepository userRepo;
    public static List<String> idsUsers;
    
    @BeforeClass
    public static void init() throws Exception {
        gameRepo = new GameBoardRepository();
        userRepo = new UserRepository();
        idsUsers = new ArrayList<>();
        
        // Mock users.
        User mistrz = UserFactory.create("Mistrz");
        User malgorzata = UserFactory.create("Malgorzata");
        String id = mistrz.getId();
        idsUsers.add(id);
        
        mistrz.addRole(Role.createPlayer());
        malgorzata.addRole(Role.createPlayer());
        id = malgorzata.getId();
        idsUsers.add(id);
        
        UserRepository userRepo = new UserRepository();
        userRepo.save(mistrz);
        userRepo.save(malgorzata);
    }
    
    @AfterClass
    public static void destruct() throws Exception {
        User user;
        for (String id : idsUsers) {
            user = userRepo.find(id);
            userRepo.remove(user);
        }
    }
    
    @Test
    public void saveTest() throws Exception {
        GameBoard game = GameBoardFactory.create(3);
        User player1 = userRepo.find(idsUsers.get(0));
        User player2 = userRepo.find(idsUsers.get(1));
        List<User> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        game.setPlayers(players);
        gameRepo.save(game);
        GameBoard actualGame = gameRepo.find(game.getId());
        Assert.assertNotNull(actualGame);
        
        // Clean
        gameRepo.remove(game);
    }
}
