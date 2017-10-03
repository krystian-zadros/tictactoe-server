package pl.kzadros.tictactoe.test.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.kzadros.tictactoe.entities.Role;
import pl.kzadros.tictactoe.entities.User;
import pl.kzadros.tictactoe.entities.factory.UserFactory;
import pl.kzadros.tictactoe.repository.MockDatabase;
import pl.kzadros.tictactoe.repository.UserRepository;

/**
 *
 * @author kzadros
 */
public class UserRepositoryTest {
    UserRepository userRepo;
    
    @Before
    public void init() throws Exception {
        userRepo = new UserRepository();
        //MockDatabase.initDatabase();
        User mistrz = UserFactory.create("Mistrz");
        User malgorzata = UserFactory.create("Malgorzata");

        mistrz.addRole(Role.createPlayer());
        malgorzata.addRole(Role.createPlayer());
        
        userRepo.save(mistrz);
        userRepo.save(malgorzata);
    }
    
    @Test
    public void findById() {
        // Given
        String expectedId = "2";
        // Do
        User actualUser = userRepo.find(expectedId);
        String actualId = actualUser.getId();
        // Then
        Assert.assertNotNull(actualUser);
        Assert.assertNotNull(actualId);
        Assert.assertEquals(expectedId, actualId);
    }
    
    @Test
    public void saveTest() throws Exception {
        // Given
        User expected = UserFactory.create("TestUser");
        String userId = expected.getId();
        // Do
        userRepo.save(expected);
        // Then
        User actual = userRepo.find(userId);
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void removeTest() throws Exception {
        // Given
        User expected = UserFactory.create("TestUser");
        // Do
        userRepo.save(expected);
        // Then
        userRepo.remove(expected);
        User actual = userRepo.find(expected.getId());
        Assert.assertNull(actual);
    }
}
