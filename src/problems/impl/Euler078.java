package problems.euler076To100;

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
                long index2 = i - (j * ((3 * j) + 1)) / 2;
                long p1 = index1 >= 0 ? partitions.get((int) index1) : 0;
                long p2 = index2 >= 0 ? partitions.get((int) index2) : 0;
                sum += sign * (p1 + p2);
            }
            sum %= 1000000;
            partitions.add(sum);
            if (sum % 1000000 == 0) {
                return String.valueOf(i);
            }
            i++;
        }

        // Subtract one since we ignore 100 = 100
//        return null;
    }
}
