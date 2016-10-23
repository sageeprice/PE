package problems.euler025to050;

import problems.Problem;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Problem 32:
 * https://projecteuler.net/problem=32
 *
 * Solved by Sage on 10/22/16.
 */
public class Euler032 implements Problem {

    private static final String DIGITS = "123456789";

    @Override
    public String solve() {

        Set<Integer> products = new HashSet();

        for (int i = 1; i <= 9; i++) {
            for (int j = 1234; j <= 9876; j++) {
                if (isPandigital(i, j, i * j)) {
                    if (!products.contains(i * j)) {
                        products.add(i * j);
                    }
                }
            }
        }

        for (int i = 12; i <= 98; i++) {
            for (int j = 123; j <= 987; j++) {
                if (isPandigital(i, j, i * j)) {
                    if (!products.contains(i * j)) {
                        products.add(i * j);
                    }
                }
            }
        }

        return String.valueOf(products.stream().mapToInt(Integer::valueOf).distinct().sum());
    }

    private boolean isPandigital(int a, int b, int c) {
        char[] number = (String.valueOf(a) + String.valueOf(b) + String.valueOf(c)).toCharArray();
        Arrays.sort(number);

        return String.valueOf(number).equals(DIGITS);
    }
}
