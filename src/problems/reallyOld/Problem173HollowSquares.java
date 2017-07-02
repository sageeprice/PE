package problems.reallyOld;

/**
 * Created by Sage on 10/24/2015.
 */
public class Problem173HollowSquares {

    private static final long MAX_TILES = 1000000;

    public static void main(String[] args) {
        long count = 0;
        long x = 3;
        while (4*(x-1) <= MAX_TILES) {
            for (long i = x-2; i > 0; i -= 2) {
                if (x*x - i*i <= MAX_TILES) {
                    count++;
                } else {
                    break;
                }
            }
            x += 1;
        }
        System.out.println(count);
    }
}