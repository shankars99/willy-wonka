import java.io.*;
import java.net.*;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.spec.InvalidKeySpecException;
import java.security.*;
import java.math.*;
import cryptfiles.*;

public class Client {

    // gets the message from the user
    public static String getValue() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the message ->");
        String s = br.readLine();

        return (s);
    }

    public static void main(String[] args)throws IllegalBlockSizeException, InvalidKeyException,
            NoSuchPaddingException, BadPaddingException, IOException{
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgFGVfrY4jQSoZQWWygZ83roKXWD4YeT2x2p41dGkPixe73rT2IW04glagN2vgoZoHuOPqa5and6kAmK2ujmCHu6D1auJhE2tXP+yLkpSiYMQucDKmCsWMnW9XlC5K7OSL77TXXcfvTvyZcjObEz6LIBRzs6+FqpFbUO9SJEfh6wIDAQAB";

        RSA rsa = new RSA();
        try{
            String input = getValue();
            String cipher_text = rsa.do_encrypt(input, publicKey);
            System.out.println(cipher_text);

            Socket sock = new Socket("localhost", 6666);
            DataOutputStream out = new DataOutputStream(sock.getOutputStream());

            out.writeUTF(cipher_text);

            out.flush();
            out.close();

            sock.close();

        }catch(Exception e){

        }
    }
/*        try {

            elgamal elgamal = new elgamal();
            diffie diffie = new diffie();

            // generates the diffie key
            String secretKey = diffie.getKey();
            elgamal.setSecretKey(secretKey);
            // encodes with the key got
            String arr[] = elgamal.enc(getValue());

            Socket sock = new Socket("localhost", 6666);
            DataOutputStream out = new DataOutputStream(sock.getOutputStream());

            out.writeUTF(secretKey);
            for (String str : arr) {
                out.writeUTF(str);
            }

            out.writeUTF("\n");

            out.flush();
            out.close();

            sock.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
*/
}
