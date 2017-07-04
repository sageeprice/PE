package problems.impl;

import problems.Problem;

/**
 * Answer: 1209002624
 */
public class Euler202 implements Problem {

    private static final long BOUNCES = 12017639147L;
    private static final long LEVELS = (BOUNCES+3)/2;

    @Override
    public String solve() {
        long factor = 3;
        long x = LEVELS;
        long relativePrimes = x;
        long primeFactors = 0;
        while (x != 1) {
            if (x % factor == 0) {
                relativePrimes /= factor;
                relativePrimes *= (factor - 1);
                while (x%factor == 0) {
                    x /= factor;
                }
                primeFactors++;
            }
            factor += 2;
        }
        return String.valueOf((relativePrimes - Math.pow(2, primeFactors))/3);
    }
}
