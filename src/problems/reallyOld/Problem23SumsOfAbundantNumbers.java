package problems.reallyOld;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import errata.Erastosthenes;

/**
 * Correct: 4179871
 */
public class Problem23SumsOfAbundantNumbers {

	private static Erastosthenes sieve;
	private static int MAX = 28123;
	
	public static void main(String[] args) {
		sieve = new Erastosthenes(MAX);
		sieve.fillSieve();

		boolean[] nums = new boolean[MAX+1];
		for (int i = 1; i < MAX+1; i++) {
			nums[i] = true;
		}
		List<Integer> abundantNums = new ArrayList<Integer>();
		for (int num = 1; num <= MAX; num++) {
			if (isAbundant(num)) {
				for (int abNum : abundantNums) {
					if (abNum + num < MAX+1) {
						nums[abNum + num] = false;
						//System.out.println("False: "+ (abNum + num));
					}
				}
				if (2*num < MAX+1) {
					nums[2*num] = false;
				}
				//System.out.println("found abundant: " + num);
				abundantNums.add(num);
			}
		}
		long sum = 0;
		for (int i = 1; i < MAX+1; i++) {
			if (nums[i])
				sum += i;
		}
		System.out.println("Sum is " + sum);
	}

	private static boolean isAbundant(int num) {
		int divSum = 1;
		Map<Integer, Integer> primePowerPairs = sieve.getMaxPowFactorsAsMap(num);
		for (Map.Entry<Integer, Integer> primeToPower : primePowerPairs.entrySet()) {
			int powSum = 1;
			for (int j = 1; j <= primeToPower.getValue(); j++) {
				powSum += Math.pow(primeToPower.getKey(), j);
			}
			divSum *= powSum;
		}
		return (divSum - num) > num ? true : false;
	}

}
