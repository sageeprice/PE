package problems.reallyOld;

import java.util.Arrays;

/**
 * Could be cleaned up if clever, look at forums. Dynamic programming works though
 */
public class Problem158LexicStringOrder {

    private static final int LETTERS = 26;

    public static void main(String[] args) {
        // [number of digits][last digit]
        long[][] arrangements = new long[LETTERS][LETTERS];
        for (int i = 0; i < LETTERS; i++) {
            Arrays.fill(arrangements[i], 0);
        }

        arrangements[1][1] = 1;
        for (int i = 2; i < LETTERS; i++) {
            for (int j = 0; j <= i; j++) {
                if (j != 0)
                    arrangements[i][j] = 1;
                for (int k = j; k <= i; k++) {
                    arrangements[i][j] += arrangements[i-1][k];
                }
            }
        }
        long best = 0;
        long top = 0;
        for (int i = 0; i < LETTERS; i++) {
            long sum = 0;
            for (int j = 0; j <= i; j++) {
                sum += arrangements[i][j];
            }
            if (sum*nCp(LETTERS,i+1) > best) {
                best = sum*nCp(LETTERS, i+1);
            }
        }
        System.out.println(best);
    }

    public static long nCp(long n, long p) {
        long prod = 1;
        p = p>n-p ? n-p : p;
        for (int i = 1; i <= p; i++) {
            prod *= n-(i-1);
            prod /= i;
        }
        return prod;
    }
}
