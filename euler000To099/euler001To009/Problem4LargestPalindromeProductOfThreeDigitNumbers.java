package euler000To099.euler001To009;

/**
 * Correct: 906609
 * @author Sage
 *
 */
public class Problem4LargestPalindromeProductOfThreeDigitNumbers {

    public static void main(String[] args) {
        int i = 101;
        int j, prod;
        int palindrome = 0;
        while (i < 1000) {
            j = 101;
            if (i % 10 != 0 && j % 10 != 0) {
                while (j < 1000) {
                    prod = i*j;
                    if (checkPalindrome(prod) && prod > palindrome)
                        palindrome = prod;
                    j++;
                }
            }
            i++;
        }
        System.out.println("Palindrome is " + palindrome);

    }

    //Note: only works for numbers between 100,000 and 1,000,000
    public static boolean checkPalindrome(int number) {
        if (number % 10 == number / 100000) {
            number = (number % 100000) / 10;
            if (number % 10 == number / 1000) {
                number = (number % 1000) / 10;
                if (number % 10 == number / 10)
                    return true;
            }
        }
        return false;
    }
}
