/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nslab3;
import java.util.Scanner;

/**
 *
 * @author Rosheen
 */
public class Vignere_Cypher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println("Hello user!! Welcome to vigenere cypher!!!");
        System.out.println("Enter text which you want to encrypt or decrypt");
        
        // Take input from user
        Scanner myObj = new Scanner(System.in);
        String text = myObj.nextLine();

        //Call methods to convert raw message and key strings to desired ones
        String convertedText = Vigenère.compress(text);
        String convertedKey = Vigenère.expandedKey(convertedText);
        
        System.out.println("For encryption, press 0. For decryption , press 1");
        Scanner input = new Scanner(System.in);
        int mode = input.nextInt();
        switch (mode) {
            case 0:
                System.out.println("Encrypting");
                String codedText = Vigenère.encode(convertedText, convertedKey);
                break;
            case 1:
                System.out.println("Decrypting");
                Vigenère.decode(convertedText, convertedKey);
                break;
            default:
                System.out.println("Entered number is not valid");
                break;
        }  
    }     
}

class Vigenère  {
    
    public static String compress(String text) {
        
        text = text.replaceAll("[^a-zA-Z]", "");
        text = text.toLowerCase();
        System.out.println("The converted text is: " + text);
        return text.toLowerCase();
    }
    
    public static String expandedKey(String text) {
        
        System.out.println("Enter the key");
        Scanner myObj = new Scanner(System.in);
        String key = myObj.nextLine();   
        
        // Make sure we never have to expand...
        StringBuilder builder = new StringBuilder(text.length() + key.length() - 1);
        while (builder.length() < text.length()) {
            builder.append(key);
        }
        builder.setLength(text.length()); //Make key length equal to text length
        String expandedKey = builder.toString();
        System.out.println("The expanded key is: " + expandedKey );
                
        return expandedKey;
    }
    
    public static String encode(String message, String key) {
        
        String encryptedMessage = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        
        for( int i=0; i <message.length() ; i++) {
            int keyIndex = alphabet.indexOf(key.charAt(i));
            int textIndex = alphabet.indexOf(message.charAt(i));
            int shiftIndex = (textIndex + keyIndex) % 26;
            encryptedMessage = encryptedMessage + alphabet.charAt(shiftIndex); 
            
        }
        System.out.println("The encrypted text is: " + encryptedMessage);
        
        return encryptedMessage;
    }
    
      public static String decode(String message, String key) {
        
        String decryptedMessage = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        
        for( int i=0; i <message.length() ; i++) {
            int keyIndex = alphabet.indexOf( key.charAt(i));
            int textIndex = alphabet.indexOf(message.charAt(i));
            int shiftIndex = (textIndex - keyIndex) % 26;
            if (shiftIndex < 0) {
                shiftIndex = 26 + shiftIndex;
            }
          
            decryptedMessage = decryptedMessage + alphabet.charAt(shiftIndex); 
          
        }
        System.out.println("The decrypted text is: " + decryptedMessage);
        
        return decryptedMessage;
    }   
}