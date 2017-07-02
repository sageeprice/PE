package problems.impl;

import problems.Problem;

import java.util.Arrays;

/**
 * Problem 38:
 * https://projecteuler.net/problem=38
 *
 * Solved by Sage on 10/23/16.
 */
public class Euler038 implements Problem {

    private static final String DIGITS = "123456789";

    @Override
    public String solve() {

        // Given from problem, base is 9
        int best = 918273645;

        // Things to note: starting point must begin with a 9. Then consider cases by digits of starter.
        // Start is 2 digits: x.2x.3x.4x => 2x+ are all 3 digits or more, no way this works (2,5,8,11 > 9)
        // Start is 3 digits: x.2x.3x => 2x+ are all 4 digits, this can't work either (3,7,11 > 9)
        // Start is 4 digits: x.2x => 2x is 5 digits, so this works
        // Start is 5 digits+: Clearly won't work, multiply by 2 and you're already at 10/11 digits
        // So we only have to check 4 digit starters beginning with 9. Thus smallest start is 9123, end is 9876
        for (int i = 9123; i <= 9876; i++) {
            int concat = 100000 * i + 2*i;
            if (concat > best && isPandigital(i, 2*i)) {
                best = concat;
            }
        }

        return String.valueOf(best);
    }

    /**
     * Returns whether a and b collectively contain every digit 1 through 9 once each
     * @param a some positive integer
     * @param b some positive integer
     * @return do a and b together contain every digit 1 through 9 once
     */
    private boolean isPandigital(int a, int b) {
        char[] number = (String.valueOf(a) + String.valueOf(b)).toCharArray();
        Arrays.sort(number);

        return String.valueOf(number).equals(DIGITS);
    }
}
