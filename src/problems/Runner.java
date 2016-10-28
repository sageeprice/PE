package problems;

import problems.euler051to075.Euler059;

/**
 * Created by Sage on 10/7/16.
 */
public class Runner {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println(new Euler059().solve());
        System.out.println("Time to compute was " + (System.currentTimeMillis()-startTime) + " milliseconds.");
    }
}
