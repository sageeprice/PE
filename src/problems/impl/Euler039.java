package problems.impl;

import problems.Problem;

/**
 * Problem 39:
 * https://projecteuler.net/problem=39
 *
 * Solved by Sage on 10/23/16.
 */
public class Euler039 implements Problem {

    private static final int PERIM_LIMIT = 1000;
    private static final int SHORT_LIMIT = 292; // 1000 / (2 + sqrt(2))

    @Override
    public String solve() {

        int[] ways = new int[PERIM_LIMIT + 1];

        // Simple bounds for the short side
        for (int a = 3; a <= SHORT_LIMIT; a++) {
            // Easy bounds for long side
            for (int b = SHORT_LIMIT + 1; b <= Math.sqrt(PERIM_LIMIT*PERIM_LIMIT - SHORT_LIMIT*SHORT_LIMIT); b++) {
                // Only one possible value for c at this point, but I'm lazy and there are few
                // enough possible values where it is quick to check each of them
                for (int c = b+1; c <= PERIM_LIMIT / 2; c++) {
                    if (a*a + b*b == c*c && a+b+c <= PERIM_LIMIT) {
                        ways[a+b+c]++;
                    }
                }
            }
        }

        int best = 0;
        for (int i = 5; i <= PERIM_LIMIT; i++) {
            if (ways[i] > ways[best]) {
                best = i;
            }
        }

        return String.valueOf(best);
    }
}
