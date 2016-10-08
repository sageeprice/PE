package problems.euler001to025;

import problems.Problem;

/**
 * Problem 6:
 * Find the difference between the sum of the squares of the
 * first one hundred natural numbers and the square of the sum.
 *
 * Solved by Sage on 10/7/16.
 */
public class Euler006 implements Problem {

    private static final int MAX = 100;

    @Override
    public String solve() {

        /**
         * Fun fact:
         * (1+2+...+n) = n * (n+1) / 2
         * Proof is trivial, easy to see if represented by doubling the list
         * and pairing up the entries in reverse, like
         *   1 + 2 + ... + n
         *   n + ... + 2 + 1
         * You're then left with n pairs of n+1. Divide by 2 to get the total.
         * Note that this is strictly greater than the other component, as it
         * trivially will contain each of i*i.
         */
        long squareSum = (MAX * (MAX + 1)) / 2;
        squareSum = squareSum * squareSum;

        /**
         * Fun fact:
         * 1^2 + 2^2 + ... + n^2 = n*(n+1)*(2n+1)/6
         * Easiest proof is by induction, which I won't Javadoc.
         */
        long sumSquares = MAX * (MAX + 1) * (2 * MAX + 1) / 6;

        return String.valueOf(squareSum - sumSquares);
    }
}
