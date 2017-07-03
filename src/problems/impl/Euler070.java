package problems.impl;

import problems.Problem;

import java.util.Arrays;

/**
 * Problem 70:
 * https://projecteuler.net/problem=70
 *
 * Solved by Sage on 10/31/16.
 */
public class Euler070 implements Problem {

    private static final int LIMIT = 10_000_000;

    @Override
    public String solve() {

        boolean[] sieve = sieveTo(LIMIT);

        int[] numerators = new int[LIMIT];
        int[] denominators = new int[LIMIT];
        for (int i = 1; i < LIMIT; i++) {
            numerators[i] = 1;
            denominators[i] = 1;
        }

        double best = 2;
        long bestIndex = 0;

        for (long i = 2; i < LIMIT; i++) {
            if (sieve[(int) i]) {
                // This part is slow, if I care about performance,
                // could optimize this to happen while generating
                // the sieve.
                for (int j = (int) i; j < LIMIT; j += i) {
                    numerators[j] *= (i-1);
                    denominators[j] *= i;
                }
            }
            long phi = i * numerators[(int) i] / denominators[(int) i];
            if (isPermutation(i, phi) && (double)i / phi < best) {
                best = (double) i / phi;
                bestIndex = i;
            }
        }

        return String.valueOf(bestIndex);
    }

    private boolean isPermutation(long x, long y) {
        char[] xStr = String.valueOf(x).toCharArray();
        char[] yStr = String.valueOf(y).toCharArray();
        Arrays.sort(xStr);
        Arrays.sort(yStr);

        return Arrays.equals(xStr, yStr);
    }

    /**
     * Generate an array of booleans where value represents if index is prime
     * @param x largest value in sieve
     * @return array of booleans
     */
    private boolean[] sieveTo(int x) {
        boolean[] sieve = new boolean[x+1];
        if (x >= 2) {
            sieve[2] = true;
        }
        for (int i = 3; i <= x; i+=2) {
            sieve[i] = true;
        }
        for (int i = 3; i <= Math.sqrt(x); i+= 2) {
            if (sieve[i]) {
                /**
                 * Since you'll forget this Sage:
                 *  - if it's less than i*i more than i, it'll be covered by a smaller prime
                 *  - all primes > 2 are odd, so only need to check every other above i*i
                 *  One improvement that could be made: technically only need to check
                 *  of the form 6k+1 and 6k-1 (k an int), as 6k+3 is multiple of 3.
                 */
                for (int j = i * i; j <= x; j += i * 2) {
                    sieve[j] = false;
                }
            }
        }
        return sieve;
/*
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
        */
    }
}
