package problems.impl;

import problems.Problem;

import static problems.EulerLib.primesTo;

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
    boolean[] sieve = primesTo(500);

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
}
