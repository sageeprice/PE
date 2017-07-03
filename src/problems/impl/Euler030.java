package problems.impl;

import problems.Problem;

/**
 * Problem 30:
 * https://projecteuler.net/problem=30
 *
 * Solved by Sage on 10/22/16.
 */
public class Euler030 implements Problem {

    // Limit reduced to speed calculation slightly,
    // original was 6 * 9^5.
    private static final int LIMIT = 200000;
    private static final int POWER = 5;
    private static final int BASE = 10;

    @Override
    public String solve() {

        int sum = 0;

        for (int i = 10; i < LIMIT; i++) {
            if (i == sumPowerOfDigits(i)) {
                sum += i;
            }
        }

        return String.valueOf(sum);
    }

	private static int sumPowerOfDigits(int check) {
		int sum = 0;
		int test = check;
		while (test > 0) {
            sum += Math.pow(test% BASE, POWER);
			test /= BASE;
			if (sum > check) {
				return 0;
			}
		}
		return sum;
	}
}
