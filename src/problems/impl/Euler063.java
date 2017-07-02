package problems.impl;

import problems.Problem;

/**
 * Problem 63:
 * https://projecteuler.net/problem=63
 *
 * Solved by Sage on 10/29/16.
 */
public class Euler063 implements Problem {

    @Override
    public String solve() {

        // Definitely one of those problems that could easily be done by hand

        int total = 1;
        for (int i = 2; i < 10; i++) {
            double x = i;
            int count = 0;
            while (Math.log10(x) > count) {
                x *= i;
                count++;
            }
            total += count;
        }
        return String.valueOf(total);
    }
}
