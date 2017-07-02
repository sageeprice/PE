package problems.impl;

import problems.Problem;

import java.util.HashMap;
import java.util.Map;

/**
 * Current impl works, but is too slow;
 */
public class Euler549 implements Problem {

    private static final int LIMIT = 1000;

    @Override
    public String solve() {
        long s = 0;
        for (int i = 2; i <= LIMIT; i++) {
            s += smallestFactorial(i);
        }
        return String.valueOf(s);
    }

    private static int smallestFactorial(int x) {
        if (x == 1) {
            return x;
        }
        int upperBound = x/2;
        Map<Integer, Integer> factorMap = new HashMap<>();
        while (x % 2 == 0) {
            incrementValue(factorMap, 2);
            x /= 2;
        }
        for (int i = 3; i <= upperBound && x != 1; i += 2) {
            while (x % i == 0) {
                incrementValue(factorMap, i);
                x /= i;
            }
        }
        // No prime factors? Must be prime.
        if (factorMap.isEmpty()) {
            return x;
        }
        int max = 0;
        for (int factor : factorMap.keySet()) {
            int m = factor* getMinFactorMultiple(factor, factorMap.get(factor));
            if (m > max)
                max = m;
        }
        return max;
    }
    private static void incrementValue(Map<Integer, Integer> factors, int factor) {
        if (factors.containsKey(factor)) {
            factors.put(factor, factors.get(factor) + 1);
        } else {
            factors.put(factor, 1);
        }
    }

    private static int getMinFactorMultiple(int factor, int exp) {
        if (exp == 1)
            return 1;
        int tarExp = exp;
        for (int i = tarExp - 1; ; i--) {
            int x = getTotalFactors(factor, i);
            if (x < tarExp)
                return i + 1;
        }
    }

    private static int getTotalFactors(int factor, int multiple) {
        int s = multiple;
        while (multiple > 0) {
            multiple /= factor;
            s += multiple;
        }
        return s;
    }

}
