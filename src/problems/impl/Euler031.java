package problems.euler025to050;

import problems.Problem;

/**
 * Problem 31:
 * https://projecteuler.net/problem=31
 *
 * Solved by Sage on 10/22/16.
 */
public class Euler031 implements Problem {

    @Override
    public String solve() {

        int ways = 0;

        // Super lazy backtracking impl
        // May come back and do this dynamically at some point,
        // could try to be clever and track number of ways of summing
        // up to each value using coins with some limit on value
        for (int i = 0; i <= 200; i += 200) {
            for (int j = 0; i + j <= 200; j += 100) {
                for (int k = 0; i + j + k <= 200; k += 50) {
                    for (int l = 0; i + j + k + l <= 200; l += 20) {
                        for (int m = 0; i + j + k + l + m <= 200; m += 10) {
                            for (int n = 0; i + j + k + l + m + n <= 200; n += 5) {
                                for (int o = 0; i + j + k + l + m + n + o <= 200; o += 2) {
                                    for (int p = 0; i + j + k + l + m + n + o + p <= 200; p += 1) {
                                        if (i + j + k + l + m + n + o + p == 200) {
                                            ways++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return String.valueOf(ways);
    }
}
