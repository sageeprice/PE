package problems.impl;

import problems.Problem;

/**
 * So for any Gaussian Integer z = a + bi, z|a*a + b*b. Let g = gcd(a, b). Then
 * z = g(a' + b'i), and we say y := z/g = a' + b'i. This implies y|(a*a + b*b)/g.
 * Thus we only need to consider Gaussian Integers where gcd(a, b) = 1. Call such
 * values minimized Gaussian Integers. Then for any minimized Gaussian Integer x,
 * the smallest integer which x divides is xx*, and x also evenly divides each
 * multiple of xx*. From this we do some algebra, and we're done.
 *
 * Note -- current solution is a little slow, primarily due to gcd calculation
 * and repeated division. The latter could be cached if this needs to speed up...
 *
 * Answer: 17971254122360635
 */
public class Euler153 implements Problem {

  private static final int LIMIT = 100_000_000;

  @Override
  public String solve() {
    long s = 0;
    // Add in the integer factors.
    for (int i = 1; i <= LIMIT; i++) {
      s += i * (LIMIT / i);
    }

    for (int i = 1; i <= Math.sqrt(LIMIT); i++) {
      for (int j = 1; j <= i; j++) {
        if (gcd(i, j) != 1) {
          continue;
        }
        long x = (long) i*i + j*j;
        if (x > LIMIT) {
          continue;
        }
        long t = 2L * i;
        if (i != j) {
          t += 2 * j;
        }
        for (int k = 1; ; k++) {
          if (k*x > LIMIT) {
            break;
          }
          s += t * k * (LIMIT / (k * x));
        }
      }
    }

    return String.valueOf(s);
  }

  // Efficient gcd calculation, assumes a > b
  private static long gcd(long a, long b) {
    while (a % b != 0) {
      long t = a;
      a = b;
      b = t % a;
    }
    return b;
  }
}
