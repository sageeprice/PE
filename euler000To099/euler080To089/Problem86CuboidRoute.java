package euler000To099.euler080To089;

public class Problem86CuboidRoute {

    public static void main(String[] args) {
        int M = 2;
        long count = 0;
        double x;
        int y;
        double z;
        while (count < 1000000) {
            M++;
//            System.out.println("---"+M+"---");
            x = M;
            for (y = 2; y <= 2*M; y++) {
                z = Math.sqrt(x*x + y*y);
                if (Math.floor(z) == z) {
//                    System.out.println((int) x + ", " + y + ", " + (int) z);
                    count += y < M ? y / 2 : y/2 - (y-M) + 1;
                }

            }
            /* TODO: this section will find all pythagorean triples, pythagoras
            x = M;
            for (y = M+1; y <= 2*M-2; y++) {
                z = Math.sqrt(x*x + y*y);
                if (Math.floor(z) == z) {
//                    System.out.println((int) x + ", " + y + ", " + (int) z);
                    count++;
                }

            }
            y = 2*M;
            for (x = Math.ceil(Math.sqrt(2*y+1)); x <= M; x++) {
                z = Math.sqrt(x*x + y*y);
                if (Math.floor(z) == z) {
//                    System.out.println((int) x + ", " + y + ", " + (int) z);
                    count++;
                }
            }
            y = 2*M-1;
            for (x = Math.ceil(Math.sqrt(2*y+1)); x <= M; x++) {
                z = Math.sqrt(x*x + y*y);
                if (Math.floor(z) == z) {
//                    System.out.println((int) x + ", " + y + ", " + (int) z);
                    count++;
                }
            }
            */
        }
        System.out.println("Total is " + count);
        System.out.println("End is " + M);
    }
}
