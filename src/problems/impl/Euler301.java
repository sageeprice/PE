package problems.impl;

import problems.Problem;

/**
 * XOR brute force. Forums say you can use Fibonacci but for this scale
 * whatever, XOR is fast enough.
 *
 * Answer: 2178309
 */
public class Euler301 implements Problem {

    @Override
    public String solve() {
        long wins = 0;
        long cap = (long) Math.pow(2, 30);
        for (int i = 1; i <= cap; i++) {
            wins += (i ^ (2 * i) ^ (3 * i)) == 0 ? 1 : 0;
        }
        return String.valueOf(wins);
    }
}
