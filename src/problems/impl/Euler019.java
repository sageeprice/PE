package problems.impl;

import problems.Problem;

import java.util.Calendar;

/**
 * Problem 19:
 * How many Sundays fell on the first of the month during
 * the twentieth century (1 Jan 1901 to 31 Dec 2000)?
 *
 * Solved by Sage on 10/14/16.
 */
public class Euler019 implements Problem {

    private static final int FIRST_YEAR = 1901;
    private static final int FINAL_YEAR = 2000;
    private static final int FIRST_MONTH = 1;
    private static final int FINAL_MONTH = 12;

    @Override
    public String solve() {

        // Blatantly exploiting the Java calendar
        Calendar calendar = Calendar.getInstance();
        int sundayCount = 0;

        for (int y = FIRST_YEAR; y <= FINAL_YEAR; y++) {
            for (int m = FIRST_MONTH; m <= FINAL_MONTH; m++) {
                calendar.set(y,m,1);
                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    sundayCount++;
                }
            }
        }

        return String.valueOf(sundayCount);
    }
}
