package problems.impl;

import problems.Problem;

import java.util.HashSet;
import java.util.Set;

/**
 * Brute force. Time is dominated by calculating primes.
 *
 * Answer: 2209
 */
public class Euler293 implements Problem {

  private static final int LIMIT = 1_000_000_000;

  @Override
  public String solve() {
    // Add some buffer in case the following prime is larger than LIMIT.
    boolean[] sieve = sieveTo(LIMIT + 1_000);
    Set<Integer> pseudoFortunates = new HashSet<>();
    for (int i = 2; i < LIMIT; i += 2) {
      if (isAdmissible(i, sieve)) {
        pseudoFortunates.add(getPseudoFortunate(i, sieve));
      }
    }
    for (int i = 2; i < LIMIT; i *= 2) {
      pseudoFortunates.add(getPseudoFortunate(i, sieve));
    }
    return String.valueOf(pseudoFortunates.stream().mapToInt(Integer::intValue).sum());
  }

  private static boolean isAdmissible(int x, boolean[] primes) {
    while (x % 2 == 0) {
      x /= 2;
    }
    for (int i = 3; x != 1; i += 2) {
      if (!primes[i]) {
        continue;
      }
      if (x % i != 0) {
        return false;
      }
      while (x % i == 0) {
        x /= i;
      }
    }
    return true;
  }

  private static int getPseudoFortunate(int x, boolean[] sieve) {
    for (int i = x+2; ; i++) {
      if (sieve[i]) {
        return i - x;
      }
    }
  }

  private static boolean[] sieveTo(int n) {
    boolean[] sieve = new boolean[n+1];
    if (n >= 2) {
      sieve[2] = true;
    }
    for (int i = 3; i <= n; i+=2) {
      sieve[i] = true;
    }
    for (int i = 3; i <= Math.sqrt(n); i+= 2) {
      if (sieve[i]) {
        for (int j = i*i; j <= n; j += i * 2) {
          sieve[j] = false;
        }
      }
    }

    return sieve;
  }

}
