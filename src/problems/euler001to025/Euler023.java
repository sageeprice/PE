package problems.euler001to025;

import problems.Problem;

import java.util.HashSet;
import java.util.Set;

/**
 * Problem 23:
 * https://projecteuler.net/problem=23
 *
 * Created by Sage on 10/15/16.
 */
public class Euler023 implements Problem {

    private static final int LIMIT = 28123;

    @Override
    public String solve() {
        Set<Integer> abundants = new HashSet();

        for (int i = 3; i < LIMIT; i++) {
            if (sumFactors(i) > i) {
                abundants.add(i);
            }
        }

        int sum = 0;

        for (int i = 1; i < LIMIT; i++) {
            boolean isSumOfAbundantPair = false;
            for (int abundant : abundants) {
                if (abundants.contains(i-abundant)) {
                    isSumOfAbundantPair = true;
                    break;
                }
            }
            if (!isSumOfAbundantPair) {
                sum += i;
            }
        }
        return String.valueOf(sum);
    }

    // Copied from problem 21, same logic
    public int sumFactors(int x) {

        double root = Math.sqrt(x);

        int factors = 0;

        for (int i = 1; i < root; i++) {
            if (x % i == 0) {
                factors += i + (x / i);
            }
        }

        int rootFloor = (int) Math.floor(root);

        if (rootFloor*rootFloor == x) {
            factors += rootFloor;
        }

        // We only want the sum of proper divisors, so subtract x
        return factors - x;
    }
}
