package pl.kzadros.tictactoe.test.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.kzadros.tictactoe.entities.GameBoard;
import pl.kzadros.tictactoe.entities.Role;
import pl.kzadros.tictactoe.entities.User;
import pl.kzadros.tictactoe.entities.factory.GameBoardFactory;
import pl.kzadros.tictactoe.entities.factory.UserFactory;
import pl.kzadros.tictactoe.repository.GameBoardRepository;
//import pl.kzadros.tictactoe.repository.MockDatabase;
import pl.kzadros.tictactoe.repository.UserRepository;

/**
 *
 * @author kzadros
 */
public class GameBoardRepositoryTest {
    GameBoardRepository gameRepo;
    UserRepository userRepo;
    
    @Before
    public void init() throws Exception {
        User mistrz = UserFactory.create("Mistrz");
        User malgorzata = UserFactory.create("Malgorzata");
        mistrz.setId("1test");
        mistrz.addRole(Role.createPlayer());
        malgorzata.addRole(Role.createPlayer());
        malgorzata.setId("2test");
        
        UserRepository userRepo = new UserRepository();
        userRepo.save(mistrz);
        userRepo.save(malgorzata);
    }
    
    /*@Test
    public void saveTest() {
        GameBoard game = GameBoardFactory.create(3);
        User user = userRepo.find("1test");
        String a = "aaa";
        Assert.assertNotNull(user);
    }*/
}
