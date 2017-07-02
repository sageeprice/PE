package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem 50:
 * https://projecteuler.net/problem=50
 *
 * Solved by Sage on 10/25/16.
 */
public class Euler050 implements Problem {

    private static final int LIMIT = 1000000;

    @Override
    public String solve() {

        boolean[] primeSieve = sieveTo(LIMIT);

        // Create list of primes from sieve so we don't
        // have to scan the full sieve every time
        List<Integer> primeList = new ArrayList<>();
        for (int i = 2; i < LIMIT; i++) {
            if (primeSieve[i]) {
                primeList.add(i);
            }
        }

        int numPrimes = primeList.size();

        int bestChain = 0;
        int best = 0;
        int consecutivePrimeSum;
        // Sum the i through j terms
        for (int i = 0; i < numPrimes; i++) {
            consecutivePrimeSum = primeList.get(i);
            for (int j = i+1; j < numPrimes; j++) {
                consecutivePrimeSum += primeList.get(j);
                // Stop processing when we exceed threshold
                if (consecutivePrimeSum >= LIMIT) {
                    break;
                }
                // Update variables if new long sequence is found
                if (primeSieve[consecutivePrimeSum] && j - i > bestChain) {
                    best = consecutivePrimeSum;
                    bestChain = j-i+1;
                }
            }
        }

        return String.valueOf(best);
    }

    /**
     * Generate an array of booleans where value represents if index is prime
     * @param x largest value in sieve
     * @return array of booleans
     */
    private boolean[] sieveTo(int x) {

        // Simple sieve implementation
        boolean[] isPrime = new boolean[x + 1];

        for (int i = 2; i <= x; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i <= x; i++) {
            if (isPrime[i]) {
                for (int j = 2; j * i <= x; j++) {
                    isPrime[j * i] = false;
                }
            }
        }

        return isPrime;
    }
}
