package problems.impl;

import problems.Problem;

import java.math.BigInteger;

/**
 * Work in mod 250, simplify everything. Only need to keep track of the count of subsets which sum
 * to each desired value. Then use dynamic programming... Could but won't optimize.
 *
 * Answer: 1425480602091519
 */
public class Euler250 implements Problem {

  private static final int LIM = 250;
  private static final BigInteger BIG_MOD = BigInteger.valueOf(LIM);
  private static final long LAST_SIXTEEN = (long) Math.pow(10, 16);

  @Override
  public String solve() {
    long[] subsetsSummingTo = new long[LIM];
    long[] nextSums;
    subsetsSummingTo[0] = 1; // Empty set.
    for (int i = 0; i < LIM; i++) {
      // 1001 iterations since 250250 = 250 * 1001
      for (int j = 1; j <= 1001; j++) {
        BigInteger m = BigInteger.valueOf(i).modPow(BigInteger.valueOf(LIM * j + i), BIG_MOD);
        nextSums = subsetsSummingTo.clone();
        for (int k = 0; k < LIM; k++) {
          nextSums[k] += subsetsSummingTo[(LIM + k - m.intValue()) % LIM];
          nextSums[k] %= LAST_SIXTEEN;
        }
        subsetsSummingTo = nextSums;
      }
    }

    // Exclude empty set.
    return String.valueOf(subsetsSummingTo[0] - 1);
  }
}
