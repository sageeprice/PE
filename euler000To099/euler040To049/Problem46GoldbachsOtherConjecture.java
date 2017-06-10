package euler000To099.euler040To049;

import java.util.ArrayList;

import UsefulForMath.Erastosthenes;

public class Problem46GoldbachsOtherConjecture {
	
	private static int max = 6000;
	private static Erastosthenes sieve;
	private static ArrayList<Integer> primes;

	public static void main(String[] args) {
		sieve = new Erastosthenes(max);
		sieve.fillSieve();

		primes = sieve.getPrimesList();
		boolean found = false;
		int i = 37;
		while (!found) {
		//	System.out.println("Testing " + i);
			if (sieve.isPrime(i))
				i += 2;
			else {
				boolean maybe = false;
				for (int prime : primes) {
					if (prime < i && !maybe)
						if (isTwoXSquare(i-prime)) {
							maybe = true;
						//	System.out.println(i + "fails");
						}
				}
				if (!maybe)
					found = true;
				else
					i+=2;
			}
		}
		System.out.println("Found answer: " + i);
	}

	private static boolean isTwoXSquare(int test) {
		test = test/2;
		double root = Math.sqrt(test);
		if (root*root == ((int)root)*root)
			return true;
		else 
			return false;
	}
}
