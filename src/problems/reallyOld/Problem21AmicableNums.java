package problems.reallyOld;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import UsefulForMath.Erastosthenes;

/**
 * Correct: 31626
 */
public class Problem21AmicableNums {
	private static int MAX = 10000;

	public static void main(String[] args) {
		Erastosthenes sieve = new Erastosthenes(MAX);
		sieve.fillSieve();
		int[] divSums = new int[MAX+1];
		divSums[0] = 0;
		for (int i = 1; i <= MAX; i++) {
			int divSum = 1;
			Map<Integer, Integer> primePowerPairs = sieve.getMaxPowFactorsAsMap(i);
			for (Map.Entry<Integer, Integer> primeToPower : primePowerPairs.entrySet()) {
				int powSum = 1;
				for (int j = 1; j <= primeToPower.getValue(); j++) {
					powSum += Math.pow(primeToPower.getKey(), j);
				}
				divSum *= powSum;
			}
			divSums[i] = divSum - i;
		}
		Set<Integer> amicableNums = new HashSet<Integer>();
		int pairSum = 0;
		for (int i = 1; i < MAX+1; i++) {
			if (divSums[i] <= MAX && divSums[divSums[i]] == i && divSums[i] != i) {
				amicableNums.add(i);
				pairSum += i;
				System.out.println("added: " + i + ", which pairs with: " + divSums[i]);
			}
		}
		System.out.println("total sum is: " + pairSum);
	}
}
