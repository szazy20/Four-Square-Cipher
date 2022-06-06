/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.java.cipher.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Represents the way of handling user interface
 * @author Borys Pala
 * @version 3.0
 */
public class AppView {
    
     private Stream<String> stream;
    /**
     * Non parameter constructor
     */
    public AppView(){
        
    }
    
    /**
     * Shows introduction
     */
    public void showIntroduction() {
        System.out.println("Four square cipher");
        System.out.println("The program allows to encrypt and decrypt messages using four square cipher");
    }
    
    /**
     * Displays data
     * @param data ArrayList of cipher text
     * @param message string containing a certain message
     */
    public void displayData(ArrayList<String> data, String message) {
        System.out.println(message);
        
        // java 8
        data.forEach(word -> {
            System.out.print(word + " ");
        });
        
    }
    
    /**
     * Prints a message in the parameter
     * @param message string containing a certain message
     */
    public void showInfo(String message){
        System.out.println(message);
    }
    
    /**
     * Method for entering keys
     * @return entered key
     */
    public String enterKey(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter key: ");
        String k1 = scanner.nextLine();
        String[] key = k1.split(" ");
        
        return key[0];
    }
    /**
     * Allows to choose encryption or decryption
     * @return x user input for option
     */
    public int choice(){
        System.out.println("Choose 1 for encryption or 2 for decryption");
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        return x;
    }
   
    /**
     * Fills string array with user inputs 
     * @return user input as array of strings
     */
    public ArrayList<String> askAboutText(){
        System.out.println("Enter text: ");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String[] data = text.split(" ");
        
        // rewritint to arraylist
        ArrayList<String> trueData = new ArrayList<String>();

        stream = Stream.of(data);
        stream.forEach(p -> trueData.add(p));
        
        return trueData;
    }
    
    /**
     * Prints filtered key
     * @param key string containing filtered key
     */
    public void filteredKey(String key){
       System.out.println("Filtered key: " + key);    
    }
    
    /**
     * Prints cipher squares
     * @param plot 2d array of characters of cipher squares
     */
    public void printPlot(char[][] plot){
        System.out.println("Plot: " + Arrays.toString(plot[0]));
    }
    
}
