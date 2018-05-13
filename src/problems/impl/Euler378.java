package problems.impl;

import problems.Problem;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Let's pretend this solution never happened. There is a more efficient approach.
 *
 * Answer: 147534623725724718
 */
public class Euler378 implements Problem {

//  private static final int LIMIT = 1000;
  private static final int LIMIT = 60_000_000;

  @Override
  public String solve() {
    long[] factors = divisorSieve(LIMIT+1);
    // For each entry, account for the fact each triangular number is divided by 2.
    for (int i = ((factors.length - 1) / 2) * 2; i >= 2; i -= 2) {
      factors[i] = factors[i/2];
    }
    // Renaming for clarity.
    long[] triangleFactors = factors;
    for (int i = 1; i <= LIMIT; i++) {
      triangleFactors[i] *= triangleFactors[i+1];
    }
    Map<Long, Integer> numsWithFactor = new HashMap<>();
    for (int i = 1; i < triangleFactors.length; i++) {
      numsWithFactor.compute(triangleFactors[i], (k, v) -> v == null ? 1 : v++);
    }

    Map<Long, Long> inversions = new HashMap<>();
    numsWithFactor = new HashMap<>();
    BigInteger tripleCounts = BigInteger.ZERO;
    for (int i = LIMIT; i >= 1; i--) {
      long f = triangleFactors[i];
      numsWithFactor.compute(f, (k, v) -> v == null ? 1 : v + 1);
      long invs = inversions.getOrDefault(f, 0L);
      invs += numsWithFactor.entrySet().stream().filter(e -> e.getKey() < f).mapToLong(Map.Entry::getValue).sum();
      inversions.put(f, invs);
      tripleCounts = tripleCounts.add(BigInteger.valueOf(inversions.entrySet().stream().filter(e -> e.getKey() < f).mapToLong(Map.Entry::getValue).sum()));
    }
    return String.valueOf(tripleCounts).substring(4);
  }

  private static long[] divisorSieve(int x) {
    long[] divisors = new long[x+1];
    for (int i = 1; i <= x; i++) {
      divisors[i] = 1;
    }
    for (int i = 2; i <= x; i++) {
      if (divisors[i] == 1) {
        divisors[i] = 2;
        for (int j = i + i; j <= x; j += i) {
          int p = 1;
          int m = i;
          while (j % m == 0) {
            p++;
            m *= i;
          }
          divisors[j] *= p;
        }
      }
    }
    return divisors;
  }
}
