package problems.impl;

import problems.Problem;

/**
 * Created by Sage on 9/13/2015.
 */
public class Euler117 implements Problem {

    private static int BOARD_SIZE = 50;

    @Override
    public String solve() {
        long[] board = new long[BOARD_SIZE + 1];
        board[0] = 1;
        board[1] = 1;
        board[2] = 2;
        board[3] = 4;
        for (int i = 4; i <= BOARD_SIZE; i++) {
            board[i]= board[i-1] + board[i-2] + board[i-3] + board[i-4];
        }
        return String.valueOf(board[50]);
    }
}
