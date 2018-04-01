package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * It's possible to recursively generate the worst cases starting from an
 * ordered set of carriages by performing worst case flips at each point.
 * Sample for 6 carriages:
 *
 *  012345
 *  012354
 *  012453
 *  012435
 *  015342 (Note, order is fixed to here).
 *  1- 015243    2- 015324
 *
 *  Now: above, at each point where the first n are sorted, we flip the last
 *  sorted carriage to the end. That carriage must then be flipped between the
 *  sorted results and the end in order to ensure that it will take two flips
 *  to sort that carriage. So when 2 is at the end, it may swap with either the
 *  3 or 4 numbered carriage. If swapped with 5, we'd be back to the start.
 *  Similarly, if not swapped with anything then that is one fewer swap needed
 *  for Simple Simon's algorithm. By exploring each branch this yields, and
 *  continuing until the 0 carriage is between the first and last carriages, we
 *  are able to generate every worst case ordering. Then just sort and pick.
 *
 *  Answer: CAGBIHEFJDK
 */
public class Euler336 implements Problem {

    private static final int LIMIT = 11;
    private static final String[] LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};

    @Override
    public String solve() {
        int[] orderedCarriages = new int[LIMIT];
        for (int i = 0; i < LIMIT; i++) {
            orderedCarriages[i] = i;
        }
        return getMaximixes(orderedCarriages).get(2010);
    }

    private static List<String> getMaximixes(int[] carriages) {
        List<String> maximixes = new ArrayList<>();

        // Perform mandatory reversals.
        reverseFrom(carriages.length - 2, carriages);
        reverseFrom(carriages.length - 3, carriages);
        reverseFrom(carriages.length - 2, carriages);

        generateMaximixes(LIMIT - 3, carriages, maximixes);
        return maximixes.stream().sorted().collect(Collectors.toList());
    }

    private static void generateMaximixes(int sortedCount, int[] carriages, List<String> maximixes) {
        if (sortedCount == 0) {
            maximixes.add(asString(carriages));
            return;
        }
        int[] s1Carriages = Arrays.copyOf(carriages, carriages.length);
        reverseFrom(sortedCount - 1, s1Carriages);
        for (int i = sortedCount; i < carriages.length - 1; i++) {
            int[] s2Carriages = Arrays.copyOf(s1Carriages, carriages.length);
            reverseFrom(i, s2Carriages);
            generateMaximixes(sortedCount - 1, s2Carriages, maximixes);
        }

    }

    private static void reverseFrom(int index, int[] carriages) {
        for (int i = index, j = 0; i < (carriages.length + index) / 2; i++, j++) {
            int t = carriages[i];
            carriages[i] = carriages[carriages.length - j - 1];
            carriages[carriages.length - j - 1] = t;
        }
    }

    private static String asString(int[] carriages) {
        StringBuilder str = new StringBuilder();
        for (int carriage : carriages) {
            str.append(LETTERS[carriage]);
        }
        return str.toString();
    }
}
