package problems.impl;

import problems.Problem;

/**
 * Problem 47:
 * https://projecteuler.net/thread=47
 *
 * Solved by Sage on 10/23/16.
 */
public class Euler047 implements Problem {

    private static final int LIMIT = 1_000_000;

    @Override
    public String solve() {

        // An array counting the number of unique prime factors each index has
        // Same idea as Sieve of Eratosthenes
        int[] factorSieve = new int[LIMIT];

        for (int i = 0; i < LIMIT; i++) {
            factorSieve[i] = 0;
        }

        for (int i = 2; i < LIMIT; i++) {
            if (factorSieve[i] == 0) {
                for (int j = 2*i; j < LIMIT; j += i) {
                    factorSieve[j]++;
                }
            }
        }

        for (int i = 2; i < LIMIT; i++) {
            if (factorSieve[i] == 4
                    && factorSieve[i+1] == 4
                    && factorSieve[i+2] == 4
                    && factorSieve[i+3] == 4) {
                return String.valueOf(i);
            }
        }
        return null;
    }
}
