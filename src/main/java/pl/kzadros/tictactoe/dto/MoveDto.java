package pl.kzadros.tictactoe.dto;

/**
 *
 * @author kzadros
 */
public class MoveDto {
    private String userId;
    private Integer row;
    private Integer col;
    private String gameId;

    public MoveDto() {
    }
    
    public String getUser() {
        return userId;
    }

    public void setUser(String user) {
        this.userId = user;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer x) {
        this.row = x;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer y) {
        this.col = y;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameBoardId) {
        this.gameId = gameBoardId;
    }
    
}
