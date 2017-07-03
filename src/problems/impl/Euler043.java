package problems.impl;

import problems.Problem;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * Problem 43:
 * https://projecteuler.net/problem=43
 *
 * Solved by Sage on 10/23/16.
 */
public class Euler043 implements Problem {

private static long d1;
	private static long d2;
	private static long d3;
	private static long d4;
	private static long d5;
	private static long d6;
	private static long d7;
	private static long d8;
	private static long d9;
	private static long d10;
	private static Set<Long> digits= new TreeSet<>();

	private static long total = 0L;

    @Override
    public String solve() {
        // Directly calculate possibilities with some backtracking.
		d6 = 5;
		digits.add(d6);
		for (d5 = 0L; d5 < 10; d5++) {
			if (!digits.contains(d5)) {
				digits.add(d5);
				getD7();
				digits.remove(d5);
			}
		}
		digits.remove(d6);
		d6 = 0;
		digits.add(d6);
		for (d5 = 0; d5 < 10; d5++) {
			if (!digits.contains(d5)) {
			digits.add(d5);
			getD7();
			digits.remove(d5);
			}
		}
		return String.valueOf(total);
	}

	private static void getD7() {
		for (long i = 0; i < 10; i++) {
			if (((100*d5 + 10*d6 + i) % 7) == 0 && !digits.contains(i)) {
				d7 = i;
				digits.add(d7);
				getD8();
				digits.remove(d7);
			}
		}
	}

	private static void getD8() {
		for (long i = 0; i < 10; i++) {
			if (((d6+i-d7)%11) == 0 && !digits.contains(i)) {
				d8 = i;
				digits.add(d8);
				getD9();
				digits.remove(d8);
				return;
			}
		}
	}

	private static void getD9() {
		for (long i = 0; i < 10; i++) {
			if ((100*d7 + 10*d8 + i)%13 == 0 && !digits.contains(i)) {
				d9 = i;
				digits.add(d9);
				getD10();
				digits.remove(d9);
				return;
			}
		}
	}

	private static void getD10() {
		for (long i = 0; i < 10; i++) {
			if ((100*d8 + 10*d9 + i)%17 == 0 && !digits.contains(i)) {
				d10 = i;
				digits.add(d10);
				getD4();
				digits.remove(d10);
				return;
			}
		}
	}

	// must be even
	private static void getD4() {
		for (long i = 0; i < 10; i += 2) {
			if (!digits.contains(i)) {
				d4 = i;
				digits.add(d4);
				getD3();
				digits.remove(d4);
			}
		}
	}

	private static void getD3() {
		for (long i = 0; i < 10; i++) {
			if ((i+d4+d5)%3 == 0 && !digits.contains(i)) {
				d3 = i;
				digits.add(d3);
				getD1AndD2();
				digits.remove(d3);
			}
		}
	}

	private static void getD1AndD2() {
		for (long i = 0; i < 10; i++) {
			if (!digits.contains(i)) {
				d2 = i;
				digits.add(d2);
				for (long j = 1; j < 10; j++) {
					if (!digits.contains(j)) {
						d1 = j;
						finish();
					}
				}
				digits.remove(d2);
			}
		}
	}

	private static void finish() {
		long pandig = d1*1000000000 +
				d2*100000000 +
				d3*10000000 +
				d4*1000000 +
				d5*100000 +
				d6*10000 +
				d7*1000 +
				d8*100 +
				d9*10 +
				d10;
		total += pandig;
    }

    private static final String DIGITS = "0123456789";
    private static final int[] PRIMES = {2,3,5,7,11,13,17};
    private static final int NUM_DIGITS = 10;
    private static final int TEN = 10;

    public String solveInefficiently() {

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
            // Extract the part of x we need to check
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
