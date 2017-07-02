package problems.reallyOld;

/**
 * Correct: 837799
 */
public class Problem14LongestCollatzUnderOneMillion {

	private static int MAX = 1000000;
	private static int[] cache = new int[MAX + 1];


	public static void main(String[] args) {
		// do I have to initialize?
		for (int i = 0; i < cache.length; i++)
			cache[i] = 0;
		cache[1] = 1;
		int best = 1;
		int bestLength = 1;
		for (int i = 2; i < 1000001; i++) {
			cache[i] = collatzLength(i);
			if (cache[i] > bestLength) {
				best = i;
				bestLength = cache[i];
			}
		}
		System.out.println("Longest sequence under 1000000 starts at " + best + " with length " + bestLength);
	}

	public static int collatzLength(long start) {
		if (inCache(start))
			return  cache[(int) start];
		int length = 1;
		while (start != 1) {
			if (start % 2 == 0)
				start /= 2;
			else
				start = 3*start + 1;
			if (inCache(start))
				return cache[(int) start] + length;
			else length++;
		}
		return length;
	}

	private static boolean inCache(long check) {
		//System.out.println("checking " + check);
		if (check < MAX && cache[(int) check] != 0)
			return true;
		else return false;
	}
}
