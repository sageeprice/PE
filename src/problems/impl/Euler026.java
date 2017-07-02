package problems.impl;

import problems.Problem;

import java.math.BigInteger;

/**
 * Created by Sage on 10/16/16.
 */
public class Euler026 implements Problem {

    @Override
    public String solve() {

        for (int i = 999; i > 10; i -= 2) {
            // period is always a factor of denominator - 1,
            // best possible is for primes where period is p - 1
            // so we just find the first prime less than 1000
            // where the period is p - 1
            if (isPrime(i)) {
                // best possible period is one less than the denominator
                if (findPeriod(i) == i - 1) {
                    return String.valueOf(i);
                }
            }
        }
        return null;
    }

    private boolean isPrime(int x) {

        for (int i = 2; i <= Math.sqrt(x); i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calculates the period of decimal representation of 1/x
     * by calculating multiplicative order of 10 base x
     * @param x the base
     * @return multiplicative order of 10 base x
     */
    private int findPeriod(int x) {
        BigInteger tenPow = BigInteger.TEN;
        int pow = 1;
        BigInteger bigX = new BigInteger(String.valueOf(x));
        while (tenPow.remainder(bigX).compareTo(BigInteger.ONE) != 0) {
            tenPow = tenPow.multiply(BigInteger.TEN);
            pow++;
        }
        return pow;
    }
}
