package problems.impl;

import problems.Problem;

public class Euler249 implements Problem {

  private static final long LAST_SIXTEEN = (long) Math.pow(10, 16);

  @Override
  public String solve() {
    // Sum of all primes less than 5000 is 1,548,136.
    long[] subsetSums = new long[1_600_000];
    subsetSums[0] = 1;
    boolean[] sieve = sieveTo(1_600_000);
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



  private static boolean[] sieveTo(int n) {
    boolean[] sieve = new boolean[n+1];
    // initial conditions
    if (n >= 2) {
      sieve[2] = true;
    }
    // only need to check odds
    for (int i = 3; i <= n; i+=2) {
      sieve[i] = true;
    }
    // Composites larger than sqrt(n) must have a smaller factor, and are thus eliminated
    // already. Therefore we need only proceed to check  values up through sqrt(n)
    for (int i = 3; i <= Math.sqrt(n); i+= 2) {
      if (sieve[i]) {
        /*
         * Since you'll forget this Sage:
         *  - if it's less than i*i more than i, it'll be covered by a smaller prime
         *  - all primes > 2 are odd, so only need to check every other above i*i
         *  One improvement that could be made: technically only need to check
         *  of the form 6k+1 and 6k-1 (k an int), as 6k+3 is multiple of 3.
         */
        for (int j = i*i; j <= n; j += i * 2) {
          sieve[j] = false;
        }
      }
    }

    return sieve;
  }
}
