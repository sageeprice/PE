package problems.impl;

import problems.Problem;

import java.util.HashSet;
import java.util.Set;

/**
 * Easy
 */
public class Euler125 implements Problem {

    private static final int CAP = 100000000;
    private static final int LIM = 10000;

    @Override
    public String solve() {
        long palindromes = 0;
        long sum;
        Set<Long> palindromeSet = new HashSet<>(); // for potential duplicates

        // Aggressively inefficient, but good enough.
        for (int i = 1; i < LIM; i++) {
            sum = i*i;
            for (int j = i+1; j < LIM; j++) {
                sum += j*j;
                if (sum >= CAP)
                    break;
                if (isPalindrome(sum) && !palindromeSet.contains(sum)) {
                    palindromes += sum;
                    palindromeSet.add(sum);
                }
            }
        }
        return String.valueOf(palindromes);
    }

    public static boolean isPalindrome(long x) {
        String num = String.valueOf(x);
        StringBuilder builder = new StringBuilder(num);
        return (num).equals(builder.reverse().toString());
    }
}
