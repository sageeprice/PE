package problems.impl;

import problems.Problem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Brute force evaluation of each set. Recursively generate all subsets for
 * each input set and check that constraints are met. Early termination is
 * supported for when a subset sum appears more than once.
 *
 * Answer: 73702
 */
public class Euler105 implements Problem {

    private static final String FILE_NAME = "src/text/p105_sets.txt";

    @Override
    public String solve() {

        List<String> specialSets = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while ((line = reader.readLine()) != null) {
                specialSets.add(line);
            }
        } catch (Exception e) {
            throw new RuntimeException("Something broke... ", e);
        }

        return String.valueOf(
            specialSets.stream()
                .map(Euler105::toIntArray)
                .filter(Euler105::isSpecialSumSet)
                .map(Arrays::stream)
                .map(IntStream::sum)
                .mapToInt(Integer::intValue)
                .sum());
    }

    private static int[] toIntArray(String setStr) {
        String[] strEntries = setStr.split(",");
        int[] intSet = new int[strEntries.length];

        for (int i = 0; i < strEntries.length; i++) {
            intSet[i] = Integer.valueOf(strEntries[i]);
        }
        return intSet;
    }

    private static boolean isSpecialSumSet(int[] nums) {
        Map<Integer, Integer> nMaxSums = new HashMap<>();
        Map<Integer, Integer> nMinSums = new HashMap<>();

        if (!recursiveCheck(0, new ArrayList<>(), nums, new HashSet<>(), nMaxSums, nMinSums)) {
            return false;
        }

        for (int i = 1; i < nums.length - 1; i++) {
            if (nMaxSums.get(i) > nMinSums.get(i+1)) {
                return false;
            }
        }

        return true;
    }

    private static boolean recursiveCheck(
            int index,
            List<Integer> subset,
            int[] nums,
            Set<Integer> subsetSums,
            Map<Integer, Integer> nMaxs,
            Map<Integer, Integer> nMins) {
        if (index == nums.length) {
            int sum = subset.stream().mapToInt(Integer::intValue).sum();
            if (subsetSums.contains(sum)) {
                return false;
            } else {
                subsetSums.add(sum);
            }
            nMaxs.compute(subset.size(), (size, max) -> max == null || max < sum ? sum : max);
            nMins.compute(subset.size(), (size, min) -> min == null || min > sum ? sum : min);
            return true;
        }
        if (!recursiveCheck(index + 1, subset, nums, subsetSums, nMaxs, nMins)) {
            return false;
        }
        subset.add(nums[index]);
        if (!recursiveCheck(index + 1, subset, nums, subsetSums, nMaxs, nMins)) {
            return false;
        }
        subset.remove(subset.size() - 1);
        return true;
    }
}
