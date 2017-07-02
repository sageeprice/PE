package problems.impl;

import problems.Problem;

import java.math.BigInteger;

/**
 * Problem 16:
 * What is the sum of the digits of the number 2^1000?
 *
 * Created by Sage on 10/8/16.
 */
public class Euler016 implements Problem {

    @Override
    public String solve() {
        // BigInteger trivializes this. I don't think there is a faster way to do this.
        String twoToTheOneThousand = BigInteger.ONE.shiftLeft(1000).toString();
        int sum = 0;
        for (int i = 0; i < twoToTheOneThousand.length(); i++) {
            sum += twoToTheOneThousand.charAt(i) - '0';
        }
        return String.valueOf(sum);
    }
}
