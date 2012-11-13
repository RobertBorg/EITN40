import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


public class Simulation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private static void generateCoin(){
		Random rand = new Random();
		//BigInteger r = new BigInteger (String.valueOf(rand.nextInt()%20));
		int r = rand.nextInt();
		
		int p = 5;
		int q = 7;
		int n = p * q;
		int eulerN = (p-1)*(q-1);
		int d = gcd(2, eulerN)[0];
		while (d != 1){
			
		}
		
		// BigInteger x = new BigInteger (String.valueOf(rand.nextInt()%20));
		
		BigInteger bankPublicKey = new BigInteger("123123123");
		int bankPublicKeyInt = 123;
		
		MessageDigest md5Digest = null;
		try {
			md5Digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		byte[] hashOfX = md5Digest.digest(bankPublicKey.toByteArray());
		
		BigInteger B = r.pow(bankPublicKeyInt);
		
	}
	
	
	static int[] gcd(int p, int q) {
	      if (q == 0)
	         return new int[] { p, 1, 0 };

	      int[] vals = gcd(q, p % q);
	      int d = vals[0];
	      int a = vals[2];
	      int b = vals[1] - (p / q) * vals[2];
	      return new int[] { d, a, b };
	   }

}
