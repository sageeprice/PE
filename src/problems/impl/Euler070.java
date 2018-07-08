package problems.impl;

import problems.Problem;

import static problems.EulerLib.isPermutation;
import static problems.EulerLib.primesTo;

/**
 * Problem 70:
 * https://projecteuler.net/problem=70
 *
 * Solved by Sage on 10/31/16.
 */
public class Euler070 implements Problem {

    private static final int LIMIT = 10_000_000;

    @Override
    public String solve() {

        boolean[] sieve = primesTo(LIMIT);

        int[] numerators = new int[LIMIT];
        int[] denominators = new int[LIMIT];
        for (int i = 1; i < LIMIT; i++) {
            numerators[i] = 1;
            denominators[i] = 1;
        }

        double best = 2;
        long bestIndex = 0;

        for (long i = 2; i < LIMIT; i++) {
            if (sieve[(int) i]) {
                // This part is slow, if I care about performance,
                // could optimize this to happen while generating
                // the sieve.
                for (int j = (int) i; j < LIMIT; j += i) {
                    numerators[j] *= (i-1);
                    denominators[j] *= i;
                }
            }
            long phi = i * numerators[(int) i] / denominators[(int) i];
            if (isPermutation(i, phi) && (double)i / phi < best) {
                best = (double) i / phi;
                bestIndex = i;
            }
        }

        return String.valueOf(bestIndex);
    }
}
