package problems.reallyOld;

import UsefulForMath.Erastosthenes;

/**
 * Correct: -59231
 */
public class Problem27QuadraticPrimes {

	public static void main(String[] args) {
		Erastosthenes sieve = new Erastosthenes(2000997);
		sieve.fillSieve();

		int longest = 1;
		int bestA = 1;
		int bestB = 1;
		for (int b = 2; b < 1000; b++) {
			if (sieve.isPrime(b)) {
				for (int a = 1-b; a < 1000; a++) {
					int testVal= 0;
					int out = testVal*testVal + a*testVal + b;
					while (out > 0 && sieve.isPrime(out)) {
						testVal++;
						out = testVal*testVal + a*testVal + b;
					}
					if (testVal > longest) {
						longest = testVal;
						bestA = a;
						bestB = b;
					}
				}
			}
		}
		System.out.println("BEST IS: " + bestA + " and " + bestB + " with length " + longest);
		System.out.println("With product " + (bestA*bestB));
	}

}
