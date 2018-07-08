package problems.impl;

import problems.Problem;

import static problems.EulerLib.primesTo;

/**
 * Directly calculate solutions.
 *
 * Answer: 17427258
 */
public class Euler187 implements Problem {

    private static final int CAP = 100_000_000;

    @Override
    public String solve() {
        boolean[] isPrime = primesTo(CAP);
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
}
