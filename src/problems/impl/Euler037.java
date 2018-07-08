package problems.impl;

import problems.Problem;

import java.util.*;

import static problems.EulerLib.primesTo;

/**
 * Problem 37:
 * https://projecteuler.net/problem=37
 *
 * Solved by Sage on 10/22/16.
 */
public class Euler037 implements Problem {

    @Override
    public String solve() {
        Set<Integer> truncatablePrimes = new HashSet();
        truncatablePrimes.add(3);
        truncatablePrimes.add(7);

        boolean[] isPrime = primesTo(1_000_000);

        // Generate the truncatables from the base set, keep going
        // until 11 have been generated
        while (truncatablePrimes.size() - 2 < 11) {
            List<Integer> newTruncs = new ArrayList<>();
            for (int i : truncatablePrimes) {
                // Couldn't figure out why this didn't work for a while,
                // the last value that works is 739397 so it can't be generated
                // one digit at a time - got to make a big leap. Hence the big
                // upper bound on what to add. Should think of a better way.
                for (int j = 1; j <= 99999; j++) {
                    int maybeTrunc = j * (int) (Math.pow(10, Math.ceil(Math.log10(i)))) + i;
                    if (isTruncatable(maybeTrunc, isPrime)) {
                        newTruncs.add(maybeTrunc);
                    }
                }
            }
            truncatablePrimes.addAll(newTruncs);
        }

        // Subtract 10 since 3 and 7 don't count
        return String.valueOf(truncatablePrimes.stream().mapToInt(Integer::valueOf).sum() - 10);
    }

    private boolean isTruncatable(int x, boolean[] primes) {
        int y = x;

        do {
            if (!primes[y])
                return false;
            y /= 10;
        } while (y > 0);

        y = x;

        do {
            if (!primes[y])
                return false;
            y = y % (int) (Math.pow(10, Math.floor(Math.log10(y))));
        } while (y > 0);
        return true;
    }
}
