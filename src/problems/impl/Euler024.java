package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem 24:
 * What is the millionth lexicographic permutation of
 * the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 *
 * Solved by Sage on 10/16/16.
 */
public class Euler024 implements Problem {

    private static final int GOAL = 1000000;

    /**
     * So the logic here is that we find the next digit by
     * adding the number of permutations starting with each
     * digit until the number of permutations exceeds our goal.
     * Once we pass our target number of permutations, we
     * know the previous digit is the correct one.
     *
     * E.g. say we want the 12th permutation of 1,2,3,4.
     * Fix the first digit. If it is 1, there are 3! ways
     * of ordering 2,3,4. So 1 is too small and we try 2.
     * Starting with 2 leaves 3! ways of permuting 1,3,4,
     * so 2 is the correct starting digit. We then need the
     * 6th permutation of 1,3,4 (12 - 3! = 6), so we repeat
     * our process with 1,3,4, and so on.
     */
    @Override
    public String solve() {
        List<Long> digits = new ArrayList<>();
        for (long i = 0; i < 10; i++) {
            digits.add(i);
        }

        long permutations = 0;
        String answer = "";

        while (digits.size() > 1) {
            int count = 0;
            while (permutations < GOAL) {
                permutations += factorial(digits.size() - 1);
                count++;
            }
            permutations -= factorial(digits.size() - 1);
            answer += String.valueOf(digits.remove(count - 1));
        }
        // Don't forget to tack on the last digit!
        return answer + String.valueOf(digits.remove(0));
    }

    /**
     * Calculates n!
     * @param n number of things
     * @return n!
     */
    private long factorial(long n) {
        long product = 1;
        for (long i = 2; i <= n; i++) {
            product *= i;
        }
        return product;
    }
}
