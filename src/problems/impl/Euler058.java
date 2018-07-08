package problems.impl;

import problems.Problem;

import static problems.EulerLib.isPrime;

/**
 * Problem 59:
 * https://projecteuler.net/problem=58
 *
 * Solved by Sage on 10/27/16.
 */
public class Euler058 implements Problem {

    @Override
    public String solve() {
        // Count of primes
        int pCount = 0;
        // Total numbers checked
        int totalSeen = 1;

        // the current number being looked at in spiral
        int current = 1;
        // the number of integers to jump, increases by 2 every ring
        int jump = 2;
        do {
            // final corner is always a square, so only check the first 3
            for (int corner = 0; corner < 3; corner++) {
                current += jump;
                totalSeen++;
                if (isPrime(current)) {
                    pCount++;
                }
            }
            // Add to current and seen since we skipped final corner
            current += jump;
            totalSeen++;
            // Increase jump value since we're on to the next ring
            jump += 2;
        } while (pCount * 10 > totalSeen);

        // subtract one since we went to the next ring
        return String.valueOf(jump - 1);
    }
}
