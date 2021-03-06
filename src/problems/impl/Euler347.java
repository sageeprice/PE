package problems.impl;

import problems.Problem;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static problems.EulerLib.primesTo;

/**
 * Check all pairs of primes that multiply to a number less than the LIMIT, and
 * then find the largest for each pair.
 *
 * Answer: 11109800204052
 */
public class Euler347 implements Problem {

    private static final int LIMIT = 10_000_000;

    @Override
    public String solve() {
        long total = 0;

        boolean[] sieve = primesTo(LIMIT / 2);
        List<Long> primes = new ArrayList<>();
        primes.add(2L);
        for (int i = 3; i < sieve.length; i += 2) {
            if (sieve[i])
                primes.add((long) i);
        }

        for (int i = 0; i < primes.size(); i++) {
            long p1 = primes.get(i);
            // Stop when no possibilities.
            if (p1 * (p1 + 2) > LIMIT) {
                break;
            }
            for (int j = i + 1; j < primes.size(); j++) {
                long p2 = primes.get(j);
                // Stop when no possibilities.
                if (p1 * p2 > LIMIT) {
                    break;
                }
                total += maxMultiple(p1, p2);
            }
        }
        return String.valueOf(total);
    }

    private static long maxMultiple(long p, long q) {

        Queue<Long> pqMultiples = new ArrayDeque<>();
        pqMultiples.add(p * q);
        long max = 0;
        while (!pqMultiples.isEmpty()) {
            long pqMult = pqMultiples.poll();
            if (pqMult <= LIMIT) {
                max = max < pqMult ? pqMult : max;
                if (pqMult * p <= LIMIT) {
                    pqMultiples.add(pqMult * p);
                }
                if (pqMult * q <= LIMIT) {
                    pqMultiples.add(pqMult * q);
                }
            }
        }
        return max;
    }
}
