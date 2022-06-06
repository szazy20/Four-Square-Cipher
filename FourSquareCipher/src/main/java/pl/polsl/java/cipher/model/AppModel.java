/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.java.cipher.model;

import java.util.ArrayList;

/**
 * Represents logic of the application
 * @author Borys Pala
 * @version 3.1
 */

public class AppModel {
   /**
    * Input text for encryption/decryption, can be multi word
    */
    private ArrayList<String> data;
    /**
     * Encoded cipher text
     */
    private ArrayList<String> encoded;
    /**
     * First key for the cipher
     */
    private String key1;
    /**
     * Second key for the cipher
     */
    private String key2;
    /**
     * Array of chars used for alphabet plots and keys
     */
    private char[][] plot;
    
    /**
     * Non parameter constructor
     */
    public AppModel(){
        this.key1 = "";
        this.key2 = "";
    }
    
    /**
     * Constructs model with given parameters
     * @param key1 first key
     * @param key2 second key
     * @param data input text for encryption/decryption
     */
    public AppModel(String key1, String key2, String ... data) {      
        this.key1 = key1;
        this.key2 = key2;

        // rewriting to arraylist
        this.data = new ArrayList<String>();
        for(String element: data) {
            this.data.add(element);
        }
        
        this.key1 = removeDuplicates(key1);
        this.key2 = removeDuplicates(key2);
        this.encoded = this.data;
    }
    
     /**
     * Constructs model with given parameters
     * @param key1 first key
     * @param key2 second key
     * @param data ArrayList with text for encryption/decryption
     */
    public AppModel(String key1, String key2, ArrayList<String> data) {      
        this.key1 = key1;
        this.key2 = key2;

        this.data = data;
        this.key1 = removeDuplicates(key1);
        this.key2 = removeDuplicates(key2);
        this.encoded = data;
    }
    
    /**
     * data getter
     * @return string of data text
     */
    public ArrayList<String> getData() {
        return data;
    }
    
    /**
     * getter for encrypted data
     * @return encrypted data text
     */
    public ArrayList<String> getEncryptedData() {
        return encoded;
    }
    
    /**
     * Decrypts a text using Four square cipher
     */
    public void decrypt() {
        String[] pEncryptText;
        String overflow = "";
        char overflowCode = ' ';
        
        for(int k = 0; k < data.size(); k++){
            String originalCaseData = encoded.get(k);
            encoded.set(k, encoded.get(k).toUpperCase());
            
            char[] pairs = new char[2];
            String encryptedText = "";
            int cursor = 0;
            int length = encoded.get(k).length();

            if(!overflow.equals("")) {
                length = encoded.get(k).length() - 1;
                encryptedText += overflowCode;
                
                cursor = 1;
                overflow = "";
            }
            
            if(length%2==0){
                pEncryptText = new String[length / 2];
            }
            else{
                pEncryptText = new String[(length / 2) + 1];
            }

            for (int i = 0; i < (length / 2); i++) {
                pEncryptText[i] = "" + data.get(k).charAt(cursor) + data.get(k).charAt(cursor + 1);
                cursor = cursor + 2;
            }

            if(length%2!=0 && overflow.equals("")) {
                pEncryptText[pEncryptText.length-1] = "" + data.get(k).charAt(data.get(k).length()-1);
                if(k+1 < data.size()) {
                    overflow += data.get(k+1).charAt(0);
                    overflow = overflow.toUpperCase();
                }
                else {
                    pEncryptText[pEncryptText.length-1] += 'X';
                }
            }

            for (int i = 0; i < pEncryptText.length; i++) {
                int columnA = 0;
                int rowA = 0;
                int columnB = 0;
                int rowB = 0;

                pairs[0] = pEncryptText[i].charAt(0);

                if(i == pEncryptText.length - 1) {
                    if(!overflow.equals("")) {
                        pairs[1] = overflow.charAt(0);
                    }
                    else {
                        pairs[1] = pEncryptText[i].charAt(1); 
                    }
                }
                else {
                    pairs[1] = pEncryptText[i].charAt(1); 
                }

                for (int j = 0; j < plot[0].length; j++) {
                    if(pairs[0] == plot[1][j]) {
                        rowA = (j / 5) * 5;
                        columnA = j % 5;
                    }

                    if(pairs[1] == plot[2][j]) {
                        rowB = (j / 5) * 5;
                        columnB = j % 5;
                    }
                }

                encryptedText += plot[0][rowA + columnB];

                if(i == pEncryptText.length-1) {
                    if(!overflow.equals("")) {
                        overflowCode = plot[0][rowB + columnA];
                    }
                    else {
                        encryptedText += plot[0][rowB + columnA];  
                    }
                }
                else {
                    encryptedText += plot[0][rowB + columnA];  
                    
                }
            }
            StringBuilder result = new StringBuilder(encryptedText);  
            
            for(int g = 0; g < originalCaseData.length(); g++) {
                if(Character.isLowerCase(originalCaseData.charAt(g))) {
                    char letter = encryptedText.charAt(g);
                    result.setCharAt(g, Character.toLowerCase(letter));
                }
            }
            data.set(k, result.toString());
        }
    }
    
