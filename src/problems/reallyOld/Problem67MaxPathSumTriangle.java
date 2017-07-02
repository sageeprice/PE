package problems.reallyOld;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Correct: 7273
 * 
 * Use dynamic programming, trace up
 */
public class Problem67MaxPathSumTriangle {

	private static int width;
	
	public static void main(String[] args) throws IOException {
		File triText = new File("C:\\Users\\Sage\\workspace\\SageKatas\\bin\\euler60To69\\triangle.txt");
		int[][] tri = readTriangle(triText);
		int[][] dynamVal = new int[width][width];
		int row = width -1;
		for (int i = 0; i < width; i++) {
			dynamVal[row][i] = tri[row][i];
			System.out.print(dynamVal[row][i] + "\t");
		}
		System.out.println();
		for (row = width-2; row >= 0; row--) {
			for (int i = 0; i <= row; i++) {
				dynamVal[row][i] = Math.max(dynamVal[row+1][i], dynamVal[row+1][i+1]) + tri[row][i];
				System.out.print(dynamVal[row][i] + "\t");
			}
			System.out.println();
		}
		System.out.println("Best total is: " + dynamVal[0][0]);
	}

	@SuppressWarnings("resource")
	private static int[][] readTriangle(File filename) throws IOException {
		FileReader fr = new FileReader(filename);
		BufferedReader textReader = new BufferedReader(fr);
		// get number of lines/width
		FileReader frPrime = new FileReader(filename);
		BufferedReader textReaderPrime = new BufferedReader(frPrime);
		String line;
		while ((line = textReaderPrime.readLine()) != null) {
			width++;
		}
		// read in and parse lines
		int[][] triangle = new int[width][width];
		String[] splitLine = new String[width];
		int row = 0;
		while ((line = textReader.readLine()) != null) {
			System.out.println("line is: " + line);
			splitLine = line.split(" ");
			for (int i = 0; i < row+1; i++) {
				triangle[row][i] = Integer.parseInt(splitLine[i]);
				System.out.println(triangle[row][i]);
			}
			for (int i = row+1; i < width; i++) {
				triangle[row][i] = 0;
				System.out.println(triangle[row][i]);
			}
			row++;
		}
		return triangle;
	}
}
