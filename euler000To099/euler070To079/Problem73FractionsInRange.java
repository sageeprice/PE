package euler000To099.euler070To079;

import java.util.Set;
import java.util.TreeSet;

/**
 * Brute force, a bit slow, return to it.
 */
public class Problem73FractionsInRange {

    public static void main(String[] args) {
        Fraction half = new Fraction(1,2);
        Fraction third = new Fraction(1,3);
        Set<Fraction> fractions = new TreeSet<>();
        Fraction fraction;
        for (int i = 4; i <= 12000; i++) {
            for (int j = (i / 3); j <= i / 2; j++) {
                fraction = new Fraction(j, i);
                if (fraction.compareTo(third) > 0 && fraction.compareTo(half) < 0) {
                    fractions.add(fraction);
                }
            }
        }
        System.out.println("Number of valid fractions is: " + fractions.size());
    }

    private static class Fraction implements Comparable<Fraction> {
        private int num;
        private int den;

        public Fraction(int num, int den) {
            this.num = num;
            this.den = den;
        }

        public String toString() {
            return num + "/" + den;
        }

        public boolean equals(Fraction o) {
            if (getNum() * o.getDen() == getDen() * o.getNum()) {
                return true;
            }
            return false;
        }

        @Override
        public int compareTo(Fraction o) {
            int left = getNum() * o.getDen();
            int right = getDen() * o.getNum();
            if (left > right) {
                return 1;
            } else if (left < right) {
                return -1;
            }
            return 0;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getDen() {
            return den;
        }

        public void setDen(int den) {
            this.den = den;
        }
    }
}
