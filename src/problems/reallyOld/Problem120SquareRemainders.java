package problems.reallyOld;

/**
 * Created by Sage on 9/12/2015.
 *
 * This problem was a joke. Expand the terms of the series and sum, see what cancels.
 * After a little thinking left with 2 cases:
 *  1. a is odd - remainder is a*a - a
 *  2. a is even - remainder is a*a - 2*a
 * Simply sum these to get the correct result. Code is almost instantaneous.
 */
public class Problem120SquareRemainders {

    public static void main(String[] args) {
        long sum = 0;
        for (int i = 3; i <= 1000; i++) {
            if (i%2==0) {
                sum += i*i - 2*i;
            } else {
                sum += i*i - i;
            }
        }
        System.out.println("Resultant sum is: " + sum);
    }
}
