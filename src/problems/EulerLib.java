package problems;

import java.util.Arrays;

/** Utility methods to help with solving Project Euler problems. Mostly related to primes... */
public final class EulerLib {

  /**
   * Returns an {@code boolean[]} where the value of each element of the array indicates whether
   * the index of the element is prime.
   *
   * <p>Implementation uses the Sieve of Eratosthenes. Consequently, runtime is roughly O(n*n).
   *
   * @param n the length of the returned array.
   */
  public static boolean[] primesTo(int n) {
    boolean[] sieve = new boolean[n+1];
    // initial conditions
    if (n >= 2) {
      sieve[2] = true;
    }
    // only need to check odds
    for (int i = 3; i <= n; i+=2) {
      sieve[i] = true;
    }
    // Composites larger than sqrt(n) must have a smaller factor, and are thus eliminated
    // already. Therefore we need only proceed to check  values up through sqrt(n)
    for (int i = 3; i <= Math.sqrt(n); i+= 2) {
      if (sieve[i]) {
        /*
         * Since you'll forget this Sage:
         *  - if it's less than i*i more than i, it'll be covered by a smaller prime
         *  - all primes > 2 are odd, so only need to check every other above i*i
         *  One improvement that could be made: technically only need to check
         *  of the form 6k+1 and 6k-1 (k an int), as 6k+3 is multiple of 3.
         */
        for (int j = i*i; j <= n; j += i * 2) {
          sieve[j] = false;
        }
      }
    }

    return sieve;
  }

  /**
   * Given a populated boolean array representing a Sieve of Eratosthenes, returns an array
   * containing all primes within the array.
   *
   * <p>Note: meant to be used with {@link #primesTo}, e.g.:
   * <code>toIntegerPrimes(primesTo(n))</code>.
   */
  public static int[] toIntegerPrimes(boolean[] sieve) {
    int pCount = 0;
    for (int i = 2; i < sieve.length; i++) {
      if (sieve[i]) {
        pCount++;
      }
    }
    int[] hammingPrimes = new int[pCount];
    int pIndex = 0;
    for (int i = 0; i < sieve.length; i++) {
      if (sieve[i]) {
        hammingPrimes[pIndex++] = i;
      }
    }
    return hammingPrimes;
  }

  /** Returns true IFF {@code x} is a prime number. */
  public static boolean isPrime(long x) {
    if (2 >= x || 0 == x % 2) {
      return false;
    }
    for (int i = 3; i <= Math.sqrt(x); i += 2) {
      if (x % i == 0) {
        return false;
      }
    }
    return true;
  }

  /** Returns true IFF {@code x} is a permutation of {@code y}. */
  public static boolean isPermutation(long x, long y) {
    char[] xStr = String.valueOf(x).toCharArray();
    char[] yStr = String.valueOf(y).toCharArray();
    Arrays.sort(xStr);
    Arrays.sort(yStr);

    return Arrays.equals(xStr, yStr);
  }


  /** Returns true IFF {@code a} and {@code b} are anagrams of each other. */
  public static boolean isAnagram(int a, int b) {
    char[] aStr = String.valueOf(a).toCharArray();
    Arrays.sort(aStr);

    char[] bStr = String.valueOf(b).toCharArray();
    Arrays.sort(bStr);

    return Arrays.equals(aStr, bStr);
  }

  private EulerLib() {}
}
