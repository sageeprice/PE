package problems.impl;

import problems.Problem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
        return smallSubsetsAreSmaller(nums)
            && containsRepeatedSubsetSum(0, new ArrayList<>(), nums, new HashSet<>());

    }

    /** Returns false when {@code nums} contains subsets B and C such that |B| < |C| but sum(b in B) > sum(c in C). */
    private static boolean smallSubsetsAreSmaller(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i <= nums.length / 2; i++) {
            int lowSum = 0;
            int highSum = 0;
            for (int j = 0; j <= i; j++) {
                lowSum += nums[j];
                highSum += nums[nums.length - j - 1];
            }
            if (lowSum + nums[i+1] < highSum) {
                return false;
            }
        }
        return true;
    }

    private static boolean containsRepeatedSubsetSum(
            int index,
            List<Integer> subset,
            int[] nums,
            Set<Integer> subsetSums) {
        if (index == nums.length) {
            int sum = subset.stream().mapToInt(Integer::intValue).sum();
            if (subsetSums.contains(sum)) {
                return false;
            } else {
                subsetSums.add(sum);
                return true;
            }
        }
        // Case I: integer at index is included.
        subset.add(nums[index]);
        if (!containsRepeatedSubsetSum(index + 1, subset, nums, subsetSums)) {
            return false;
        }
        subset.remove(subset.size() - 1);
        // Case II: integer at index is excluded.
        return containsRepeatedSubsetSum(index + 1, subset, nums, subsetSums);
    }
}
