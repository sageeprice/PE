package problems.euler076To100;

import problems.Problem;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Problem 82:
 * https://projecteuler.net/problem=82
 *
 * Solved by Sage on 11/2/16.
 */
public class Euler082 implements Problem {

    private static final int MATRIX_SIZE = 80;
    private static final String FILE_READER = "src/text/p082_matrix.txt";
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

        // Set first column
        for (int i = 0; i < MATRIX_SIZE; i++) {
            routeCost[i][0] = matrix[i][0];
        }
        // Sweep down then up
        for (int c = 1; c < MATRIX_SIZE; c++) {
            routeCost[0][c] = matrix[0][c] + routeCost[0][c-1];
            for (int r = 1; r < MATRIX_SIZE; r++) {
                routeCost[r][c] = matrix[r][c] + Math.min(routeCost[r - 1][c], routeCost[r][c - 1]);
            }
            for (int r = MATRIX_SIZE - 2; r >= 0; r--) {
                routeCost[r][c] = Math.min(routeCost[r][c], routeCost[r + 1][c] + matrix[r][c]);
            }
        }

        int m = Integer.MAX_VALUE;
        for (int i = 0; i < MATRIX_SIZE; i++) {
            m = Math.min(m, routeCost[i][MATRIX_SIZE - 1]);
        }

        return String.valueOf(m);
    }
}
