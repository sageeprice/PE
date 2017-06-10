package euler000To099.euler010To019;

import java.util.ArrayList;

/**
 * Correct: solution is 142913828922
 */
public class Problem10SumOfPrimesUnderN {

	private static final int N = 2000000;

	public static void main(String[] args) {
		ArrayList<Integer> primes = new ArrayList<Integer>();
		primes.add(2);
		primes.add(3);
		int prime = 3;
		long sum = 5L;
		while (primes.get(primes.size()-1) < N) {
			prime = nextPrime(primes, (int) primes.size());
			if (prime < N) {
				sum += prime;
				primes.add(prime);
			} else
				break;
		}
		System.out.println("sum is " + sum);
		System.out.println("last prime is " + primes.get(primes.size()-1));
	}
	
	public static int nextPrime(ArrayList<Integer> primes, int size) {
		int prime = primes.get(size-1) + 2;
		while (!isPrime(primes, prime))
			prime += 2;
		return prime;
	}

	public static boolean isPrime(ArrayList<Integer> primes, int numToCheck) {
		int root = (int) Math.sqrt((double) numToCheck);
		for (int i = 0; primes.get(i) <= root; i++) {
			if (numToCheck % primes.get(i) == 0)
				return false;
		}
		return true;
	}

}
