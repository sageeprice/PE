package problems.euler001to025;

import problems.Problem;

import java.math.BigInteger;

/**
 * Problem 20:
 * Find the sum of the digits in the number 100!
 *
 * Solved by Sage on 10/14/16.
 */
public class Euler020 implements Problem {

    @Override
    public String solve() {
        BigInteger n = BigInteger.ONE;

        // Java BigInteger annihilates this problem
        for (int i = 1; i <= 100; i++) {
            n = n.multiply(new BigInteger(String.valueOf(i)));
        }

        int sumOfDigits = 0;

        // Until n is 0, add last digit to the sum and remove the last digit
        while (n.compareTo(BigInteger.ZERO) != 0) {
            sumOfDigits += Integer.valueOf(n.remainder(BigInteger.TEN).toString());
            n = n.divide(BigInteger.TEN);
        }

        return String.valueOf(sumOfDigits);
    }
}
