package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem 78:
 * https://projecteuler.net/problem=78
 *
 * Solved by Sage on 11/1/16.
 */
public class Euler078 implements Problem {

    private static final int MILLI = 1_000_000;
    @Override
    public String solve() {

        List<Long> partitions = new ArrayList<>();
        partitions.add(1L);
        partitions.add(1L);
        partitions.add(2L);

        // http://mathworld.wolfram.com/PartitionFunctionP.html
        int i = 3;
        while (true) {
            long sum = 0;
            long sign = -1;
            for (long j = 1; j <= i; j++) {
                sign *= -1;
                long index1 = i - (j * ((3 * j) - 1)) / 2;
                if (index1 < 0)
                    break;
                long p1 = partitions.get((int) index1);
                sum += sign * p1;

                long index2 = i - (j * ((3 * j) + 1)) / 2;
                if (index2 < 0)
                    break;
                long p2 = partitions.get((int) index2);
                sum += sign * p2;
            }
            sum %= MILLI;
            partitions.add(sum);
            if (sum % MILLI == 0) {
                return String.valueOf(i);
            }
            i++;
        }
    }
}
