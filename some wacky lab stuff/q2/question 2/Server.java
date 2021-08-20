import java.io.*;  
import java.net.*;
import java.security.*;
import java.util.*;
import javax.crypto.*;  
import javax.crypto.spec.SecretKeySpec;
import cryptfiles.*;

public class Server {  


	public static void main(String[] args) {  
		try{  
            AES aes = new AES();
            int port = 6666;
            // Creates a server socket 
			ServerSocket ss = new ServerSocket(port);  
            // To accept new client connection
			Socket s = ss.accept();

            // To create input and output streams 
			DataInputStream din = new DataInputStream(s.getInputStream());  
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());  

            // Accept the string secret key from client
			String key = (String) din.readUTF();  
			System.out.println("Key: " + key);

            // Accept the string encryptedText from client
			String encryptedText = (String) din.readUTF();  
			System.out.println("Recieved EncryptedText: " + encryptedText);

            // Performing decryption
			String decryptedText = aes.decrypt(encryptedText, key);
			System.out.println("Decryption Done: " + decryptedText);

            // Sending success signal to the client
			dout.writeUTF("Decryption Done Successfully!");
			dout.flush();  

            // To close the socket connection
			ss.close();  
		} catch(Exception e) {
			System.out.println(e);
		}  
	}  
}  