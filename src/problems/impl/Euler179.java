package problems.impl;

import problems.Problem;

import java.util.Arrays;

/**
 * Directly calculate number of factors. Only need to be cute about powers
 * for primes p where p*p < limit. After that each prime yields double the
 * total number of factors for all multiples thereof.
 *
 * Answer: 986262
 * Runtime: 1.5 seconds.
 */
public class Euler179 implements Problem {

    private final int LIMIT = 10_000_000;

    @Override
    public String solve() {
        int[] factors = new int[LIMIT];
        Arrays.fill(factors, 1);

        int[] multipliers = new int[LIMIT];
        Arrays.fill(multipliers, 1);

        for (int i = 2; i < Math.sqrt(LIMIT); i++) {
            if (i % 100_000 == 0) {
                System.out.println(i);
            }
           if (factors[i] == 1) {
               for (int j = i; 0 < j && j < LIMIT; j *= i) {
                   for (int k = j; k < LIMIT; k += j) {
                       multipliers[k]++;
                   }
               }
               for (int j = i; j < LIMIT; j += i) {
                   factors[j] *= multipliers[j];
               }
               Arrays.fill(multipliers, 1);
           }
        }
        for (int i = (int) Math.sqrt(LIMIT) + 1; i < LIMIT; i++) {
            if (factors[i] == 1) {
                for (int j = i; 0 < j && j < LIMIT; j += i) {
                    factors[j] *= 2;
                }
            }
        }
        int count = 0;
        for (int i = 3; i < LIMIT; i++) {
            if (factors[i] == factors[i-1]) {
//                System.out.println(i + ": " + factors[i]);
                count++;
            }
        }
        return String.valueOf(count);
    }
}
