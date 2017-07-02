package problems.euler051to075;

import problems.Problem;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem 74:
 * https://projecteuler.net/problem=74
 *
 * Solved by Sage on 10/31/16.
 */
public class Euler074 implements Problem {

    private static final int FAC_SUM_MAX = 2177280; // = 9! * 6, the sum from 999999
    private static final int LIMIT = 1000000;

    private static int[] factorials;

    @Override
    public String solve() {

        // Array where value is equal to the length of the chain for the key
        int[] chainLength = new int[FAC_SUM_MAX+1];
        for (int i = 0; i < chainLength.length; i++) {
            chainLength[i] = 0;
        }
        chainLength[169] = 3;

        // Array of factorials (for quick lookup)
        factorials = new int[10];
        for (int i = 0; i < 10; i++) {
            factorials[i] = factorial(i);
        }

        // Check all chains
        int total = 0;
        for (int i = 1; i < LIMIT; i++) {
            int l = getChainLength(i, new ArrayList<>(), chainLength);
            if (l == 60)
                total++;
        }

        return String.valueOf(total);
    }

    private int getChainLength(int x, List<Integer> chain, int[] chainLengths) {

        if (chainLengths[x] != 0) {
            return chainLengths[x];
        }

        int xDigFacSum = 0;

        int y = x;
        while (y > 0) {
            xDigFacSum += factorials[y % 10];
            y /= 10;
        }

        if (chain.contains(xDigFacSum)) {
            int loc = chain.indexOf(xDigFacSum);
            chainLengths[xDigFacSum] = chain.size() - loc - 1;
            return chainLengths[xDigFacSum];
        } else {
            chain.add(xDigFacSum);
            int cL = getChainLength(xDigFacSum, chain, chainLengths);
            chainLengths[x] = cL + 1;
            return chainLengths[x];
        }
    }

    /**
     * Calculates n!, uses int since we're limited to 9! in this problem
     * @param n number of things
     * @return n!
     */
    private int factorial(int n) {
        int product = 1;
        for (int i = 2; i <= n; i++) {
            product *= i;
        }
        return product;
    }
}
