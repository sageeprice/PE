package problems.impl;

import problems.Problem;

/**
 * Problem 4:
 * Find the largest palindrome made from the product of two 3-digit numbers.
 *
 * Solved by Sage on 10/7/16.
 */
public class Euler004 implements Problem {

    private static final int MIN = 101;
    private static final int MAX = 1000;

    /**
     * 1000 * 1000 = 1000000, so one million is max possible value
     * only 900 * 900 = 810000 possibilities to check, so brute force
     * it for simplicities sake.
     */
    @Override
    public String solve() {

        int highPalindrome = 0;

        for (int i = MIN; i < MAX; i++) {
            // Small optimization, only check values which will give a larger number
            // than the current best palindrome so we don't always start from MIN
            for (int j = Math.max(highPalindrome / i, 101); j < MAX; j++) {
                if (isPalindrome(j*i)) {
                    highPalindrome = j*i;
                }
            }
        }

        return String.valueOf(highPalindrome);

    }

    // Note: only works for numbers between 100,000 and 1,000,000
    private static boolean isPalindrome(int number) {
        if (number % 10 == number / 100000) {
            number = (number % 100000) / 10;
            if (number % 10 == number / 1000) {
                number = (number % 1000) / 10;
                if (number % 10 == number / 10)
                    return true;
            }
        }
        return false;
    }
}
