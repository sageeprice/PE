package errata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// ~40 seconds to fill sieve of size 1,000,000,000
public class Erastosthenes {

	private boolean[] primes;
	private int max;
	public Erastosthenes(int max) {
		this.primes = new boolean[max+1];
		this.max = max;
	}

	public boolean[] getPrimes() {
		return primes;
	}
	
	public ArrayList<Integer> getPrimesList() {
		ArrayList<Integer> primeList = new ArrayList<Integer>();
		for (int i = 2; i <= max; i++) {
			if (primes[i])
				primeList.add(i);
		}
		return primeList;
	}

	public void fillSieve() {
		primes[1] = false;
		for (int i = 2; i < max; i++) {
			primes[i] = true;
		}
		int nawp;
		for (int i = 2; i < max; i++) {
			if (primes[i]) {
				nawp = 2*i;
				while (nawp < max) {
					primes[nawp] = false;
					nawp+= i;
				}
			}
		}
	}
	
	public Boolean isPrime(int check) {
		return check > max ? null : primes[check];
	}

	public Set<Integer> getPrimeFactors(int num) {
		Set<Integer> factors = new HashSet<Integer>();
		for (int i = 2; i < num; i++) {
			if (primes[i] && num%i == 0)
				factors.add(i);
		}
		return factors;
	}

	public Set<Integer> getMaxPoweredFactors(int num) {
		Set<Integer> factors = new HashSet<Integer>();
		for (int i = 2; i < num; i++) {
			if (primes[i] && num%i == 0) {
				int j = i;
				while (num%j == 0)
					j*=i;
				j/=i;
				factors.add(j);
				num /= j;
			}
		}
		return factors;
	}

	public Map<Integer, Integer> getMaxPowFactorsAsMap(int num) {
		Map<Integer, Integer> factors = new HashMap<Integer, Integer>();
		for (int i = 2; i <= num; i++) {
			if (primes[i] && num%i == 0) {
				int j = i;
				int pow = 1;
				while (num%j == 0) {
					j*=i;
					pow++;
				}
				num/=(j/i);
				factors.put(i, pow-1);
			}
		}
		return factors;
	}
}
