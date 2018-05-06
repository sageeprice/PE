package problems.impl;

import problems.Problem;

/**
 * Brute force - check all paths.
 *
 * Answer: 199740353/29386561536000
 */
public class Euler329 implements Problem {

  private static final boolean[] CROAKS =
      new boolean[] {
          true,
          true,
          true,
          true,
          false,
          false,
          true,
          true,
          true,
          false,
          true,
          true,
          false,
          true,
          false};

  @Override
  public String solve() {
    boolean[] sieve = sieveTo(500);

    long num = 0;
    for (int i = 1; i <= 500; i++) {
      num += exploreFrom(i, 0, sieve);
    }
    // 500 starting positions.
    long den = 3 * 500;
    for (int i = 1; i <= CROAKS.length - 1; i++) {
      den *= 6;
    }
    // Reduce to simplest terms, 5 omitted but w/e.
    while (num % 3 == 0) {
      num /= 3;
      den /= 3;
    }
    while (num % 2 == 0) {
      num /= 2;
      den /= 2;
    }
    return num + "/" + den;
  }

  private static long exploreFrom(int square, int hopsMade, boolean[] sieve) {
    // Multiplier.
    long m = sieve[square] == CROAKS[hopsMade] ? 2 : 1;
    if (hopsMade == CROAKS.length - 1) {
      return m;
    }

    // Always go to 499.
    if (square == sieve.length - 1) {
      return 2 * m * exploreFrom(499, hopsMade + 1, sieve);
    }
    // Always go to 2.
    if (square == 1) {
      return 2 * m * exploreFrom(2, hopsMade + 1, sieve);
    }
    return
        m *
            (exploreFrom(square + 1, hopsMade + 1, sieve)
                + exploreFrom(square - 1, hopsMade + 1, sieve));
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
