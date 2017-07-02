package problems.reallyOld;

public class Problem25FibonacciDigits {

	/**
	 * Correct: 4782
	 * 
	 * Note: if all we care about is number of digits, can simply divide whenever
	 * 	we pass 10, and increment a count of the digits.
	 */
	public static void main(String[] args) {
		double f1, f2, f3 = 0;
		f1 = 1;
		f2 = 1;
		int digs = 1;
		int fibs = 2;
		while (digs < 1000) {
			f3 = f1 + f2;
			f1 = f2;
			f2 = f3;
			fibs++;
			if (f2 > 10) {
				f2 /= 10;
				f1 /= 10;
				digs++;
			}
		}
		System.out.println(fibs);

	}

}
