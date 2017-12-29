package pl.kzadros.tictactoe.entities.figures;

import pl.kzadros.tictactoe.states.FieldStates;

/**
 *
 * @author kzadros
 */
public class Field {
    private FieldStates state;
    private Integer userNumber;

    public Field() {
    }

    public Field(FieldStates state, Integer userOrder) {
        this.state = state;
        this.userNumber = userOrder;
    }
    
    public FieldStates getState() {
        return state;
    }

    public void setState(FieldStates state) {
        this.state = state;
    }

    public Integer getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
    }
    
}
