package problems.euler076To100;

import problems.Problem;

/**
 * Problem 91:
 * https://projecteuler.net/problem=91
 *
 * Solved by Sage on 11/5/16.
 */
public class Euler091 implements Problem {

    private static final int LIMIT = 50;

    // First point of triangle
    private static final int x0 = 0;
    private static final int y0 = 0;

    @Override
    public String solve() {

        int count = 0;
        // (x1, y1) is the second point of triangle
        for (int x1 = 0; x1 <= LIMIT; x1++) {
            for (int y1 = 0; y1 <= LIMIT; y1++) {

                // Skip point on origin
                if (x1 + y1 == 0)
                    continue;

                // (x2, y2) third point of triangle
                for (int x2 = 0; x2 <= LIMIT; x2++) {
                    for (int y2 = 0; y2 <= LIMIT; y2++) {

                        // Skip point on origin, first point and second point same
                        if (x2 + y2 == 0 || (x1 == x2 && y1 == y2))
                            continue;

                        if (verifyRightTriple(x0, y0, x1, y1, x2, y2))
                            count++;
                    }
                }
            }
        }

        // Divide by two since all points were checked twice
        return String.valueOf(count / 2);
    }

    /**
     * Check that three points form a right triangle, will fail if a point is repeated
     * @param x0 x coord of first point
     * @param y0 y coord of second point
     * @param x1 x coord of first point
     * @param y1 y coord of second point
     * @param x2 x coord of first point
     * @param y2 y coord of second point
     * @return whether the three points form a right triangle
     */
    private boolean verifyRightTriple(int x0, int y0, int x1, int y1, int x2, int y2) {
        int l0x = x1 - x0;
        int l0y = y1 - y0;
        int l1x = x2 - x1;
        int l1y = y2 - y1;
        int l2x = x0 - x2;
        int l2y = y0 - y2;

        return (l0x * l1x == -1 * l0y * l1y) || (l1x * l2x == -1 * l1y * l2y) || (l2x * l0x == -1 * l2y * l0y);
    }
}
