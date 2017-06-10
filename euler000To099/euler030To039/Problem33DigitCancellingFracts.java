package euler000To099.euler030To039;

/**
 * works, returns num and prod. should fix to simplify value and return answer
 * Correct: 100
 */
public class Problem33DigitCancellingFracts {

	public static void main(String[] args) {
		int numer = 11;
		int denom;
		int numprod = 1;
		int dprod = 1;
		int pair;
		for (; numer < 100; numer++) {
			if ((numer % 10 == 0) || (numer % 10 == numer / 10))
				continue;
			else {
				for (denom = numer + 1; denom < 100; denom++) {
					pair = eligible(numer, denom);
					if ((denom % 10 == 0) || (denom % 10 == denom / 10))
						continue;
					else if (pair == 0)
						continue;
					else if (bleh(numer, denom, pair)) {
						System.out.println("found pair: (" + numer + ", " + denom + "), from case "+ pair);
						numprod *= numer;
						dprod *= denom;
					}
				}
			}
		}
		System.out.println("numprod is: " +numprod);
		System.out.println("denprod is: " +dprod);
	}
	
	public static boolean bleh(int numer, int denom, int pair) {
		switch(pair) {
			case 1:
				if (numer * (denom%10) == denom * (numer/10))
					return true;
			case 2:
				if ((numer * (denom/10)) == (denom * (numer%10)))
					return true;
		}
		return false;
	}
	
	public static int eligible(int num, int den) {
		if (num == den)
			return 0;
		if (num%10 == den/10)
			return 1;
		if (num/10 == den%10)
			return 2;
		return 0;
	}

}
