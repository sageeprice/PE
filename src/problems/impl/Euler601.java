package problems.impl;

import problems.Problem;

import java.util.HashSet;
import java.util.Set;

/**
 * Surprisingly trivial.
 */
public class Euler601 implements Problem {

    private static final long[] PRIMES = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};

    @Override
    public String solve() {
        Set<Long> primeSet = new HashSet<>();
        for (long prime : PRIMES) {
            primeSet.add(prime);
        }
        long[] lcms = new long[32 + 1];
        for (int i = 1; i <= 32; i++) {
            lcms[i] = lcm(i);
        }
        long sum = 0;
        for (int i = 1; i < 32; i++) {
            double p1 = Math.floor((Math.pow(4, i) - 2) / (double) lcms[i]);
            double p2 = Math.floor((Math.pow(4, i) - 2) / (double) lcms[i+1]);
            sum += p1 - p2;
        }
        // Subtract 2 because calculations for i = 2, 4 are close enough to introduce errors.
        return String.valueOf(sum);
    }

    private static long lcm(long n) {
        long p = 1;
        for (long prime : PRIMES) {
            if (prime > n)
                return p;
            long k = prime;
            while (k * prime <= n)
                k *= prime;
            p *= k;
        }
        return p;
    }
}
