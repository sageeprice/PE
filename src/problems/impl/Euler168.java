package problems.impl;

import problems.Problem;

/**
 * https://oeis.org/A034089 for the hard part, then account for numbers which are just a
 * repeated string of the same digit.
 *
 * Answer: 59206
 */
public class Euler168 implements Problem {

  private static final int MOD = 100_000;

  @Override
  public String solve() {
    // The case where the number is the same digit repeated some number of times.
    long addOn = 45 * (11 + 111 + 1_111 + 11_111 * 96) % MOD;
    long sum = 0;
    for (int k = 2; k < 10; k++) {
      for (int j = k; j < 10; j++) {
        String repeater = getRepeatingFrom(10*k - 1, j);
        // Since getRepeating from returns extra digits, all the code from here is to strip
        // the trailing bits such that exactly the repeating part is retained.
        stop:
          for (int i = 2; i <= repeater.length() / 2; i++) {
            if (repeater.endsWith(repeater.substring(0, i))) {
              repeater = repeater.substring(0, repeater.length() - i);
              for (int l = i; l <= repeater.length() / 2; l++) {
                if (repeater.endsWith(repeater.substring(0, l))) {
                  while (repeater.length() > l && repeater.endsWith(repeater.substring(0, l))) {
                    repeater = repeater.substring(0, repeater.length() - l);
                  }
                  break stop;
                }
              }
            }
          }
        // Add all repetitions of the number to the sum, each is trivially valid.
        sum += Integer.valueOf(repeater.substring(repeater.length() - 5)) * (100 / repeater.length());
      }
    }
    return String.valueOf((sum + addOn) % MOD);
  }

  /** Returns the repeating part of a continued fraction, potentially with some extra trailing digits. */
  private static String getRepeatingFrom(int i, int b) {
    StringBuilder repeatingBit = new StringBuilder();
    int start = b * 10;
    for (int j = 1; j < i; j++) {
      repeatingBit.append(start / i);
      start %= i;
      start *= 10;
    }
    return repeatingBit.toString();
  }
}
