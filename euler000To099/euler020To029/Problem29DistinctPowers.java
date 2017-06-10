package euler000To099.euler020To029;

/**
 * Correct: 9183
 * 
 * Everything counts but the overlap, just need to count valid powers of 2, 3, 4, 5, 6, 7, 10.
 */
public class Problem29DistinctPowers {

	public static void main(String[] args) {

		int twoCount = 99;
		for (int i = 101; i <= 600; i++) {
			if (i < 201 && i % 2 == 0)
				twoCount++;
			else if (i < 301 && i%3 == 0)
				twoCount++;
			else if (i < 401 && i % 4 == 0)
				twoCount++;
			else if (i < 501 && i % 5 == 0)
				twoCount++;
			else if (i < 601 && i % 6 == 0)
				twoCount++;
		}
		
		int threeCount = 99;
		for (int i = 101; i <= 400; i++) {
			if (i < 201 && i%2 == 0)
				threeCount++;
			else if (i < 301 && i%3 == 0)
				threeCount++;
			else if (i < 401 && i % 4 == 0)
				threeCount++;
		}

		int elseCount = 99;
		for (int i = 101; i <= 200; i++) {
			if (i < 201 && i%2 == 0)
				elseCount++;
		}

		System.out.println((99*81+ twoCount + threeCount + 4*elseCount));
	}

}
