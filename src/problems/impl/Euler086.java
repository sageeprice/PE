package problems.impl;

import problems.Problem;

/**
 * From the really old solutions, seems like a direct calculation.
 *
 * Answer: 1818
 */
public class Euler086 implements Problem {

    @Override
    public String solve() {
        int m = 2;
        long count = 0;
        double x;
        int y;
        double z;
        while (count < 1000000) {
            m++;
            x = m;
            for (y = 2; y <= 2*m; y++) {
                z = Math.sqrt(x*x + y*y);
                if (Math.floor(z) == z) {
                    count += y < m ? y / 2 : y/2 - (y-m) + 1;
                }

            }
        }
        return String.valueOf(m);
    }
}
