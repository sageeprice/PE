package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Brute force. Generate all primes less than 100_000_000 for quick primality
 * checking (note that no 9-digit pandigital can be prime, all divisible by 3).
 * Generate all pandigitals. Generate each valid sequence of primes from the
 * pandigitals by knocking off the end digits, and collect each unique set of
 * primes into a set.
 *
 * Answer: 44680
 */
public class Euler118 implements Problem {

  @Override
  public String solve() {
    boolean[] sieve = sieveTo(100_000_000);
    Set<Integer> primes = new HashSet<>();
    for (int i = 2; i < sieve.length; i++) {
      if (sieve[i] && hasUniqueDigits(i)) {
        primes.add(i);
      }
    }
    Set<String> panPrimes = new HashSet<>();
    for (int pandigital : getPandigitals()) {
      makePrimeSequences(pandigital, new ArrayList<>(), panPrimes, primes);
    }
    return String.valueOf(panPrimes.size());
  }

  private static void makePrimeSequences(
      int remaining,
      List<Integer> ints,
      Collection<String> panSequences,
      Set<Integer> primes) {
    if (remaining == 0) {
      panSequences.add(ints.stream().sorted().collect(Collectors.toList()).toString());
    }

    int next = 0;
    while (remaining > 0) {
      // Note: this reverses the order, but since we check all pandigitals the
      // same sequence in reverse will appear somewhere else anyway...
      int digit = remaining % 10;
      remaining /= 10;
      next = next * 10 + digit;
      if (primes.contains(next)) {
        ints.add(next);
        makePrimeSequences(remaining, ints, panSequences, primes);
        ints.remove(ints.size() - 1);
      }
    }
  }

  private static List<Integer> getPandigitals() {
    List<Integer> pandigitals = new ArrayList<>();

    boolean[] digitsUsed = new boolean[10];
    generatePandigitals(0, digitsUsed, pandigitals);
    return pandigitals;
  }

  private static void generatePandigitals(int num, boolean[] digitsUsed, List<Integer> pandigitals) {
    if (num > 100_000_000) {
      pandigitals.add(num);
      return;
    }
    for (int i = 1; i < digitsUsed.length; i++) {
      if (!digitsUsed[i]) {
        num *= 10;
        num += i;
        digitsUsed[i] = true;
        generatePandigitals(num, digitsUsed, pandigitals);
        digitsUsed[i] = false;
        num /= 10;
      }
    }
  }

  private static boolean hasUniqueDigits(int x) {
    Set<Integer> digits = new HashSet<>();
    while (x  > 0) {
      if (digits.contains(x % 10)) {
        return false;
      }
      digits.add(x % 10);
      x /= 10;
    }
    return true;
  }

  /**
   * Returns a prime sieve of the first n integers or containing the first
   * pCount primes - whichever is smaller.
   */
  private static boolean[] sieveTo(int n) {
    boolean[] sieve = new boolean[n + 1];
    // initial conditions
    if (n >= 2) {
      sieve[2] = true;
    }
    // only need to check odds
    for (int i = 3; i <= n; i += 2) {
      sieve[i] = true;
    }
    // Composites larger than sqrt(n) must have a smaller factor, and are thus eliminated
    // already. Therefore we need only proceed to check values up through sqrt(n).
    for (int i = 3; i <= Math.sqrt(n); i += 2) {
      if (sieve[i]) {
        /*
         * Since you'll forget this Sage:
         *  - if it's less than i*i more than i, it'll be covered by a smaller prime
         *  - all primes > 2 are odd, so only need to check every other above i*i
         *  One improvement that could be made: technically only need to check
         *  of the form 6k+1 and 6k-1 (k an int), as 6k+3 is multiple of 3.
         */
        for (int j = i * i; j <= n; j += i * 2) {
          sieve[j] = false;
        }
      }
    }
    return sieve;
  }
}
