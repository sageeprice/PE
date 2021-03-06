package problems.impl;

import problems.Problem;

/**
 * Problem 48:
 * https://projecteuler.net/thread=48
 *
 * Solved by Sage on 10/23/16.
 */
public class Euler048 implements Problem {

    private final long TEN_TO_THE_TEN = 10_000_000_000L;

    @Override
    public String solve() {

        // just take remainders so we don't have to
        // deal with BigInteger
        long lastTen = 0;
        for (int i = 1; i <= 1000; i++) {
            long product = 1;
            for (long j = 1; j <= i; j++) {
                product *= i;
                product %= TEN_TO_THE_TEN;
            }
            lastTen += product;
            lastTen %= TEN_TO_THE_TEN;
        }
        return String.valueOf(lastTen);
    }
}
