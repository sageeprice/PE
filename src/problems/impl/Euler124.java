package problems.impl;


import problems.Problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Is good.
 */
public class Euler124 implements Problem {

    private static int CAP = 100000;

    @Override
    public String solve() {
        int[] rads = new int[CAP+1];
        Arrays.fill(rads, 1);
        // Same idea as a sieve of Eratosthenes.
        for (int i = 2; i <= CAP; i++) {
            if (rads[i] == 1) {
                for (int j = i; j <= CAP; j += i) {
                    rads[j] *= i;
                }
            }
        }
        Set<Pair> radSet = new TreeSet<>();
        for (int i = 1; i <= CAP; i++) {
            radSet.add(new Pair(i, rads[i]));
        }
        List<Pair> list = new ArrayList<>(radSet);
        Pair answer = list.remove(9999);
        return String.valueOf(answer.getX());
    }

    private static class Pair implements Comparable<Pair> {

        private int x;
        private int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Pair b) {
            if (this.getY() < b.getY()) {
                return -1;
            } else if (this.getY() > b.getY()){
                return 1;
            } else if (this.getY() == b.getY()) {
                if (this.getX() < b.getY())
                    return -1;
                else
                    return 1;
            } else {
                System.out.println("ERROR");
                return 0;
            }
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
