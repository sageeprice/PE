package problems.impl;

import problems.Problem;

/**
 * Problem 27:
 * https://projecteuler.net/problem=27
 *
 * Solved by Sage on 10/16/16.
 */
public class Euler027 implements Problem {

    private static final int SIEVE_SIZE = 1000000;
    private static final int BOUNDS = 1000;

    @Override
    public String solve() {

        boolean[] isPrime = sieveTo(SIEVE_SIZE);

        int mostConsecutivePrimes = 0;
        int ab = 0;

        for (int i = 2; i < BOUNDS; i++) {
            if (isPrime[i]) {
                for (int a = 1-BOUNDS; a < BOUNDS; a++) {
                    int x = 0;
                    // Since we start at 0, b must always be prime, so use the current
                    // prime as our start point for b
                    int y = evaluate(x,a,i);
                    while (y >= 0 && y < SIEVE_SIZE && isPrime[y]) {
                        x++;
                        y = evaluate(x,a,i);
                    }
                    if (x > mostConsecutivePrimes) {
                        mostConsecutivePrimes = x;
                        ab = a * i;
                    }
                }
            }
        }

        return String.valueOf(ab);
    }

    /**
     * Evaluate the expression x^2 + a*x + b
     * @param x an int, the input
     * @param a coefficient term for x^1
     * @param b constant term
     * @return result
     */
    private int evaluate(int x, int a, int b) {
        return x*x + a*x + b;
    }

    /**
     * Generate an array of booleans where value represents if index is prime
     * @param x largest value in sieve
     * @return array of booleans
     */
    private boolean[] sieveTo(int x) {

        // Simple sieve implementation
        boolean[] isPrime = new boolean[SIEVE_SIZE + 1];

        for (int i = 2; i <= SIEVE_SIZE; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i <= SIEVE_SIZE; i++) {
            if (isPrime[i]) {
                for (int j = 2; j * i <= SIEVE_SIZE; j++) {
                    isPrime[j * i] = false;
                }
            }
        }

        return isPrime;
    }
}
