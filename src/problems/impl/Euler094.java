package problems.impl;

import problems.Problem;

/**
 * Brute forced. An additional check implemented in one case
 * due to a rounding error resulting from square root being
 * imperfect in Java.
 *
 * Answer: 518408346
 */
public class Euler094 implements Problem {

    private static final long CAP = (long)Math.pow(10, 9);

    @Override
    public String solve() {
        long x;
        double y;
        long perimSum = 0;
        for (long hypot = 5; hypot <= CAP/3; hypot += 2) {
            x = (hypot-1)/2;
            y = Math.sqrt(hypot*hypot - x*x);
            if (Math.floor(y) == Math.ceil(y)) {
                if (x*x + y*y == hypot*hypot) {
                    perimSum += 2 * (x + hypot);
                }
            }
            x = (hypot+1)/2;
            y = Math.sqrt(hypot*hypot - x*x);
            if (Math.floor(y) == Math.ceil(y)) {
                if (x*x + y*y == hypot*hypot) {
                    int x10 = (int) x % 10;
                    int y10 = (int) y % 10;
                    int z10 = (int) hypot % 10;
                    // This is necessary due to a rounding error which
                    // discovers an invalid solution.
                    if ((x10*x10 + y10*y10) % 10 != (z10 * z10) % 10) {
                        continue;
                    }
                    perimSum += 2 * (x + hypot);
                }
            }
        }
        return String.valueOf(perimSum);
    }
}
