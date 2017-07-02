package problems.euler025to050;

import problems.Problem;

/**
 * Problem 40:
 * https://projecteuler.net/problem=40
 *
 * Solved by Sage on 10/23/16.
 */
public class Euler040 implements Problem {

    private static final int LIMIT = 1000000;

    /**
     * Could also clean this whole thing up by batching the digits of same length
     * together when appending to the irrational number.
     * @return 210 = 1*1*5*3*7*2*1
     */
    @Override
    public String solve() {

        int product = 1; // the product of the relevant digit
        int breakpoint = 1; // the index of the digit we want to check
        int irraLen = 0; // if it were a string, the length of the irrational
        int i = 1; // the current number to append to the irrational

        // Keep going until we've checked all breakpoints
        while (breakpoint <= LIMIT) {
            // Add numbers to the end of the irrational until we pass a breakpoint
            while (irraLen < breakpoint) {
                irraLen += String.valueOf(i++).length();
            }
            // Check the previous number added to the irrational
            String number = String.valueOf(i-1);
            // Multiply by the character at the breakpoint, some char math
            product *= number.charAt(number.length() - (irraLen - breakpoint) - 1) - '0';
            // Increment breakpoint
            breakpoint *= 10;
        }

        return String.valueOf(product);
    }
}
