package problems.reallyOld;

/**
 * Created by Sage on 9/12/2015.
 *
 * The problem is simply asking for the number of partitions of n, minus 1.
 * Thus we iteratively produce the number of partitions for 1 through n and output
 * the final value less 1. Code ctrl+c ctrl+v'ed from Problem78.
 *
 * Answer is 190569291, almost instantaneous.
 */
public class Problem76CountingSummations {

    public static void main(String[] args) {
        // 101 due to array things
        int n = 101;
        long rowSum = 1;
        long sum;
        long[][] parts = new long[n][];

        // initialize array
        for (int i = 0; i< n; i++) {
            parts[i] = new long[i+1];
            for (int j = 0; j < i+1; j++) {
                parts[i][j] = 0;
            }
        }

        // start condition
        parts[1][1] = 1;

        for (int index = 2; index < n; index ++) {
            parts[index][index] = 1;
            rowSum = 1;
            for (int j = 1; j <= index / 2; j++) {
                sum = 0;
                for (int k = j; k <= index - j; k++) {
                    sum += parts[index - j][k];
                }
                rowSum += sum;
                parts[index][j] = sum;
            }
        }
        // Subtract one to exclude 100 = 100
        System.out.println("Final entry was: " + (rowSum-1) + " at location " + (n-1));
    }
}
