package problems.impl;

import problems.Problem;

/**
 * Can set up a recurrence to directly calculate the expected number of cars
 * needed to win given how many have been seen so far. This is slightly
 * complicated by 500, which pairs with itself. Adding an additional arg to
 * track 500 is simple enough though. Turns this into a very simple dynamic
 * programming problem. Algebra omitted here, but it is straightforward.
 *
 * Answer: 40.66368097
 */
public class Euler371 implements Problem {

  @Override
  public String solve() {

    // Array containing expected number of cars you'll need to see to win given
    // that you've seen x license plates > 0, and y indicating whether or not
    // 500 has been seen yet.
    double[][] expNeeded = new double[501][2];
    // This means you are done, so always 1.
    expNeeded[500][0] = 1;
    // Either next car ends it or doesn't, easy recurrence: S = 1+S/2 -> S = 2
    expNeeded[500][1] = 2;

    for (double i = 499; i >= 0; i--) {
      expNeeded[(int) i][1] = getExpectationWith500(expNeeded, i);
      expNeeded[(int) i][0] = getExpectationWithout500(expNeeded, i);
    }

    return String.format("%.8f", expNeeded[0][0]);
  }

  private double getExpectationWithout500(double[][] expNeeded, double i) {
    return (1000 + expNeeded[(int)i+1][1] + 2 * (499-i) * expNeeded[(int)i+1][0]) / (999-i);
  }

  private double getExpectationWith500(double[][] expNeeded, double i) {
    return 1000 * (1 + (1000 - 2*i) / 1000.0 * expNeeded[(int) i+1][1]) / (1000-i);
  }
}
