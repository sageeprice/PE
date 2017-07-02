package problems.reallyOld;

/**
 * Success: 840, with 10
 * @author Sage
 *
 */
public class Problem39IntegerRightTriangles {

	public static void main(String args[]) {
		int maxSolutions = 1;
		int bestSoFar = 5;
		int[] trips = new int[1001];
		for (int i = 0; i < 1001; i++) {
			trips[i] = 0;
		}
		for (int c = 6; c < 800; c++) {
			for (int b = c - 1; b >= c/2; b--) {
				double test = Math.sqrt((double)(c*c - b*b));
				int p = (int) test + b + c;
				if (Math.floor(test) == test && p < 1001) {
					trips[p]++;
					//System.out.println("Found new triple: " + test + ", " + b + ", " + c + ".");
				}
			}
		}
		for (int i = 6; i < 1001; i++) {
			if (maxSolutions < trips[i]) {
				maxSolutions = trips[i];
				bestSoFar = i;
				System.out.println("New best is " + i + " with " + maxSolutions);
			}
		}
		System.out.println("Best is " + bestSoFar);
	}
}
