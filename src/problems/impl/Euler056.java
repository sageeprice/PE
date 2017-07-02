package problems.impl;

import problems.Problem;

import java.math.BigInteger;

/**
 * Problem 56:
 * https://projecteuler.net/problem=56
 *
 * Solved by Sage on 10/26/16.
 */
public class Euler056 implements Problem {

    @Override
    public String solve() {

        int bestDigitalSum = 0;
        for (int i = 1; i < 100; i++) {
            BigInteger x = new BigInteger(String.valueOf(i));
            BigInteger xMult = new BigInteger(String.valueOf(i));
            int digitalSum = getDigitSum(x);
            if (digitalSum > bestDigitalSum)
                bestDigitalSum = digitalSum;
            for (int j = 1; j < 99; j++) {
                x = x.multiply(xMult);
                if ((digitalSum = getDigitSum(x)) > bestDigitalSum) {
                    bestDigitalSum = digitalSum > bestDigitalSum ? digitalSum : bestDigitalSum;
                }
            }
        }
        return String.valueOf(bestDigitalSum);
    }

    private int getDigitSum(BigInteger x) {
        char[] digits = x.toString().toCharArray();
        int sum = 0;
        for (char d : digits) {
            sum += d - '0';
        }
        return sum;
    }
}
