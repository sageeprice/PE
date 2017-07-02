package problems.reallyOld;

/**
 * Created by Sage on 10/6/2015.
 */
public class Problem109Darts {

    private static final int HIGH = 100;
    private static final int BULL = 25;
    private static final int ZONES = 20;
    private static final int TEST = 6;

    public static void main(String[] args) {
        int[] oneThrow = new int[HIGH+1];
        int[] twoThrow = new int[HIGH+1];
        int[] threeThrow = new int[HIGH+1];

        int testCheckouts = 0;

        int checkouts = 0;
        for (int i = 1; i <= Math.min(HIGH,ZONES); i++) {
            oneThrow[i] += 1;
            if (2*i < HIGH) {
                oneThrow[2 * i] += 1;
                checkouts += 1;
                if (2*i == TEST) {
                    testCheckouts += 1;
                    System.out.println("One: 2*" + i + ": " + oneThrow[2 * i] + " - " + testCheckouts);
                }
            }
            if (3*i < HIGH) {
                oneThrow[3 * i] += 1;
            }
        }
        if (HIGH > 2*BULL) {
            oneThrow[BULL] += 1;
            oneThrow[BULL] += 1;
        }
        for (int i = 0; i < HIGH; i++) {
            for (int j = 1; j <= ZONES; j++) {
                if (i + j < HIGH) {
                    twoThrow[i + j] += oneThrow[i];
                }
                if (i + 2*j < HIGH && 2*j <= i) {
                    twoThrow[i + 2*j] += oneThrow[i];
                    if (i + 2*j == TEST) {
                        testCheckouts += oneThrow[i];
                        System.out.println("Two: " + i + ", " + j + ": " + oneThrow[i] + " - " + testCheckouts);
                    }
                    checkouts += oneThrow[i];
                }
                if (i + 3*j < HIGH && 3*j <= i) {
                    twoThrow[i + 3*j] += oneThrow[i];
                }
            }
            if (i + BULL < HIGH) {
                twoThrow[i+BULL] += oneThrow[i];
            }
            if (i + 2*BULL < HIGH) {
                twoThrow[i+2*BULL] += oneThrow[i];
                checkouts += oneThrow[i];
            }
        }
        for (int i = 1; i < HIGH; i++) {
            for (int j = 1; j <= ZONES; j++) {
                if (i + 2*j < HIGH && 2*j <= i) {
                    threeThrow[i + 2*j] += twoThrow[i];
                    if (i + 2*j == TEST) {
                        testCheckouts += twoThrow[i];
                        System.out.println("Three: " + i + ", 2*" + j + ": " + twoThrow[i] + " - " + testCheckouts);
                    }
                    checkouts += twoThrow[i];
                }
            }
            if (i + BULL < HIGH) {
                threeThrow[i+BULL] += twoThrow[i];
            }
            if (i + 2*BULL < HIGH) {
                threeThrow[i+2*BULL] += twoThrow[i];
                checkouts += twoThrow[i];
            }
        }
        for (int i = 0; i < HIGH; i++) {
            System.out.println(i + ": " + oneThrow[i] + ", " + twoThrow[i] + ", " + threeThrow[i]);
        }
        System.out.println("Total number of checkout is: " + checkouts);
    }
}
