package problems.reallyOld;

import java.util.Set;
import java.util.TreeSet;

/**
 * Correct: total is 16695334890
 * 
 * Idea for solution: many digits are dependent on the others, e.g. d6 + d8 - d7 must be divisible by 11
 * Use this info to calculate numbers, significantly reducing the possibilities.
 */
public class Problem43PandigitalSubstring {
	private static long d1;
	private static long d2;
	private static long d3;
	private static long d4;
	private static long d5;
	private static long d6;
	private static long d7;
	private static long d8;
	private static long d9;
	private static long d10;
	private static Set<Long> digits= new TreeSet<Long>();
	
	private static long total = 0L;

	public static void main(String args[]) {
		d6 = 5;
		digits.add(d6);
		System.out.println("d6: " + d6);
		for (d5 = 0L; d5 < 10; d5++) {
			if (!digits.contains(d5)) {
				digits.add(d5);
				System.out.println(" d5: " + d5);
				getD7();
				digits.remove(d5);
			}
		}
		digits.remove(d6);
		d6 = 0;
		digits.add(d6);
		for (d5 = 0; d5 < 10; d5++) {
			if (!digits.contains(d5)) {
			digits.add(d5);
			System.out.println(" d5: " + d5);
			getD7();
			digits.remove(d5);
			}
		}
		System.out.println("Total is " + total);
	}

	private static void getD7() {
		for (long i = 0; i < 10; i++) {
			if (((100*d5 + 10*d6 + i) % 7) == 0 && !digits.contains(i)) {
				d7 = i;
				digits.add(d7);
				System.out.println("  d7: " + d7);
				getD8();
				digits.remove(d7);
			}
		}
	}

	private static void getD8() {
		for (long i = 0; i < 10; i++) {
			if (((d6+i-d7)%11) == 0 && !digits.contains(i)) {
				d8 = i;
				digits.add(d8);
				System.out.println("   d8: " + d8);
				getD9();
				digits.remove(d8);
				return;
			}
		}
	}

	private static void getD9() {
		for (long i = 0; i < 10; i++) {
			if ((100*d7 + 10*d8 + i)%13 == 0 && !digits.contains(i)) {
				d9 = i;
				digits.add(d9);
				System.out.println("    d9: " + d9);
				getD10();
				digits.remove(d9);
				return;
			}
		}
	}

	private static void getD10() {
		for (long i = 0; i < 10; i++) {
			if ((100*d8 + 10*d9 + i)%17 == 0 && !digits.contains(i)) {
				d10 = i;
				digits.add(d10);
				System.out.println("     d10: " + d10);
				getD4();
				digits.remove(d10);
				return;
			}
		}
	}

	// must be even
	private static void getD4() {
		for (long i = 0; i < 10; i++) {
			if (i%2 == 0 && !digits.contains(i)) {
				d4 = i;
				digits.add(d4);
				System.out.println("      d4: " + d4);
				getD3();
				digits.remove(d4);
			}
		}
	}

	private static void getD3() {
		for (long i = 0; i < 10; i++) {
			if ((i+d4+d5)%3 == 0 && !digits.contains(i)) {
				d3 = i;
				digits.add(d3);
				System.out.println("       d3: " + d3);
				getD1AndD2();
				digits.remove(d3);
			}
		}
	}
	
	private static void getD1AndD2() {
		for (long i = 0; i < 10; i++) {
			if (!digits.contains(i)) {
				d2 = i;
				digits.add(d2);
				System.out.println("        d2: " + d2);
				for (long j = 1; j < 10; j++) {
					if (!digits.contains(j)) {
						d1 = j;
						System.out.println("         d1: " + d1);
						finish();
					}
				}
				digits.remove(d2);
			}
		}
	}
	
	private static void finish() {
		long pandig = d1*1000000000 +
				d2*100000000 +
				d3*10000000 +
				d4*1000000 +
				d5*100000 +
				d6*10000 +
				d7*1000 +
				d8*100 +
				d9*10 +
				d10;
		System.out.println("Found one: " + pandig);
		total += pandig;
	}
}
