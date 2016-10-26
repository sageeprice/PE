package problems.euler051to075;

import problems.Problem;

/**
 * Problem 53:
 * https://projecteuler.net/problem=53
 *
 * Solved by Sage on 10/26/16.
 */
public class Euler053 implements Problem {

    // first row is 0 row, so 100 + 1
    private final int PYRAMID_SIZE = 101;

    @Override
    public String solve() {

        int[][] pyramid = new int[PYRAMID_SIZE][PYRAMID_SIZE];

        for (int i = 0; i < PYRAMID_SIZE; i++) {
            for (int j = 0; j < PYRAMID_SIZE; j++) {
                pyramid[i][j] = 0;
            }
        }

        // Initial row is 0 C 0
        pyramid[0][0] = 1;
        // Count of numbers over 1000000
        int milliCount = 0;

        // Just calculate Pascal's triangle dynamically
        for (int i = 1; i < PYRAMID_SIZE; i++) {
            for (int j = 0; j < PYRAMID_SIZE; j++) {
                int x = pyramid[i-1][j];
                int y = j == 0 ? 0 : pyramid[i-1][j-1];

                // Use -1 to represent over 1000000 so we don't
                // have to worry about overflowing ints
                if (x == -1 || y == -1 || x + y >= 1000000) {
                    pyramid[i][j] = -1;
                    milliCount++;
                } else {
                    pyramid[i][j] = x + y;
                }
            }
        }
        return String.valueOf(milliCount);
    }
}
