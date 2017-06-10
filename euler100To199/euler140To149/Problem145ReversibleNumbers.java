package euler100To199.euler140To149;

/**
 * Created by Sage on 9/15/2015.
 *
 * Slow but functional, 45 seconds-ish for brute force
 */
public class Problem145ReversibleNumbers {

    public static void main(String[] args) {
        long rev;
        long count = 0;
        for (long i = 12; i<1000000000; i++) {
            rev = reverse(i);
            if (rev==0)
                continue;
            if (allOdd(rev+i)) {
                count++;
            }
        }
        System.out.println("Final count is: "+count);
    }

    public static long reverse(long num) {
        long rev = 0;
        // can't allow leading 0's
        if (num%10==0)
            return 0;
        while (num>0) {
            rev*=10;
            rev+= num%10;
            num/=10;
        }
        return rev;
    }

    public static boolean allOdd(long num) {
        while (num>0) {
            if (num%2==0)
                return false;
            num/=10;
        }
        return true;
    }
}
