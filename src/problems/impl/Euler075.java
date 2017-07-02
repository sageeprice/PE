package problems.impl;

import problems.Problem;

/**
 * Problem 75:
 * https://projecteuler.net/problem=75
 *
 * Solved by Sage on 10/31/16.
 */
public class Euler075 implements Problem {

    private static final int LIMIT = 1500000;

    @Override
    public String solve() {

        int[] numTriangles = new int[LIMIT + 1];
        for (int i = 0; i < numTriangles.length; i++) {
            numTriangles[i] = 0;
        }

        int total = 0;

        // Generate all triples, then mark down all multiples of the perimeter
        // https://en.wikipedia.org/wiki/Pythagorean_triple#Generating_a_triple
        for (long m = 2; m < (int) Math.sqrt(LIMIT / 2); m++) {
            for (long n = 1; n < m; n++) {
                if ((n + m) % 2 == 1 && gcd(m, n) == 1) {
                    long a = m * m - n * n;
                    long b = 2 * m * n;
                    long c = m * m + n * n;
                    for (long p = a + b + c; p <= LIMIT; p += a + b + c) {
                        numTriangles[(int) p]++;
                    }
                }
            }
        }

        for (int i = 5; i < LIMIT; i++) {
            if (numTriangles[i] == 1) {
                total++;
            }
        }

        return String.valueOf(total);
    }

    // Efficient gcd calculation, assumes a > b
    private static long gcd(long a, long b) {
        while (a % b != 0) {
            long t = a;
            a = b;
            b = t % a;
        }
        return b;
    }
}
