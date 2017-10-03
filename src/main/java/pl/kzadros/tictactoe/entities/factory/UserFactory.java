package pl.kzadros.tictactoe.entities.factory;

import java.util.ArrayList;
import java.util.List;
import pl.kzadros.tictactoe.entities.Role;
import pl.kzadros.tictactoe.entities.User;

/**
 *
 * @author kzadros
 */
public class UserFactory {
    private static int count = 0;
    
    public static User create(String name) {
        User user = create();
        user.setName(name);
        return user;
    }
    
    public static User create() {
        String id = Integer.toString(++count);
        User user = new User();
        List<Role> roles = new ArrayList<>();
        roles.add(Role.createUser());
        user.setId(id);
        user.setRoles(roles);
        return user;
    }
}
