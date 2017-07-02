package problems.impl;

import problems.Problem;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Problem 67:
 * https://projecteuler.net/problem=67
 *
 * Solved by Sage on 10/30/16.
 */
public class Euler067 implements Problem {

    private static final int SIZE = 100;
    private static final String FILE_NAME = "src/text/p067_triangle.txt";
    private static final String SPLITTER = " ";
    private long[][] PYRAMID = new long[SIZE][SIZE];

    @Override
    public String solve() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            for (int i = 0; i < SIZE; i++) {
                String[] numberStrs = reader.readLine().split(SPLITTER);
                for (int j = 0; j < numberStrs.length; j++) {
                    PYRAMID[i][j] = Long.valueOf(numberStrs[j]);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong... ", e);
        }

        /**
         * Everything below this line copy + pasted from Euler018, with int
         * changed to long where appropriate (though ultimately unnecessary)
         * -----------------------------------------------------------------
         */
        long[][] travel_length = new long[SIZE][SIZE];

        // Initialize first column
        travel_length[0][0] = PYRAMID[0][0];
        for (int i = 1; i < PYRAMID.length; i++) {
            travel_length[i][0] = travel_length[i-1][0] + PYRAMID[i][0];
        }

        // Dynamic programming, find longest path to given location.
        // This will always be the current entry plus the larger of the two elements above.
        for (int i = 1; i < PYRAMID.length; i++) {
            for (int j = 1; j < PYRAMID.length; j++) {
                travel_length[i][j] = PYRAMID[i][j] + Math.max(travel_length[i-1][j-1], travel_length[i-1][j]);
            }
        }

        // Finally, go through the last row and find the highest value.
        long best = 0;
        for (long path : travel_length[PYRAMID.length-1]) {
            if (path > best) {
                best = path;
            }
        }
        return String.valueOf(best);
    }
}
