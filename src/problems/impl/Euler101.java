package problems.euler101To125;

import problems.Problem;

/**
 * Created by Sage on 11/10/16.
 */
public class Euler101 implements Problem {

    @Override
    public String solve() {

        for (long i = 0; i < 11; i++) {
            long value = ((long)Math.pow(i, 11) + 1) / (i + 1);
            System.out.println(i + ": " + value);
        }
        return null;
    }
}
