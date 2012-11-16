import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


public class Simulation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		generateCoin();

	}
	
	private static void generateCoin(){
		Random rand = new Random();
		
		// Generate two big prime numbers
		BigInteger pB = new BigInteger("977");
		BigInteger qB = new BigInteger("983");
		BigInteger nB = pB.multiply(qB);
		BigInteger phi = pB.subtract(new BigInteger("1")).multiply(qB.subtract(new BigInteger("1")));
		
		// Choose a public key that is coprime to eulerNB. (is 3 in the example)
		BigInteger bankPublic = new BigInteger("17");
		// Take the modular inverse as the private key 
		BigInteger bankPrivate = bankPublic.modInverse(phi);
		
		
		MessageDigest md5Digest = null;
		try {
			md5Digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException u) {
			// TODO Auto-generated catch block
			u.printStackTrace();
		}
		// Alice generates a random R and X (coin).
		// She also calculates the inverse of R
		BigInteger rB = new BigInteger(10, rand);
		BigInteger rInverse = rB.modInverse(nB);
		BigInteger coin = new BigInteger(10, rand);
		System.out.println("This is the coin that Alice has produced: " + coin);
		// Hash the coin
		byte[] hashOfX = md5Digest.digest(coin.toByteArray());
		BigInteger hashInteger = new BigInteger(hashOfX);
		System.out.println("This is the hashed value of the coin that Alice has produced: " + hashInteger);
		
		// Sign R with banks public key
		BigInteger signedR = rB.pow(bankPublic.intValue());
		
		// Multiply it with the hash value of the coin to get string B to send to bank
		BigInteger B = (signedR.multiply(hashInteger));
		
		System.out.println("This is what bank sees: " + B.toString());
		
		// Bank withdraws 1 unit of money from Alice's bank account
		// Bank signs B with his private key
		BigInteger signedB = B.modPow(bankPrivate, nB);
		
		System.out.println("This is what Alice gets back: " + signedB.toString());
		
		// Alice extracts the signed hash by using the inverse of R
		BigInteger signedCoin = signedB.multiply(rInverse);
		
		// Alice sends the money to Bob
		
		System.out.println("Bob checks that " + signedCoin.modPow(bankPublic, nB) + " matches " + hashInteger.mod(nB));
		
	}
	

}
