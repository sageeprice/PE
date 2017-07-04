package problems.impl;

import problems.Problem;

/**
 * Created by Sage on 10/24/2015.
 */
public class Euler173 implements Problem {

    private static final long MAX_TILES = 1000000;

    @Override
    public String solve() {
        long count = 0;
        long x = 3;
        while (4*(x-1) <= MAX_TILES) {
            for (long i = x-2; i > 0; i -= 2) {
                if (x*x - i*i <= MAX_TILES) {
                    count++;
                } else {
                    break;
                }
            }
            x += 1;
        }
        return String.valueOf(count);
    }
}