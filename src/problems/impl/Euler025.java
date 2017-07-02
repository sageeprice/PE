package problems.impl;

import problems.Problem;

/**
 * Created by Sage on 10/16/16.
 */
public class Euler025 implements Problem {

    private static final double PHI = 1.6180339887;
    private static final int DIGITS = 999;

    // Phi^n approximates Fibonacci numbers. We want the
    // number of digits, so take log base 10 and keep
    // adding until the value reaches number of digits wanted.
    @Override
    public String solve() {
        double logPhi = Math.log10(PHI);
        double digits = logPhi;
        int count = 2;
        while (digits < DIGITS) {
            digits += logPhi;
            count++;
        }
        return String.valueOf(count);
    }
}
