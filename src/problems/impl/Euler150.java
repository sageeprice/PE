package problems.impl;

import problems.Problem;

/**
 * Used dynamic programming. Defining triangles as the root location, then how many rows beyond to
 * extend. So f(0, 0, 1) is the sub-triangle sum of the first two rows of the triangle. Then each
 * entry can be expressed in terms of smaller triangles with the following relation:
 *   f(i, j, k) = f(i+1, j, k-1) + f(i+1, j+1, k-1) - f(i+2, j+1, k-2) + s(i, j)
 * where the negative term is replaced with 0 in the event k < 2, and s(i, j) is the value of the
 * entry at location (i, j). This is basically an application of the Principle of Inclusion/
 * Exclusion. We add two sub-triangles, subtract the overlap, and finally add the root value.
 *
 * Answer: -271248680
 */
public class Euler150 implements Problem {

  private static final int ROWS = 1000;

  @Override
  public String solve() {
    int[][] triangle = new int[ROWS][];
    for (int i = 0; i < ROWS; i++) {
      triangle[i] = new int[i+1];
    }

    // Construct initial triangle.
    int t = 0;
    for (int i = 0; i < 1000; i++) {
      for (int j = 0; j <= i; j++) {
        t = (615949*t + 797807) & ((1<<20) - 1);
        triangle[i][j] = t - (1<<19);
      }
    }

    // Generate array to track sub-triangle sums. Populate base cases.
    long[][][] sums = new long[ROWS][][];
    for (int i = 0; i < ROWS; i++) {
      sums[i] = new long[i+1][];
      for (int j = 0; j <= i; j++) {
        sums[i][j] = new long[ROWS - i];
        sums[i][j][0] = triangle[i][j];
      }
    }

    // Dynamic programming from above.
    long least = Long.MAX_VALUE;
    for (int i = ROWS - 2; i >= 0; i--) {
      for (int j = 0; j <= i; j++) {
        for (int k = 1; k < ROWS - i; k++) {
          long s = sums[i + 1][j][k - 1] + sums[i + 1][j + 1][k - 1];
          s -= (k > 1) ? sums[i+2][j+1][k-2] : 0;
          s += triangle[i][j];
          sums[i][j][k] = s;
          // Store result when a new minimum is seen.
          if (s < least) {
            least = s;
          }
        }
      }
    }
    return String.valueOf(least);
  }
}
