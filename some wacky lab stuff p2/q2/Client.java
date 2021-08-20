import java.io.*;
import java.net.*;
import java.security.KeyStore.SecretKeyEntry;

import java.math.*;
import cryptfiles.DH;

public class Client {
    public static void main(String[] args) throws IOException {
        try {
            //read the data from the files
            DH obj = new DH();
            long Q = obj.getVal("cryptfiles/q");
            long Alpha = obj.getVal("cryptfiles/alpha");

            long Xa = obj.getRandomVal(Q);
            System.out.println("The private key a for Alice:" + Xa);

            long Ya = obj.calculateY(Alpha, Xa, Q);
            System.out.println("Ya:" + Ya);

            System.out.println("Sending Ya to Bob");
            Socket sock = new Socket("localhost", 6666);
            DataOutputStream out = new DataOutputStream(sock.getOutputStream());

            out.writeUTF(String.valueOf(Ya));

            DataInputStream in = new DataInputStream(sock.getInputStream());
            long Yb = Long.parseLong((String) in.readUTF());

            System.out.println("\nGot Yb from Bob");
            long Ka = obj.calculateK(Yb, Xa, Q);
            System.out.println("Ka:" + Ka);

            in.close();
            sock.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
