package problems.reallyOld;

/**
 * Created by Sage on 9/12/2015.
 */
public class Problem112BouncyNumbers {
    public static void main(String[] args) {
        double ratio = 0;
        double bouncyCount = 0;
        long index = 100;
        while(ratio < .99) {
            index++;
            if (isBouncy(index)) {
                bouncyCount++;
                ratio = bouncyCount/index;
            }
            if (index%10000 == 0) {
                System.out.println("Ratio at "+index+" is: "+ratio);
            }
        }
        System.out.println("Ratio breaks .99 at: " + index);
    }

    public static boolean isBouncy(long num) {
        return !(isIncreasing(num) || isDecreasing(num));
    }

    public static boolean isIncreasing(long num) {
        int x = (int) num%10;
        num /= 10;
        int y;
        boolean inc = true;
        while (num > 0) {
            y = (int) num % 10;
            num /= 10;
            if (y > x) {
                return false;
            }
            x = y;
        }
        return true;
    }

    public static boolean isDecreasing(long num) {
        int x = (int) num%10;
        num /= 10;
        int y;
        boolean inc = true;
        while (num > 0) {
            y = (int) num % 10;
            num /= 10;
            if (y < x) {
                return false;
            }
            x = y;
        }
        return true;

    }
}
