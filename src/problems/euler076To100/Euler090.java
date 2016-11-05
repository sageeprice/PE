package problems.euler076To100;

import problems.Problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Problem 90:
 * https://projecteuler.net/problem=90
 *
 * Solved by Sage on 11/4/16.
 */
public class Euler090 implements Problem {

    private static final int DIE_SIZE = 6;

    @Override
    public String solve() {

        // Set up to generate all dice
        boolean[] digits = new boolean[10];
        for (int i = 0; i < digits.length; i++) {
            digits[i] = false;
        }

        Integer[] die = new Integer[DIE_SIZE];
        for (int i = 0; i < DIE_SIZE; i++) {
            die[i] = -1;
        }

        List<Integer[]> dice = new ArrayList<>();
        generateDice(digits, die, 0, dice);

        // Count the number of dice pair solutions
        int count = 0;
        for (int i = 0; i < dice.size(); i++) {
            Set<Integer> d1 = new HashSet<>(Arrays.asList(dice.get(i)));
            // 6 == 9
            if (d1.contains(6))
                d1.add(9);
            if (d1.contains(9))
                d1.add(6);

            for (int j = 0; j <= i; j++) {
                Set<Integer> d2 = new HashSet<>(Arrays.asList(dice.get(j)));
                // 6 == 9
                if (d2.contains(6))
                    d2.add(9);
                if (d2.contains(9))
                    d2.add(6);

                if (isValidPairing(d1, d2)) {
                    count++;
                }
            }
        }

        return String.valueOf(count);
    }

    // So lazy. Should clean this up.
    private boolean isValidPairing(Set<Integer> d1, Set<Integer> d2) {
        return ((d1.contains(0) && d2.contains(1)) || (d1.contains(1) && d2.contains(0)))
                && ((d1.contains(0) && d2.contains(4)) || (d1.contains(4) && d2.contains(0)))
                && ((d1.contains(0) && d2.contains(9)) || (d1.contains(9) && d2.contains(0)))
                && ((d1.contains(1) && d2.contains(6)) || (d1.contains(6) && d2.contains(1)))
                && ((d1.contains(2) && d2.contains(5)) || (d1.contains(5) && d2.contains(2)))
                && ((d1.contains(3) && d2.contains(6)) || (d1.contains(6) && d2.contains(3)))
                && ((d1.contains(4) && d2.contains(9)) || (d1.contains(9) && d2.contains(4)))
                && ((d1.contains(6) && d2.contains(4)) || (d1.contains(4) && d2.contains(6)))
                && ((d1.contains(8) && d2.contains(1)) || (d1.contains(1) && d2.contains(8)));
    }

    // Generate all permutations using backtracking
    // I need to find a better way to do this
    private void generateDice(boolean[] digits, Integer[] die, int dieIndex, List<Integer[]> dice) {
        if (dieIndex == DIE_SIZE) {
            dice.add(Arrays.copyOf(die, DIE_SIZE));
            return;
        }
        int high = -1;
        for (int i = 0; i < DIE_SIZE; i++) {
            if (die[i] != -1)
                high = Math.max(high, die[i]);
            else
                break;
        }
        for (int i = high + 1; i < digits.length; i++) {
            if (!digits[i]) {
                die[dieIndex] = i;
                digits[i] = true;
                generateDice(digits, die, dieIndex + 1, dice);
                digits[i] = false;
                die[dieIndex] = -1;
            }
        }
    }
}
