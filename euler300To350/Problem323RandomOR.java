package euler300To350;

/**
 * Probability of all 1's at iteration i = (1 - (.5)^n)^32
 * So probability of ending at iter # i = (1 - (.5)^n)^32 - (1 - (.5)^(n-1))^32
 * Expectation is just N * P(stop at N), so sum from 1 to infin, until difference in terms is small enough.
 */
public class Problem323RandomOR {

    // 10^13 gives a little more security, don't want to stop too early.
    private static final double DIFF = Math.pow(10, -13);

    public static void main(String[] args) {
        double expect = 0;
        double lastExpect = 0;
        long step = 1;
        while (Math.abs(expect - lastExpect) > DIFF || step < 10) {
            lastExpect = expect;
            expect += step * prob(step);
            if (step % 1000000 == 0)
                System.out.println("Current estimate is: " + expect + " at step number " + step);
            step++;
        }
        System.out.println("Final expectation is: " + expect + " after " + step + " iterations.");
    }

    private static double prob(long n) {
        return n == 0 ? 0 : Math.pow(1 - Math.pow(2, 0-n), 32) - Math.pow(1 - Math.pow(2, 1-n), 32);
    }
}
