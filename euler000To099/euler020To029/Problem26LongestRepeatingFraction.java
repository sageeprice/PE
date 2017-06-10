package euler000To099.euler020To029;

import UsefulForMath.Erastosthenes;

/**
 * Correct: 983
 * 
 * Just need to keep track of remainders, find when 10^n-1 has remainder 0.
 */
public class Problem26LongestRepeatingFraction {

	private static int MAX = 1000;

	public static void main(String[] args) {
		Erastosthenes sieve = new Erastosthenes(MAX);
		sieve.fillSieve();

		int longestRepeat = 1;
		int length = 3;
		int digs = 1;
		for (int i = 7; i < MAX; i++) {
			if (sieve.isPrime(i)) {
				digs = 1;
				int num = 9;
				while (num != 0 && digs < i) {
					num = ((num + 1) * 10 - 1)%i;
					digs++;
				}
				if (digs == i) {
					digs = i-1;
				}
				if (digs > longestRepeat) {
					length = digs;
					longestRepeat = i;
				//	System.out.println("New worst: " + longestRepeat + " at " + digs);
				}
			}
		}
		System.out.println("Worst is " + longestRepeat + " with repeating part of length " + length);
	}

}
