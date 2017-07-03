package problems.impl;

import problems.Problem;

/**
 * Problem 72:
 * https://projecteuler.net/problem=72
 *
 * Solved by Sage on 10/31/16.
 */
public class Euler072 implements Problem {

    private static final int LIMIT = 1000000;


    @Override
    public String solve() {

        int[] numerators = new int[LIMIT];
        int[] denominators = new int[LIMIT];
            for (int i = 1; i < LIMIT; i++) {
            numerators[i] = 1;
            denominators[i] = 1;
        }

        // So fun fact, the number of reduced proper fractions with
        // denominator d is phi(d). Think about it for a second, if
        // gcd(n,d) != 1 then the fraction can be reduced. Therefore
        // the set of reduced proper fractions is just those with no
        // shared factor with d, thus there are phi(d) of them.
        //
        // So we just reuse our code from Problem 70 with optimizations
        // to perform the calculations in a single pass
        long total = 0;

        for (long i = 2; i < LIMIT; i++) {
            if (denominators[(int) i] == 1) {
                for (int j = (int) i; j < LIMIT; j += i) {
                    numerators[j] *= (i-1);
                    denominators[j] *= i;
                }
            }
            total += i * numerators[(int) i] / denominators[(int) i];
        }

        return String.valueOf(total);
    }
}
