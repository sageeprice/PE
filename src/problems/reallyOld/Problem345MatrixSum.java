package problems.reallyOld;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Sage on 9/14/2015.
 *
 * TODO: make it work. Algorithm must be wrong. Probably need some dynamic shit.
 * XXX: Look into Held-Karp algorithm
 */
public class Problem345MatrixSum {

    private static final int M_SIZE = 15;

    public static void main(String[] args) throws IOException {
        int lNum = 0;
        int sum = 0;
        int[][] matrix = new int[M_SIZE][M_SIZE];

        // read it in
        for (String line : Files.readAllLines(Paths.get("C:\\Users\\Sage\\workspace\\SageKatas\\eulerTxtFiles\\p345_matrix.txt"))) {
            String[] nums = line.split(" ");
            for (int i = 0; i<M_SIZE; i++) {
                matrix[lNum][i] = Integer.valueOf(nums[i]);
            }
            lNum++;
        }

        boolean[] rows = new boolean[M_SIZE];
        boolean[] cols = new boolean[M_SIZE];
        for (int i = 0; i< M_SIZE;i++) {
            rows[i] = false;
            cols[i] = false;
        }

        int dropoff,r,c,hi,hi2,hiR,hiC, locR, locC;
        for (int i = 0; i < M_SIZE; i++) {
            dropoff=0;
            hi=0;
            hi2=0;
            locC=0;
            locR=0;
            hiR=0;
            hiC=0;
            // try each row, find biggest drop off
            for (r = 0; r < M_SIZE; r++) {
                if (rows[r]) {
                    continue;
                }
//                System.out.println("Trying row "+r);
                for (c = 0; c < M_SIZE; c++) {
                    if (cols[c]) {
                        continue;
                    }
                    if (matrix[r][c] > hi) {
                        hi = matrix[r][c];
                        locR = r;
                        locC = c;
                    }
                }
                for (c = 0; c < M_SIZE; c++) {
                    if (cols[c]) {
                        continue;
                    }
                    if (matrix[r][c] > hi2 && matrix[r][c] != hi) {
                        hi2 = matrix[r][c];
                    }
                }
                if (hi - hi2 > dropoff) {
                    dropoff = hi -hi2;
                    hiR = locR;
                    hiC = locC;
//                    System.out.println("New high dropoff ROW: "+dropoff);
                }
            }
            hi = 0;
            hi2 = 0;
            // try each column, find biggest dropoff
            for (c = 0; c < M_SIZE; c++) {
                if (cols[c]) {
                    continue;
                }
//                System.out.println("trying column "+c);
                for (r = 0; r < M_SIZE; r++) {
                    if (rows[r]) {
                        continue;
                    }
                    if (matrix[r][c] > hi) {
                        hi = matrix[r][c];
                        locR = r;
                        locC = c;
                    }
                }
                for (r = 0; r < M_SIZE; r++) {
                    if (rows[r]) {
                        continue;
                    }
                    if (matrix[r][c] > hi2 && matrix[r][c] != hi) {
                        hi2 = matrix[r][c];
                    }
                }
                if (hi - hi2 > dropoff) {
                    dropoff = hi - hi2;
                    hiR = locR;
                    hiC = locC;
//                    System.out.println("New high dropoff COLUMN: "+dropoff);
//                    System.out.println("Hi, hi2 of: "+hi+", "+hi2);
                }
            }
//            System.out.println("Selected "+matrix[hiR][hiC]+" from ("+hiR+", "+hiC+"), dropoff of: "+ dropoff);
            sum += matrix[hiR][hiC];
            rows[hiR] = true;
            cols[hiC] = true;
        }
        System.out.println("Total is: "+sum);
    }
}
