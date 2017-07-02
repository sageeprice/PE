package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.List;

/**
 * Answer is 168, score is 1053389. Same as problem 114.
 */
public class Euler115 implements Problem {

    private static final int FILL = 50;
    private static final int LIMIT = 1_000_000;

    @Override
    public String solve() {
        List<List<Long>> fillCounts = new ArrayList<>();
        for (int i = 0; i <= FILL; i++) {
            List<Long> list = new ArrayList<>();
            list.add(1L);
            fillCounts.add(list);
        }

        int index = 0;
        for (int i = 1; fillCounts.get(0).get(i-1) + fillCounts.get(FILL).get(i-1) < LIMIT; i++) {
            fillCounts.get(0).add(fillCounts.get(0).get(i-1) + fillCounts.get(FILL).get(i-1));
            fillCounts.get(FILL).add(fillCounts.get(FILL).get(i-1) + fillCounts.get(FILL - 1).get(i-1));
            for (int j = 1; j < FILL; j++) {
                fillCounts.get(j).add(fillCounts.get(j-1).get(i-1));
            }
            index++;
        }
        return String.valueOf(FILL + index);
    }
}
