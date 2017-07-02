package problems.impl;

import problems.Problem;

/**
 * Problem 34:
 * https://projecteuler.net/problem=34
 *
 * Solved by Sage on 10/22/16.
 */
public class Euler034 implements Problem {

    private static final int LIMIT = 1000000;

    @Override
    public String solve() {

        int sum = 0;

        for (int i = 10; i < LIMIT; i++) {
            if (digFacSum(i) == i) {
                sum += i;
            }
        }

        return String.valueOf(sum);
    }

    /**
     * Return the sum of the factorial of each digit in x
     * @param x input number
     * @return digit factorial sum
     */
    private long digFacSum(int x) {
        int sum = 0;
        while (x > 0) {
            sum += factorial(x % 10);
            x /= 10;
        }
        return sum;
    }

    /**
     * Calculates n!
     * @param n number of things
     * @return n!
     */
    private long factorial(long n) {
        long product = 1;
        for (long i = 2; i <= n; i++) {
            product *= i;
        }
        return product;
    }
}
