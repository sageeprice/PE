package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Nothing super clever, memo-ized backtracking. Just a pain to implement.
 * Answer: 464399
 */
public class Euler151 implements Problem {
    private static Map<List<Integer>, Double> countMap = new HashMap<>();

    @Override
    public String solve() {
        List<Integer> end = new ArrayList<>();
        end.add(5);
        countMap.put(end, 1.0);

        List<Integer> singleSheet = new ArrayList<>();
        singleSheet.add(1);
        // Subtract 2 for first and last drawing.
        double expected = drawAndIterate(singleSheet) - 2;
        // Get last 6 digits and round it.
        int answer = (int) (expected * 1_000_000 + .5);
        return String.valueOf(answer);
    }

    private static double drawAndIterate(List<Integer> sheets) {
        if (countMap.containsKey(sheets)) {
            return countMap.get(sheets);
        }
        double singleSheets = 0.0;
        for (int i = 0; i < sheets.size(); i++) {
            List<Integer> sheetsCopy = new ArrayList<>(sheets);
            int sheet = sheetsCopy.remove(i);
            for (int j = sheet + 1; j <= 5; j++) {
                sheetsCopy.add(j);
            }
            double iteratedResult = drawAndIterate(sheetsCopy);
            singleSheets += iteratedResult / sheets.size();
        }
        if (sheets.size() == 1) {
            singleSheets++;
        }
        countMap.put(sheets, singleSheets);
        return singleSheets;
    }
}
