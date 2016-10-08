package problems.euler001to025;

import problems.Problem;

/**
 * Problem 9:
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 *
 * Solved by Sage on 10/8/16.
 */
public class Euler009 implements Problem {

    private static final int SUM = 1000;

    @Override
    public String solve() {

        // Triangle, so c less than half the perimeter, and c >= perimeter / (1 + sqrt(2))
        for (int c = (int) Math.ceil(SUM / (1 + Math.sqrt(2))); c < SUM/2; c++) {
            // We define b >= a. Then b > c / sqrt(2) and b < c
            for (int b = (int) Math.ceil(c/Math.sqrt(2)) ; b < c; b++) {
                // from a + b + c = perimeter
                int a = SUM - c - b;
                // a little bit of algebra got here (could also just do a*a + b*b = c*c)
                if (a*b == SUM * (SUM / 2 - c)) {
                    return String.valueOf(a*b*c);
                }
            }
        }
        return null;
    }
}
