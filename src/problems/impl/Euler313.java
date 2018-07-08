package problems.impl;

import problems.Problem;

import static problems.EulerLib.primesTo;

/**
 * Two cases:
 *  m == n:
 *    Play around a bit, find that optimum is 8x-11.
 *    8x-11 = p*p ->  p*p = 5 mod 8. Impossible, so we're done.
 *  m > n:
 *    Play around some more, optimum is 6x+2y-13.
 *    Implies (p*p+13)/2 = 3x + y. Then we just find the set of valid y.
 *    3x + y = a -> y < a/4, so y <= (a-1)/4.
 *    Then divide by 3, and account for y > 1.
 *
 * Then just iterate over the primes in our range...
 *
 * Answer: 2057774861813004
 */
public class Euler313 implements Problem {

  @Override
  public String solve() {

    long sum = 0;
    boolean[] isPrime = primesTo(1_000_000);
    for (int p = 3; p < isPrime.length; p += 2) {
      if (isPrime[p]) {
        sum += countGrids(p);
      }
    }
    // Account for symmetry.
    return String.valueOf(sum * 2);
  }

  private static long countGrids(long p) {
    long a = p*p + 13;
    a /= 2;
    // 3x + y = a implies y < a / 4.
    long y = (a - 1) / 4;
    return (y+1)/3;
  }
}
