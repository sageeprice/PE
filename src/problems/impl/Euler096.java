package problems.euler076To100;

import problems.Problem;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Problem 96:
 * https://projecteuler.net/problem=96
 *
 * Solved by Sage on 11/5/16.
 */
public class Euler096 implements Problem {

    private static final String FILE_NAME = "src/text/p096_sudoku.txt";
    private static final int DIMENSION = 9;
    private static final int DIM_ROOT = (int) Math.floor(Math.sqrt(DIMENSION));

    // Eww global state. But this is the easiest way
    // to handle the recursive board check for completion
    private boolean done;

    @Override
    public String solve() {

        // The board we're currently working on
        int[][] board = new int[DIMENSION][DIMENSION];

        // Sum of all values from solving boards
        int sum = 0;

        // Read and process file
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));

            // Handle each puzzle individually
            for (int k = 0; k < 50; k++) {
                // Skip the first line "Board XX"
                reader.readLine();

                // Process the board
                for (int i = 0; i < DIMENSION; i++) {
                    String[] nums = reader.readLine().split("");
                    for (int j = 0; j < DIMENSION; j++) {
                        board[i][j] = Integer.valueOf(nums[j]);
                    }
                }

                // Recursively backtrack to solve the board
                sum += solveBoard(board);
            }

        } catch (Exception e) {
            throw new RuntimeException("Something broke... ", e);
        }

        return String.valueOf(sum);
    }

    /**
     * Find solution to the given board, return first 3 digits concatenated
     * @param board an unsolved sudoku board with a solution
     * @return int of concatenated first 3 numbers in the solved board
     */
    private int solveBoard(int[][] board) {
        // Reset state
        done = false;

        // Solve recursively
        backtrackSolve(board, 0);

        // Return desired sum
        // TODO: generalize for DIMENSION changing
        return 100 * board[0][0] + 10 * board[0][1] + board[0][2];
    }

    private int[][] backtrackSolve(int[][] board, int index) {

        // Return if done
        if (index >= 81) {
            done = true;
            return board;
        }

        // If entry is already filled skip it
        if (board[index / DIMENSION][index % DIMENSION] != 0)
            backtrackSolve(board, index + 1);

        else {
            // Test all possible solutions for puzzle
            for (int i = 1; i <= DIMENSION; i++) {
                if (validPlacement(board, index, i)) {
                    board[index / DIMENSION][index % DIMENSION] = i;
                    board = backtrackSolve(board, index + 1);
                    if (done) {
                        return board;
                    }
                    board[index / DIMENSION][index % DIMENSION] = 0;
                }
            }
        }

        return board;
    }

    /**
     * Check that the given value is an acceptable placement for the current board
     * @param board Sudoku board
     * @param index index in the board we're looking to place value
     * @param val value to be inserted into board
     * @return boolean of whether placement is valid
     */
    private boolean validPlacement(int[][] board, int index, int val) {

        // Row and column corresponding to index
        int r = index / DIMENSION;
        int c = index % DIMENSION;

        // Validate row and column
        for (int i = 0; i < DIMENSION; i++) {
            if (board[r][i] == val)
                return false;
            if (board[i][c] == val)
                return false;
        }

        // 3x3 box index corresponds to
        int boxR = r / DIM_ROOT;
        int boxC = c / DIM_ROOT;

        // Validate box
        for (int i = 0; i < DIM_ROOT; i++) {
            for (int j = 0; j < DIM_ROOT; j++) {
                if (board[DIM_ROOT * boxR + i][DIM_ROOT * boxC + j] == val)
                    return false;
            }
        }

        return true;
    }
}
