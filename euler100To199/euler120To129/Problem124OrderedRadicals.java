package euler100To199.euler120To129;

import java.util.*;

/**
 * Is good.
 */
public class Problem124OrderedRadicals {

    private static int CAP = 100000;

    public static void main(String[] args) {
        int[] rads = new int[CAP+1];
        Arrays.fill(rads, 1);
        for (int i = 2; i <= CAP; i++) {
            if (rads[i] == 1) {
                for (int j = i; j <= CAP; j += i) {
                    rads[j] *= i;
                }
            }
        }
        Set<Pair> radSet = new TreeSet<>();
        for (int i = 1; i <= CAP; i++) {
            if (!radSet.add(new Pair(i, rads[i])))
                System.out.println("FAILED TO ADD: " + i);
        }
        List<Pair> list = new ArrayList<>(radSet);
        Pair answer = list.remove(9999);
        System.out.println(answer.getX());
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
