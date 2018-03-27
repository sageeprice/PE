package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Math behind this is fairly well-known
 * @see <@a href="https://en.wikipedia.org/wiki/Out_shuffle">Wikipedia</a>.
 *
 * Answer: 3010983666182123972
 */
public class Euler622 implements Problem {

    private static final int POWER = 60;
    private static final long TWO_TO_THE_SIXTY = (long) Math.pow(2, POWER);

    @Override
    public String solve() {
        List<Long> factors = generateFactors(factorize(TWO_TO_THE_SIXTY - 1));
        long solutions = 0;
        for (long factor : factors) {
            if (isSolution(factor)) {
                solutions += factor + 1;
            }
        }
        return String.valueOf(solutions);
    }

    /** Returns prime factorization of {@code n} as map from factor to power. Assumes composite input. */
    private static Map<Long, Long> factorize(long n) {
        Map<Long, Long> factorMap = new HashMap<>();
        for (long i = 2; n != 1; i++) {
                while (n % i == 0) {
                    // Note: key is unused in value calculation.
                    factorMap.compute(i, (k, v) -> (v == null) ? 1 : v + 1);
                    n /= i;
                }
        }
        return factorMap;
    }

    /**
     * Given a prime factorization, generates a list of all factors by considering all combinations of prime factors and
     * possible powers thereof.
     */
    private static List<Long> generateFactors(Map<Long, Long> primeFactorization) {
        List<Map.Entry<Long, Long>> primesToPowers = new ArrayList<>(primeFactorization.entrySet());
        List<Long> factors = new ArrayList<>();
        recursivelyGenerate(factors, 1, primesToPowers, 0);
        return factors;
    }

    /** Recursive helper for {@link #generateFactors}. */
    private static void recursivelyGenerate(
            List<Long> generatedFactors,
            long val,
            List<Map.Entry<Long, Long>> primesToPowers,
            int curIndex) {
        if (curIndex == primesToPowers.size()) {
            generatedFactors.add(val);
            return;
        }
        Map.Entry<Long, Long> primeAndPower = primesToPowers.get(curIndex);
        // 0 case: do not use current prime.
        recursivelyGenerate(generatedFactors, val, primesToPowers, curIndex + 1);
        for (int i = 0; i < primeAndPower.getValue(); i++) {
            val *= primeAndPower.getKey();
            recursivelyGenerate(generatedFactors, val, primesToPowers, curIndex + 1);
        }
    }

    /**
     * Checks if the given input is a solution by verifying that no power of 2 less than {@link #POWER} is congruent to
     * one mod n. If this is the case, then the shuffle would return to normal in less than 60 shuffles.
     */
    private static boolean isSolution(long n) {
        long pow = 1;
        for (int i = 0; i < POWER - 2; i++) {
            pow *= 2;
            pow %= n;
            if (pow == 1) {
                return false;
            }
        }
        return n != 1;
    }
}
