package problems.euler025to050;

import problems.Problem;

/**
 * Problem 28:
 * https://projecteuler.net/problem=28
 *
 * Solved by Sage on 10/22/16.
 */
public class Euler028 implements Problem {

    private static final int SIZE = 1001;

    @Override
    public String solve() {

        int sum = 1;

        /**
         * At each ring, where n is ring length, you add,
         * n^2 - 0*(n-1)
         * n^2 - 1*(n-1),
         * n^2 - 2*(n-1),
         * n^2 - 3*(n-1)
         * -------------- for a total of
         * 4*n^2 - 6*(n-1) at each ring.
         */
        for (int i = 3; i <= SIZE; i += 2) {
            sum += 4 * i*i - (6 * (i-1));
        }

        return String.valueOf(sum);
    }
}
