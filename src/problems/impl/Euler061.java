package problems.euler051to075;

import problems.Problem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Problem 61:
 * https://projecteuler.net/problem=61
 *
 * Solved by Sage on 10/29/16.
 */
public class Euler061 implements Problem {

    // Length of cycle to construct, there are solutions for longer cycles as well,
    // simply modify this and add some print statements at the end of the main
    // method to see them.
    private static final int CYCLE_SIZE = 6;
    // Index of which figurate set to start with, largely irrelevant
    private static final int START_SET = 3;
    // Bounds within which figurate numbers can be used
    private static final int LOWER_BOUND = 1000;
    private static final int UPPER_BOUND = 10000;
    // Represents that cycle value hasn't been found yet
    private static final int UNKNOWN = -1;

    @Override
    public String solve() {
        // This solution is a brute force implementation using backtracking
        // that generates the first acceptable cycle

        // sets of the figurate numbers, S := [x | 1000 <= x < 10000 && x is figurate]
        List<Set<Integer>> figurateSets = new ArrayList<>();
        for (int i = 0; i < CYCLE_SIZE; i++) {
            figurateSets.add(new HashSet<>());
        }

        // add all valid figurates to the appropriate set
        for (int i = 5; i < 145; i++) {
            for (int j = 0; j < CYCLE_SIZE; j++) {
                // Generic formula for figurates
                int figurate = i * ((j+1) * i + (1 - j)) / 2;

                if (figurate >= LOWER_BOUND && figurate < UPPER_BOUND) {
                    figurateSets.get(j).add(figurate);
                }
            }
        }

        int numFigurateSets = figurateSets.size();

        // array to track which figurates have been used
        boolean[] hasUsedFigurateSet = new boolean[numFigurateSets];
        for (int i = 0; i < numFigurateSets; i++) {
            hasUsedFigurateSet[i] = false;
        }
        // arbitrarily start with triangular numbers, since we want
        // a cycle the choice of which set to start with is unimportant
        hasUsedFigurateSet[START_SET] = true;

        // array containing elements of the cycle we have found so far
        int[] cycle = new int[numFigurateSets];
        for (int i = 0; i < numFigurateSets; i++) {
            cycle[i] = UNKNOWN;
        }

        // Recursively construct the cycle
        for (int start : figurateSets.get(START_SET)) {
            cycle[0] = start;
            if (buildCycleRecursively(cycle, 1, hasUsedFigurateSet, figurateSets)) {
                break;
            }
            cycle[0] = UNKNOWN;
        }

        // Sum up the cycle and return
        int sum = 0;
        for (int i = 0; i < cycle.length; i++) {
            sum += cycle[i];
        }
        return String.valueOf(sum);
    }

    /**
     * Construct the cycle via recursion + backtracking
     * @param cycle the set of integers forming the cycle
     * @param cycleIndex the index in the cycle of the element to insert
     * @param hasUsedSet array of booleans representing which figurate numbers have been used
     * @param figurateSets list of sets of figurate numbers
     * @return boolean indicating whether or not a cycle has been found
     */
    private boolean buildCycleRecursively(int[] cycle,
                                          int cycleIndex,
                                          boolean[] hasUsedSet,
                                          List<Set<Integer>> figurateSets) {

        if (isCycleComplete(cycle, cycleIndex)) {
            return true;
        }

        // Get two digits to form base of next cycle element
        int base = (cycle[cycleIndex-1] % 100) * 100;

        // search unused sets for potential next elements
        for (int i = 0; i < hasUsedSet.length; i++) {
            if (!hasUsedSet[i]) {
                hasUsedSet[i] = true;
                Set<Integer> figSet = figurateSets.get(i);
                // check each possible ending for the next value in the cycle
                for (int j = 10; j < 100; j++) {
                    // recursively check for next element in cycle
                    if (figSet.contains(base+j)) {
                        cycle[cycleIndex] = base+j;
                        if (buildCycleRecursively(cycle, cycleIndex + 1, hasUsedSet, figurateSets)) {
                            return true;
                        }
                        cycle[cycleIndex] = UNKNOWN;
                    }
                }
                hasUsedSet[i] = false;
            }
        }
        return false;
    }

    // Check if the cycle is complete and valid
    private boolean isCycleComplete(int[] cycle, int cycleIndex) {
        return (cycleIndex == CYCLE_SIZE) && (cycle[0] / 100 == cycle[CYCLE_SIZE - 1] % 100);
    }
}
