package problems.impl;

import problems.Problem;

/**
 * Created by Sage on 9/15/2015.
 *
 * Slow but usable, ~10 seconds. Brute force. Slight savings using an
 * array to store checked values.
 */
public class Euler145 implements Problem {

    @Override
    public String solve() {
        boolean[] checked = new boolean[1_000_000_000];
        int rev;
        int count = 0;
        for (int i = 100_000_000, j = 1; i < 1_000_000_000; i += 100_000_000, j++) {
           for (int k = j % 2; k < 100_000_000; k += 2) {
               checked[i+k] = true;
           }
        }
        for (int i = 12; i < 1_000_000_000; i++) {
            if (checked[i]) continue;
            checked[i] = true;
            rev = reverse(i);
            checked[rev] = true;
            if (rev==0)
                continue;
            if (allOdd(rev+i)) {
                count += 2;
            }
        }
        return String.valueOf(count);
    }

    private static int reverse(int num) {
        int rev = 0;
        // can't allow leading 0's
        if (num%10==0)
            return 0;
        while (num>0) {
            rev*=10;
            rev+= num%10;
            num/=10;
        }
        return rev;
    }

    private static boolean allOdd(int num) {
        while (num>0) {
            if (num%2==0)
                return false;
            num/=10;
        }
        return true;
    }
}
