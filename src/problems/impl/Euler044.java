package problems.euler025to050;

import problems.Problem;

import java.util.HashSet;
import java.util.Set;

/**
 * Problem 44:
 * https://projecteuler.net/problem=44
 *
 * Solved by Sage on 10/23/16.
 */
public class Euler044 implements Problem {

    @Override
    public String solve() {
        // HashSet for quick lookup
        Set<Integer> pentagonals = new HashSet<>();
        for (int i = 1; i < 5000; i++) {
            pentagonals.add(i * (3*i - 1) / 2);
        }
        for (int penta : pentagonals) {
            for (int penta2 : pentagonals) {
                if (pentagonals.contains(Math.abs(penta - penta2)) && pentagonals.contains(Math.abs(penta + penta2))) {
                    return String.valueOf(Math.abs(penta - penta2));
                }
            }
        }
        return null;
    }
}
