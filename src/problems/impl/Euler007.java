package problems.impl;

import problems.Problem;

/**
 * Problem 7:
 * Find the 10001st prime number.
 *
 * Solved by Sage on 10/7/16.
 */
public class Euler007 implements Problem {

    private static final int SIEVE_CAPACITY = 200000;
    private static final int TARGET_PRIME = 10001;

    /**
     * https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
     */
    @Override
    public String solve() {
        boolean[] isPrime = new boolean[SIEVE_CAPACITY];
        int primeCount = 0;

        for (int i = 2; i < SIEVE_CAPACITY; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i < SIEVE_CAPACITY; i++) {
            if (isPrime[i]) {
                primeCount++;
                if (primeCount == TARGET_PRIME) {
                    return String.valueOf(i);
                }
                for (int j = i+i; j < SIEVE_CAPACITY; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        return null;
    }
}
