package euler300To350;

import java.util.Set;
import java.util.TreeSet;

/**
 * Smart brute force
 */
public class Problem346StrongRepunits {

    public static void main(String[] args) {
        long cap = 1000000000000L;
        long repunitSum = 0;
        Set<Long> repunitSet = new TreeSet<>();
        for (long i = 2; i*i < cap; i++) {
            long base = i*i+i+1;
            while (base < cap) {
                if (repunitSet.add(base)) {
                    repunitSum += base;
                }
                if (cap / i < base) {
                    break;
                }
                base = base*i+1;
            }
        }
        System.out.println("Number of repunits below " + cap + " is: " + repunitSet.size());
        System.out.println("Sum of repunits is: " + (repunitSum+1));
    }
}
