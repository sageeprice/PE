package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static problems.EulerLib.primesTo;

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
    boolean[] sieve = primesTo(100_000_000);
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
}
