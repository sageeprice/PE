package problems.impl;

import problems.Problem;

/**
 * Algebra time:
 *  j^2 - k^2 - l^2 = n
 *  (x+a)^2 - x^2 - (x-a)^2 = n
 *  4ax - x*x = n
 *  x * (4a - x) = n
 *  => x | n AND (4a-x)|n
 *  4a - x = n / x => 4a = x + n/x
 *
 *  Then notice constraints: x > 0, and x-a > 0 so 0 < a < x AND x < 4*a.
 *  From this we have an easy system for checking possible solutions for n.
 *  Just iterate through the factors of n, derive the value for a, and
 *  verify that all restrictions are met.
 *
 *  Answer: 4989
 */
public class Euler135 implements Problem {

  @Override
  public String solve() {
    int successes = 0;
    for (int n = 1; n < 1_000_000; n++) {
      int triples = 0;
      // TODO: optimize factor calculation.
      for (int j = 1; j <= Math.sqrt(n); j++) {
        int x = n / j;
        if (x * j == n) {
          if ((x + j) % 4 == 0) {
            int k = (x + j) / 4;
            if (k < x && 4 * k > x) {
              triples++;
            }
            if (k < j && 4 * k > j && x != j) {
              triples++;
            }
          }
          if (triples > 10) {
            triples = 0;
            break;
          }
        }
      }
      if (triples == 10) {
        successes++;
      }
    }
    return String.valueOf(successes);
  }
}
