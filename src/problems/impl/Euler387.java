package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.List;

import static problems.EulerLib.isPrime;

/**
 * Quick and easy. Better to generate them than to check every possibility.
 * A tad slow, 2 seconds.
 *
 * Answer: 696067597313468
 */
public class Euler387 implements Problem {

    private static final long LIMIT = 100_000_000_000_000L;

    @Override
    public String solve() {
        List<Long> maybeHarshads = new ArrayList<>();
        for (long i = 1; i < 10; i++) {
            maybeHarshads.add(i);
        }
        long maybeHarshad;
        long strongSum = 0;
        while ((maybeHarshad = maybeHarshads.remove(0)) < LIMIT) {
            long s = getDigitalSum(maybeHarshad);
            // Check for strong Harshad.
            if ((maybeHarshad/s)*s == maybeHarshad) {
                // Add all successors.
                for (int i = 0; i < 10; i++) {
                    maybeHarshads.add(maybeHarshad * 10 + i);
                }
            }
            long predecessor = maybeHarshad / 10;
            if (predecessor > 0 && isPrime(predecessor / getDigitalSum(predecessor)) && isPrime(maybeHarshad)) {
                strongSum += maybeHarshad;
            }
        }

        return String.valueOf(strongSum);
    }

    /** Returns the sum of the input's digits. */
    private static long getDigitalSum(long x) {
        int s = 0;
        while (x > 0) {
            s += x % 10;
            x /= 10;
        }
        return s;
    }
}
