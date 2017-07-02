package problems.impl;

import problems.Problem;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Slow, about 10 seconds.
 *
 * Answer: 1739023853137
 */
public class Euler357 implements Problem {

    private static final int LIMIT = 100_000_000;
    private static final int ROOT_LIMIT = 10_000;

    @Override
    public String solve() {
        boolean[] sieve = new boolean[LIMIT];
        for (int i = 2; i < LIMIT; i++) {
            sieve[i] = true;
        }
        for (int i = 2; i < LIMIT; i++) {
            if (sieve[i]) {
                for (int j = i+i; j < LIMIT; j += i) {
                    sieve[j] = false;
                }
            }
        }
        // Collect all the primes
        Set<Integer> primes = new HashSet<>();
        for (int i = 2; i < LIMIT; i++) {
            if (sieve[i]) {
                primes.add(i);
            }
        }
        // Mark all values with a square prime factor.
        Set<Integer> set = new TreeSet<>();
        primes.stream().filter(p -> p < ROOT_LIMIT).forEach(set::add);
        for (int p : set) {
            for (int x = p * p; x < LIMIT; x += p * p) {
                sieve[x] = true;
            }
        }
        // Check possibilities.
        long sum = 0;
        for (int i = 6; i < LIMIT; i++) {
            if (!sieve[i] && (i % 2 == 0)) {
                boolean valid = true;
                for (int f = 1; f < Math.sqrt(i); f++) {
                    if ((i / f) * f == i) {
                        if (!primes.contains((i/f) + f)) {
                            valid = false;
                            break;
                        }
                    }
                }
                if (valid) {
                    sum += i;
                }
            }
        }
        sum += 1 + 2;
        return String.valueOf(sum);
    }
}
