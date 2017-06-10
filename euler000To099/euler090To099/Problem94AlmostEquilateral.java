package euler000To099.euler090To099;

/**
 * NOTE: Still broken, need to figure out how to eliminate last incorrect
 * result that comes from rounding error. As it is the answer is the
 * perimeter prior to adding the last triangle solution.
 */
public class Problem94AlmostEquilateral {

    private static final long CAP = (long)Math.pow(10, 9);

    public static void main(String[] args) {
        long x;
        double y;
        long perimSum = 0;
        System.out.println(Double.MAX_VALUE);
        for (long hypot = 5; hypot <= CAP/3; hypot += 2) {
            x = (hypot-1)/2;
            y = Math.sqrt(hypot*hypot - x*x);
            if (Math.floor(y) == Math.ceil(y)) {
                if (x*x + y*y == hypot*hypot) {
                    if (2 * (x + hypot) > CAP) {
                        System.out.println("Breaking");
                        break;
                    }
                    System.out.println("Minus one: " + x + ", " + y + ", " + hypot);
                    System.out.println("Perimeter is: " + (2 * (x + hypot)));
                    perimSum += 2 * (x + hypot);
                    System.out.println("Current sum of perimeters is: " + perimSum);
                }
            }
            x = (hypot+1)/2;
            y = Math.sqrt(hypot*hypot - x*x);
            if (Math.floor(y) == Math.ceil(y)) {
                if (x*x + y*y == hypot*hypot) {
                    if (2 * (x + hypot) > CAP) {
                        System.out.println("should break");
                        continue;
                    }
                    System.out.println("Plus one: " + x + ", " + y + ", " + hypot);
                    System.out.println("Perimeter is: " + (2 * (x + hypot)));
                    perimSum += 2 * (x + hypot);
                    System.out.println("Current sum of perimeters is: " + perimSum);
                }
            }
        }
        System.out.println("Sum of perimeters is: " + perimSum);
    }
}
