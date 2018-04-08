package problems.incomplete;

import problems.Problem;

import java.util.Arrays;

public class Euler110 implements Problem {

  @Override
  public String solve() {
    /*
     * So the number of factors of n*n = d(n*n) where
     * d(x) is the divisors function. Since x=n*n is square, n*n has
     * (d(n*n) + 1) / 2 factors that are <= n. But every one of these is
     * a solution to the equation from above, so this value is exactly
     * what we are trying to calculate. We're just looking for the first
     * n such that (d(n*n) + 1) / 2 >= 1000000.
     */
    // 3^14 > 1,000,000;
    int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43};
    int[] powers = new int[primes.length];
    for (int i = 0; i < primes.length; i++) {
      powers[i] = 1;
    }
    System.out.println(divisorCount(powers));
    return null;
  }

  private static long divisorCount(int[] powers) {
    long count = 1;
    for (int p : powers) {
      count *= 2*p + 1;
    }
    return ++count/2;
  }

  // How to optimize this?
  private static void optimize(int[] primes, int[] powers) {
    int[] optimumPowers = Arrays.copyOf(powers, powers.length);
    int[] powersCopy = Arrays.copyOf(powers, powers.length);

    for (int i = powersCopy.length - 1; i > 0; i--) {
      powersCopy[i] = 0;
      int maxPrime = primes[i];
      if (divisorCount(powersCopy) < 1_000_000) {
        ;
      }
    }
  }
}
