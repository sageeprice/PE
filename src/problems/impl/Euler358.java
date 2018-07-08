package problems.impl;

import problems.Problem;

import static problems.EulerLib.primesTo;

/**
 * Description in comments. Cyclic number must be prime. Then limit options
 * to examine based on given criteria.
 */
public class Euler358 implements Problem {

    // Last five digits determined by figuring out what
    // to multiply by to get ending of 99999.
    private static final int LOW_LIMIT  = 724709891; // From 1/.00000000138
    private static final int HIGH_LIMIT = 729927000; // From 1/.00000000137

    @Override
    public String solve() {
        boolean[] sieve = primesTo((int) Math.sqrt(HIGH_LIMIT));
        int primeCount = 1;
        for (int i = 3; i < (int) Math.sqrt(HIGH_LIMIT); i += 2) {
            if (sieve[i]) {
                primeCount++;
            }
        }
        int[] primes = new int[primeCount];
        primeCount = 1;
        primes[0] = 2;
        for (int i = 3; i < (int) Math.sqrt(HIGH_LIMIT); i += 2) {
            if (sieve[i]) {
                primes[primeCount++] = i;
            }
        }

        for (int i = LOW_LIMIT; i < HIGH_LIMIT; i += 100_000) {
            // Number must be a prime to be cyclic.
            if (isPrime(i, primes)) {
                // First 3 digits of cyclic part * number must be 999,
                // so check that product of first three digits and 137
                // is within range of 999.
                if (99_900 - 137 * (i / 1_000_000) < 137) {
                    // Number is cyclic, so sum of some term and first
                    // term is 99999...999. Therefore, since each pair of
                    // digits must sum to 9, and they're the same digits
                    // in a rotated order, sum of all digits must be
                    // 9 * (length of number) / 2. Cyclic, so length
                    // of number is just n - 1.
                    return String.valueOf((long) ((i - 1) / 2) * 9);
                }
            }
        }

        // This won't happen.
        return "Could not find result";
    }

    private static boolean isPrime(int x, int[] primes) {
        for (int p : primes) {
            if (x % p == 0) {
                return false;
            }
        }
        return true;
    }
}
