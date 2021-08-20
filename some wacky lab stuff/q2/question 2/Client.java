import java.io.*;  
import java.net.*;
import java.security.*;
import java.util.*;
import javax.crypto.*;  
import javax.crypto.spec.SecretKeySpec;

import cryptfiles.*;

public class Client {  

	public static void main(String[] args) {  
		try {      
			AES aes = new AES();
			String host = "localhost";
			int port = 6666;

			// Create a socket connection 
			Socket s = new Socket(host, port);  

			// To create input and output streams 
			DataInputStream din = new DataInputStream(s.getInputStream());  
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());  
			
			// Getting the secretKey 
			Scanner sc = new Scanner(System.in);
	        System.out.print("Enter the Key: ");
	        String key = sc.nextLine();

	        // Sending the key to the server
			dout.writeUTF(key);
			dout.flush();

			// Getting the text to be encrypted
			System.out.print("Enter the String: ");
	        String plainText = sc.nextLine();

	        // Performing Encryption
	        String encryptedString = aes.encrypt(plainText, key);
	        System.out.println("Encryption Done: " + encryptedString);

	        // Sending the Encrypted text to server
			dout.writeUTF(encryptedString);  
			dout.flush();

			// Waiting for the final result from server
			String res = (String) din.readUTF();  
			System.out.println("Result: " + res);  
			dout.close();  

			// Close the connection
			s.close();  
		} catch(Exception e){
			System.out.println(e);
		}  
	}  
}