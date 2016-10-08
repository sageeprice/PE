package problems.euler001to025;

import problems.Problem;

/**
 * Problem 3: What is the largest prime factor of the number 600851475143
 *
 * Solved by Sage on 10/7/16.
 */
public class Euler003 implements Problem {

    // FTA explanation goes here. Every number has a unique representation
    // as the product of primes to powers.
    private static final long NUMBER = 600851475143L;
    private static final int SIEVE_SIZE = 10000;

    @Override
    public String solve() {

        // Simple sieve implementation
        // Magic number 10000 calculated retrospectively
        boolean[] isPrime = new boolean[SIEVE_SIZE + 1];

        long x = NUMBER;

        for (int i = 2; i <= SIEVE_SIZE; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i <= SIEVE_SIZE; i++) {
            if (isPrime[i]) {
                for (int j = 2; j * i <= SIEVE_SIZE; j++) {
                    isPrime[j * i] = false;
                }
                // Divide prime out of NUMBER to accelerate process
                // May need to be done repeatedly, e.g. 18 = 2 * 3 * 3
                while (x % i == 0) {
                    x /= i;

                    // When we're at one, all prime factors have been
                    // found, so return the current prime
                    if (x == 1) {
                        return String.valueOf(i);
                    }
                }
            }
        }

        return null;
    }
}
