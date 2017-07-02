package problems.impl;

import javafx.util.Pair;
import problems.Problem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem 62:
 * https://projecteuler.net/problem=62
 *
 * Solved by Sage on 10/29/16.
 */
public class Euler062 implements Problem {

    private static final int TARGET_PERMUTATIONS = 5;

    @Override
    public String solve() {
        // Map of sorted integer string to Pair of count, smallest value in permutation
        Map<String, Pair<Integer, Long>> permutationCountMap = new HashMap<>();

        for (long i = 1; ; i++) {
            // Generate cube and get sorted string of it
            long x = i * i * i;
            char[] xChars = String.valueOf(x).toCharArray();
            Arrays.sort(xChars);
            String orderedXStr = String.valueOf(xChars);

            // Check map and update or return
            if (permutationCountMap.containsKey(orderedXStr)) {
                if (permutationCountMap.get(orderedXStr).getKey() == TARGET_PERMUTATIONS - 1) {
                    return String.valueOf(permutationCountMap.get(orderedXStr).getValue());
                }
                permutationCountMap.put(
                        orderedXStr,
                        new Pair<>(permutationCountMap.get(orderedXStr).getKey() + 1,
                                permutationCountMap.get(orderedXStr).getValue()));
            } else {
                permutationCountMap.put(orderedXStr,
                        new Pair<>(1, x));
            }
        }
    }
}
