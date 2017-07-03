package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sage on 9/6/2015.
 *
 * Answer is 10-15-24, increase NUM_TURNS to increase accuracy,
 * though it is fairly accurate for 100,000.
 */
public class Euler084 implements Problem {

    private static int BOARD_SIZE = 40;
    private static int DIE_PIPS = 4;
    private static int NUM_TURNS = 100000;

    @Override
    public String solve() {
        Integer[] board = new Integer[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = 0;
        }
        Roller roller = new Roller();

        // initially begin at GO
        int place = 0;

        for (int i = 0; i < NUM_TURNS; i++) {
            place += roller.roll();
            place %= BOARD_SIZE;

            // straight to jail
            if (place == 30 || roller.jailed()) {
                place = 10;
            }
            // chance
            else if (place == 7 || place == 22 || place == 36) {
                int ch = (int) (Math.random() * 16);
                switch (ch) {
                    case 0:
                        place = 0;
                        break;
                    case 1:
                        place = 10;
                        break;
                    case 2:
                        place = 11;
                        break;
                    case 3:
                        place = 24;
                        break;
                    case 4:
                        place = 39;
                        break;
                    case 5:
                        place = 5;
                        break;
                    case 6:
                        if (place == 7) {
                            place = 15;
                        } else if (place == 22) {
                            place = 25;
                        } else {
                            place = 5;
                        }
                        break;
                    case 7:
                        if (place == 7) {
                            place = 15;
                        } else if (place == 22) {
                            place = 25;
                        } else {
                            place = 5;
                        }
                        break;
                    case 8:
                        if (place == 22) {
                            place = 28;
                        } else {
                            place = 12;
                        }
                        break;
                    case 9:
                        place += 37;
                        place %= BOARD_SIZE;
                        break;
                }
            }

            // community chest, must be separate if and come after cc
            // due to chance of moving back 3 spots
            if (place == 2 || place == 17 || place == 33) {
                int cc = (int) (Math.random() * 16);
                if (cc == 0) {
                    place = 0;
                }
                else if (cc == 1) {
                    place = 10;
                }
            }
            board[place]++;
        }
        Map<Integer, Integer> boardMap = new HashMap<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
           boardMap.put(board[i], i);
        }
        List<Object> landings = Arrays.asList(boardMap.keySet().toArray());
        List<Integer> boardList = new ArrayList<>();
        for (Object landing : landings) {
            boardList.add((int) landing);
        }
        boardList.sort(Integer::compareTo);
        return String.valueOf(
                10000 * boardMap.get(boardList.get(BOARD_SIZE - 1))
                        + 100 * boardMap.get(boardList.get(BOARD_SIZE - 2))
                        + boardMap.get(boardList.get(BOARD_SIZE - 3)));
    }

    private static void printBoard(Integer[] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.println(i + ": " + ((double) board[i])/NUM_TURNS);
        }
    }

    /**
     * Utility class to provide dice rolls
     */
    private static class Roller {

        private int offToJail;

        public Roller() {
            this.offToJail = 0;
        }

        private boolean jailed() {
            if (offToJail == 3) {
                offToJail = 0;
                return true;
            }
            return false;
        }

        /**
         * rolls two 4-sided dice
         * @return int sum of two rolls of a die
         */
        private int roll() {
            int d1 = rollDice();
            int d2 = rollDice();
            if (d1==d2) {
                offToJail++;
            } else {
                offToJail = 0;
            }
            return d1 + d2;
        }

        /**
         * rolls one 4-sided die
         * @return int in range [1, DIE_PIPS]
         */
        private int rollDice() {
            return (int) (Math.random() * DIE_PIPS + 1);
        }
    }
}
