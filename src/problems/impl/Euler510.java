package problems.impl;

import problems.Problem;

import java.util.HashSet;
import java.util.Set;

/**
 * Radii must fit 1/sqrt(a) + 1/sqrt(b) = 1/sqrt(c). Since a, b, c in Z, we set
 * a = x*x, b = y*y, c = z*z. Then xy = xz+yz -> z = xy/(x+y). Any solution to
 * this is a valid solution, and from it we can work back to the radii.
 * Further, any multiple of a valid solution is a valid solution, so we add
 * each multiple as well.
 *
 * Answer: 315306518862563689
 */
public class Euler510 implements Problem {

  private static final long LIMIT = 1_000_000_000;
  private static final long LIMIT_ROOT = (long) Math.sqrt(LIMIT);

  @Override
  public String solve() {
    // Keep the set of valid ratios so we don't double-count.
    // Could just store each (a,b) pair, but I'm lazy and this works.
    Set<String> ratios = new HashSet<>();
    long s = 0;

    for (long b = 1; b <= LIMIT_ROOT; b++) {
      for (double a = 1; a <= b; a++) {
        if (b*a % (b+a) == 0 && !ratios.contains(getRatioStr(b, a))) {
          ratios.add(getRatioStr(b, a));
          long c = (long) (b*a / (b+a));
          long radiiSum = (long) (b*b + a*a + c*c);
          // How many valid multiples there are.
          long d = LIMIT / (b*b);
          // Add radiiSum and all multiples thereof.
          s += radiiSum * d * (d+1) / 2;
        }
      }
    }

    return String.valueOf(s);
  }

  private String getRatioStr(long b, double a) {
    return String.format("%5.9f", b / a);
  }
}
