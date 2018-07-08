package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static problems.EulerLib.primesTo;

/**
 * Just generate the set of numbers worth checking, starting from the same
 * digit 9 times, then 8 times, and so on until solutions have been found for
 * each digit. This massively reduces the search space.
 *
 * Answer: 612407567715
 */
public class Euler111 implements Problem {

  private static final long MINIMUM = 1_000_000_000L;

  @Override
  public String solve() {

    // Generate list of primes to use for checking high primes.
    boolean[] sieve = primesTo(100_000);
    List<Integer> lowPrimes = new ArrayList<>();
    for (int i = 2; i < sieve.length; i++) {
      if (sieve[i]) {
        lowPrimes.add(i);
      }
    }

    Map<Integer, Long> digitMap = new HashMap<>();
    for (int i = 1; digitMap.size() < 10; i++) {
      Set<boolean[]> templates = new HashSet<>();
      recursivelyGetTemplates(new boolean[10], 0, 0, i, templates);
      for (int d = 0; d < 10; d++) {
        if (!digitMap.containsKey(d)) {
          long s =
              getPrimeSumForTemplates(lowPrimes, templates, d);
          if (s > 0) {
            digitMap.put(d, s);
          }
        }
      }
    }
    return String.valueOf(digitMap.values().stream().mapToLong(Long::longValue).sum());
  }

  /**
   * Returns the sum of all primes that can be produces using the provided {@code templates} and most-commonly occurring
   * digit {@code d}.
   */
  private long getPrimeSumForTemplates(List<Integer> lowPrimes, Set<boolean[]> templates, int digit) {
    return templates.stream()
        .map(
            t ->
                fillTemplate(t, digit).stream()
                    .filter(n -> n > MINIMUM)
                    .filter(n -> isPrime(n, lowPrimes))
                    .collect(Collectors.toList()))
        .flatMap(List::stream)
        .mapToLong(Long::longValue)
        .sum();
  }

  private static List<Long> fillTemplate(boolean[] template, int digit) {
    List<Long> nextTemplates = new ArrayList<>();
    List<Long> lastTemplates = new ArrayList<>(toDigits(template[0], digit));
    for (int i = 1; i < template.length; i++) {
      for (long num : lastTemplates) {
        for (long nextDigit : toDigits(template[i], digit)) {
          nextTemplates.add(num * 10 + nextDigit);
        }
      }
      lastTemplates = nextTemplates;
      nextTemplates = new ArrayList<>();
    }
    return lastTemplates;
  }

  private static List<Long> toDigits(boolean useDigit, int digit) {
    if (useDigit) {
      List<Long> usableDigits = new ArrayList<>();
      usableDigits.add((long) digit);
      return usableDigits;
    } else {
      return LongStream.rangeClosed(0, 9).filter(d -> d != digit).boxed().collect(Collectors.toList());
    }
  }

  /**
   * Adds all templates with up to {@code limit} places to insert a random digit into {@code completeTemplates}.
   */
  private static void recursivelyGetTemplates(boolean[] template, int index, int randomCount, int limit, Set<boolean[]> completeTemplates) {
    if (index == template.length) {
      if (randomCount == limit) {
        completeTemplates.add(Arrays.copyOf(template, template.length));
      }
      return;
    }

    if (randomCount + 1 <= limit) {
      recursivelyGetTemplates(template, index + 1, randomCount + 1, limit, completeTemplates);
    }
    template[index] = true;
    recursivelyGetTemplates(template, index + 1, randomCount, limit, completeTemplates);
    template[index] = false;
  }

  private static boolean isPrime(long n, List<Integer> primes) {
    for (int prime : primes) {
      if (n % prime == 0) {
        return false;
      }
    }
    return true;
  }

}
