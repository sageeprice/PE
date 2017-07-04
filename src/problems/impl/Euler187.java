package problems.impl;

import problems.Problem;

/**
 * Directly calculate solutions.
 *
 * Answer: 17427258
 */
public class Euler187 implements Problem {

    private static final int CAP = 100_000_000;

    @Override
    public String solve() {
        boolean[] isPrime = sieveTo(CAP);
        int count = 0;
        for (int i = 2; i < CAP; i++) {
           if (isPrime[i]) {
               count++;
           }
        }
        int[] primes = new int[count];
        int index = 0;
        for (int i = 2; i < CAP; i++) {
            if (isPrime[i]) {
                primes[index++] = i;
            }
        }
        index--;
        long semiprimes = 0;
        for (int i = 0; primes[i] < Math.sqrt(CAP); i++) {
            while (primes[index] * primes[i] > CAP) {
                index--;
            }
            semiprimes += index - i + 1;
        }
        return String.valueOf(semiprimes);
    }

     /**
     * Returns a prime sieve of the first n integers or containing the first
     */
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
        // already. Therefore we need only proceed to check values up through sqrt(n).
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
