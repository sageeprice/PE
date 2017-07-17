package problems;

import problems.incomplete.Euler174;

/** Runner class to execute Project Euler solutions. */
public class Runner {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println(new Euler174().solve());
        System.out.println("Time to compute was " + (System.currentTimeMillis() - startTime) + " milliseconds.");
    }
}
