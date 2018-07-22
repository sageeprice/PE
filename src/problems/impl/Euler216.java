package problems.impl;

import problems.Problem;

/**
 * Suppose n = i*k ± j (WLOG say plus so I don't have to copy that character any more).
 * Then 2n*n-1 = 2*(i^2 * k^2 + 2*i*j*k + j*j) - 1
 * => 2*n*n-1 = 2*i^2*k^2 + 4*i*j*k + 2*j*j - 1.
 *
 * <p>Note that this last term (2*j*j - 1) is of the same form as the sequence we care about. It
 * should be clear that when i | 2*j*j - 1, 2*n*n - 1 is composite with a factor of i. This means
 * that when we find t(n) prime, t(t(n)*k + n) has factor t(n), and thus is not prime. From this
 * idea we can generate a sieve - we start by creating an array of all t(n), and whenever we
 * encounter a prime or not fully reduced t(n) we divide all entries of the form k*t(n) + n by t(n).
 * Once we have gone through the entire sieve, all remaining terms which have not been modified are
 * prime. Some work ignored here to explain why we have to also do this same thing when the value
 * within the sieve has been modified but not fully reduced to 1...
 *
 * This is also roughly O(n^2) so it runs on the order of seconds. Not sure how to speed it up
 * further at the moment.
 *
 * Answer: 5437849
 */
public class Euler216 implements Problem {

  private static final int LIMIT = 50_000_000;

  @Override
  public String solve() {

    long[] sieve = new long[3 * LIMIT / 2];
    for (int k = 2; k < sieve.length; k++) {
      sieve[k] = 2 * (long) k * (long) k - 1;
    }
    int primeCount = 0;
    for (long k = 2; k < sieve.length; k++) {
      long m = sieve[(int) k];
      if (m < sieve.length && m > k) {
        for (int i = (int) m - (int) k; i < sieve.length; i += m) {
          do {
            sieve[i] /= m;
          } while (sieve[i] % m == 0);
        }
        for (int i = (int) m + (int) k; i < sieve.length; i += m) {
          do {
            sieve[i] /= m;
          } while (sieve[i] % m == 0);
        }
      }
    }

    for (long k = 2; k <= LIMIT; k++) {
      long m = sieve[(int) k];
      if (m == 2 * k * k - 1) {
        primeCount++;
      }
    }

    return String.valueOf(primeCount);
  }
}
