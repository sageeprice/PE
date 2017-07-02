package problems.reallyOld;

/**
 * Correct: answer is 1533776805
 *
 * Logic: can show that hex depends on Tri, with equivalency only when tri is odd
 * Then simply iterate pentas until you find a tri
 */
public class Problem45TriPentaAndHex {

	public static void main(String[] args) {
		long P = 166L;
		long T;
		long pent = penta(P);
		System.out.println("# for " + P + " is " +  pent);
		 do {
			//if (P > 10000)
				//break;
			P++;
			pent = penta(P);
			System.out.println("P: " + P + " is " +  pent);
			T = TFromP(P);
			System.out.println("T: " + T + " is " + T*(T+1)/2);
			if (pent == T*(T+1)/2)
				System.out.println("MATCH FOUND RIGHT HERE |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		} while (((T%2) == 0) || pent != T*(T+1)/2);
		System.out.println("next P is " + P);
	}

	public static long penta(long p) {
		return p*(3*p-1)/2;
	}
	
	public static boolean isTri(double check) {
		long root = (long) Math.sqrt(check*2);
		System.out.println("Root: " + root);
		System.out.println("Check: " + check);
		
		if (root*(root+1)/2 == (long) check)
			return true;
		else
			return false;
	}
	
	public static long TFromP(long P) {
		long T = (long) Math.sqrt(penta(P) * 2);
		return T;
	}
}
