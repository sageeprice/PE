package problems.impl;

import problems.Problem;

import static problems.EulerLib.isPrime;

/**
 * The prime in question must be a difference of cubes. Furthermore, factoring
 * the difference of cubes shows that it must be the difference of consecutive
 * cubes. Check those within the range.
 * Answer: 173
 */
public class Euler131 implements Problem {

    private static final int LIMIT = 1_000_000;

    @Override
    public String solve() {
        long answerCount = 0;
        for (int i = 1; ; i++) {
            // (i+1)^3 - i^3
            // Technically the next 2 lines could be
            // incorporated into the for loop conditions.
            int diff = 3*i*i + 3*i + 1;
            if (diff > LIMIT)
                break;
            if (isPrime(diff))
                answerCount++;
        }
        return String.valueOf(answerCount);
    }
}
