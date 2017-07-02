package problems.reallyOld;

import java.util.Set;
import java.util.TreeSet;

public class Problem153GaussianInts {

//    private static final long CAP = 100000000L;
//    private static final long MAX = 10000;

    private static final long CAP = 100000;
    private static final long MAX = CAP;

    public static void main(String[] args) {

        long sum = CAP - 1;
        sum += CAP * (CAP+1) / 2;
        System.out.println(sum);

        Set<Long> squares = new TreeSet<>();
        for (long i = MAX; i > 1; i--) {
            for (long square : squares) {
                if (square % i*i == 0) {
                    sum += i;
                }
            }
            squares.add(i*i);
        }

        System.out.println("Added squares");
        long x;
        for (int i = 1; i < MAX; i++) {
            x = 2*i*i;
            if (x <= CAP*CAP) {
                for (long square : squares) {
                    if (square % x == 0) {
                        sum += 2 * i;
                        System.out.println(i + ", " + i + ": " + ((i+i)));
                    }
                }
            }
            for (int j = i+1; j < MAX; j++) {
                x = i*i + j*j;
                if (x <= CAP*CAP) {
                    for (long square : squares) {
                        if (square % x == 0 && x != square) {
                            sum += 2 * (i + j);
                            System.out.println(i + ", " + j + ": " + (2*(i+j)));
                        }
                    }
                }
            }
        }
        System.out.println(sum);
    }
}
