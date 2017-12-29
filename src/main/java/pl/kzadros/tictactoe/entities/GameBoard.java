package pl.kzadros.tictactoe.entities;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import pl.kzadros.tictactoe.dto.MoveDto;
import pl.kzadros.tictactoe.entities.figures.Field;
import pl.kzadros.tictactoe.states.FieldStates;

/**
 *
 * @author kzadros
 */
public class GameBoard {
    private String id;
    private Field[][] board;
    private List<User> players;
    private Integer currentPlayerNumber;
    
    /**
     * @TODO czy hashCode() pozostaje takie samo po rekompilacji?
     * @param size 
     */
    public GameBoard() {
    }

    public List<User> getPlayers() {
        return players;
    }

    public void setPlayers(List<User> players) {
        this.players = players;
        players.sort(
            (user1, user2) -> (user1.getId().compareTo(user2.getId()))
        );
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
    
    public Field[][] getBoard() {
        return board;
    }

    public void setBoard(Field[][] boardBetter) {
        this.board = boardBetter;
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
    
    public String generateBoardAsString() {
        StringBuilder array = new StringBuilder();
        for (Field[] row : this.board) {
            array.append("[ ");
            for (Field field : row) {
                array.append("(");
                //array.append(field.getState());
                //array.append(" , ");
                array.append(field.getUserNumber()+1);
                array.append(")  ");
            }
            array.append("]\n");
        }
        return array.toString();
    }
}
