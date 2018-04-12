package problems.impl;

import problems.Problem;

/**
 * We want to find the prime factorization of R := R(10^9). Fix p a prime.
 * Then R is relatively prime to p. R = sum(10^x) from x = 1 to 10^9 - 1.
 * 10 is a generator for the Z/Z^p, so every p-1 consecutive terms of the sum
 * will sum to 0 mod p. Then there are two cases to check:
 *   case a: R = 0 mod p-1 -> p|R
 *   case b: R = x mod p-1 where x > 0. Then R is congruent to R(x) mod p-1.
 *
 * Answer: 843296
 */
public class Euler132 implements Problem {

  private static final long N = 1_000_000_000;

  @Override
  public String solve() {
    // Negligible speed up, nicely chosen ceiling.
    boolean[] sieve = sieveTo(160_002);
    int count = 0;
    long pSum = 0;
    // 2, 3, 5 are impossible, and require more logic. Skip them.
    for (int i = 7; i < sieve.length; i++) {
      long s = 0;
      if (sieve[i]) {
        long m = N % (i-1);
        if (m != 0) {
          long x = 1;
          s += x;
          for (int j = 2; j <= m; j++) {
            x = (x * 10) % i;
            s = (s + x) % i;
          }
        }
        if (s == 0) {
          pSum += i;
          if (++count == 40)
            return String.valueOf(pSum);
        }
      }
    }
    return null;
  }

  /**
   * Returns a prime sieve of the first n integers or containing the first
   * pCount primes - whichever is smaller.
   */
  private static boolean[] sieveTo(int n) {
    boolean[] sieve = new boolean[n + 1];
    // initial conditions
    if (n >= 2) {
      sieve[2] = true;
    }
    // only need to check odds
    for (int i = 3; i <= n; i += 2) {
      sieve[i] = true;
    }
    // Composites larger than sqrt(n) must have a smaller factor, and are thus eliminated
    // already. Therefore we need only proceed to check values up through sqrt(n).
    for (int i = 3; i <= Math.sqrt(n); i += 2) {
      if (sieve[i]) {
        /*
         * Since you'll forget this Sage:
         *  - if it's less than i*i more than i, it'll be covered by a smaller prime
         *  - all primes > 2 are odd, so only need to check every other above i*i
         *  One improvement that could be made: technically only need to check
         *  of the form 6k+1 and 6k-1 (k an int), as 6k+3 is multiple of 3.
         */
        for (int j = i * i; j <= n; j += i * 2) {
          sieve[j] = false;
        }
      }
    }
    return sieve;
  }
}
