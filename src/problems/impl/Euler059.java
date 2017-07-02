package problems.impl;

import problems.Problem;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Problem 59:
 * https://projecteuler.net/problem=59
 *
 * Implemented a simple frequency analysis attack. Result:
 * Passcode : 'god'
 * Decrypted passage :
 * (The Gospel of John, chapter 1)
 * 1 In the beginning the Word already existed. He was with God, and he was God.
 * 2 He was in the beginning with God.
 * 3 He created everything there is. Nothing exists that he didn't make.
 * 4 Life itself was in him, and this life gives light to everyone.
 * 5 The light shines through the darkness, and the darkness can never extinguish it.
 * 6 God sent John the Baptist
 * 7 to tell everyone about the light so that everyone might believe because of his testimony.
 * 8 John himself was not the light; he was only a witness to the light.
 * 9 The one who is the true light, who gives light to everyone, was going to come into the world.
 * 10 But although the world was made through him, the world didn't recognize him when he came.
 * 11 Even in his own land and among his own people, he was not accepted.
 * 12 But to all who believed him and accepted him, he gave the right to become children of God.
 * 13 They are reborn! This is not a physical birth resulting from human passion or plan, this rebirth comes from God.
 * 14 So the Word became human and lived here on earth among us. He was full of unfailing love and faithfulness. And we have seen his glory, the glory of the only Son of the Father.
 *
 * Solved by Sage on 10/27/16.
 */
public class Euler059 implements Problem {

    private static final String FILE_NAME = "src/text/p059_cipher.txt";
    private static final String SPLITTER = ",";

    // The three most common English letters, lower case ASCII values
    private static final int A = 97;
    private static final int E = 101;
    private static final int T = 116;

    @Override
    public String solve() {

        int[] encryptedChars;

        try {
            // read in file
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            // split into numbers
            String[] encryptedCharStrs = reader.readLine().split(SPLITTER);

            // convert numbers to ints for processing
            encryptedChars = new int[encryptedCharStrs.length];
            for (int i = 0; i < encryptedChars.length; i++) {
                encryptedChars[i] = Integer.valueOf(encryptedCharStrs[i]);
            }
        } catch (Exception e) {
            throw new RuntimeException("Something broke... ", e);
        }

        int mostFrequentCharsCount = 0;
        int[] answer = null;

        // first letter
        for (int i = 97; i < 123; i++) {
            // second letter
            for (int j = 97; j < 123; j++) {
                // third letter
                for (int k = 97; k < 123; k++) {
                    int[] decryptedChars = decrypt(encryptedChars, i, j, k);
                    int frequentCharsCount = 0;
                    for (int l = 0; l < decryptedChars.length; l++) {
                        if (decryptedChars[l] == E || decryptedChars[l] == A || decryptedChars[l] == T)
                            frequentCharsCount++;
                    }
                    if (frequentCharsCount > mostFrequentCharsCount) {
                        mostFrequentCharsCount = frequentCharsCount;
                        answer = decryptedChars;
                    }
                }
            }
        }

        int asciiSum = 0;

        for (int i = 0; i < answer.length; i++) {
            asciiSum += answer[i];
            // Uncomment this and the below line to print out the passage
//            System.out.print((char) answer[i]);
        }
//        System.out.println();

        return String.valueOf(asciiSum);
    }

    // Decrypt array of ints by XORing with 3 letter password
    private int[] decrypt(int[] chars, int x, int y, int z) {
        int[] decryptedChars = new int[chars.length];

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < chars.length; i++) {
            switch (i % 3) {
                case 0:
                    decryptedChars[i] = x ^ chars[i];
                    break;
                case 1:
                    decryptedChars[i] = y ^ chars[i];
                    break;
                case 2:
                    decryptedChars[i] = z ^ chars[i];
                    break;
            }
        }
        return decryptedChars;
    }
}
