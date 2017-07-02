package problems.impl;

import problems.Problem;

/**
 * Problem 10:
 * Find the sum of all the primes below two million.
 *
 * Solved by Sage on 10/8/16.
 */
public class Euler010 implements Problem {

    private static final int SIEVE_SIZE = 2000000;

    /**
     * Same as problem 3, just use the sieve.
     * Will extract this to a library at some point,
     * sieve is a very common pattern.
     */
    @Override
    public String solve() {

        // Simple sieve implementation
        // Magic number 10000 calculated retrospectively
        boolean[] isPrime = new boolean[SIEVE_SIZE + 1];

        long primeSum = 0;

        for (int i = 2; i <= SIEVE_SIZE; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i <= SIEVE_SIZE; i++) {
            if (isPrime[i]) {
                for (int j = 2; j * i <= SIEVE_SIZE; j++) {
                    isPrime[j * i] = false;
                }
                primeSum += i;
            }
        }

        return String.valueOf(primeSum);
    }
}
