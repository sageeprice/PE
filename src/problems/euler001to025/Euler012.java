package problems.euler001to025;

import problems.Problem;

/**
 * Problem 12:
 * What is the value of the first triangle
 * number to have over five hundred divisors?
 *
 * Solved by Sage on 10/8/16.
 */
public class Euler012 implements Problem {

    @Override
    public String solve() {
        int triangleNum = 1;
        int numDivisors = 0;

        for (int i = 2; numDivisors < 500; i++) {
            triangleNum += i;
            numDivisors = lazyDivisorCount(triangleNum);
        }
        return String.valueOf(triangleNum);
    }

    /**
     * Returns the number of factors of x
     * @param x the number
     * @return number of factors
     */
    private int lazyDivisorCount(int x) {
        int divisors = 0;
        int root = (int) Math.floor(Math.sqrt(x));

        // Only have to go to the square root and add the complement
        for (int i = 1; i < root; i++) {
            if (x%i == 0) {
                divisors += 2;
            }
        }
        if (root * root == x) {
            divisors++;
        }
        return divisors;
    }
}
