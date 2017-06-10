package euler000To099.euler070To079;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sage on 9/30/2015.
 */
public class Problem74DigitFactorialChains {

    private static final int CAP = 1000000;
    private static int[] array = new int[CAP];

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 1; i < CAP; i++) {
            array[i] = getSequenceLength(i);
        }

        int count = 0;
        for (int i = 1; i < CAP; i++) {
            if (array[i] == 60) {
                count++;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(count);
        System.out.println("Total time for solution is " + (end-start) + " milliseconds.");
    }

    private static int getSequenceLength(int x) {
        List<Integer> sequence = new ArrayList<>();
        while (!sequence.contains(x)) {
            sequence.add(x);
            x = digitalFactorialSum(x);
            // Not sure why this works, it should break if you had a sequence
            // like 2 -> 10000000 -> 3 -> 10000000 -> 3 where a term went out
            // of the scope of the array before looping. Should look into this.
            // Anyway I added it in after first success to see if it would help.
            // And it did, so TODO: investigate why this works.
            if (x < CAP && array[x] != 0) {
                return sequence.size() + array[x];
            }
        }
        return sequence.size();
    }

    private static int factorial(int n) {
        int t = n;
        int f = 1;
        while (t > 1)
            f *= t--;
        return f;
    }

    private static int digitalFactorialSum(int n) {
        int sum = 0;
        int d;
        while (n > 0) {
            d = n %10;
            sum += factorial(d);
            n /= 10;
        }
        return sum;
    }
}
