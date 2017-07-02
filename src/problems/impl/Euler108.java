package problems.euler101To125;

import problems.Problem;

/**
 * Problem 108:
 * https://projecteuler.net/problem=108
 *
 * Solved by Sage on 11/7/16.
 */
public class Euler108 implements Problem {

    private static final int PRIME_FILLED_NUMBER = 420; // 2 * 2 * 3 * 5 * 7 = blaze it?
    private static final int TARGET = 1000;

    @Override
    public String solve() {

        /**
         * Suppose that x >= y . Then x = 2n is a clear upper bound for x.
         * Furthermore, 1/x + 1/y = 1/n => x, y > n , so n + 1 <= x <= n.
         * Then for ease of computation, suppose x = n + k where 1 <= k <= n.
         *  1/(n+k) + 1/y = 1/n =>
         *  1/n - 1/(n+k) = 1/y =>
         *  k/(n * (n+k)) = 1/y =>
         *  k/(n*n + n*k) = 1/y =>
         *  y = (n*n + n*k) / k =>
         *  k | n*n
         * So we know that k is a factor of n*n, and 1 <= k <= n.
         * Thus we want n*n to have a crapton of factors, so we should only
         * check numbers n which have many prime factors, hence the big oldSolved
         * PRIME_FILLED_NUMBER start point, and checking increments thereof.
         *
         * The thread for this problem has an even smarter solution that
         * extends from this. So the number of factors of n*n = d(n*n) where
         * d(x) is the divisors function. Since x=n*n is square, n*n has
         * (d(n*n) + 1) / 2 factors that are <= n. But every one of these is
         * a solution to the equation from above, so this value is exactly
         * what we are trying to calculate. We're just looking for the first
         * n such that (d(n*n) + 1) / 2 >= 1000.
         */

        int solutionCount = 0;
        long testVal = PRIME_FILLED_NUMBER;

        while (solutionCount < TARGET) {

            // Reset count, increment by prime-y number
            solutionCount = 0;
            testVal += PRIME_FILLED_NUMBER;

            // for each of k = 1...n, check if k|n*n
            for (long i = 1; i <= testVal; i++) {
                if ((testVal * testVal) % i == 0) {
                    solutionCount++;
                }
            }
        }

        return String.valueOf(testVal);
    }
}
