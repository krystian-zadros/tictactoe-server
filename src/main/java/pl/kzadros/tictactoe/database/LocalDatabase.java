package pl.kzadros.tictactoe.database;

import java.util.HashSet;
import java.util.Set;
import pl.kzadros.tictactoe.entities.GameBoard;
import pl.kzadros.tictactoe.entities.User;

/**
 *
 * @author kzadros
 */
public class LocalDatabase {
    private static final Set<GameBoard> gameBoards = new HashSet<>();
    private static final Set<User> users = new HashSet<>();
    
    public Set<GameBoard> getGameBoards() {
        return gameBoards;
    }
    
    public Set<User> getUsers() {
        return users;
    }
}
