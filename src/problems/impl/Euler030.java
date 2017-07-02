package problems.euler025to050;

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

    @Override
    public String solve() {

        int sum = 0;

        for (int i = 10; i < LIMIT; i++) {
            // Lazy way to do it: iterate through all integers and evaluate.
            int digitalSum = String.valueOf(i).chars().map(d -> d - '0').map(d -> (int) Math.pow(d,POWER)).sum();
            if (i == digitalSum) {
                System.out.println("Found solution: " + i);
                sum += i;
            }
        }

        return String.valueOf(sum);
    }
}
