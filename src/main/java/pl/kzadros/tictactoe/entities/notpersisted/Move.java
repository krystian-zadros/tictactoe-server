package pl.kzadros.tictactoe.entities.notpersisted;

import pl.kzadros.tictactoe.dto.MoveDto;
import pl.kzadros.tictactoe.entities.GameBoard;
import pl.kzadros.tictactoe.entities.User;

/**
 *
 * @author kzadros
 */
public class Move {
    private User user;
    private GameBoard game;
    private int row;
    private int col;

    public Move(User user, GameBoard game) {
        this.user = user;
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GameBoard getGame() {
        return game;
    }

    public void setGame(GameBoard game) {
        this.game = game;
    }
    
    public void setCoordinates(MoveDto moveDto) {
        this.row = moveDto.getRow();
        this.col = moveDto.getCol();
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
