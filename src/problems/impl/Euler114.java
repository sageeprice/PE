package problems.impl;

import problems.Problem;

/**
 * Dynamic programming.
 *
 * Answer: 1647564009
 */
public class Euler114 implements Problem {

    private static final int LENGTH = 50;

    @Override
    public String solve() {
        long[] o = new long[LENGTH];
        long[] or = new long[LENGTH];
        long[] orr = new long[LENGTH];
        long[] r = new long[LENGTH];

        o[2] = 1;
        or[2] = 1;
        orr[2] = 1;
        r[2] = 1;

        for (int i = 3; i < LENGTH; i++) {
            o[i] = o[i-1] + r[i-1];
            or[i] = o[i-1];
            orr[i] = or[i-1];
            r[i] = r[i-1] + orr[i-1];
        }

        return String.valueOf(o[LENGTH-1] + r[LENGTH-1]);
    }
}
