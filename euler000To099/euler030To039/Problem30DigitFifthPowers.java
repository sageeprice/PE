package euler000To099.euler030To039;

/**
 * Correct: 443839
 * 
 * Don't need to check above 270000 as values above are unreachable
 */
public class Problem30DigitFifthPowers {

	public static void main(String[] args) {
		long total = 0;
		for (int i = 2; i < 270000; i++) {
		//	System.out.println("testing " + i);
			if (sumFifthPowerOfDigits(i) == i) {
				total += i;
				System.out.println("found one: " + i);
			}
		}
		System.out.println(total);
	}

	private static int sumFifthPowerOfDigits(int check) {
		int sum = 0;
		int test = check;
		while (test > 0) {
		//	System.out.println("digit " + test%10 + " gives " + Math.pow(test%10, 5));
			sum += Math.pow(test%10, 5);
			test /= 10;
			if (sum > check) {
		//		System.out.println("breaking for " + check);
				return 0;
			}
		}
		return sum;
	}
}
