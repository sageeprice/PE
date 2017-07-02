package problems.reallyOld;

/**
 * Correct: answer is 9110846700
 *
 * Problem destroyed by long
 */
public class Problem48SelfPowers {

	public static void main(String args[]) {
		long tot = 0;
		for (long i = 1; i < 1001; i++) {
			tot += lastTen(i);
		}
		tot %= 10000000000L;
		System.out.println(tot);
	}
	private static long lastTen(long x) {
		long bah = 1;
		for (long t = x; t > 0; t--) {
			bah *= x;
			bah %= 10000000000L;
		}
		System.out.println(x + " to the " + x + " has last ten digits: " + bah);
		return bah;
	}
}
