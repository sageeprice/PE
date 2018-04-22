package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.List;

/**
 * Fraction will be a repeating decimal if the denominator has any factors
 * besides 2 and 5 (which are not factors of the numberator). Small enough
 * numbers here that it is better to check the max by evaluating the log of
 * (x/k)^k rather than direct evaluation.
 *
 * Answer: 48861552
 */
public class Euler183 implements Problem {

  private static final int LIMIT = 10000;

  @Override
  public String solve() {

    List<Integer> factorMap = new ArrayList<>();
    factorMap.add(1);
    factorMap.add(1);
    for (int i = 2; i <= LIMIT; i++) {
      int f = i;
      while (f % 2 == 0) {
        f /= 2;
      }
      while (f % 5 == 0) {
        f /= 5;
      }
      factorMap.add(f);
    }

    int prior = 2;
    long sum = 0;
    for (double i = 5; i <= LIMIT; i++) {
      double best = 0;
      int index = 0;
      for (int j = prior; j <= i/2; j++) {
        double x = j * Math.log10(i/j);
        best = best > x ? best : x;
        index = best > x ? index : j;
      }
      if ((int) i % factorMap.get(index) == 0) {
        sum -= i;
      } else {
        sum += i;
      }
      prior = index;
    }
    return String.valueOf(sum);
  }
}
