package problems.impl;

import problems.Problem;

/**
 * Linearity of expectation applies nicely. Expected number of colors is equal
 * to the sum of the expectation of each color. Expectation here is just an
 * indicator variable (either 1 if it's there, 0 if not), so E = Probability.
 * Probability a color appears is just 1 - P(does not appear).
 *
 * Answer: 6.818741802
 */
public class Euler493 implements Problem {

  @Override
  public String solve() {
    double pNoColor = nCk(60, 20) / (double) nCk(70, 20);
    double pColor = 1 - pNoColor;
    return String.format("%1.9f", 7 * pColor);
  }

  /**
   * Calculates n choose k.
   *
   * @param n number of things
   * @param k number of choices
   * @return n! / (k! * (n-k)!)
   */
  private long nCk(long n, long k) {
    // Definitional special case.
    if (k == 0 || k == n) {
      return 1;
    }
    long product = n;
    for (long i = 2; i <= Math.min(k, n-k); i++) {
      product *= (n - i + 1);
      product /= i;
    }
    return product;
  }
}