    /**
     * Encrypts a text using Four square cipher
     */
    public void encrypt() {
        String[] pEncryptText;
        String overflow = "";
        char overflowCode = ' ';
        
        for(int k = 0; k < data.size(); k++){
            String originalCaseData = data.get(k);
            data.set(k, data.get(k).toUpperCase());
            
            char[] pairs = new char[2];
            String encryptedText = "";
            int cursor = 0;
            int length = encoded.get(k).length();

            if(!overflow.equals("")) {
                length = encoded.get(k).length() - 1;
                encryptedText += overflowCode;
                
                cursor = 1;
                overflow = "";
            }
            
            if(length%2==0){
                pEncryptText = new String[length / 2];
            }
            else{
                pEncryptText = new String[(length / 2) + 1];
            }

            for (int i = 0; i < (length / 2); i++) {
                pEncryptText[i] = "" + data.get(k).charAt(cursor) + data.get(k).charAt(cursor + 1);
                cursor = cursor + 2;
            }

            if(length%2!=0 && overflow.equals("")) {
                pEncryptText[pEncryptText.length-1] = "" + data.get(k).charAt(data.get(k).length()-1);
                if(k+1 < data.size()) {
                    overflow += data.get(k+1).charAt(0);
                    overflow = overflow.toUpperCase();
                }
                else {
                    pEncryptText[pEncryptText.length-1] += 'X';
                }
            }

            for (int i = 0; i < pEncryptText.length; i++) {
                int columnA = 0;
                int rowA = 0;
                int columnB = 0;
                int rowB = 0;

                pairs[0] = pEncryptText[i].charAt(0);

                if(i == pEncryptText.length - 1) {
                    if(!overflow.equals("")) {
                        pairs[1] = overflow.charAt(0);
                    }
                    else {
                        pairs[1] = pEncryptText[i].charAt(1); 
                    }
                }
                else {
                    pairs[1] = pEncryptText[i].charAt(1); 
                }

                for (int j = 0; j < plot[0].length; j++) {
                    if(pairs[0] == plot[0][j]) {
                        rowA = (j / 5) * 5;
                        columnA = j % 5;
                    }

                    if(pairs[1] == plot[0][j]) {
                        rowB = (j / 5) * 5;
                        columnB = j % 5;
                    }
                }

                encryptedText += plot[1][rowA + columnB]; 

                if(i == pEncryptText.length-1) {
                    if(!overflow.equals("")) {
                        overflowCode = plot[2][rowB + columnA];
                    }
                    else {
                        encryptedText += plot[2][rowB + columnA];  
                    }
                }
                else {
                    encryptedText += plot[2][rowB + columnA]; 
                    
                }
                
            }
            
            StringBuilder result = new StringBuilder(encryptedText);  
            
            for(int g = 0; g < originalCaseData.length(); g++) {
                if(Character.isLowerCase(originalCaseData.charAt(g))) {
                    char letter = encryptedText.charAt(g);
                    result.setCharAt(g, Character.toLowerCase(letter));
                }
            }
            encoded.set(k, result.toString());   
        }
    }
    
