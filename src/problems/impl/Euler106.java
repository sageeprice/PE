package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Big thing to note: if condition (II) is met, then equality check is only needed when two subsets are of the same
 * size. This means that we only have to consider subsets of the same size.
 * Algorithm:
 *  1. For each k <= 12 s.t. 2|k.
 *  2. Generate all partitions of a set of size k into two sets.
 *  3. Filter these partitions into those which divide k evenly.
 *  4. Filter further to partitions beginning with first element in first partition (eliminate redundancy).
 *  5. Filter these into sets requiring an equality check (see {@link #requiresValidation}.
 *  6. Multiply by 12 choose k to determine how elements in neither subset may be distributed.
 *  7. Add it all up.
 *
 * Note that there are more clever ways to do this. In particular, the conditions defined are equivalent to counting the
 * number of paths through a k by k grid moving only right and down such that a path starts with a step down, then at
 * some point passes above/to the right of the main diagonal.
 *
 * Answer: 21384
 */
public class Euler106 implements Problem {

  @Override
  public String solve() {
    long totalToCheck = 0;
    for (int i = 4; i <= 12; i += 2) {
      long splitsNeedingValidationCount =
          evenSplitSets(i).stream().filter(Euler106::requiresValidation).count();

      totalToCheck += nCk(12, i) * splitsNeedingValidationCount;
    }
    return String.valueOf(totalToCheck);
  }

  /**
   * Returns all sets of size {@code setSize} which are split into two equally sized subsets. A set is represented as a
   * binary sequence of 1 or 2, where the number indicates the subset to which an element belongs. As there exists a
   * bimorphism between these sets where swapping 1 with 2 and vice versa yields functionally the same set, only sets
   * which begin with a 1 are returned.
   */
  private static Set<List<Integer>> evenSplitSets(int setSize) {
    Set<List<Integer>> subsets = new HashSet<>();
    recursivelyBuild(setSize, subsets, new ArrayList<>());
    return subsets.stream()
        .filter(s -> s.stream().filter(g -> g == 1).count() == setSize / 2)
        .filter(Euler106::startsWithOne)
        .collect(Collectors.toSet());
  }

  /**
   * Recursively constructs all sets of size {@code setSize} with each element assigned to one of two subsets.
   *
   * @param setSize the size of the complete set
   * @param subsets accumulator for completely built sets
   * @param subsetList the set under construction
   */
  private static void recursivelyBuild(int setSize, Collection<List<Integer>> subsets, List<Integer> subsetList) {
    // When set is of appropriate size, add it to the collection.
    if (subsetList.size() == setSize) {
      subsets.add(new ArrayList<>(subsetList));
      return;
    }
    // Recursive check for sets with 1 added as next element.
    subsetList.add(1);
    recursivelyBuild(setSize, subsets, subsetList);
    subsetList.remove(subsetList.size() - 1);
    // Recursive check for sets with 2 added as next element.
    subsetList.add(2);
    recursivelyBuild(setSize, subsets, subsetList);
    subsetList.remove(subsetList.size() - 1);
  }

  private static boolean startsWithOne(List<Integer> splitSet) {
    return splitSet.get(0) == 1;
  }

  /**
   * Returns whether the provided {@code splitSet} requires equality validation per the problem description.
   *
   * <p>Determination of this status is derived from the distribution of elements belonging to subset one. Input sets
   * are assumed to begin with a 1. In such a case, an equality check is needed whenever the final 2k-1 elements contain
   * at least k elements belonging to subset 1 for some k < |{@code splitSet}| / 2. This relationship holds because when
   * violated, for every element of subset 1 there exists an element of subset 2 appearing later in the sequence.
   * Because the provided set is sorted, if we pair these elements then the sum of the set of elements in set 1 will be
   * trivially less than the sum of the set of elements comprising set 2.
   */
  private static boolean requiresValidation(List<Integer> splitSet) {
    int size = splitSet.size();
    Collections.reverse(splitSet);
    for (int i = 1; i < size / 2; i++) {
      int endSetLength = 2 * i - 1;
      int oneCount = 0;
      for (int j = 0; j < endSetLength; j++) {
        if (splitSet.get(j) == 1) {
          oneCount++;
        }
      }
      if (oneCount == i) {
        return true;
      }
    }
    return false;
  }

  /**
   * Calculates n choose k.
   *
   * @param n number of things
   * @param k number of choices
   * @return n! / (k! * (n-k)!)
   */
  private long nCk(long n, long k) {
    // Definitional special case.
    if (k == 0 || k == n) {
      return 1;
    }
    long product = n;
    for (long i = 2; i <= Math.min(k, n-k); i++) {
      product *= (n - i + 1);
      product /= i;
    }
    return product;
  }
}
