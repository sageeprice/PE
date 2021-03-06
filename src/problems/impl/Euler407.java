package problems.impl;

import problems.Problem;

/**
 * a*a === a mod m => a*a - a === 0 => a(a-1) === 0
 * So since m = p_1^q_1 * p_2^q_2 ... , each factor p_i of m must divide one of
 * a or a-1. Stupid approach: subtract the largest prime factor of m from m
 * until we find that either the number plus 1 or the number we check is an
 * idempotent. That must be the largest solution.
 *
 * Answer: 39782849136421
 */
public class Euler407 implements Problem {

    private static final int LIMIT = 10_000_000;

    @Override
    public String solve() {
        int[] maxFactors = maxPrimeFactors(LIMIT);

        long sum = 0;
        for (int i = 2; i <= LIMIT; i++) {
            if (maxFactors[i] == i) {
                sum += 1;
            }
            else {
                sum += getMaxIdempotent((long) i, (long) maxFactors[i]);
            }
        }
        return String.valueOf(sum);
    }

    private static int[] maxPrimeFactors(int lim) {

        int[] maxFactors = new int[lim+1];
        for (int i = 1; i <= lim; i++) {
            maxFactors[i] = 1;
        }
        for (int i = 2; i <= lim; i++) {
            if (maxFactors[i] == 1) {
                maxFactors[i] = i;
                for (int j = i + i; j <= lim; j += i) {
                    maxFactors[j] = i;
                }
            }
        }
        return maxFactors;
    }

    private static int getMaxIdempotent(long base, long maxFactor) {
        long x = maxFactor;
        while (base % x == 0) {
            x *= maxFactor;
        }
        x /= maxFactor;
        for (long i = base - x; i > 0; i -= x) {
            if (isIdempotent(i+1, base)) {
                return (int) i + 1;
            }
            if (isIdempotent(i, base)) {
                return (int) i;
            }
        }
        return 1;
    }

    private static boolean isIdempotent(long x, long m) {
        return (x*x) % m == x;
    }
}
