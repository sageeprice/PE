package problems.euler076To100;

import problems.Problem;

/**
 * Problem 76:
 * https://projecteuler.net/problem=76
 *
 * Solved by Sage on 10/31/16.
 */
public class Euler076 implements Problem {

    private static final int LIMIT = 100;

    @Override
    public String solve() {
        long[] partitions = new long[LIMIT + 1];
        for (int i = 0; i < partitions.length; i++) {
            partitions[i] = 0;
        }
        partitions[0] = 1;
        partitions[1] = 1;
        partitions[2] = 2;

        // http://mathworld.wolfram.com/PartitionFunctionP.html
        for (int i = 3; i <= LIMIT; i++) {
            long sum = 0;
            long sign = -1;
            for (int j = 1; j <= i; j++) {
                sign *= -1;
                int index1 = i - (j * ((3 * j) - 1)) / 2;
                int index2 = i - (j * ((3 * j) + 1)) / 2;
                long p1 = index1 >= 0 ? partitions[index1] : 0;
                long p2 = index2 >= 0 ? partitions[index2] : 0;
                sum += sign * (p1 + p2);
            }
            partitions[i] = sum;
        }

        // Subtract one since we ignore 100 = 100
        return String.valueOf(partitions[LIMIT] - 1);
    }
}
