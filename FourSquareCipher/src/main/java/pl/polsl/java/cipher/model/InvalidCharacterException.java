/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.java.cipher.model;

/**
 * Represents handling of exception for invalid characters
 * @author Borys Pala
 * @version 3.1
 */
public class InvalidCharacterException extends Exception {
    /**
     * InvalidCharacterException constuctor with no parameters
     */
    public InvalidCharacterException() {
    }

    /**
     * InvalidCharacterException constructor
     *
     * @param message exception message
     */
    public InvalidCharacterException(String message) {
        super(message);
    }
}
