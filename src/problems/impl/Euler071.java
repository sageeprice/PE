package problems.impl;

import problems.Problem;

/**
 * Problem 71:
 * https://projecteuler.net/problem=71
 *
 * Solved by Sage on 10/31/16.
 */
public class Euler071 implements Problem {

    private static final long BASE_NUM = 3;
    private static final long BASE_DEN = 7;
    private static final int LIMIT = 1000000;
    // RATIO is slightly less than 3/7 = .428571...
    private static final double RATIO = .4285;

    // This is actually a pretty easy pencil and paper problem

    @Override
    public String solve() {
        // All we care about is the number left of 7, track with these
        long num = 2;
        long denom = 5;

        for (long d = 6; d < LIMIT; d++) {
            // Start juuust below 3/7
            for (long n = (long) (d * RATIO); n < d / 2; n++) {
                // Fraction comparison, if (n/d < BASE_NUM/BASE_DEN) {...}
                if (n * BASE_DEN < d * BASE_NUM) {
                    if (n * denom > d * num) {
                        num = n;
                        denom = d;
                    }
                } else
                    break;
            }
        }
        return String.valueOf(num);
    }
}
