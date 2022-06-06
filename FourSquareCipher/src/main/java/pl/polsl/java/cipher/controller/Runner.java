/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pl.polsl.java.cipher.controller;

import javax.swing.JFrame;

/**
 * Represents the main class of the program
 * @author Borys Pala
 * @version 3.1
 */
public class Runner {

    /**
     * Non parameter constructor
     */
    public Runner(){
        
    }
    
    /**
     * Main method of the application
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AppController menu = new AppController(args);
        //menu.start();
    }
    
}
