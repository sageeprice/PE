package problems.reallyOld;

/**
 * Created by Sage on 9/10/2015.
 *
 * Basic combinatorics problem.
 *
 * Solution found in under a second, answer is 2772.
 */
public class Problem85CountingRectangles {

    public static void main(String[] args) {

        long bestDiff = 1000000;
        int diff;

        int x = 2000;
        int y = 2;
        int numRectangles;
        while (x >= y) {
            numRectangles = x*(x+1)*y*(y+1)/4;
            diff = Math.abs(2000000 - numRectangles);
            if (diff < bestDiff) {
                System.out.println("New best: " + numRectangles + " occurring at (" + x +", " + y + ") area of " + x*y);
                bestDiff = diff;
            }
            if (numRectangles > 2000000) {
                x--;
            } else {
                y++;
            }
        }
    }
}
