package problems.impl;

import problems.Problem;

/**
 * Same as {@link Euler135} with some additional reasoning after the algebra.
 * Since 4a = n/x + x = (n+x*x)/x, a = (n+x*x)/4x. We know x|n, so either
 * 4|x => 4|n or 4|(x*x+n) => n = 3 mod 4. Tiny step further shows a must be
 * odd when n is odd, and even when n is even.
 *
 * Further refinements can be done to show n has a special relationship to
 * primes, but I didn't get that far since the initial analysis was sufficient.
 *
 * Answer: 2544559
 */
public class Euler136 implements Problem {

  @Override
  public String solve() {
    int successes = 0;
    for (int n = 1; n < 50_000_000; n++) {
      int res = n % 4;
      if (res == 1 || res == 2) {
        continue;
      }
      if (n % 1000000 == 0) {
        System.out.println(n);
      }
      int triples = 0;
      for (int j = res  == 0 ? 2 : 1; j <= Math.sqrt(n); j += 2) {
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
          if (triples > 1) {
            triples = 0;
            break;
          }
        }
      }
      successes += triples;
    }
    return String.valueOf(successes);
  }
}
