package problems.impl;

import problems.Problem;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * Problem 29:
 * https://projecteuler.net/problem=29
 *
 * Solved by Sage on 10/22/16.
 */
public class Euler029 implements Problem {

    private static final int BASE_POWER_COUNT = 99;
    private static final int UNIQUE_POWER_NUMS = 81;

    @Override
    public String solve() {
        // Count of powers for 2 (covers 4, 8, 16, 32, 64).
        int twoCount = BASE_POWER_COUNT;
        for (int i = 101; i <= 600; i++) {
            if (i < 201 && i % 2 == 0)
                twoCount++;
            else if (i < 301 && i % 3 == 0)
                twoCount++;
            else if (i < 401 && i % 4 == 0)
                twoCount++;
            else if (i < 501 && i % 5 == 0)
                twoCount++;
            else if (i < 601 && i % 6 == 0)
                twoCount++;
        }

        // Count of powers for 3 (covers 9, 27, 81).
        int threeCount = BASE_POWER_COUNT;
        for (int i = 101; i <= 400; i++) {
            if (i < 201 && i%2 == 0)
                threeCount++;
            else if (i < 301 && i%3 == 0)
                threeCount++;
            else if (i < 401 && i % 4 == 0)
                threeCount++;
        }

        // Count of powers for 5, 6, 7, 10 (covers 25, 36, 49, 100).
        int elseCount = BASE_POWER_COUNT;
        for (int i = 101; i <= 200; i++) {
            if (i < 201 && i%2 == 0)
                elseCount++;
        }

        int overlapPower = twoCount + threeCount + 4 * elseCount;
        int uniquePowers = BASE_POWER_COUNT * UNIQUE_POWER_NUMS;
        return String.valueOf(overlapPower + uniquePowers);
    }

}
