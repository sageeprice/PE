package problems.impl;

import problems.Problem;

import java.util.Arrays;

/**
 * Direct calculation via manipulation of Binet's formula for leading digits,
 * only performed after finding a solution with correct terminating digits.
 * Answer: 329468
 */
public class Euler104 implements Problem {

    private static final String DIGITS = "123456789";
    private static final long BILLION = 1_000_000_000;
    private static final double LOG_PHI = Math.log10((1 + Math.sqrt(5))/2.0);
    private static final double LOG_ROOT_FIVE = Math.log10(Math.sqrt(5));

    @Override
    public String solve() {
        long f1 = 1;
        long f2 = 1;
        long t;
        int count = 2;
        while (true) {
            t = f1 + f2;
            f1 = f2;
            f2 = t % BILLION;
            count++;
            if (isPandigital(String.valueOf(f2))) {
                System.out.print(count + ": ");

                // F_n ~= phi^n / root(5). we only want leading digits, so take logarithm
                // of this and drop the integer part to get the needed decimal. Then multiply
                // by 10^8 (or just add 8 to the log...) and grab the integer part to get
                // the first 10 digits.
                double lPow = LOG_PHI * count - LOG_ROOT_FIVE;
                lPow = lPow - (long) lPow;

                int leadingDigits = (int) Math.floor(Math.pow(10, lPow + 8));
                System.out.println(leadingDigits);
                if (isPandigital(String.valueOf(leadingDigits))) {
                    return String.valueOf(count);
                }
            }
        }
    }

    /** Returns whether a given String is pandigital. */
    private static boolean isPandigital(String digits) {
        if (digits.length() != 9)
            return false;

        char[] charArray = digits.toCharArray();
        Arrays.sort(charArray);

        return String.valueOf(charArray).equals(DIGITS);
    }
}
