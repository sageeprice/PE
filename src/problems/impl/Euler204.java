package problems.impl;

import problems.EulerLib;
import problems.Problem;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * If you know the primes, Hamming numbers can be generated directly. Fortunately the requested
 * result is small enough that the set of Hamming numbers (and numbers to check) can fit into
 * memory.
 *
 * Answer: 2944730
 */
public class Euler204 implements Problem {

  @Override
  public String solve() {
    return String.valueOf(generalizedHammingCount(100, 1_000_000_000));
  }

  private static long generalizedHammingCount(int hammingLimit, int rangeMax) {
    // Heap to store potential Hamming numbers.
    PriorityQueue<Integer> hammingHeap = new PriorityQueue<>(10_000);
    // Set of already seen Hamming numbers.
    Set<Integer> hammingSet = new HashSet<>();
    // Array of primes which are permitted as factors of the generalized Hamming numbers.
    int[] hammingPrimes = EulerLib.toIntegerPrimes(EulerLib.primesTo(hammingLimit));

    hammingHeap.add(1);
    while (!hammingHeap.isEmpty()) {
      int hammingNumber = hammingHeap.poll();
      if (!hammingSet.contains(hammingNumber)) {
        hammingSet.add(hammingNumber);
        for (int prime : hammingPrimes) {
          if (prime * hammingNumber <= rangeMax) {
            hammingHeap.add(hammingNumber * prime);
          } else {
            break;
          }
        }
      }
    }
    return hammingSet.size();
  }
}
