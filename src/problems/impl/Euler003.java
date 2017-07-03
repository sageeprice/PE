package problems.impl;

import problems.Problem;

/**
 * Problem 3:
 * What is the largest prime factor of the number 600851475143
 *
 * Solved by Sage on 10/7/16.
 */
public class Euler003 implements Problem {

    private static final long NUM = 600851475143L;
    @Override
    public String solve() {
        long reducedNum = NUM;
        int i = 3;
        while (i < reducedNum) {
            while (reducedNum % i == 0)
                reducedNum /= i;
            i += 2;
        }
        return String.valueOf(reducedNum);
    }
}
