package euler100To199.euler110To119;

import java.util.ArrayList;
import java.util.List;

/**
 * Answer is 168, score is 1053389. Same as problem 114.
 */
public class Problem115CountingBlocks2 {

    private static final int FILL = 50;
    private static final int LIMIT = 1_000_000;

    public static void main(String[] args) {

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
        System.out.println("Done at index " + (FILL + index) + " with sum " + (fillCounts.get(0).get(index) + fillCounts.get(FILL).get(index)));
    }
}
