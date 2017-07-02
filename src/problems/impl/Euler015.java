package problems.impl;

import problems.Problem;

/**
 * Problem 15:
 * How many routes are there starting from the upper left corner
 * and going only right and down through a 20×20 grid?
 *
 * Solved by Sage on 10/8/16.
 */
public class Euler015 implements Problem {

    private static final long GRID_SIZE = 20;

    /**
     * Easy question so I'll give a quick explanation.
     *
     * This is kind of a classic problem. The easy way to
     * think about it is that you must take 40 steps. 20 of them
     * must be right, 20 must be down. So if we pick 20 out of
     * the 40 steps to be right, the others must be down, and
     * the selection of these steps uniquely determine the
     * route taken. The simple way to get this count is to
     * calculate n choose k.
     *
     * If we pretend all 40 things are unique, there are 40!
     * ways to order them. However, in our case they are split
     * into 2 sets of 20 of the same thing. So within our
     * ordering, any ordering of those 20 is equivalent. So in
     * total there are 20! ways to order each set, so we divide
     * our total by each of these sets of ordering. This leaves
     * us with the simple 40! / (20! * 20!), or 40 choose 20.
     *
     * This is easily generalizable for whatever grid dimensions
     * you want. The problem gets more interesting when you use
     * a non-rectangular grid or add holes in the grid, but the
     * problem can still be solved simply with a dynamic approach.
     */
    @Override
    public String solve() {
        return String.valueOf(nCk(GRID_SIZE * 2, GRID_SIZE));
    }

    /**
     * Calculates n choose k
     * @param n number of things
     * @param k number of choices
     * @return n! / (k! * (n-k)!)
     */
    private long nCk(long n, long k) {
        long product = n;
        for (long i = 2; i <= Math.min(k, n-k); i++) {
            product *= (n - i + 1);
            product /= i;
        }
        return product;
    }
}
