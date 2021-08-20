import java.io.*;
import java.net.*;
import java.security.KeyStore.SecretKeyEntry;

import java.math.*;
import cryptfiles.*;

public class Client {

    // gets the message from the user
    public static String getValue() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the message ->");
        String s = br.readLine();

        String s_int = "";
        for(int i = 0; i< s.length(); i++){
            s_int += (int)(s.charAt(i));
        }

        System.out.println("input in integers:" + s_int);
        return (s_int);
    }

    public static void main(String[] args) throws IOException {
        try {

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
}
