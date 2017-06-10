package euler100To199.euler160To169;

/**
 * DP so easy
 */
public class Problem164 {

    public static void main(String[] args) {
        long[][][] numbers = new long[20][10][10];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    numbers[i][j][k] = 0;
                }
            }
        }

        // initialize first
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i+j < 10) {
                    numbers[1][i][j] = 1;
                }
            }
        }

        for (int i = 2; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    for (int l = 0; l < 10; l++) {
                        if (j+k+l < 10) {
                            numbers[i][k][l] += numbers[i-1][j][k];
                        }
                    }
                }
            }
        }
        long sum = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                sum += numbers[19][i][j];
            }
        }
        System.out.println(sum);
    }
}
