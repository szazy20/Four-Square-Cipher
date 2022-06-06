package pl.polsl.java.cipher.tests;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.java.cipher.model.AppModel;
import pl.polsl.java.cipher.model.InvalidCharacterException;

/**
 * Class tests AppModel methods
 * 
 * @author Borys Pala
 * @version 2.1
 */
public class FourSquareCipherModelTest {
    
    /**
     * Model field
     */
    private AppModel model;
    
    /**
     * Contains key1
     */
    private final String key1;
    
    /**
     * Contains key2
     */
    private final String key2;
    
    /**
     * non-parameter constructor
     */
    public FourSquareCipherModelTest() {
        model = new AppModel();
        key1 = "GEASGWAWGHA";
        key2 = "AHHEAEWAW";
        
        model.setKey1(key1);
        model.setKey2(key2);
        model.initPlot();
    }
    
    /**
    * throwing exception test by passing invalid characters to key, validateCharacters() method
    */
    @ParameterizedTest
    @ValueSource(strings = {"&^DF", "xd1"})
    void invalidKey(String text) {
        assertThrows(InvalidCharacterException.class, () -> model.validateCharacters(text), "invalid input");
    }
    
    /**
    * throwing exception test by passing invalid characters to input data, validateData() method
    */
    @ParameterizedTest
    @ValueSource(strings = {"&^DF", "xd1"})
    void invalidInputData(String text) {
        ArrayList<String> data = new ArrayList<>();
        data.add(text);
        model.setData(data);
        assertThrows(InvalidCharacterException.class, () -> model.validateData(), "invalid input");
    }

    /**
     * checks whether plot was generated without letter 'Q', initPlot() method
     */
    @Test
    void plotTest(){
        model.initPlot();
        char[] modelResult = model.getPlot1();
        char[] result = new char[25];
        int counter = 0;
        
        for(char i='A'; i<='Z'; i++){
            if(i=='Q'){
                continue;
            }
            result[counter] = i;
            counter++;
        }
           
        for(int i = 0; i < modelResult.length; i++) {
            if(!(modelResult[i] == result[i])) {
                assertEquals(modelResult[i], result[i]);
            }
        }
        
        assertTrue(true, "Success!");
    }
    
    /**
     * checks encryption with multiword input
     */
    @Test
    void encryptionByMultiWords(){
        ArrayList<String> data = new ArrayList<>();
        data.add("hi");
        data.add("testing");
        data.add("machine");
        model.setData(data);
        
        model.encrypt();
        ArrayList<String> encrypted = model.getEncryptedData();
        
        ArrayList<String> result = new ArrayList<>();
        result.add("df");
        result.add("twrrdmc");
        result.add("kaadfmw");
        
        for(int i = 0; i < encrypted.size(); i++) {
            if(!(encrypted.get(i).equals(result.get(i)))) {
                assertEquals(encrypted.get(i), result.get(i));
            }
        }
        
        assertTrue(true, "Success!");
    }
    
    /**
     * check encryption with one word input
     */
    @Test
    void encryptionByOneWord(){
       ArrayList<String> data = new ArrayList<>();
        data.add("testing");
        model.setData(data);
        
        model.encrypt();
        ArrayList<String> encrypted = model.getEncryptedData();
        
        ArrayList<String> result = new ArrayList<>();
        result.add("twrrdmc");
        result.add(result.get(0) + "V");
        
        if(!encrypted.get(0).equals(result.get(0))) {
            if(encrypted.get(0).equals(result.get(1)) ){
               assertEquals(encrypted.get(0), result.get(1)); 
            }
            else {
                assertEquals(encrypted.get(0), result.get(0)); 
            }  
        }
        
        assertTrue(true, "Success!");  
    }
    
    /**
     * checks encryption values of small letter
     */
    @Test
    void encryptionSmallLetter(){
        ArrayList<String> data = new ArrayList<>();
        data.add("b");
        model.setData(data);
        
        model.encrypt();
        ArrayList<String> encrypted = model.getEncryptedData();
        
        ArrayList<String> result = new ArrayList<>();
        result.add("aV");
        
        
        if(!encrypted.get(0).equals(result.get(0))) {
            assertEquals(encrypted.get(0), result.get(0)); 
        }
        
        assertTrue(true, "Success!");  
    }
    
     /**
     * checks encryption values of capital letter
     */
    @Test
    void encryptionCapitalLetter(){
        ArrayList<String> data = new ArrayList<>();
        data.add("C");
        model.setData(data);
        
        model.encrypt();
        ArrayList<String> encrypted = model.getEncryptedData();
        
        ArrayList<String> result = new ArrayList<>();
        result.add("AX");      
        
        if(!encrypted.get(0).equals(result.get(0))) {
            assertEquals(encrypted.get(0), result.get(0)); 
        }
        
        assertTrue(true, "Success!");  
    }
    
    /**
     * checks decryption with multiword input
     */
    @Test
    void decryptionByMultiWords(){
        ArrayList<String> data = new ArrayList<>();
        data.add("df");
        data.add("twrrdmc");
        data.add("kaadfmw");
        model.setData(data);
        
        model.decrypt();
        ArrayList<String> encrypted = model.getData();
        
        ArrayList<String> result = new ArrayList<>();
        result.add("hi");
        result.add("testing");
        result.add("machine");
        
        for(int i = 0; i < encrypted.size(); i++) {
            if(!(encrypted.get(i).equals(result.get(i)))) {
                assertEquals(encrypted.get(i), result.get(i));
            }
        }
        
        assertTrue(true, "Success!");
    }
    
    /**
     * checks decryption with single word
     */
    @Test
    void decryptionByOneWord(){
        ArrayList<String> data = new ArrayList<>();
        data.add("twrrdmcV");
        model.setData(data);
        
        model.decrypt();
        ArrayList<String> encrypted = model.getData();
        
        ArrayList<String> result = new ArrayList<>();
        result.add("testingX");
        
        if(!encrypted.get(0).equals(result.get(0))) {
            assertEquals(encrypted.get(0), result.get(0)); 
        }
        
        assertTrue(true, "Success!");  
    }
    
    /**
     * checks decryption values of small letter
     */
    @Test
    void decryptionSmallLetter(){
        ArrayList<String> data = new ArrayList<>();
        data.add("aV");
        model.setData(data);
        
        model.decrypt();
        ArrayList<String> encrypted = model.getData();
        
        ArrayList<String> result = new ArrayList<>();
        result.add("bX");
        
        
        if(!encrypted.get(0).equals(result.get(0))) {
            assertEquals(encrypted.get(0), result.get(1)); 
        }
        
        assertTrue(true, "Success!");  
    }
    
    /**
    * checks decryption values of capital letter
    */
    @Test
    void decryptionCapitalLetter(){
       ArrayList<String> data = new ArrayList<>();
        data.add("AX");
        model.setData(data);
        
        model.decrypt();
        ArrayList<String> encrypted = model.getData();
        
        ArrayList<String> result = new ArrayList<>();
        result.add("CX");      
        
        if(!encrypted.get(0).equals(result.get(0))) {
            assertEquals(encrypted.get(0), result.get(0)); 
        }
        
        assertTrue(true, "Success!");   
    }
    
}
