package problems.impl;

import problems.Problem;

import java.util.Arrays;

/**
 * Problem 49:
 * https://projecteuler.net/problem=49
 *
 * Solved by Sage on 10/25/16.
 */
public class Euler049 implements Problem {

    private static final int SIEVE_SIZE = 10000;

    @Override
    public String solve() {
        boolean[] primes = sieveTo(SIEVE_SIZE);

        // Skip the first result, which is supplied in the problem
        boolean hasSkippedFirst = false;

        for (int i = 1001; i < SIEVE_SIZE; i += 2) {
            if (primes[i]) {
                for (int j = 18; 2 * j + i < SIEVE_SIZE; j += 18) {
                    if (primes[i + j]
                            && primes[i + 2 * j]
                            && isAnagram(i, i + j)
                            && isAnagram(i, i + 2 * j)) {
                        if (!hasSkippedFirst) {
                            hasSkippedFirst = true;
                        } else {
                            return String.valueOf(
                                    (long) 100000000 * i
                                    + (long) 10000 * (i + j)
                                    + (long) (i + 2 * j)
                            );
                        }
                    }
                }
            }
        }
        return null;
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

    /**
     * Returns whether a and b are anagrams of each other
     * @param a some positive integer
     * @param b some positive integer
     * @return a and b are anagrams of each other
     */
    private boolean isAnagram(int a, int b) {
        char[] aStr = String.valueOf(a).toCharArray();
        Arrays.sort(aStr);

        char[] bStr = String.valueOf(b).toCharArray();
        Arrays.sort(bStr);

        return Arrays.equals(aStr, bStr);
    }
}
