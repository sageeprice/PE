package problems.impl;

import problems.Problem;

/**
 * Problem 113:
 * https://projecteuler.net/problem=113
 * Created by Sage on 11/6/16.
 */
public class Euler113 implements Problem {

    private static final long DIGITS = 100;

    @Override
    public String solve() {

        /**
         * This one came out super short, so here's an overly brief explanation of
         * how I've been viewing this problem.
         *
         * So we are arranging n empty spaces, and k dividers. Position relative to
         * the dividers determine what the number is. Consider the increasing example:
         *      _||||_||_||| => 046
         *       1234 56 789
         *
         * The same logic holds for the decreasing example, with an extra divider due
         * to the fact that you may have leading zeros.
         *
         *      ||_|||_||_|||_ => 8530
         *      09 876 54 321
         *
         * So at this point we have two simple combinatorial values to solve, then
         * we just subtract the shared values (e.g. 1111, 333, 44444) and zeros. The
         * number of shared values is just 10 times the number of digits.
         *
         * ALTERNATIVE: if this doesn't make sense, you can use a dynamic programming
         * approach, keeping track of the number of increasing/decreasing numbers ending
         * with each digit and iterating on that. This is however O(n) in the number of
         * digits, whereas the direct calculation is O(1). Though either should be fine
         * for any reasonable number of digits.
         */

        long increasing = nCk(DIGITS + 9, 9);
        long decreasing = nCk(DIGITS + 10, 10);

        return String.valueOf(increasing + decreasing - 10 * DIGITS - 2);
    }

    private long nCk(long n, long k) {
        long prod = 1;
        for (long i = 1; i <= k; i++) {
            prod *= (n + 1) - i;
            prod /= i;
        }
        return prod;
    }
}
