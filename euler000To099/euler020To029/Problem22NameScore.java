package euler000To099.euler020To029;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Correct: 871198282
 *
 * Today Sage learned how to use Scanner and Iterator.
 */
public class Problem22NameScore {

	public static void main(String[] args) throws IOException {
		File nameFile = new File("C:\\Users\\Sage\\workspace\\SageKatas\\bin\\euler20To29\\names.txt");
		Scanner in = new Scanner(nameFile).useDelimiter("[\",]+");
		ArrayList<String> text = new ArrayList<String>();
		while (in.hasNext()) {
			text.add(in.next());
		}
		in.close();
		Collections.sort(text);
		Iterator<String> iter = text.iterator();
		long nameSum = 0;
		long i = 1;
		while (iter.hasNext()) {
			String name = iter.next();
			nameSum += getTextValue(i, name);
			i++;
		}
		
		System.out.println("Total value is: " + nameSum);
	}

	private static long getTextValue(long i, String name) {
		char[] chars = name.toCharArray();
		int sum = 0;
		for (char cha : chars) {
			sum += cha-64;
		}
		return sum*i;
	}
}
