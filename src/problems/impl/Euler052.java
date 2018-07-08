package problems.impl;

import problems.Problem;

import java.util.Arrays;

import static problems.EulerLib.isAnagram;

/**
 * Problem 52:
 * https://projecteuler.net/problem=52
 *
 * Solved by Sage on 10/26/16.
 */
public class Euler052 implements Problem {

    // I actually knew this one off-hand, 1/7 is quite familiar.
    // Anyway, super simple brute force anagram check.

    @Override
    public String solve() {
        for (int i = 100006; i < 1000000 / 6; i++) {
            boolean found = true;
            for (int y = i + i; y <= 6 * i; y += i) {
                if (!isAnagram(y, i)) {
                    found = false;
                    break;
                }
            }
            if (found)
                return String.valueOf(i);
        }
        return null;
    }
}
