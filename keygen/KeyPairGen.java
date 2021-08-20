import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class KeyPairGen{
   public static void main(String args[]) throws Exception{
      KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");

      keyPairGen.initialize(2048);

      KeyPair pair = keyPairGen.generateKeyPair();

      PrivateKey privKey = pair.getPrivate();

      PublicKey publicKey = pair.getPublic();
      System.out.println("Keys generated");
      System.out.println(publicKey);
   }
}