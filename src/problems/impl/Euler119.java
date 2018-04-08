package problems.impl;

import problems.Problem;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Simplest approach is to generate answers by raising small numbers to powers.
 * Then just sort and pick the 30th from the beginning.
 *
 * Answer: 248155780267521
 */
public class Euler119 implements Problem {

  @Override
  public String solve() {
    List<Long> answers = new ArrayList<>();
    // 150 is a "large enough" threshold arrived at from testing.
    // Realistically, unlikely for answer to be a 16 digit number...
    for (long i = 2; i < 150; i++) {
      long exp = i;
      // Enforce a limit so we do not have to deal with overflow.
      long limit = Long.MAX_VALUE / i;
      while (exp < limit) {
        exp *= i;
        if (getDigitSum(exp) == i) {
          answers.add(exp);
        }
      }
    }
    return String.valueOf(answers.stream().sorted().collect(Collectors.toList()).get(29));
  }

  /** Returns the sum of the digits of {@code x}. */
  private static long getDigitSum(long x) {
    long sum = 0;
    while (x > 0) {
      sum += x % 10;
      x /= 10;
    }
    return sum;
  }
}
