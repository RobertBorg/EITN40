import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Random;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			byte[] z = {(byte) 0xFF,(byte) 0xFF,(byte) 0xFF,(byte) 0xFF};
			generateCoin(BitSet.valueOf(z), 1);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Long> generateCoin(BitSet z, long numK) throws NoSuchAlgorithmException{
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		Random rand = new Random();
		HashMap<BitSet, ArrayList<Long>> buckets = new HashMap<BitSet,ArrayList<Long>>();
		final int t = z.length();
		long numTries = 0;
		long numAdded= 0;
		long[] dummy = new long[1];
		while(true){
			final long kTest = rand.nextLong(); // should simply use a byte[] to not be limited to 63 bits
			dummy[0] = kTest;
			final BitSet value = BitSet.valueOf(dummy);
			final BitSet digest = BitSet.valueOf(md5.digest(value.toByteArray())); // it looks like BitSet.valueOf discards the leading zero bits, bad bad, but good enough to get a glimpse at how slow this is.
			final int fromBit = digest.size() - t - 1;
			final int toBit = digest.size()-1;
			final BitSet toValidate = digest.get(fromBit,toBit ); 
			if (toValidate.equals(z)){ // not sure if this works, but i think it does.
				numAdded ++;
				final BitSet index = digest.get(0, digest.size() -t -1);
				ArrayList<Long> arr = buckets.get(index);
				if( arr == null){
					arr = new ArrayList<Long>();
					buckets.put(index, arr);
				}
				arr.add(kTest);
				if(arr.size() == numK) {
					System.out.println("TADA");
					return arr;
				}
			}
			numTries++;
			if( numTries % 1000000 == 0){
				System.out.println(String.format("%dM %d",numTries/1000000, numAdded));
			}
		}
	}
	

}
