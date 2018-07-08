package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static problems.EulerLib.primesTo;

/**
 * Problem 60:
 * https://projecteuler.net/problem=60
 *
 * Solved by Sage on 10/27/16.
 */
public class Euler060 implements Problem {

    // Reduced after finding out what the answer was
    // Having it this low actually skips one solution
    // But this program runs in O(n^2) where n is the set
    // of primes to check.
    private static final int PRIME_LIMIT = 8500;
    // Ceiling for concatenation of two primes under prime limit
    private static final int SIEVE_SIZE = PRIME_LIMIT * 10000;
    private static final int TARGET_SET_SIZE = 5;
    // field so it can be accessed in the isPrime method
    private static boolean[] primeSieve;

    @Override
    public String solve() {

        // Size of sieve makes the sieve creation the
        // limiting factor for this implementation, with
        // current size it takes about 2 seconds to make.
        // Without sieve, time to check all concatenated
        // primes is way too high to work with.
        primeSieve = primesTo(SIEVE_SIZE);

        // Generate list of primes
        List<Integer> primes = new ArrayList<>();
        // 2 will never work, skip it
        for (int i = 3; i < PRIME_LIMIT; i += 2) {
            if (primeSieve[i])
                primes.add(i);
        }

        // Map of prime p to a list of primes that concatenate with
        // p to create primes, we use this for the DFS to get sets of 5
        Map<Integer, List<Integer>> primeConcatsMap = new HashMap<>();

        for (int i = 0; i < primes.size(); i++) {
            List<Integer> concatPairs = new ArrayList<>();
            // 7000 chosen to speed things up, anything above that will
            // only be the last element in the set so we don't need to
            // know the pairings for it
            if (primes.get(i) < 7000) {
                for (int j = i + 1; j < primes.size(); j++) {
                    if (concatToPrimes(primes.get(i), primes.get(j))) {
                        concatPairs.add(primes.get(j));
                    }
                }
            }
            primeConcatsMap.put(primes.get(i), concatPairs);
        }

        // Depth first search to find the smallest sum
        int bestSum = 1000000;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < primes.size(); i++) {
            int p = primes.get(i);
            // Optimization: since the first element we insert
            // is the smallest prime in the set, if it is 1/5 the
            // best sum we know we can just skip it
            if (5 * p > bestSum)
                continue;
            // The set of 5 pairwise concatenatable primes, initialize with first element
            set.add(p);
            // Do backtracking
            int sum = generateConcatPairSet(
                    set,
                    primeConcatsMap,
                    primeConcatsMap.get(p),
                    bestSum,
                    p);
            // Remove element, start fresh with next one
            set.remove(p);
            bestSum = sum < bestSum ? sum : bestSum;
        }

        return String.valueOf(bestSum);
    }

    private int generateConcatPairSet(
            Set<Integer> set,
            Map<Integer, List<Integer>> primePairMap,
            List<Integer> primesToCheck,
            int bestSum,
            int currentSum) {
        // If full set, done!
        if (set.size() == TARGET_SET_SIZE) {
            return currentSum;
        }
        // Otherwise, iterate through set
        for (int i = 0; i < primesToCheck.size(); i++) {
            int p = primesToCheck.get(i);
            // break out early if sum is too large
            if (currentSum + p > bestSum)
                break;

            // Check that the current prime concatenates
            // with all other primes in the set
            boolean foundFit = true;
            for (int prime : set) {
                if (!primePairMap.get(prime).contains(p)) {
                    foundFit = false;
                    break;
                }
            }
            // If it does, add it to the set and search
            if (foundFit) {
                set.add(p);
                // Recursive call
                int best = generateConcatPairSet(
                        set,
                        primePairMap,
                        primePairMap.get(p),
                        bestSum,
                        currentSum + p);
                if (best < bestSum)
                    bestSum = best;
                // Remove from the set after checking
                set.remove(p);
            }
        }
        return bestSum;
    }

    private boolean concatToPrimes(int p, int q) {
        return primeSieve[Integer.valueOf(String.valueOf(p) + String.valueOf(q))]
                && primeSieve[Integer.valueOf(String.valueOf(q) + String.valueOf(p))];
    }

}
