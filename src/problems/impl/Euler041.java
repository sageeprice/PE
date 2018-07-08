package problems.impl;

import problems.Problem;

import java.util.Arrays;

import static problems.EulerLib.isPrime;

/**
 * Problem 41:
 * https://projecteuler.net/problem=41
 *
 * Solved by Sage on 10/23/16.
 */
public class Euler041 implements Problem {

    // See below analysis on why only 7 digits
    private static final String DIGITS = "1234567";

    @Override
    public String solve() {
        // Some quick analysis:
        //
        //   Say x is 9 digits pandigital. Then it contains all of 1-9, and the
        //   Sum of the digits is thus 45 => x mod 3 = 0 => x is not prime.
        //
        //   Similarly, if x is 8 digit pandigital, sum of digits is 36, also not prime.
        //
        //   Therefore, a 7 digit pandigital is the first which could possibly be prime.
        //   We can iterate by increments of 18 since all pandigitals will have same sum
        //   mod 9 and even numbers aren't prime
        for (int i = 7654321; i > 7123456; i -= 18) {
            if (isPandigital(i) && isPrime(i)) {
                return String.valueOf(i);
            }
        }
        return null;
    }

    /**
     * Returns whether a and b collectively contain every digit 1 through 9 once each
     * @param a some positive integer
     * @return does a contain every digit 1 through
     */
    private boolean isPandigital(int a) {
        char[] number = String.valueOf(a).toCharArray();
        Arrays.sort(number);

        return String.valueOf(number).equals(DIGITS);
    }
}
