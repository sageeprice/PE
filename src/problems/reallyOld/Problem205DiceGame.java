package problems.reallyOld;

/**
 * Created by Sage on 9/24/2015.
 */
public class Problem205DiceGame {

    public static void main(String[] args) {
        double total = Math.pow(4, 9) * Math.pow(6, 6);
        long sum = 0;
        long[] fours = new long[37];
        long[] sixes = new long[37];
        // lol
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) {
                for (int k = 1; k <= 6; k++) {
                    for (int l = 1; l <= 6; l++) {
                        for (int m = 1; m <= 6; m++) {
                            for (int n = 1; n <= 6; n++) {
                                sixes[i+j+k+l+m+n]++;
                            }
                        }
                    }
                }
            }
        }
        // lololololololol
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                for (int k = 1; k <= 4; k++) {
                    for (int l = 1; l <= 4; l++) {
                        for (int m = 1; m <= 4; m++) {
                            for (int n = 1; n <= 4; n++) {
                                for (int o = 1; o <= 4; o++) {
                                    for (int p = 1; p <= 4; p++) {
                                        for (int q = 1; q <= 4; q++) {
                                            fours[i+j+k+l+m+n+o+p+q]++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int i = 9; i <= 36; i++) {
            for (int j = 6; j < i; j++) {
                sum += fours[i]*sixes[j];
            }
        }
        System.out.println("Answer is: " + (sum / total));
    }
}
