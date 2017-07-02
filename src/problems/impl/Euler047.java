package problems.euler025to050;

import problems.Problem;

/**
 * Problem 47:
 * https://projecteuler.net/thread=47
 *
 * Solved by Sage on 10/23/16.
 */
public class Euler047 implements Problem {

    @Override
    public String solve() {

        // An array counting the number of unique prime factors each index has
        // Same idea as Sieve of Erastosthenes
        int[] factorSieve = new int[1000000];

        for (int i = 0; i < 1000000; i++) {
            factorSieve[i] = 0;
        }

        for (int i = 2; i < 1000000; i++) {
            if (factorSieve[i] == 0) {
                for (int j = 2*i; j < 1000000; j += i) {
                    factorSieve[j]++;
                }
            }
        }

        for (int i = 2; i < 1000000; i++) {
            if (factorSieve[i] == 4
                    && factorSieve[i+1] == 4
                    && factorSieve[i+2] == 4
                    && factorSieve[i+3] == 4) {
                return String.valueOf(i);
            }
        }
        return null;
    }
}
