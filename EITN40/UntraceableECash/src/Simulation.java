import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


public class Simulation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start");
		generateCoin();
		System.out.println("Stop");

	}
	
	private static void generateCoin(){
		Random rand = new Random();
		//BigInteger r = new BigInteger (String.valueOf(rand.nextInt()%20));
		int r = rand.nextInt();
		
		BigInteger pB = new BigInteger("61");
		BigInteger qB = new BigInteger("53");
		BigInteger nB = pB.multiply(qB);
		System.out.println("N equals " + nB.intValue());
		BigInteger eulerNB = pB.subtract(new BigInteger("1")).multiply(qB.subtract(new BigInteger("1")));
		
		BigInteger eB = new BigInteger("17");
		BigInteger dB = eB.modInverse(eulerNB);
		System.out.println("D equals " + dB.toString());
		
		BigInteger message = new BigInteger("1234");
		
		BigInteger encryptedMessage = message.modPow(eB, nB);
		
		System.out.println(encryptedMessage.toString());
		
		BigInteger decryptedMessage = encryptedMessage.modPow(dB, nB);
		System.out.println(decryptedMessage);
		
		
		MessageDigest md5Digest = null;
		try {
			md5Digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException u) {
			// TODO Auto-generated catch block
			u.printStackTrace();
		}
		
		
		//byte[] hashOfX = md5Digest.digest(bankPublicKey.toByteArray());
		
		//BigInteger B = r.pow(bankPublicKeyInt);
		
	}
	
	
	static long modInverse(long a, long n) {
		 long i = n, v = 0, d = 1;
		 while (a>0) {
		  long t = i/a, x = a;
		  a = i % x;
		  i = x;
		  x = d;
		  d = v - t*x;
		  v = x;
		 }
		 v %= n;
		 if (v<0) v = (v+n)%n;
		 return v;
		}
	
	
	static long modinv(long u, long v)
	{
	    long inv, u1, u3, v1, v3, t1, t3, q;
	    long iter;
	    /* Step X1. Initialise */
	    u1 = 1;
	    u3 = u;
	    v1 = 0;
	    v3 = v;
	    /* Remember odd/even iterations */
	    iter = 1;
	    /* Step X2. Loop while v3 != 0 */
	    while (v3 != 0)
	    {
	        /* Step X3. Divide and "Subtract" */
	        q = u3 / v3;
	        t3 = u3 % v3;
	        t1 = u1 + q * v1;
	        /* Swap */
	        u1 = v1; v1 = t1; u3 = v3; v3 = t3;
	        iter = -iter;
	    }
	    /* Make sure u3 = gcd(u,v) == 1 */
	    if (u3 != 1)
	        return 0;   /* Error: No inverse exists */
	    /* Ensure a positive result */
	    if (iter < 0)
	        inv = v - u1;
	    else
	        inv = u1;
	    return inv;
	}
	
	 static long gcd(long a, long b){
		 if (a == 0){
			 return b;
		 }
		 while (b != 0){
			 if (a > b){
				 a = a - b;
			 } else {
				 b = b - a;
			 }
		 }
		 return a;
		 
	 }

}
