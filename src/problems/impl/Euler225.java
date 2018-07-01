package problems.impl;

import problems.Problem;

/**
 * Working within a modulus, there are a limited possible set of triples. Just iterate through the
 * triples until it cycles or until a term is divisible by the desired dividend. Note that since no
 * two distinct triples may have the same succeeding triple, if there is a cycle it must include the
 * initial term {1, 1, 1} -- so we can check for this as an end condition.
 *
 * Answer: 2009
 */
public class Euler225 implements Problem {

  private static final int REQUIRED_SOLUTIONS = 124;

  @Override
  public String solve() {
    int solutions = 0;
    int dividend = 1;

    do {
      dividend += 2;
      if (!isSolution(dividend)) {
        solutions++;
      }
    } while (REQUIRED_SOLUTIONS != solutions);

    return String.valueOf(dividend);
  }

  private static boolean isSolution(int dividend) {
    // Start with second term since 1 cannot ever be divisible by dividend.
    int t1 = 1;
    int t2 = 1;
    int t3 = 3;
    int t;
    while (0 != t3 % dividend) {
      if (1 == t1 && 1 == t2 && 1 == t3) {
        return false;
      }
      t = t1;
      t1 = t2;
      t2 = t3;
      t3 = (t + t1 + t2) % dividend;
    }
    return true;
  }

}
