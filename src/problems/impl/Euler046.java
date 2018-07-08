package problems.impl;

import problems.Problem;

import java.util.HashSet;
import java.util.Set;

import static problems.EulerLib.primesTo;

/**
 * Problem 46:
 * https://projecteuler.net/problem=46
 *
 * Solved by Sage on 10/23/16.
 */
public class Euler046 implements Problem {

    // Determined in retrospect, actual answer is surprisingly small
    private static final int LIMIT = 10000;

    @Override
    public String solve() {

        // Create set of possibilities, initially all odds > 1
        Set<Integer> possibilities = new HashSet<>();
        for (int i = 3; i < LIMIT; i += 2) {
            possibilities.add(i);
        }

        boolean[] primes = primesTo(LIMIT);

        // Remove primes
        for (int i = 3; i < LIMIT; i += 2) {
            if (primes[i]) {
                // remove prime from set of possibilities
                if (possibilities.contains(i)) {
                    possibilities.remove(i);
                }
                // remove all numbers of correct form
                for (int j = 1; 2*j*j + i < LIMIT; j++) {
                    if (possibilities.contains(2*j*j + i)) {
                        possibilities.remove(2*j*j + i);
                    }
                }
            }
        }
        // Find the smallest answer and return it
        int smallest = LIMIT;
        for (int num : possibilities) {
            smallest = num < smallest ? num : smallest;
        }
        return String.valueOf(smallest);
    }

}
