package problems;

import problems.euler025to050.Euler030;

/**
 * Created by Sage on 10/7/16.
 */
public class Runner {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println(new Euler030().solve());
        System.out.println("Time to compute was " + (System.currentTimeMillis()-startTime) + " milliseconds.");
    }
}