        /**
         * Initializes alphabet plot
         */
      public void initPlot(){
        plot = new char[3][25];

        int counter = 0;
        for(char i='A'; i<='Z'; i++){
            if(i=='Q'){
                continue;
            }
            plot[0][counter] = i;
            counter++;
        }
          
        plot[1] = plotKey(key1, 'Q');
        plot[2] = plotKey(key2, 'Q');
      } 

    /**
     * Removes duplicates from the string 
     * @param key string to remove duplicates
     * @return filtered key string
     */
    public String removeDuplicates(String key) {
        char[] characters = key.toCharArray();
        String filtered = "";
        for(int i=0; i<characters.length; i++){
            boolean isRepeated = false;

            for(int j=0; j<i; j++){
                if(characters[i]==characters[j]){
                    isRepeated = true;
                    break;
                }
            }
            if(!isRepeated){
                filtered+=characters[i];
            }
        }
        return filtered;
    }
    
    /**
     * key1 getter
     * @return key1
     */
    public String getKey1(){
        return key1;
    }
    
    /**
     * key2 setter
     * @return key2
     */
    public String getKey2(){
        return key2;
    }
    
    /**
     * key1 setter
     * @param key string containing a key
     */
    public void setKey1(String key) {
        key1 = removeDuplicates(key);
        key1 = key1.toUpperCase();
    }
    
    /**
     * key2 setter
     * @param key string containing a key
     */
    public void setKey2(String key) {
        key2 = removeDuplicates(key);
        key2 = key2.toUpperCase();
    }
    
    /**
     * data setter
     * @param data string array of cipher text
     */
    public void setData(ArrayList<String> data) {
        this.data = data;
        this.encoded = data;
    }
   
    /**
     * plot getter
     * @return plot char array
     */
   public char[] getPlot1(){
       return plot[0];
   }
   
   /**
    * Validates input key
    * @param s string containing key
    * @return true if key consists only of english letters
    * @throws InvalidCharacterException when an encountered character is not a letter from english alphabet
    */
    public Boolean validateCharacters(String s) throws InvalidCharacterException {
        
            for(int i = 0; i < s.length(); i++){
                if(!Character.isLetter(s.charAt(i))){
                    throw new InvalidCharacterException("Invalid character detected in key!");
                }
            }
        
        return true;
    }
    
    /**
     * Validates input text
     * @return true if data consists only of english letters
     * @throws InvalidCharacterException when an encountered character is not a letter from english alphabet
     */
    public Boolean validateData() throws InvalidCharacterException{
        
           for(int i = 0; i < data.size(); i++) {
                for(int j = 0; j < data.get(i).length(); j++) {
                    if(!Character.isLetter(data.get(i).charAt(j))) {
                        throw new InvalidCharacterException("Invalid character detected in input data!");
                    }
                }
            } 
        
        return true;
    }

    /**
     * Plots alphabet plot starting with a key
     * @param key string containing cipher key
     * @param remove char to remove from the plot
     * @return char array with the plot
     */
    private char[] plotKey(String key, char remove) {
        char[] plot = new char[25];
        int cursor = 0; 
        char[] pkey = key.toCharArray(); 

        for (int i = 0; i < pkey.length; i++){
            plot[i] = pkey[i];
        }

        for (int i = pkey.length; i < plot.length; i++){

            if((char)('A' + cursor) == remove){
                cursor++;
            }

            int checks = 2; 
            for (int j = 0; j < checks; j++){
                for (int k = 0; k < pkey.length; k++){
                    if(pkey[k] == (char)('A' + cursor)){
                        cursor++;
                        break;
                    }
                }
            }

            if((char)('A' + cursor) == remove){ 
                cursor++;
            }

            plot[i] = (char)('A' + cursor);
            cursor++;

        }
        return plot;
    }
    

}

