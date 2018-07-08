package problems.impl;

import problems.Problem;

import java.util.HashSet;
import java.util.Set;

import static problems.EulerLib.primesTo;

/**
 * Problem 35:
 * https://projecteuler.net/problem=35
 *
 * Solved by Sage on 10/22/16.
 */
public class Euler035 implements Problem {

    private static final int SIEVE_SIZE = 1000000;

    @Override
    public String solve() {
        boolean[] isPrime = primesTo(SIEVE_SIZE);

        Set<Integer> circularPrimes = new HashSet<>();

        for (int i = 2; i < SIEVE_SIZE; i++) {
            if (isPrime[i]) {
                int x = i;
                boolean isCircular = true;
                // Check that each rotation of x is a prime
                while ((x = rotateInt(x)) != i) {
                    if (!isPrime[x]) {
                        isCircular = false;
                        break;
                    }
                }
                if (isCircular) {
                    circularPrimes.add(i);
                }
            }
        }

        return String.valueOf(circularPrimes.size());
    }

    /**
     * Returns integer where ones digit of x has been moved to the highest order digit
     * @param x input
     * @return rotated x with units digit as highest digit
     */
    private int rotateInt(int x) {
        if (x < 10)
            return x;
        int onesDig = x % 10;
        x /= 10;
        return x + onesDig * (int) Math.pow(10, String.valueOf(x).length());
    }
}
