package problems.euler001to025;

import problems.Problem;

/**
 * Problem 1:
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.
 * The sum of these multiples is 23. Find the sum of all the multiples of 3 or 5 below 1000.
 *
 * Solved by Sage on 10/7/16.
 */
public class Euler001 implements Problem {

    @Override
    public String solve() {
        int sumThrees = sumMultiples(3, 999);
        int sumFives = sumMultiples(5, 999);
        int sumFifteens = sumMultiples(15, 999);

        // Principle of Inclusion/Exclusion: https://en.wikipedia.org/wiki/Inclusion–exclusion_principle
        return String.valueOf(sumThrees + sumFives - sumFifteens);
    }

    /**
     * Calculates sum(y | i > 0 && y = i*x && y < max)
     * @param x the multiplier
     * @param max the highest value
     * @return sum
     */
    private int sumMultiples(int x, int max) {
        int highMultiple = max / x;
        return x * (highMultiple * (highMultiple + 1) / 2);
    }
}
