/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.java.cipher.controller;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import pl.polsl.java.cipher.model.InvalidCharacterException;
import pl.polsl.java.cipher.view.AppView;
import pl.polsl.java.cipher.model.AppModel;
import pl.polsl.java.cipher.view.GUIView;

/**
 * Represents the way of managing program flow
 * @author Borys Pala
 * @version 3.1
 */
public class AppController {
    
    /**
     * View field
     */
    private AppView menu;
    
    
    /**
     * GUIView field
     */
    private GUIView menu2;
    
    /**
     * Model field
     */
    private AppModel model;
    
    /**
     * Contains the state of inputs validation
     */
    private boolean validated;
    
    /**
     * Non parameter constructor
     */
    public AppController(){
        
    }
    
    /**
     * AppController constructor
     * @param args program parameters
     */
    public AppController(String[] args){
        this.menu = new AppView();
        this.menu2 = new GUIView();
        this.validated = false;
        this.menu2.getbtnAdd().addActionListener(e -> saveTextFields(args));
        this.menu2.getbtnEncrypt().addActionListener(e -> doEncryption());
        this.menu2.getbtnDecrypt().addActionListener(e -> doDecryption());
        
//        String key1 = this.menu2.enterKey();
//        String key2 = this.menu2.enterKey2();
//        //this.menu.showIntroduction();
//        
//        //String key1 = this.menu.enterKey();
//        //String key2 = this.menu.enterKey();
//
//        if(checkData(args)){
//            this.model = new AppModel(key1.toUpperCase(), key2.toUpperCase(), args);
//        }
//        else{
//            ArrayList<String> data = this.menu.askAboutText();
//            this.model = new AppModel(key1.toUpperCase(), key2.toUpperCase(), data);
//        }
//       
//        boolean key1Validation = false;
//        boolean key2Validation = false;
//        boolean dataValidation = false;
//        
//        do {
//            do{
//                try{
//                    key1Validation = model.validateCharacters(model.getKey1());
//                }
//                catch(InvalidCharacterException ex){
//                    menu.showInfo(ex.getMessage());
//                }
//                if(!key1Validation) {
//                    key1 = menu2.enterKey();
//                    model.setKey1(key1.toUpperCase());
//                }
//            }while(key1Validation != true);
//            do{
//                try{
//                    key2Validation = model.validateCharacters(model.getKey2());
//                }
//                catch(InvalidCharacterException ex){
//                     menu.showInfo(ex.getMessage());
//                }
//                if(!key2Validation) {
//                    key2 = menu2.enterKey2();
//                    model.setKey2(key2.toUpperCase());
//                }
//            }while(key2Validation != true);
//            
//            do{
//                try{
//                    dataValidation = model.validateData();
//                }
//                catch(InvalidCharacterException ex){
//                     menu.showInfo(ex.getMessage());
//                }
//                if(!dataValidation) {
//                    ArrayList<String> data = menu.askAboutText();
//                    model.setData(data);
//                }
//            }while(dataValidation != true);
//        }while(key1Validation != true || key2Validation != true || dataValidation != true);
//        
//        model.initPlot();
    }
    
    /**
     * Takes text field values and performs validation
     * @param args program parameters
     */
    private void saveTextFields(String[] args){
       
        String key1 = menu2.getKey1();
        String key2 = menu2.getKey2();
   
        if(checkData(args)){            
            this.model = new AppModel(key1.toUpperCase(), key2.toUpperCase(), args);
        }
        else{
            ArrayList<String> data = this.menu2.getData();
            this.model = new AppModel(key1.toUpperCase(), key2.toUpperCase(), data);
        }
       
        boolean key1Validation = false;
        boolean key2Validation = false;
        boolean dataValidation = false;
        

                try{
                    key1Validation = model.validateCharacters(model.getKey1());
                }
                catch(InvalidCharacterException ex){
                     JOptionPane.showMessageDialog(null, "Key1 error");
                     return;
                }
//                if(!key1Validation) {
//                    key1 = menu2.getKey1();
//                    model.setKey1(key1.toUpperCase());
//                }


                try{
                    key2Validation = model.validateCharacters(model.getKey2());
                }
                catch(InvalidCharacterException ex){
                     JOptionPane.showMessageDialog(null, "Key2 error");
                     return;
                }
//                if(!key2Validation) {
//                    key2 = menu2.getKey2();
//                    model.setKey2(key2.toUpperCase());
//                }

            

                try{
                    dataValidation = model.validateData();
                }
                catch(InvalidCharacterException ex){
                     JOptionPane.showMessageDialog(null, "Data error");
                     return;
                }
//                if(!dataValidation) {
//                    ArrayList<String> data = menu2.getData();
//                    model.setData(data);
//                }
        JOptionPane.showMessageDialog(null, "Inputs successfully validated!");
        model.initPlot();
        validated = true;
        menu2.visibleEncrypt();
        menu2.visibleDecrypt();
    } 
    
    /**
     * Performs encryption on press of Encrypt button
     */
    private void doEncryption(){
        if(validated){
        model.encrypt();
             ArrayList<String> encrypted = model.getEncryptedData();
             String result = "";
             for(int i=0; i<encrypted.size(); i++){
                 result+=encrypted.get(i)+" ";
             }
             
             menu2.displayData(encrypted, "Encrypted string:");
             //menu2.setOutput(result);
             menu2.invisibleEncrypt();
             menu2.invisibleDecrypt();
        }
        else{
            JOptionPane.showMessageDialog(null, "Inputs not validated!");
        }
    }
    
     /**
      * Performs decryption on press of Decrypt button
      */
     private void doDecryption(){
        //menu2.invisible();
        if(validated){
        model.decrypt();
             ArrayList<String> data = model.getData();
             String result = "";
             for(int i=0; i<data.size(); i++){
                 result+=data.get(i) + " ";
             }
             
             menu2.displayData(data, "Decrypted string:");
             //menu2.setOutput(result);
             menu2.invisibleEncrypt();
             menu2.invisibleDecrypt();
        }
        else{
            JOptionPane.showMessageDialog(null, "Inputs not validated!");
        }
    }
    
    
     /**
      * Used for encryption/decryption based on user's choice
      */
     public void start(){
       
          //String k1 = model.getKey1();
//        String k2 = model.getKey2();
//        menu.filteredKey(k1);
//        menu.filteredKey(k2);
//        
//        menu2.setLabel(k1);
//        
//         int choice = menu.choice();
//         if(choice == 1){
//             model.encrypt();
//             ArrayList<String> encrypted = model.getEncryptedData();
//             menu.displayData(encrypted, "Encrypted string:");
//         }
//         else if(choice==2){
//             model.decrypt();
//             ArrayList<String> data = model.getData();
//             menu.displayData(data, "Decrypted string: ");
//         }
     }
     
    /**
    * Checks if the parameters were inputed
    * @param args program parameters
    * @return whether parameters were passed
    */
    private boolean checkData(String[] args) {
        if(args.length != 0) {
            return true;
        }
        else {
            return false;
        }
    }
     
}
