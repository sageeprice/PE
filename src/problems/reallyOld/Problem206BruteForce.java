package problems.reallyOld;

/**
 * Created by Sage on 9/14/2015.
 *
 * Dumb brute force
 */
public class Problem206BruteForce {

    private static final long NUM = 123456789;
    private static long[] mergeArray = new long[19];

    public static void main(String[] args) {
        for (long i = 0; i < 100000000; i+=1) {
            long x = merge(i);
            long y = (long) Math.floor(Math.sqrt(x));
            if (y*y == x) {
                System.out.println("Answer is: "+y+"0");
                break;
            }
        }
    }

    private static long merge (long x) {
        long y = NUM;
        for (int i = 9; i >= 1; i--) {
            mergeArray[2*i] = y%10;
            y/=10;
            mergeArray[2*i-1] = x%10;
            x/=10;
        }
        mergeArray[0] = y;
        x=0;
        for (int i = 0; i < 19; i++) {
            x*=10;
            x += mergeArray[i];
        }
        return x;

    }
}
