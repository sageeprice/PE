package problems.impl;

import problems.Problem;

/**
 * Values start alternating, sum rapidly converges...
 *
 * Answer: 1.710637717
 */
public class Euler197 implements Problem {

  @Override
  public String solve() {
    double x = -1;
    double sum = 0;
    for (int i = 1; i < 1000; i++) {
      double y = Math.floor(Math.pow(2, 30.403243784 - x*x)) * .000000001;
      sum = x + y;
      x = y;
    }
    return String.format("%.9f", sum);
  }
}
