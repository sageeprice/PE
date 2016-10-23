package problems.euler025to050;

import problems.Problem;

/**
 * Problem 36:
 * https://projecteuler.net/problem=36
 *
 * Solved by Sage on 10/22/16.
 */
public class Euler036 implements Problem {

    @Override
    public String solve() {

        int sum = 0;

        // Note that given base 2 must be palindrome, number must be odd
        for (int i = 1; i < 1000000; i += 2) {
            if (isPalindrome(String.valueOf(i)) && isPalindrome(Integer.toBinaryString(i))) {
                sum += i;
            }
        }
        return String.valueOf(sum);
    }

    /**
     * Just compare to the reverse. Easiest way to do it is with StringBuilder
     * @param x number to check if palindromic
     * @return x is [not] a palindrome
     */
    private boolean isPalindrome(String x) {
        return new StringBuilder(x).reverse().toString().equals(String.valueOf(x));
    }
}
