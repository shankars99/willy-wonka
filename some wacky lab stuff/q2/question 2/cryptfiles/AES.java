package cryptfiles;


import java.security.*;
import java.util.*;
import javax.crypto.*;  
import javax.crypto.spec.SecretKeySpec;

public class AES {
    
	private static SecretKeySpec secretKey;
    private static byte[] byteKey;
 
 	// To set the secretKey based on the key string that we pass in
    public static void setKey(String myKey) {
        try {
            byteKey = myKey.getBytes("UTF-8");

            // Create instance of SHA-1 message digest
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byteKey = sha.digest(byteKey);
            byteKey = Arrays.copyOf(byteKey, 16); 

            // Create the secretKey
            secretKey = new SecretKeySpec(byteKey, "AES");

            // Encoding the secretKey for printing it out
            System.out.println("Generated Secret Key: ");
            String text = Base64.getEncoder().encodeToString(secretKey.getEncoded());
            System.out.println(text);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String encrypt(String plainText, String secret) {
        try {

            setKey(secret);

            // Create an instance 
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            // Instantiate the object 
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            // encryption on the plain text 
            byte[] enc = cipher.doFinal(plainText.getBytes("UTF-8"));

            // Encode the byte array (enc) as a string
            return Base64.getEncoder().encodeToString(enc);
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }


    public static String decrypt(String encryptedText, String secret) {
        try {
            //set the secret key
            setKey(secret);

            // AES cipher object
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");

            // Instantiate the object with parameters
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Decode the encrypted string that was sent from client
            byte[] enc = Base64.getDecoder().decode(encryptedText);

            // AES Encryption
            byte[] dec = cipher.doFinal(enc);
            return new String(dec);
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

}
