package problems.euler001to025;

import problems.Problem;

/**
 * Problem 5:
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 *
 * Solved by Sage on 10/7/16.
 */
public class Euler005 implements Problem {

    public static final int MAX = 20;

    /**
     * We want the LCM of [1,20]. So we start with 1, and then iterate.
     * At each number x, divide all following numbers that are multiples
     * of x by x so that we do not repeatedly multiply by whatever x is.
     * e.g. 6 = 1*2*3, so when we hit 6 we've already multiplied by 2 and 3
     * and don't want to repeat the action.
     *
     * The end result of this is that we only ever multiply our answer by
     * new primes or new powers of primes. This leads us rapidly to LCM.
     */
    @Override
    public String solve() {

        int answer = 1;
        int[] multipliers = new int[MAX + 1];

        for (int i = 0; i <= MAX; i++) {
            multipliers[i] = i;
        }

        for (int i = 2; i < MAX; i++) {
            answer *= multipliers[i];
            for (int j = i+i; j <= MAX; j += i) {
                multipliers[j] /= multipliers[i];
            }
        }

        return String.valueOf(answer);
    }
}
