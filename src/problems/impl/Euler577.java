package problems.impl;

import problems.Problem;

/**
 * Consider the number of hexagons created by looking at points in the triangle
 * with highest point at the center of the larger triangle.
 *       *
 *      - *
 *     - - *
 *    - - - *
 *   - - - - *
 * Every dash represents a point on a new shape of hexagon centered at the
 * point at the top of the triangle. Each new row comes in every 3rd iteration.
 * The number of new hexagons each dash yields for subsequent iterations is
 *   (n - 3 * i + 1) * (n - 3 * i + 2) / 2
 * where n = lattice size, i = floor(n/3). This can be noted by considering
 * the number of triangles of side length n that fit into each subsequent
 * iteration. So at that point all you need to do is add up the number of
 * hexagons at each iteration.
 *
 * Answer: 265695031399260211
 */
public class Euler577 implements Problem {

    private static final long LIMIT = 12345;

    @Override
    public String solve() {
        long sum = 0;
        for (long i = 3; i <= LIMIT; i++) {
            sum += hexagonCount(i);
        }
        return String.valueOf(sum);
    }

    private static long hexagonCount(long x) {
        long terms = x / 3;
        long sum = 0;
        for (long i = 1; i <= terms; i++) {
            sum += (x*x + 3*x + 2 + 9*i*i - 9*i - 6*x*i) * i / 2;
        }
        return sum;
    }
}
