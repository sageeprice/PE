package problems.euler051to075;

import problems.Problem;

import java.math.BigInteger;

/**
 * Problem 55:
 * https://projecteuler.net/problem=55
 *
 * Solved by Sage on 10/26/16.
 */
public class Euler055 implements Problem {

    @Override
    public String solve() {

        int lychrellCount = 0;
        for (int i = 1; i < 10000; i++) {
            if (isLychrell(i)) {
                lychrellCount++;
            }
        }

        return String.valueOf(lychrellCount);
    }

    private boolean isLychrell(long x) {
        BigInteger bigX = new BigInteger(String.valueOf(x));

        for (int i = 0; i < 49; i++) {
            bigX = bigX.add(new BigInteger(new StringBuilder(bigX.toString()).reverse().toString()));
            if (isPalindrome(bigX))
                return false;
        }
        return true;
    }

    private boolean isPalindrome(BigInteger x) {
        return x.toString().equals(new StringBuilder(x.toString()).reverse().toString());
    }
}
