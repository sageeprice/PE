package problems.reallyOld;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Sage on 9/12/2015.
 *
 * Find cheapest traversal of matrix from left to right, where you may go right, up, or down
 * at each step. Dynamic programming approach used, use prior column to compute the next one.
 * Sweep down then up in each column to see which option is best.
 */
public class Problem82PathSum3 {

    private final static int MTRX_SIZE = 80;
    private static final String FILE_NAME = "C:\\Users\\Sage\\workspace\\SageKatas\\eulerTxtFiles\\p082_matrix.txt";
    public static void main(String[] args) throws IOException {

        int[][] matrix = new int[MTRX_SIZE][MTRX_SIZE];
        int[][] route = new int[MTRX_SIZE][MTRX_SIZE];


        int row = 0;
        for (String line : Files.readAllLines(Paths.get(FILE_NAME))) {
            String[] nums = line.split(",");
            for (int i = 0; i < MTRX_SIZE; i++) {
                matrix[row][i] = Integer.valueOf(nums[i]);
            }
            row++;
        }

        // initialize first column of solution matrix
        for (row = 0; row < MTRX_SIZE; row++) {
            route[row][0] = matrix[row][0];
        }

        // Three sweeps per column:
        //  1. route[r][c] = route[r-1][c] + matrix[r][c]
        //  2. sweep down
        //  3. sweep up
        for (int column = 1; column < MTRX_SIZE; column++) {
            route[0][column] = route[0][column-1] + matrix[0][column];

            // first and second sweep, ternary op to check whether entry above or to
            // left of current location is cheaper
            for (row = 1; row < MTRX_SIZE; row++) {
                route[row][column] = (route[row][column-1] > route[row-1][column]) ?
                        route[row-1][column] + matrix[row][column] :
                        route[row][column-1] + matrix[row][column];
            }
            // third sweep, done after first two so we have a frame of reference.
            for (row = MTRX_SIZE-2; row >= 0; row--) {
                if (route[row][column] > route[row+1][column] + matrix[row][column]) {
                    route[row][column] = route[row+1][column] + matrix[row][column];
                }
            }
        }
        int best = Integer.MAX_VALUE;
        for (row = 0; row < MTRX_SIZE; row++) {
            if (route[row][MTRX_SIZE-1] < best) {
                best = route[row][MTRX_SIZE-1];
            }
        }
        System.out.println("Cheapest route is: " + best);
    }
}
