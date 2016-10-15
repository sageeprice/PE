package problems.euler001to025;

import problems.Problem;

/**
 * Problem 21:
 * https://projecteuler.net/problem=21
 *
 * Solved by Sage on 10/15/16.
 */
public class Euler021 implements Problem {

    private static final int LIMIT = 10000;
    @Override
    public String solve() {
        int[] sumOfDivisors = new int[LIMIT + 1];

        sumOfDivisors[1] = 1;
        sumOfDivisors[2] = 2;

        for (int i = 3; i <= LIMIT; i++) {
            sumOfDivisors[i] = sumFactors(i);
        }

        int sumOfAmicables = 0;

        for (int i = 1; i <= LIMIT; i++) {
            if (sumOfDivisors[i] <= LIMIT
                    && sumOfDivisors[sumOfDivisors[i]] == i
                    && sumOfDivisors[i] != i) {
                sumOfAmicables += i;
            }
        }

        return String.valueOf(sumOfAmicables);
    }

    public int sumFactors(int x) {

        double root = Math.sqrt(x);

        int factors = 0;

        for (int i = 1; i < root; i++) {
            if (x % i == 0) {
                factors += i + (x / i);
            }
        }

        int rootFloor = (int) Math.floor(root);

        if (rootFloor*rootFloor == x) {
            factors += rootFloor;
        }

        // We only want the sum of proper divisors, so subtract x
        return factors - x;
    }
}
