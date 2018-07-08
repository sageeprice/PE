package problems.impl;

import problems.Problem;

import static problems.EulerLib.primesTo;

/**
 * Sum of all needed primes is small enough to fit in memory, so use an array to keep track of
 * the subset sums. Then just use dynamic programming.
 *
 * Answer: 9275262564250418
 */
public class Euler249 implements Problem {

  private static final long LAST_SIXTEEN = (long) Math.pow(10, 16);

  @Override
  public String solve() {
    // Sum of all primes less than 5000 is 1,548,136.
    long[] subsetSums = new long[1_600_000];
    subsetSums[0] = 1;
    boolean[] sieve = primesTo(1_600_000);
    for (int i = 2; i < 5000; i++) {
      if (sieve[i]) {
        for (int j = subsetSums.length - 1; j >= i; j--) {
          subsetSums[j] += subsetSums[j - i];
          subsetSums[j] %= LAST_SIXTEEN;
        }
      }
    }
    long sum = 0;
    for (int i = 2; i < subsetSums.length; i++) {
      if (sieve[i]) {
        sum += subsetSums[i];
        sum %= LAST_SIXTEEN;
      }
    }
    return String.valueOf(sum);
  }



}
