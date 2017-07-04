package problems.impl;

import problems.Problem;

/**
 * Probability of all 1's at iteration i = (1 - (.5)^n)^32
 * So probability of ending at iter # i = (1 - (.5)^n)^32 - (1 - (.5)^(n-1))^32
 * Expectation is just N * P(stop at N), so sum from 1 to infin, until difference in terms is small enough.
 */
public class Euler323 implements Problem {

    // 10^13 gives a little more security, don't want to stop too early.
    private static final double DIFF = Math.pow(10, -13);

    @Override
    public String solve() {
        double expect = 0;
        double lastExpect = 0;
        long step = 1;
        while (Math.abs(expect - lastExpect) > DIFF || step < 10) {
            lastExpect = expect;
            expect += step * prob(step);
            step++;
        }
        return String.format("%.10f", expect);
    }

    private static double prob(long n) {
        return n == 0 ? 0 : Math.pow(1 - Math.pow(2, 0-n), 32) - Math.pow(1 - Math.pow(2, 1-n), 32);
    }
}
