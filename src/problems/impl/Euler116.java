package problems.impl;

import problems.Problem;

public class Euler116 implements Problem {

    private static int BOARD_LENGTH = 50;
    private static int RED_TILES = 2;
    private static int GREEN_TILES = 3;
    private static int BLUE_TILES = 4;

    @Override
    public String solve() {
        long sum = 0;
        sum += tile(BOARD_LENGTH, RED_TILES);
        sum += tile(BOARD_LENGTH, GREEN_TILES);
        sum += tile(BOARD_LENGTH, BLUE_TILES);
        return String.valueOf(sum);
    }

    public static long tile(long x, long y) {
        long tilings = 0;
        for (long i = 1; i <= x / y; i++) {
            tilings += nCp(BOARD_LENGTH - i * (y - 1), i);
        }
        return tilings;
    }

    public static long nCp(long n, long p) {
        long prod = 1;
        p = p > n - p ? n - p : p;
        for (int i = 1; i <= p; i++) {
            prod *= n - (i - 1);
            prod /= i;
        }
        return prod;
    }
}
