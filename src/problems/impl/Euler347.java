package problems.impl;

import problems.Problem;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Euler347 implements Problem {

    private static final int LIMIT = 10_000_000;

    @Override
    public String solve() {
        long total = 0;

        boolean[] sieve = sieveTo(LIMIT / 2);
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


    private static boolean[] sieveTo(int n) {
        boolean[] sieve = new boolean[n+1];
        // initial conditions
        if (n >= 2) {
            sieve[2] = true;
        }
        // only need to check odds
        for (int i = 3; i <= n; i+=2) {
            sieve[i] = true;
        }
        // Composites larger than sqrt(n) must have a smaller factor, and are thus eliminated
        // already. Therefore we need only proceed to check  values up through sqrt(n)
        for (int i = 3; i <= Math.sqrt(n); i+= 2) {
            if (sieve[i]) {
                /**
                 * Since you'll forget this Sage:
                 *  - if it's less than i*i more than i, it'll be covered by a smaller prime
                 *  - all primes > 2 are odd, so only need to check every other above i*i
                 *  One improvement that could be made: technically only need to check
                 *  of the form 6k+1 and 6k-1 (k an int), as 6k+3 is multiple of 3.
                 */
                for (int j = i*i; j <= n; j += i * 2) {
                    sieve[j] = false;
                }
            }
        }

        return sieve;
    }
}
