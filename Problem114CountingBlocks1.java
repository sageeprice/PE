package euler100To199.euler110To119;

/**
 * Created by Sage on 6/12/2017.
 */
public class Problem114CountingBlocks1 {

    private static final int LENGTH = 50;

    public static void main(String[] args) {

        long[] o = new long[LENGTH];
        long[] or = new long[LENGTH];
        long[] orr = new long[LENGTH];
        long[] r = new long[LENGTH];

        o[2] = 1;
        or[2] = 1;
        orr[2] = 1;
        r[2] = 1;

        for (int i = 3; i < LENGTH; i++) {
            o[i] = o[i-1] + r[i-1];
            or[i] = o[i-1];
            orr[i] = or[i-1];
            r[i] = r[i-1] + orr[i-1];
        }

        System.out.println("Total combos is: " + (o[LENGTH-1] + r[LENGTH-1]));
    }
}
