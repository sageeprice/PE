package problems.reallyOld;

/**
 * Created by Sage on 9/13/2015.
 *
 * The brute force approach, still works very quickly. Go check Euler117 for nice version.
 */
public class Problem117RGBTilesPlus {

    private static int BOARD_LENGTH = 50;
    private static int RED_TILES = 2;
    private static int GREEN_TILES = 3;
    private static int BLUE_TILES = 4;

    public static void main(String[] args) {
        long sum = 0;
        sum += tile(BOARD_LENGTH, RED_TILES, GREEN_TILES, BLUE_TILES);
        System.out.println("There are " + sum + " ways to tile.");
    }

    public static long tile(long board, long red, long green, long blue) {
        long tilings = 0;
        long spaces;
        for (int r = 0; r <= board/red; r++) {
            for (int g = 0; g <= (board-(red)*r)/green; g++) {
                for (int b = 0; b <= (board - (red)*r - (green)*g)/blue; b++) {
                    System.out.println("Calculating (r,g,b): "+r+", "+g+", "+b);
                    spaces = board - (red-1)*r - (green-1)*g - (blue-1)*b;
                    tilings += nCp(spaces, r)*nCp(spaces-r,g)*nCp(spaces-r-g,b);
                }
            }
        }
        return tilings;
    }

    public static long nCp(long n, long p) {
        if (p==0)
            return 1;
        long prod = 1;
        p = p>n-p ? n-p : p;
        for (int i = 1; i <= p; i++) {
            prod *= n-(i-1);
            prod /= i;
        }
        return prod;
    }
}
