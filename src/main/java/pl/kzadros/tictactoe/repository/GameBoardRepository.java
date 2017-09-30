package pl.kzadros.tictactoe.repository;

import pl.kzadros.tictactoe.database.LocalDatabase;
import pl.kzadros.tictactoe.entities.GameBoard;

/**
 *
 * @author kzadros
 */
public class GameBoardRepository implements Repository<GameBoard> {

    private final LocalDatabase database = new LocalDatabase();
    
    @Override
    public GameBoard find(String id) {
        GameBoard entity = database.getGameBoards()
                .stream()
                .filter(board->board.getId().equals(id))
                .findAny()
                .orElse(null);
        return entity;
    }

    @Override
    public GameBoard save(GameBoard entity) throws Exception {
        if (find(entity.getId()) != null)
            throw new Exception("GameBoard already exists in database.");
        database.getGameBoards().add(entity);
        return entity;
    }

    @Override
    public void remove(GameBoard entity) throws Exception {
        GameBoard obj = find(entity.getId());
        if (obj == null)
            throw new Exception("GameBoard can't be removed, it doesn't exist in database.");
        database.getGameBoards().remove(obj);
    }
    
}
