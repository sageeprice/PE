package problems.impl;

import problems.Problem;

/**
 * Problem 85:
 * https://projecteuler.net/problem=85
 *
 * Solved by Sage on 11/3/16.
 */
public class Euler085 implements Problem {

    private static final long TWO_MILLION = 2000000;
    private final int TWO_THOUSAND = 150;

    @Override
    public String solve() {

        long[] nChoose2 = new long[TWO_THOUSAND];
        for (long i = 2; i < TWO_THOUSAND; i++) {
            nChoose2[(int) i] = i * (i - 1) / 2;
        }

        long best = 0;
        int area = 0;
        for (int x = 2; x < TWO_THOUSAND; x++) {
            for (int y = 2; y < x; y++) {
                long xyRects = nChoose2[x] * nChoose2[y];
                if (Math.abs(xyRects - TWO_MILLION) < Math.abs(best - TWO_MILLION)) {
                    best = xyRects;
                    area = (x - 1) * (y - 1);
                }
            }
        }
        return String.valueOf(area);
    }
}
