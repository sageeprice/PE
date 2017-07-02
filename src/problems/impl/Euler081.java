package problems.impl;

import problems.Problem;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Problem 81:
 * https://projecteuler.net/problem=81
 *
 * Solved by Sage on 11/2/16.
 */
public class Euler081 implements Problem {

    private static final int MATRIX_SIZE = 80;
    private static final String FILE_READER = "src/text/p081_matrix.txt";
    private static final String SPLITTER = ",";

    @Override
    public String solve() {

        int[][] matrix = new int[MATRIX_SIZE][MATRIX_SIZE];

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_READER));
            for (int i = 0; i < MATRIX_SIZE; i++) {
                String[] numberStrs = reader.readLine().split(SPLITTER);
                for (int j = 0; j < MATRIX_SIZE; j++) {
                    matrix[i][j] = Integer.valueOf(numberStrs[j]);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Something broke...", e);
        }

        int[][] routeCost = new int[MATRIX_SIZE][MATRIX_SIZE];

        // Yay for dynamic programming
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                int left = j == 0 ? Integer.MAX_VALUE : routeCost[i][j-1];
                int up = i == 0 ? Integer.MAX_VALUE : routeCost[i-1][j];

                int least;
                if (left == Integer.MAX_VALUE && up == Integer.MAX_VALUE) {
                    least = 0;
                } else
                    least = Math.min(left, up);

                routeCost[i][j] = least + matrix[i][j];
            }
        }

        return String.valueOf(routeCost[MATRIX_SIZE - 1][MATRIX_SIZE - 1]);
    }
}
