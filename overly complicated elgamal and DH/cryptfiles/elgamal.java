package cryptfiles;

import java.math.*;
import java.util.*;

import javax.crypto.SecretKey;

import java.security.*;
import java.io.*;

public class elgamal {

    final static Random sc = new SecureRandom();;
    static BigInteger secretKey;

    //setting the key that is obtained from the diffie algorithm
    public static void setSecretKey(String key) {
        secretKey = new BigInteger(key);
    }

    //the pre processing
    public static BigInteger[] calc() {
        BigInteger p, b, c;
        p = BigInteger.probablePrime(64, sc);
        b = new BigInteger("3");
        c = b.modPow(secretKey, p);

        BigInteger[] arr = { p, b, c, secretKey };
        return arr;
    }

    //performing the algorithm on the input from the user
    public String[] enc(String s) throws IOException {

        BigInteger[] calc = calc();

        BigInteger p = calc[0];
        BigInteger b = calc[1];
        BigInteger c = calc[2];
        BigInteger secretKey = calc[3];

        BigInteger X = new BigInteger(s);
        BigInteger r = new BigInteger(64, sc);
        BigInteger EC = X.multiply(c.modPow(r, p)).mod(p);
        BigInteger brmodp = b.modPow(r, p);

        String arr[] = { brmodp.toString(), secretKey.toString(), p.toString(), EC.toString() };

        return arr;
    }

    //decoding the message got
    public String dec(String brmodp_str, String secretKey_str, String p_str, String EC_str) {

        BigInteger brmodp = new BigInteger(brmodp_str);
        BigInteger secretKey = new BigInteger(secretKey_str);
        BigInteger p = new BigInteger(p_str);
        BigInteger EC = new BigInteger(EC_str);

        BigInteger crmodp = brmodp.modPow(secretKey, p);
        BigInteger d = crmodp.modInverse(p);
        BigInteger ad = d.multiply(EC).mod(p);

        return (ad.toString());
    }
}
