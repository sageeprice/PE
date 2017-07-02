package problems.reallyOld;

/**
 * Created by Sage on 10/25/2015.
 */
public class Problem407Idempotents {

    public static void main(String[] args) {

        boolean found;
        long sum = 1;
        for (int i = 2; i <= 10000000; i++) {
            found = false;
            for (int j = i-1; j >= i/2; j--) {
                if (j*j%i == j) {
//                    System.out.println(i + ": " + j);
                    sum += j;
                    found = true;
                    break;
                }
            }
            if (!found) {
                sum += 1;
            }
            if (i %100000 == 0) {
                System.out.println(i);
            }
        }
        System.out.println(sum);
    }
}
