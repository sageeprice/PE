package problems;

import problems.impl.Euler183;

/** Runner class to execute Project Euler solutions. */
public class Runner {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println(new Euler183().solve());
        System.out.println("Time to compute was " + (System.currentTimeMillis() - startTime) + " milliseconds.");
    }
}
