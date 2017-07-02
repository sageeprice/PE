package problems.reallyOld;

import java.util.Map;

import errata.Erastosthenes;

/**
 * Correct: 232792560
 * 
 * Should think of a better way to do this...
 */
public class Problem5SmallestMultiple {

    public static void main(String[] args) {
        Erastosthenes sieve = new Erastosthenes(20);
        sieve.fillSieve();
        int lcm = 1;
        int[] mults = new int[21];
        for (int i = 0; i < 21; i++)
            mults[i] = 0;
        for (int i = 2; i <= 20; i++) {
            Map<Integer, Integer> maxPowPrimeFactors = sieve.getMaxPowFactorsAsMap(i);
            for (Map.Entry<Integer, Integer> entry : maxPowPrimeFactors.entrySet()) {
                if (entry.getValue() > mults[entry.getKey()])
                    mults[entry.getKey()] = entry.getValue();
            }
        }
        for (int i = 1; i < 21; i++) {
            System.out.println(i + ": " + mults[i]);
        }
        for (int i = 2; i < 21; i++) {
            if (mults[i] > 0)
                lcm*=Math.pow(i, mults[i]);
        }
        System.out.println("lcm is: " + lcm);
    }

}
