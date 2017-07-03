package problems.impl;

import problems.Problem;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Problem 80:
 * https://projecteuler.net/problem=80
 *
 * Solved by Sage on 11/1/16.
 */
public class Euler080 implements Problem {

    private static final BigDecimal TWO = new BigDecimal(2);

    @Override
    public String solve() {

        int sum = 0;
        Set<Integer> squares = new HashSet<>();
        for (int i = 1; i <= 10; i++) {
            squares.add(i * i);
        }

        for (int x = 2; x < 100; x++) {
            if (squares.contains(x))
                continue;
            BigDecimal guess = new BigDecimal(x / 2);
            BigDecimal square = new BigDecimal(x);
            for (int i = 2; i < 20; i++) {
                guess = newtonSqrt(guess, square);
            }
            System.out.println(guess);
            String num = guess.toPlainString().replace(".", "").substring(0,100);
            for (char c : num.toCharArray()) {
                sum += c - '0';
            }
        }
        return String.valueOf(sum);
    }

    // Iterative approximation of square root using Newton's method
    //
    // https://en.wikipedia.org/wiki/Newton%27s_method#Square_root_of_a_number
    private BigDecimal newtonSqrt(BigDecimal x, BigDecimal sq) {
        return x.subtract(
                x.multiply(x)
                        .subtract(sq)
                        .divide(TWO.multiply(x),
                                150,
                                BigDecimal.ROUND_DOWN));
    }

}
