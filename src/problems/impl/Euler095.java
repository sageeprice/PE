package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Too slow, takes ~75 seconds to run.
 *
 * Answer: 14316
 */
public class Euler095 implements Problem {

    private static final int CHAIN_MAX = 1_000_000;

    @Override
    public String solve() {
        int[] successor = new int[CHAIN_MAX];

        for (int i = 1; i < CHAIN_MAX; i++) {
            successor[i] = -1;
        }
        // Generate successors
        for (int i = 1; i < CHAIN_MAX; i++) {
            if (i % 100000 == 0) {
                System.out.println(i);
            }
            successor[i] = getSumOfFactors(i);
            if (successor[i] >= CHAIN_MAX) {
                successor[i] = 1;
            }
        }

        int longestChain = 1;
        int minLongestChain = CHAIN_MAX;

        for (int i = 2; i < CHAIN_MAX; i++) {
            // Can skip perfect numbers, trivial chains.
            if (successor[i] == i) {
                continue;
            }
            List<Integer> chain = new ArrayList<>();
            int current = i;
            chain.add(i);
            do {
                current = successor[current];
                chain.add(current);
            } while (!chain.contains(successor[current]) && current < CHAIN_MAX);
            // Verify current element in tolerable range, then check for amicable chain.
            if (current < CHAIN_MAX && successor[current] == i) {
                if (chain.size() > longestChain) {
                    longestChain = chain.size();
                    minLongestChain = getChainMin(chain);
                }
            }
        }
        return String.valueOf(minLongestChain);
    }

    /** Returns the smallest element in a list. */
    private static int getChainMin(List<Integer> chain) {
        int x = CHAIN_MAX;
        for (int link : chain) {
            if (link < x) {
                x = link;
            }
        }
        return x;
    }

    /**
     * Returns the sum of all proper divisors of x.
     * NOTE: almost all the time is spent here, this should be sped up.
     */
    private static int getSumOfFactors(int x) {
        int x0 = x;
        if (x < 4) {
            return 1;
        }
        Map<Integer, Integer> factorMap = new HashMap<>();
        while (x % 2 == 0) {
            addToMap(2, factorMap);
            x /= 2;
        }
        for (int i = 3; (i < x0/2 + 1) && (x != 1); i += 2) {
            while (x % i == 0) {
                addToMap(i, factorMap);
                x /= i;
            }
        }
        if (x0 == x) {
            return 1;
        }
        int factorSum = 1;
        // Sum up the discovered factors;
        for (int primeFactor : factorMap.keySet()) {
            int powSum = 1;
            int pow = primeFactor;
            for (int i = 1; i <= factorMap.get(primeFactor); i++) {
                powSum += pow;
                pow *= primeFactor;
            }
            factorSum *= powSum;
        }
        return factorSum - x0;
    }

    /** Adds x to the map, or increments the value of x in the map. */
    private static void addToMap(int x, Map<Integer, Integer> factorMap) {
        if (factorMap.containsKey(x)) {
            factorMap.put(x, factorMap.get(x) + 1);
        } else {
            factorMap.put(x, 1);
        }
    }
}
