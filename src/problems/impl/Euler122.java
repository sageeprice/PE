package problems.impl;

import problems.Problem;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Use dynamic programming - generate and store all sequences of minimal length
 * to get to int x. Then for x+1, check pairs that sum to x, and find the set
 * of sequences with minimal intersection.
 *
 * Answer: 1582
 */
public class Euler122 implements Problem {

  private static final int LIMIT = 201;

  @Override
  public String solve() {
    List<List<List<Integer>>> sequenceTo = new ArrayList<>();
    sequenceTo.add(new ArrayList<>());
    List<Integer> one = new ArrayList<>();
    one.add(1);
    List<List<Integer>> oneSolutions = new ArrayList<>();
    oneSolutions.add(one);
    sequenceTo.add(oneSolutions);

    for (int i = 2; i < LIMIT; i++) {
      List<List<Integer>> iSeqs = new ArrayList<>();
      for (int j = 1; j <= i / 2; j++) {
        for (List<Integer> lower : sequenceTo.get(j)) {
          for (List<Integer> higher : sequenceTo.get(i - j)) {
            List<Integer> base = new ArrayList<>();
            base.addAll(lower);
            base.addAll(higher);
            base = base.stream().distinct().collect(Collectors.toList());
            base.add(i);

            if (iSeqs.isEmpty()) {
              iSeqs.add(base);
            } else if (iSeqs.get(0).size() > base.size()) {
              iSeqs = new ArrayList<>();
              iSeqs.add(base);
            } else if (iSeqs.get(0).size() == base.size()) {
              iSeqs.add(base);
            }
          }
        }
      }
      sequenceTo.add(iSeqs);
    }
    return String.valueOf(
        sequenceTo.stream()
            .skip(1)
            .map(seqs -> seqs.get(0).size() - 1)
            .mapToInt(Integer::intValue)
            .sum());
  }
}
