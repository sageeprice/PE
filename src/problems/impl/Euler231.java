package problems.impl;

import problems.Problem;

import java.util.HashMap;
import java.util.Map;

import static problems.EulerLib.primesTo;

/**
 * Let a = upper value, b = lower value, and f(x) be a function returning the sum of the terms in
 * the prime factorization of x. Then we want f(aCb). It is quick to prove that f(xy) = f(x) + f(y),
 * and f(x/y) = f(x) - f(y). Therefore:
 *   f(aCb) = f(a! / (b! * (a-b)!)) = f(a!) - f(b!) - f((a-b)!)
 *   f(x!) = f(x) + f(x-1) + f(x-2) + ... + f(2) + f(1)
 * Combining these two, we can use a sieve to rapidly calculate f(x!), and then just combine terms.
 *
 * Answer: 7526965179680
 */
public class Euler231 implements Problem {

  private static final int UP_BOUND = 20_000_000;
  private static final int LOW_BOUND = 5_000_000;

  @Override
  public String solve() {
    boolean[] primes = primesTo(UP_BOUND);
    long s1 = sumOfKeyTimesValue(factorialFactorsMap(primes, UP_BOUND));
    long s2 = sumOfKeyTimesValue(factorialFactorsMap(primes, LOW_BOUND));
    long s3 = sumOfKeyTimesValue(factorialFactorsMap(primes, UP_BOUND - LOW_BOUND));
    return String.valueOf(s1 - s2 - s3);
  }

  /**
   * Returns a map keyed by primes less than {@code lim}, with values equal to the number of times
   * the key is a factor of any number less than {@code lim}.
   */
  private static Map<Long, Long> factorialFactorsMap(boolean[] primes, long lim) {
    Map<Long, Long> factorCountMap = new HashMap<>();

    for (long i = 2; i < primes.length && i <= lim; i++) {
      if (primes[(int) i]) {
        factorCountMap.put(i, 0L);
        for (long j = i; j <= lim; j *= i) {
          long fCount = lim / j;
          factorCountMap.computeIfPresent(i, (unused, v) -> v + fCount);
        }
      }
    }
    return factorCountMap;
  }

  private static long sumOfKeyTimesValue(Map<Long, Long> factorMap) {
    return factorMap.entrySet().stream().mapToLong(e -> e.getValue() * e.getKey()).sum();
  }
}
