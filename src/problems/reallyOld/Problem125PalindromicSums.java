package problems.reallyOld;

import java.util.HashSet;
import java.util.Set;

/**
 * Easy
 */
public class Problem125PalindromicSums {

    private static final int CAP = 100000000;
    private static final int LIM = 10000;

    public static void main(String[] args) {
        long palindromes = 0;
        long sum;
        Set<Long> palindromeSet = new HashSet<>(); // for potential duplicates

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
        System.out.println(palindromes);
    }

    public static boolean isPalindrome(long x) {
        String num = String.valueOf(x);
        StringBuilder builder = new StringBuilder(num);
        return (num).equals(builder.reverse().toString());
    }
}
