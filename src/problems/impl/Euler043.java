package problems.euler025to050;

import problems.Problem;

import java.util.Arrays;

/**
 * Problem 43:
 * https://projecteuler.net/problem=43
 *
 * Solved by Sage on 10/23/16.
 */
public class Euler043 implements Problem {

    private static final String DIGITS = "0123456789";
    private static final int[] PRIMES = {2,3,5,7,11,13,17};
    private static final int NUM_DIGITS = 10;
    private static final int TEN = 10;

    @Override
    public String solve() {

        boolean[] unusedDigits = new boolean[NUM_DIGITS];
        for (int i = 0; i < NUM_DIGITS; i++) {
            unusedDigits[i] = true;
        }
        long sum = generateAndSumPermutations(new StringBuilder(), unusedDigits, 0);

        return String.valueOf(sum);
    }

    /**
     * Recursively generates all permutations of 0-9 via backtracking and adds up the valid permutations
     * @param builder a StringBuilder containing the digits used so far
     * @param unusedDigits an array of booleans indicating which digits have not yet been used
     * @param sum the sum of all valid permutations
     * @return sum
     */
    private long generateAndSumPermutations(StringBuilder builder, boolean[] unusedDigits, long sum) {
        if (builder.toString().length() == NUM_DIGITS) {
            long x = Long.valueOf(builder.toString());
            if (isValid(x) && isPandigital(x)) {
                sum += x;
            }
        } else {
            for (int i = 0; i < NUM_DIGITS; i++) {
                // If a digit has not yet been used, check all permutations using it
                if (unusedDigits[i]) {
                    unusedDigits[i] = false;
                    // Recursive call, store updated sum
                    sum = generateAndSumPermutations(builder.append(i), unusedDigits, sum);
                    // After checking permutations, reset the state
                    builder.deleteCharAt(builder.length() - 1);
                    unusedDigits[i] = true;
                }
            }
        }
        return sum;
    }

    // Check if given long is pandigital (uses each of 0 through 9 once)
    // Note that longs beginning with 0 will always fail this check for our given case
    private boolean isPandigital(long a) {
        char[] number = String.valueOf(a).toCharArray();
        Arrays.sort(number);

        return String.valueOf(number).equals(DIGITS);
    }

    // This checks the division requirements, still thinking of better name for method
    private boolean isValid(long x) {
        for (int i = 0; i < PRIMES.length; i++) {
            // lol math magic, line 73 cleverly extracts the part of x we need to check
            // for divisibility against the given prime
            if (!primeDividesDigits(
                    (int) ((x / (long) Math.pow(TEN, NUM_DIGITS - (i + 4))) % 1000),
                    PRIMES[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean primeDividesDigits(int x, int prime) {
        return x % prime == 0;
    }
}
