package problems.impl;

import problems.Problem;

/**
 * Easiest way is to count the number of square-full numbers, then subtract. Can do this by adding
 * up the number of multiples of the square of each prime, e.g. 2^2 = 4 => 4, 8, 12, 16...
 *
 * <p>However, in doing this we sometimes double count things like (2*3)^2. We can apply principle
 * of inclusion-exclusion to account for these, adding/subtracting based on the number of unique
 * square factors each number has. See {@link #primeFactorCounts} for implementation details.
 *
 * Answer: 684465067343069
 */
public class Euler193 implements Problem {

  private static final long LIMIT = 1L << 50;

  @Override
  public String solve() {

    int[] primeFactors = primeFactorCounts((int) Math.sqrt(LIMIT));
    long squareFullCount = 0;
    for (long i = 2; i < primeFactors.length; i++) {
      long factorCount = primeFactors[(int) i];
      if (factorCount < 0) {
        continue;
      }

      long squareMultiples = LIMIT / (i * i);
      if (factorCount % 2 == 1) {
        squareFullCount += squareMultiples;
      } else if (factorCount % 2 == 0) {
        squareFullCount -= squareMultiples;
      }
    }
    return String.valueOf(LIMIT - squareFullCount);
  }

  /**
   * Returns an {@code int[]} in which the value of each entry in some way indicates the number of
   * prime factors of the index.
   *
   * <ol>
   *   <li>Value of {@code 0} indicates the index is prime.
   *   <li>Value of {@code -1} indicates the index is the multiple of the square of a prime.
   *   <li>Value {@code > 0} indicates the number of unique prime factors of the index.
   * </ol>
   */
  private static int[] primeFactorCounts(int lim) {
    int[] result = new int[lim+1];
    for (int i = 2; i < result.length; i++) {
      // When prime...
      if (result[i] == 0) {
        result[i] = 1;
        // Go through and mark off square multiples...
        // Note casting i to long -- at some point i*i > 2*Integer.MAX_VALUE, so if we do not use
        // longs then it would silently loop back and modify further entries erroneously.
        for (long j = (long) i * (long) i; j < result.length && j > 0; j += i * i) {
          if (j >= result.length) {
            break;
          }
          result[(int) j] = -1;
        }
        // Increment factor count for non-square multiples.
        for (int j = i + i; j < result.length && j > 0; j += i) {
          if (result[j] != -1) {
            result[j]++;
          }
        }
      }
    }
    return result;
  }
}
