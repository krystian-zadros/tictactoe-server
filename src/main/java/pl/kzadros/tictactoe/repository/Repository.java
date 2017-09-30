package pl.kzadros.tictactoe.repository;

/**
 *
 * @author kzadros
 * @param <T>
 */
public interface Repository <T extends Object> {
    public T find(String id);
    public T save(T entity) throws Exception;
    public void remove(T entity) throws Exception;
}
