package problems.reallyOld;

/**
 * Correct: 40730
 */
public class Problem34FactorialDigitsSum {

	public static void main(String[] args) {
		for (int i = 1; i < 10; i++) {
			System.out.println(i + "! = " + factorial(i));
		}
		int sum = 0;
		for (int i = 3; i < 2177283; i++) {
			if (potential(i)) {
				//System.out.println("potential answer: " + i);
				if (digitalFactorialSum(i) == i) {
					System.out.println("Found one: " + i);
					sum += i;
				}
			}
		}
		System.out.println("total sum is " + sum);

	}
	
	public static int factorial(int n) {
		int t = n;
		int f = 1;
		while (t > 1)
			f *= t--;
		return f;
	}
	
	public static int digitalFactorialSum(int n) {
		int sum = 0;
		int d;
		while (n > 0) {
			d = n %10;
			sum += factorial(d);
			n /= 10;
		}
		return sum;
	}

	public static boolean potential(int n) {
			if (n < 45)
				return digCheck(n, 4);
			else if (n < 120)
				return false;
			else if (n < 360)
				return digCheck(n, 5);
			else if (n < 720)
				return false;
			else if (n < 2880)
				return digCheck(n, 6);
			else if (n < 5040)
				return false;
			else if (n < 25200)
				return digCheck(n, 7);
			else if (n < 40320)
				return false;
			else if (n < 241920)
				return digCheck(n, 8);
			else if (n < 362880)
				return false;
			else if (n < 2177282)
				return digCheck(n, 9);
			return false;
	}

// for the if statement in potential...
	public static boolean digCheck(int n, int needed) {
		int check = n;
		while (check > 0) {
			if (check % 10 >= needed)
				return true;
			else
				check /= 10;
		}
		return false;
	}
}
