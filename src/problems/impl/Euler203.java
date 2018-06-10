package problems.impl;

import problems.Problem;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Brute force with simple logic about which primes to check could be factors.
 *
 * Answer: 34029210557338
 */
public class Euler203 implements Problem {

  /**
   * The primes to check when checking to see if a number is square free. There is no need to go
   * higher than 47 because we only look at he first 51 rows of the triangle, so the only
   * numbers which may be factors are less than 50. Additionally, the only way a number could have
   * a square factor is when it is greater than the square - so we only have to look up to 7.
   */
  private static long[] PRIMES =
      new long[] {2, 3, 5, 7};

  @Override
  public String solve() {
    Set<Long> squareFrees = new HashSet<>();

    for (long i = 0; i < 51; i++) {
      for (long j = 0; j <= i / 2; j++) {
        long x = nCk(i, j);
        if (!squareFrees.contains(x) && isSquareFree(x)) {
          squareFrees.add(x);
        }
      }
    }
    return String.valueOf(squareFrees.stream().mapToLong(x -> x).sum());
  }

  private static boolean isSquareFree(long x) {
    return Arrays.stream(PRIMES).noneMatch(p -> x % (p*p) == 0);
  }

  /**
   * Calculates n choose k
   * @param n number of things
   * @param k number of choices
   * @return n! / (k! * (n-k)!)
   */
  private long nCk(long n, long k) {
    if (n == k || k == 0) {
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
