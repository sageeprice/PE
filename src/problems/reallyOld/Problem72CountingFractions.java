package problems.reallyOld;

/**
 * Created by Sage on 9/30/2015.
 */
public class Problem72CountingFractions {

    private static final int CAP = 1000000;

    public static void main(String[] args) {
        int[] totient = new int[CAP+1];
        for (int i = 2; i <= CAP; i++) {
            totient[i] = i;
        }

        long count = 0;
        // totient function phi search for this sage it is your best impl so far
        // as long as you have the memory to store everything
        for (int i = 2; i <= CAP; i++) {
            if (totient[i] != i) {
                count += totient[i];
            } else {
                count += i-1;
                for (int j = 2; j*i <= CAP; j++) {
                    totient[j*i] /= i;
                    totient[j*i] *= i-1;
                }
            }
        }
        System.out.println("Total count is: " + count);
    }

    public static long gcd(long a, long b) {
        long t = 0;
        while (b!=0) {
            t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}
