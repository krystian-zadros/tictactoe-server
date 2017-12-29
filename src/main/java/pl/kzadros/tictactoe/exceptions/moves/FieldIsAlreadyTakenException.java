/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kzadros.tictactoe.exceptions.moves;

/**
 *
 * @author kzadros
 */
public class FieldIsAlreadyTakenException extends Exception {
    private String message = "Field isn't empty.";
}
