package pl.kzadros.tictactoe.exceptions.moves;

/**
 *
 * @author kzadros
 */
public class MoveOutsideOfBoardException extends Exception {
    protected String Message = "Requested move's coordinates are out of game board.";
}
