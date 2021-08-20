import java.io.*;
import java.net.*;

import cryptfiles.*;

public class Server {
    public static void processData(String arr[]) {
    }

    public static void main(String[] args) throws IOException {

        try {
            DH obj = new DH();
            long Q = obj.getVal("cryptfiles/q");
            long Alpha = obj.getVal("cryptfiles/alpha");


            ServerSocket servSock = new ServerSocket(6666);
            Socket sock = servSock.accept();

            System.out.println("Got Ya from Alice");
            DataInputStream in = new DataInputStream(sock.getInputStream());
            long Ya =  Long.parseLong((String) in.readUTF());

            long Xb = obj.getRandomVal(Q);
            System.out.println("The private key b for Bob:" + Xb);

            long Yb = obj.calculateY(Alpha, Xb, Q);
            System.out.println("Yb:" + Yb);

            long Kb = obj.calculateK(Ya, Xb, Q);
            System.out.println("Kb:" + Kb);

            DataOutputStream out = new DataOutputStream(sock.getOutputStream());
            System.out.println("\nSending Yb to Alice");
            out.writeUTF(String.valueOf(Yb));

            out.flush();
            out.close();

            servSock.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
