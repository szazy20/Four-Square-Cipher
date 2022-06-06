/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.java.cipher.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Represents the graphical user interface
 * @author Borys Pala
 * @version 3.1
 */
public class GUIView extends JPanel implements ActionListener{
    
    /**
     * stream field
     */
    private Stream<String> stream;
    /**
     * button starting encryption
     */
    private JButton btnEncrypt;
    
    /**
     * button starting decryption
     */
    private JButton btnDecrypt;
    
    /**
     * button adding keys and data
     */
    private JButton btnAdd;
    
    /**
     * key1 text field
     */
    private JTextField textField1;
    /**
     * key2 text field
     */
    private JTextField textField2;
    /**
     * data text field
     */
    private JTextField textField3;
    /**
     * output text area
     */
    private JTextArea output;
    /**
     * text area showing keys and data
     */
    private JTextArea info;
    /**
     * key1 label
     */
    private JLabel key1Label;
    /**
     * key2 label
     */
    private JLabel key2Label;
    /**
     * data label
     */
    private JLabel dataLabel;
    /**
     * output label
     */
    private JLabel outputLabel;
    
    /**
     * Non parameter constructor
     */
    public GUIView() {
        btnAdd = new JButton("Add");
        btnEncrypt = new JButton("Encrypt");
        btnDecrypt = new JButton("Decrypt");
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    /**
     * Used to create and show GUI
     */
    private void createAndShowGUI() {        

        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Four square cipher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(this.createMenuBar());
        //frame.setLayout(new GridLayout(0,2));
        
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("Main", makePanel(""));
        tabbedPane.addTab("History", makeTablePanel(""));
        
        
//        key1Label = new JLabel("Key1: ");
//        textField1 = new JTextField();
//        key2Label = new JLabel("Key2: ");
//        textField2 = new JTextField();
//        dataLabel = new JLabel("Data: ");
//        textField3 = new JTextField();
////        btnAdd = new JButton("Add");
//        info = new JTextArea("");
//        info.setEditable(false);
//       //btnEncrypt = new JButton("Encrypt");
//        //btnDecrypt = new JButton("Decrypt");
//        outputLabel = new JLabel("Output: ");
//        output = new JTextArea("");
//        output.setEditable(false);
        
//        btnEncrypt.addActionListener(this);
//        btnDecrypt.addActionListener(this);
//        btnAdd.addActionListener(this);
           frame.add(tabbedPane);
//           frame.add(key1Label);
//           frame.add(textField1);
//           frame.add(key2Label);
//           frame.add(textField2);
//           frame.add(dataLabel);
//           frame.add(textField3);
//           frame.add(btnAdd);
//           frame.add(info);
//           frame.add(btnEncrypt);
//           frame.add(btnDecrypt);
//           frame.add(outputLabel);
//           frame.add(output);
        frame.pack();
        frame.setVisible(true);
    }
    
     /**
      * Creates a JPanel
      * @param text message
      * @return JPanel object
      */
        private JPanel makePanel(String text) {
          JPanel p = new JPanel(); 
          key1Label = new JLabel("Key1: ");
        textField1 = new JTextField(20);
        key2Label = new JLabel("Key2: ");
        textField2 = new JTextField(20);
        dataLabel = new JLabel("Data: ");
        textField3 = new JTextField(20);
//        btnAdd = new JButton("Add");
        info = new JTextArea("");
        info.setEditable(false);
       //btnEncrypt = new JButton("Encrypt");
        //btnDecrypt = new JButton("Decrypt");
        outputLabel = new JLabel("Output: ");
        output = new JTextArea(7, 35);
        output.setEditable(false);
        
//        btnEncrypt.addActionListener(this);
//        btnDecrypt.addActionListener(this);
//        btnAdd.addActionListener(this);
           p.add(key1Label);
           p.add(textField1);
           p.add(key2Label);
           p.add(textField2);
           p.add(dataLabel);
           p.add(textField3);
           p.add(btnAdd);
           p.add(info);
           p.add(btnEncrypt);
           p.add(btnDecrypt);
           p.add(outputLabel);
           p.add(output);
        return p;
    }
        
      /**
      * Creates a JPanel
      * @param text message
      * @return JPanel object
      */
        private JPanel makeTablePanel(String text) {
          JPanel p = new JPanel();
          String data[][]={ {"",""}};
          String column[]={"Operation","Date"};
          JTable jt=new JTable(data,column);
          JScrollPane sp=new JScrollPane(jt);
          p.add(sp);
          p.setVisible(true);
          return p;
    }
        
      /**
     * creates a new menu bar
     * @return menu bar 
     */
    private JMenuBar createMenuBar() {
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Menu");        
        menu.setMnemonic(KeyEvent.VK_0);
        bar.add(menu);     
        
        JMenuItem item = new JMenuItem("Reset");
        item.addActionListener(this);
        menu.add(item);
        
  
        return bar;
    }   
    
    
    /**
     * Action Listener
     * @param e ActionEvent parameter
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            output.setText("");
        
    }
    
    
    /**
     * Getter for Add buttton 
     * @return btnAdd
     */
    public JButton getbtnAdd(){
        return btnAdd;
    }
    /**
     * Getter for Encrypt button
     * @return btnDecrypt
     */
    public JButton getbtnEncrypt(){
        return btnEncrypt;
    }
    
    /**
     * Getter for Decrypt button
     * @return btnDecrypt
     */
    public JButton getbtnDecrypt(){
        return btnDecrypt;
    }
    
    /**
     * Getter for textField3 value
     * @return data from text field
     */
    public String getKey1(){
        String k1 = textField1.getText();
        String[] key = k1.split(" ");
        
        return key[0];
    }
   
    
    /**
     * Getter for textField3 value
     * @return data from text field
     */
    public String getKey2(){
        String k2 = textField2.getText();
        String[] key = k2.split(" ");
        
        return key[0];
    }
    
    /**
     * Getter for textField3 value
     * @return data from text field
     */
    public ArrayList<String> getData(){
        String text = textField3.getText();
        String[] data = text.split(" ");
        
        // rewritint to arraylist
        ArrayList<String> trueData = new ArrayList<String>();

        stream = Stream.of(data);
        stream.forEach(p -> trueData.add(p));
        
        return trueData;
    }
       
    
    /**
     * Displays data
     * @param data ArrayList of cipher text
     * @param message string containing a certain message
     */
    public void displayData(ArrayList<String> data, String message) {        
        String text = "";
        ArrayList<String> data1 = new ArrayList<String>();
        // java 8
        data.forEach(word -> {
            data1.add(word);
            
        });
        for(int i=0; i<data1.size(); i++){
            text += data1.get(i)+" ";
        }
        output.setText(text);
    }    
    
    /**
     * Disables Encrypt button
     */
    public void invisibleEncrypt(){
        btnEncrypt.setEnabled(false);
    }
    /**
     * Enables Encrypt button
     */
    public void visibleEncrypt(){
        btnEncrypt.setEnabled(true);
    }
    /**
     * Disables Decrypt button
     */
    public void invisibleDecrypt(){
        btnDecrypt.setEnabled(false);
    }
    /**
     * Enables Decrypt button
     */
    public void visibleDecrypt(){
        btnDecrypt.setEnabled(true);
    }
}
