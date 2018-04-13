package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Do it dynamically, by generating rings and adding them to existing results.
 *
 * Answer: 209566
 */
public class Euler174 implements Problem {

  private static final int LIMIT = 1_000_000;

  @Override
  public String solve() {

    int[] tilings = new int[LIMIT + 1];
    List<Integer> gaps = new ArrayList<>();

    // Evens
    tilings[12] = 1;
    gaps.add(12);

    for (int i = 6; i <= 250_000; i += 2) {
      int tileRing = (i + i - 2) * 2;
      gaps = gaps.stream()
              .map(gap -> gap + tileRing)
              .filter(gap -> gap <= LIMIT)
              .collect(Collectors.toList());
      if (tileRing <= LIMIT) {
        gaps.add(tileRing);
      }
      gaps.forEach(gap -> tilings[gap]++);
    }

    // Odds
    tilings[8]++;
    gaps.add(8);

    for (int i = 5; i <= 250_001; i += 2) {
      int tileRing = (i + i - 2) * 2;
      gaps = gaps.stream()
              .map(gap -> gap + tileRing)
              .filter(gap -> gap <= LIMIT)
              .collect(Collectors.toList());
      if (tileRing <= LIMIT) {
        gaps.add(tileRing);
      }
      gaps.forEach(gap -> tilings[gap]++);
    }

    int count = 0;
    for (int i = 1; i <= LIMIT; i++) {
      if (tilings[i] != 0 && tilings[i] <= 10) {
        count++;
      }
    }

    return String.valueOf(count);
  }
}
