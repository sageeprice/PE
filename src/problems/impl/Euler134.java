package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.List;

import static problems.EulerLib.primesTo;

/**
 * See comments, but this is mostly just brute force with a little work to
 * cleverly take advantage of the fact that each number is prime.
 *
 * Answer: 18613426663617118
 */
public class Euler134 implements Problem {

  @Override
  public String solve() {
    // Get a list of primes with at least one p > 1_000_100.
    boolean[] sieve = primesTo(1_000_100);
    List<Long> primes = new ArrayList<>();
    for (int i = 2; i < sieve.length; i++) {
      if (sieve[i]) {
        primes.add((long) i);
      }
    }

    long sum = 0;
    for (int i = 2; i < primes.size() - 1; i++) {
      long p1 = primes.get(i);
      long p2 = primes.get(i+1);
      // Find the first power of 10 exceeding p1. This will
      // be used to incrementally advance towards result.
      int tenPow = 10;
      while (tenPow < p1) {
        tenPow *= 10;
      }

      long m = p2;
      // The goal here is to incrementally approach the desired
      // result. Each time we add multiples of p2 until the next
      // digit in the multiple matches that in p1. We can then
      // increase our step size by a factor of 10, so that
      // subsequent additions of p2 do not affect the already
      // correct digits. Note that since p2 is relatively prime
      // to 10 (ignoring 5...) p2 is a generator for Z // 10^n Z
      // so it will take at least 10^n steps for another result
      // with the already matching digits to be found. Thus we
      // won't miss any intermediate results with such a jump in
      // step size.
      for (int t = 10; t <= tenPow; t *= 10) {
        while (m % t != p1 % t) {
          m += t / 10 * p2;
        }
      }
      sum += m;
      if (p2 > 1_000_000) {
        break;
      }
    }
    return String.valueOf(sum);
  }
}
