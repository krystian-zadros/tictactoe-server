package pl.kzadros.tictactoe.repository;

import pl.kzadros.tictactoe.database.LocalDatabase;
import pl.kzadros.tictactoe.entities.User;

/**
 *
 * @author kzadros
 */
public class UserRepository implements Repository<User> {

    private final LocalDatabase database = new LocalDatabase();
    
    @Override
    public User find(String id) {
        User entity = database.getUsers()
                .stream()
                .filter(board->board.getId().equals(id))
                .findAny()
                .orElse(null);
        return entity;
    }

    @Override
    public User save(User entity) throws Exception {
        if (find(entity.getId()) != null)
            throw new Exception("User already exists in database.");
        database.getUsers().add(entity);
        return entity;
    }

    @Override
    public void remove(User entity) throws Exception {
        User obj = find(entity.getId());
        if (obj == null)
            throw new Exception("User can't be removed, it doesn't exist in database.");
        database.getUsers().remove(obj);
    }
    
}
