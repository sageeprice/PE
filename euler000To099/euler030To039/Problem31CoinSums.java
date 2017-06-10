package euler000To099.euler030To039;

import java.util.ArrayList;
import java.util.List;

/**
 * Correct: 73682
 * 
 * Use dynamic programming...
 */
public class Problem31CoinSums {
	public static void main(String[] args) {
		int[] waysToSum = new int[201];
		for (int i = 0; i < 201; i++)
			waysToSum[i] = 0;
		waysToSum[0] = 1;
		List<Integer> coinSizes = new ArrayList<Integer>();
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
		for (int i = 1; i < 201; i++)
			System.out.println("The number of ways to get " + i + " is " + waysToSum[i]);
	}

}
