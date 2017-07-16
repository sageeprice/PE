package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Generate possibilities, eliminate duplicates.
 */
public class Euler109 implements Problem {

    @Override
    public String solve() {

        List<Point> hits = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 20; j++) {
                hits.add(new Point(j, i));
            }
        }
        hits.add(new Point(25, 1));
        hits.add(new Point(25, 2));
        hits.add(new Point(0, 0));

        Set<Game> checkoutGames = new HashSet<>();
        for (Point hitOne : hits) {
            if (hitOne.getValue() == 0) {
                continue;
            }
            for (Point hitTwo : hits) {
                for (Point hitThree : hits) {
                    if (hitTwo.getValue() == 0 && hitThree.getValue() != 0) {
                        continue;
                    }
                    Game game = new Game(hitOne, hitTwo, hitThree);
                    if (game.getScore() < 100 && game.isCheckout()) {
                        // Games where the order of non-final tosses are inverted
                        // are thought of as duplicates, should be ignored.
                        Game flipGame = new Game(hitTwo, hitOne, hitThree);
                        if (checkoutGames.contains(flipGame) && game.getThird().getValue() != 0) {
                            continue;
                        }
                        checkoutGames.add(game);
                    }
                }
            }
        }

        return String.valueOf(checkoutGames.size());
    }

    private class Point {

        private int section;
        private int multiplier;

        public Point(int section, int multiplier) {
           this.section = section;
           this.multiplier = multiplier;
        }

        public int getSection() {
            return section;
        }

        public int getMultiplier() {
            return multiplier;
        }

        public int getValue() {
            return section * multiplier;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;

            Point point = (Point) o;

            if (getSection() != point.getSection()) return false;
            return getMultiplier() == point.getMultiplier();
        }

        @Override
        public int hashCode() {
            int result = getSection();
            result = 31 * result + getMultiplier();
            return result;
        }
    }

    private class Game {
        private Point first;
        private Point second;
        private Point third;

        public Game(Point first, Point second, Point third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        public Point getFirst() {
            return first;
        }

        public Point getSecond() {
            return second;
        }

        public Point getThird() {
            return third;
        }

        public boolean isCheckout() {
            return third.getMultiplier() == 2
                    || (third.getSection() == 0 && second.getMultiplier() == 2)
                    || (third.getSection() == 0 && second.getSection() == 0 && first.getMultiplier() == 2);
        }

        public int getScore() {
            return first.getValue() + second.getValue() + third.getValue();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Game)) return false;

            Game game = (Game) o;

            if (!getFirst().equals(game.getFirst())) return false;
            if (!getSecond().equals(game.getSecond())) return false;
            return getThird().equals(game.getThird());
        }

        @Override
        public int hashCode() {
            int result = getFirst().hashCode();
            result = 31 * result + getSecond().hashCode();
            result = 31 * result + getThird().hashCode();
            return result;
        }
    }
}
