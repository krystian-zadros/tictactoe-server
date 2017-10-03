package pl.kzadros.tictactoe.repository;

import pl.kzadros.tictactoe.entities.Role;
import pl.kzadros.tictactoe.entities.User;
import pl.kzadros.tictactoe.entities.factory.UserFactory;

/**
 *
 * @author kzadros
 */
public class MockDatabase {
    public static void initDatabase() throws Exception {
        User mistrz = UserFactory.create("Mistrz");
        User malgorzata = UserFactory.create("Malgorzata");

        mistrz.addRole(Role.createPlayer());
        malgorzata.addRole(Role.createPlayer());
        
        UserRepository userRepo = new UserRepository();
        userRepo.save(mistrz);
        userRepo.save(malgorzata);
    }
}
