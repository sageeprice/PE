package problems.impl;

import problems.Problem;

import java.util.Set;
import java.util.TreeSet;

/**
 * Smart brute force
 */
public class Euler346 implements Problem {

    @Override
    public String solve() {
        long cap = 1_000_000_000_000L;
        long repunitSum = 0;
        Set<Long> repunitSet = new TreeSet<>();
        for (long i = 2; i * i < cap; i++) {
            long base = i * i + i + 1;
            while (base < cap) {
                if (repunitSet.add(base)) {
                    repunitSum += base;
                }
                if (cap / i < base) {
                    break;
                }
                base = base * i + 1;
            }
        }
        return String.valueOf(repunitSum + 1);
    }
}
