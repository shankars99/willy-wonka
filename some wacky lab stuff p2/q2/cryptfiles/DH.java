package cryptfiles;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DH {
	private static long power(long a, long b, long p) {
		//System.out.println(a+"^"+b+"%"+p);
		if (b == 1)
			return a;
		else
			return (((long) Math.pow(a, b)) % p);
	}

	//read the data from the file
	public static long getVal(String addr) throws FileNotFoundException{
		File file = new File(addr + ".txt");
		Scanner sc = new Scanner(file);

		sc.useDelimiter("\\Z");

		return(Long.parseLong(sc.next()));
	}

	public static long getRandomVal(long maxVal){
		double val = (double)Math.random()*(maxVal  + 1);
		//System.out.println(val);
		return (long)val;
	}

	public static long generateX(long P){
		long X = getRandomVal(P);
		return(X);
	}

	public static long calculateY(long G, long X, long P) {
		long Y = power(G, X, P);
		//System.out.println("Y : " + Y);
		return (Y);
	}

	public static long calculateK(long Y, long X, long P) {
		long K = power(Y, X, P);
		return (K);
	}
}

