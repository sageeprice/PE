package problems.impl;

import problems.Problem;

import java.util.Arrays;

import static problems.EulerLib.isAnagram;
import static problems.EulerLib.primesTo;

/**
 * Problem 49:
 * https://projecteuler.net/problem=49
 *
 * Solved by Sage on 10/25/16.
 */
public class Euler049 implements Problem {

    private static final int SIEVE_SIZE = 10000;

    @Override
    public String solve() {
        boolean[] primes = primesTo(SIEVE_SIZE);

        // Skip the first result, which is supplied in the problem
        boolean hasSkippedFirst = false;

        for (int i = 1001; i < SIEVE_SIZE; i += 2) {
            if (primes[i]) {
                for (int j = 18; 2 * j + i < SIEVE_SIZE; j += 18) {
                    if (primes[i + j]
                            && primes[i + 2 * j]
                            && isAnagram(i, i + j)
                            && isAnagram(i, i + 2 * j)) {
                        if (!hasSkippedFirst) {
                            hasSkippedFirst = true;
                        } else {
                            return String.valueOf(
                                    (long) 100000000 * i
                                    + (long) 10000 * (i + j)
                                    + (long) (i + 2 * j)
                            );
                        }
                    }
                }
            }
        }
        return null;
    }
}
