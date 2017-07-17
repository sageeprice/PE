package problems.impl;

import problems.Problem;

import java.math.BigInteger;

/**
 * Inclusion/Exclusion Principle.
 */
public class Euler162 implements Problem {

    @Override
    public String solve() {

        BigInteger sum = BigInteger.ZERO;

        for (long i = 3; i <= 16; i++) {
            long ways = nCk(16, i);
            ways *= (long) Math.pow(13, 16 - i);

            long arrangements = 0;
            for (long a = 1; a <= i / 3; a++) {
                for (long b = a; b <= (i - a) / 2; b++) {
                    long c = i - a - b;
                        long m = 6;
                        if (a == b && a == c) {
                            m = 1;
                        } else if (a == b || b == c) {
                            m = 3;
                        }
                        arrangements += m * nCk(i, a) * nCk(i - a, b);
                }
            }
            BigInteger iWays = new BigInteger(String.valueOf(ways)).multiply(new BigInteger(String.valueOf(arrangements)));
            sum = sum.add(iWays);
        }

        for (long i = 2; i < 16; i++) {
            for (long k = i; k <= 15; k++) {
                long ways = nCk(k, i);
                ways *= (long) Math.pow(13, k - i);

                long arrangements = 0;
                for (long a = 1; a <= i / 2; a++) {
                    long b = i - a;
                    long m = 2;
                    if (a == b) {
                        m = 1;
                    }
                    arrangements += m * nCk(i, a);
                }
                BigInteger iWays = new BigInteger(String.valueOf(ways)).multiply(new BigInteger(String.valueOf(arrangements)));
                sum = sum.subtract(iWays);
            }
        }

        return sum.toString(16).toUpperCase();
    }

    /**
     * Calculates n choose k
     * @param n number of things
     * @param k number of choices
     * @return n! / (k! * (n-k)!)
     */
    private long nCk(long n, long k) {
        if (n == k || k == 0) {
            return 1;
        }
        long product = n;
        for (long i = 2; i <= Math.min(k, n-k); i++) {
            product *= (n - i + 1);
            product /= i;
        }
        return product;
    }
}
