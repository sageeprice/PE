package problems.euler051to075;

import problems.Problem;

import java.util.Arrays;

/**
 * Problem 70:
 * https://projecteuler.net/problem=70
 *
 * Solved by Sage on 10/31/16.
 */
public class Euler070 implements Problem {

    private static final int LIMIT = 10000000;

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
