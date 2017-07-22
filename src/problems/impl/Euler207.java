package problems.impl;

import problems.Problem;

/**
 * See comment.
 */
public class Euler207 implements Problem {

    private static final int LIMIT = 12345;

    @Override
    public String solve() {
        int pow = 3;
        while ((Math.pow(2, pow) - 1) / (pow - 1) < LIMIT) {
            pow++;
        }
        long x = (long) Math.pow(2, pow);
        do {
            x--;
        } while (LIMIT < (x - 1) / (double) (pow - 1));
        // Partitions have value a^2 - a for all natural numbers a.
        // Perfect partitions have a = 2^i where i element of naturals.
        return String.valueOf((x + 1)*x);
    }
}
