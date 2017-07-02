package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem 2:
 * By considering the terms in the Fibonacci
 * sequence whose values do not exceed four million,
 * find the sum of the even-valued terms.
 *
 * Solved by Sage on 10/7/16.
 */
public class Euler002 implements Problem {

    private static final int MAX_VALUE = 4000000;

    // TODO: optimization - directly calculate even terms
    @Override
    public String solve() {

        // Don't want to have to recalculate Fibonacci numbers, so store them in a list
        // Brings efficiency from O(phi^n) to O(n)
        List<Integer> fibonaccis = new ArrayList();
        fibonaccis.add(1);
        fibonaccis.add(1);

        int fibLength = fibonaccis.size();
        int sumFibonacci = 0;

        while (true) {
            fibLength = fibonaccis.size();
            int nextFib = fibonaccis.get(fibLength-2) + fibonaccis.get(fibLength - 1);

            if (nextFib > MAX_VALUE) {
                break;
            }
            if (nextFib % 2 == 0) {
                sumFibonacci += nextFib;
            }
            fibonaccis.add(nextFib);
        }

        return String.valueOf(sumFibonacci);
    }
}
