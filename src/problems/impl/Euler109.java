package problems.euler101To125;

import problems.Problem;

import java.util.Arrays;

/**
 * Created by Sage on 11/15/16.
 */
public class Euler109 implements Problem {

    private static final int ROLLS = 3;
    private final int LIMIT = 100;
    private final int POINT_LIMIT = 20;
    private static final int MAX_MULTIPLIER = 3;

    @Override
    public String solve() {

        int count = 0;
        int sixCount = 0;
        int[] noRolls = new int[LIMIT];

        // first roll
        int[][] oneRoll = new int[MAX_MULTIPLIER][LIMIT];
        for (int j = 1; j < POINT_LIMIT; j++) {
            for (int m = 1; m <= MAX_MULTIPLIER; m++) {
                oneRoll[m-1][j * m] += 1;
                if (m == 2) {
                    count++;
                    if (m*j == 6) {
                        System.out.println("6 = " + m + "*" + j);
                        sixCount++;
                    }
                }
            }
        }

        for (int[] row : oneRoll) {
            System.out.println(Arrays.toString(row));
        }

        // second roll
        int[] twoRolls = new int[LIMIT];
        for (int j = 1; j < POINT_LIMIT; j++) {
            for (int m = 1; m <= MAX_MULTIPLIER; m++) {
                for (int r = 0; r < MAX_MULTIPLIER; r++) {
                    if (m + r == 3 && r + 1 > m)
                        continue;
                    for (int i = 1; i < LIMIT; i++) {
                        if (i + j * m >= LIMIT) {
                            break;
                        }
                        if (m == 2) {
                            if (m * j + i == 6) {
                                System.out.println(r+1 + ", " + m);
                                System.out.println("6 = " + m + "*" + j + "+" + i + " => " + oneRoll[r][i]);
                                sixCount += oneRoll[r][i];
                            }
                            count += oneRoll[r][i];
                        }
                        twoRolls[i + j * m] += oneRoll[r][i];
                    }
                }
            }
        }

        System.out.println(2 + ": " + Arrays.toString(twoRolls));

        System.out.println(6 + ": " + sixCount);
        // third roll
        for (int j = 1; j < POINT_LIMIT; j++) {
            for (int i = 2; i < LIMIT; i++) {
                if (i + j * 2 >= LIMIT)
                    break;
                if (2 * j + i < LIMIT) {
                    count += twoRolls[i];
                    if (2*j+i == 6) {
                        System.out.println("6 = " + 2 + "*" + j + "+" + i + " => " + twoRolls[i]);
                        sixCount += twoRolls[i];
                    }
                }
            }
        }

        System.out.println(6 + ": " + sixCount);
        return String.valueOf(count);
    }
}
