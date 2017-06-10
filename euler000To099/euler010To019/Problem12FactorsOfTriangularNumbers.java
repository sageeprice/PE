package euler000To099.euler010To019;

import java.util.ArrayList;

/**
 * Correct: 76576500
 */
public class Problem12FactorsOfTriangularNumbers {
	public static void main(String[] args) {
		int triNumber = 3;
		int f1 = 0, f2 = 0; 
		int f1Count, f2Count, totalFactors = 1;
		ArrayList<Integer> primes = new ArrayList<Integer>();
		primes.add(2);
		primes.add(3);
		while (totalFactors <= 500) {
			if (triNumber % 2 == 0) {
				f1 = triNumber/2;
				f2 = triNumber + 1;
				ListPrimesUpTo(primes, f2);
			} else {
				f1 = triNumber;
				f2 = (triNumber+1)/2;
				ListPrimesUpTo(primes, f1);
			}
			f1Count = numFactors(primes, f1);
			f2Count = numFactors(primes, f2);
			totalFactors = f1Count * f2Count;
			triNumber++;
		}
		triNumber--;
		System.out.println("The " + triNumber + "th triangular number: " + f1*f2 + " has " + totalFactors + " factors");
	}
	
	public static ArrayList<Integer> ListPrimesUpTo(ArrayList<Integer> primes, int max) {
		if (max < primes.get(primes.size() - 1))
			return primes;
		else {
			int p = primes.get(primes.size() - 1) + 2;
			while (p <= max) {
				if (isPrime(primes, p)) {
					primes.add(p);
					//System.out.println("added prime " + p);
				}
				p += 2;
			}
			return primes;
		}
	}

	private static boolean isPrime(ArrayList<Integer> primes, int numToCheck) {
		int root = (int) Math.sqrt((double) numToCheck);
		for (int num = 0; primes.get(num) <= root; num++) {
			if (numToCheck % primes.get(num) == 0)
				return false;
		}
		return true;
	}

	public static Integer numFactors(ArrayList<Integer> primes, int numToCheck) {
		if (isPrime(primes, numToCheck))
			return 2;
		if (numToCheck < 1)
			return null;

		int nFactors = 1;
		int exp, p, test = numToCheck;

		for (int i = 0; test != 1 && primes.get(i) <= test; i++) {
			exp = 1;
			p = primes.get(i);
			while (test % p == 0) {
				//System.out.print("DECREMENT: test is " + test);
				test /= p;
				//System.out.println(" and now is " + test);
				exp++;
			}
			nFactors *= exp;
			//System.out.println("prime is " + p + "	test is " + test + "	nFactors is " + nFactors);
		}
		//System.out.println(numToCheck + " has " + nFactors);
		return nFactors;
	}
}