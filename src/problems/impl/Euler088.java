package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Basically just check all possibilities by recursively
 * generating a sequence and deriving k for it.
 */
public class Euler088 implements Problem {

    private static final int LIMIT = 12_000;
    private static final int BOUND = LIMIT;
    private static long[] minK = new long[LIMIT + 1];

    @Override
    public String solve() {
        for (int i = 0; i <= LIMIT; i++) {
            minK[i] = Integer.MAX_VALUE;
        }
        List<Integer> sequence = new ArrayList<>();
        for (int i = 2; i <= BOUND; i++) {
            sequence.add(i);
            recurse(i, 1, sequence);
            sequence.remove(sequence.size() - 1);
        }
        int sum = 0;
        Set<Long> seenMinProdSums = new HashSet<>();
        for (int i = 2; i <= LIMIT; i++) {
            if (seenMinProdSums.contains(minK[i])) {
                continue;
            }
            sum += minK[i];
            seenMinProdSums.add(minK[i]);
        }
        return String.valueOf(sum);
    }

    /* Returns true iff should continue iterating through factors. */
    private static boolean recurse(int nextFactor, int depth, List<Integer> seq) {
        if (depth > 1) {
            // Going deeper will guarantee that k exceeds the limit.
            if (depth > 11)
                return false;
            long prod = 1;
            for (int el : seq) {
                prod *= el;
            }
            // Since 1*...*1*2*n always yields k = 2*n.
            if (prod > 2 * LIMIT) {
                return false;
            }
            long k = getK(prod, seq);
            // Ignore solutions that are too large.
            if (k > LIMIT) {
                return false;
            }
            if (prod < minK[(int) k]) {
                minK[(int) k] = prod;
            }
        }
        for (int i = nextFactor; i <= BOUND; i++) {
            seq.add(i);
            boolean keepGoing = recurse(i, depth + 1, seq);
            seq.remove(seq.size() - 1);
            if (!keepGoing) {
                return true;
            }
        }
        return true;
    }

    private static long getK(long product, List<Integer> seq) {
        long sum = 0;
        for (int element : seq) {
            sum += element;
        }
        return product - sum + seq.size();
    }
}
