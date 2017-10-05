package pl.kzadros.tictactoe.entities;

import java.util.List;
import pl.kzadros.tictactoe.dto.MoveDto;
import pl.kzadros.tictactoe.states.FieldStates;

/**
 *
 * @author kzadros
 */
public class GameBoard {
    private String id;
    private Integer[][] board;
    private List<User> players;
    private Integer currentPlayerNumber;

    private int encodeUserNumber(int userNumber) {
        return userNumber + FieldStates.PLAYER.ordinal();
    }

    private int decodeUserNumber(int userNumber) {
        return userNumber - FieldStates.PLAYER.ordinal();
    }
    
    /**
     * @TODO czy hashCode() jest pozostaje takie samo po rekompilacji?
     * @param size 
     */
    public GameBoard() {
    }
    
    public Integer[][] getBoard() {
        return board;
    }

    public void setBoard(Integer[][] board) {
        this.board = board;
    }

    public List<User> getPlayers() {
        return players;
    }

    public void setPlayers(List<User> players) {
        this.players = players;
    }

    public Integer getCurrentPlayerNumber() {
        return currentPlayerNumber;
    }

    public void setCurrentPlayerNumber(Integer currentPlayerNumber) {
        this.currentPlayerNumber = currentPlayerNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void nextPlayerCanMove() {
        this.currentPlayerNumber = (currentPlayerNumber+1)%players.size();
    }
    
    public void makeMove(MoveDto move) throws Exception {
        int boardHeight = board.length;
        /*int boardWidth = board[0].length;
        if (move.getRow() < 0 
                || move.getCol() < 0
                || move.getRow() > boardWidth 
                || move.getCol() > boardHeight)
            throw new Exception("Move is out of bound of board.");
        if (board[move.getRow()][move.getCol()] != FieldStates.EMPTY.ordinal())
            throw new Exception("Field isn't empty.");
        
        board[move.getRow()][move.getCol()] = encodeUserNumber(currentPlayerNumber);*/
    }
}
