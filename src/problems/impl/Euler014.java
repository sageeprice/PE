package problems.impl;

import problems.Problem;

/**
 * Problem 14:
 * Which starting number, under one million, produces the longest Collatz chain?
 *
 * Solved by Sage on 10/8/16.
 */
public class Euler014 implements Problem {

    private static final int LIMIT = 1000000;
    private long[] collatzCache = new long[LIMIT];

    @Override
    public String solve() {

        for (int i = 1; i < LIMIT; i++) {
            collatzCache[i] = -1;
        }
        collatzCache[1] = 1;

        long best = 1;
        int longest = 1;

        for (int i = 2; i < LIMIT; i++) {
            long l = collatzLength(i);

            if (l > best) {
                best = l;
                longest = i;
            }
        }

        return String.valueOf(longest);

    }

    private long collatzLength(int x) {

        if (collatzCache[x] != -1)
            return collatzCache[x];

        long y = getNext(x);
        long steps = 1;

        while (y >= LIMIT || collatzCache[(int) y] == -1) {
            steps++;
            y = getNext(y);
        }

        collatzCache[x] = collatzCache[(int) y] + steps;
        return collatzCache[x];

    }

    /**
     * Calculate next entry in Collatz sequence
     * @param x the current value
     * @return next value
     */
    private long getNext(long x) {
        if (x % 2 == 0) {
            return x / 2;
        } else {
            return 3 * x + 1;
        }
    }
}
