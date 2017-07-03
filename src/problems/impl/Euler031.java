package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Problem 31:
 * https://projecteuler.net/problem=31
 *
 * Solved by Sage on 10/22/16.
 */
public class Euler031 implements Problem {

    @Override
    public String solve() {
		int[] waysToSum = new int[201];
		for (int i = 0; i < 201; i++)
			waysToSum[i] = 0;
		waysToSum[0] = 1;
		List<Integer> coinSizes = new ArrayList<>();
		coinSizes.add(1);
		coinSizes.add(2);
		coinSizes.add(5);
		coinSizes.add(10);
		coinSizes.add(20);
		coinSizes.add(50);
		coinSizes.add(100);
		coinSizes.add(200);

		for (int coin : coinSizes) {
			for (int i = coin; i < 201; i++) {
				if (i >= coin)
					waysToSum[i] = waysToSum[i] + waysToSum[i - coin];
			}
		}
		return String.valueOf(waysToSum[200]);
	}
}
