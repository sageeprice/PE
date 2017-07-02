package problems.reallyOld;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Problem83PathSum4 {

    private final static int MTRX_SIZE = 80;
    private static final String FILE_NAME = "C:\\Users\\Sage\\workspace\\SageKatas\\eulerTxtFiles\\p083_matrix.txt";

    public static void main(String[] args) throws Exception {

        int[][] matrix = new int[MTRX_SIZE][MTRX_SIZE];
        int[][] route = new int[MTRX_SIZE][MTRX_SIZE];
        int moves = 0; // just run it a couple of times to get convergence
        int w,x,y,z;


        int row = 0;
        for (String line : Files.readAllLines(Paths.get(FILE_NAME))) {
            String[] nums = line.split(",");
            for (int i = 0; i < MTRX_SIZE; i++) {
                route[row][i] = Integer.valueOf(nums[i]);
            }
            row++;
        }

        for (int i = 0; i < MTRX_SIZE; i++) {
            for (int j = 0; j < MTRX_SIZE; j++) {
                matrix[i][j] = Integer.MAX_VALUE - 20000;
            }
        }

        matrix[0][0] = route[0][0];
        System.out.println(matrix[0][0]);
        while (moves < 4) {
            for (int i = 1; i < MTRX_SIZE; i++) {
                // first row
                w = route[i][0] + matrix[i-1][0];
                y = i == MTRX_SIZE-1 ? Integer.MAX_VALUE : route[i][0] + matrix[i+1][0];
                z = route[i][0] + matrix[i][1];
                matrix[i][0] = Math.min(Math.min(w,y),z);
                // first column
                x = route[0][i] + matrix[0][i-1];
                y = route[0][i] + matrix[1][i];
                z = i == MTRX_SIZE-1 ? Integer.MAX_VALUE : route[0][i] + matrix[0][i+1];
                matrix[0][i] = Math.min(Math.min(x,y),z);
            }
            for (int i = 1; i < MTRX_SIZE-1; i++) {
                for (int j = 1; j < MTRX_SIZE-1; j++) {
                    w = route[i][j] + matrix[i-1][j];
                    x = route[i][j] + matrix[i][j-1];
                    y = route[i][j] + matrix[i+1][j];
                    z = route[i][j] + matrix[i][j+1];
                    matrix[i][j] = Math.min(Math.min(w,x),Math.min(y,z));
                }
            }
            for (int i = 1; i < MTRX_SIZE; i++) {
                // last row
                w = route[i][MTRX_SIZE-1] + matrix[i-1][MTRX_SIZE-1];
                x = route[i][MTRX_SIZE-1] + matrix[i][MTRX_SIZE-2];
                y = i == MTRX_SIZE-1 ? Integer.MAX_VALUE : route[i][MTRX_SIZE-1] + matrix[i+1][MTRX_SIZE-1];
                matrix[i][MTRX_SIZE-1] = Math.min(Math.min(w,x),y);
                // last column
                w = route[MTRX_SIZE-1][i] + matrix[MTRX_SIZE-2][i];
                x = route[MTRX_SIZE-1][i] + matrix[MTRX_SIZE-1][i-1];
                z = i == MTRX_SIZE-1 ? Integer.MAX_VALUE : route[MTRX_SIZE-1][i] + matrix[MTRX_SIZE-1][i+1];
                matrix[MTRX_SIZE-1][i] = Math.min(Math.min(w,x),z);
            }
            moves++;
        }
        for (int i = 0; i < MTRX_SIZE; i++) {
            for (int j = 0; j < MTRX_SIZE; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("Answer is: " + matrix[MTRX_SIZE-1][MTRX_SIZE-1]);
    }
}
