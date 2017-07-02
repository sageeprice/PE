package problems.reallyOld;

/**
 * Correct: 137846528820
 *
 * Just combinatorics...
 */
public class Problem15LatticePaths {
	public static void main(String args[]) {
		System.out.println(xChooseY(40, 20));
	}

	public static long xChooseY(long x, long y) {
		long prod = x;
		for (int i = 1; i < y; i++) {
			prod*=(x-i);
			prod/=(i+1);
		}
		return prod;
	}
}
