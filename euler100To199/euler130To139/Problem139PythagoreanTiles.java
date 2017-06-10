package euler100To199.euler130To139;

/**
 * Working, and quite quickly. Pretty happy with it.
 * Should try to make iterative, cuz fuck recursion.
 * Answer: 10057761
 */
public class Problem139PythagoreanTiles {

    private static final long PERIM = 100000000;

    private static final int[] M1 = {1,-2,2,2,-1,2,2,-2,3};
    private static final int[] M2 = {1,2,2,2,1,2,2,2,3};
    private static final int[] M3 = {-1,2,2,-2,1,2,-2,2,3};

    public static void main(String[] args) {

        int[] start = {3,4,5};

        System.out.println(recurse(start));
    }

    /**
     * Generates next Pythagorean triple given a left matrix and a column
     * See https://en.wikipedia.org/wiki/Tree_of_primitive_Pythagorean_triples
     *
     * Search words: Pythagoras, right triangle, hypotenuse
     *
     * @param left magic matrix
     * @param col initial Pythagorean triple
     * @param answer the generated Pythagorean triple
     */
    private static void multiply(int[] left, int[] col, int[] answer) {
        answer[0] = left[0]*col[0] + left[1]*col[1] + left[2]*col[2];
        answer[1] = left[3]*col[0] + left[4]*col[1] + left[5]*col[2];
        answer[2] = left[6]*col[0] + left[7]*col[1] + left[8]*col[2];
    }

    private static long getTilesInMultiples(int[] triple) {
        int side = triple[0] - triple[1];
        side = side < 0 ? 0-side : side;
        long perimeter = getPerimeter(triple);
        // Worth noting for this problem: if a-b != 1 but is tile-able,
        // then it must be that this triangle is multiple of another.
        // Therefore, as we are generating primitives, we only care
        // about those triangles which have a difference in side
        // length of 1.
        if (side == 1 && perimeter < PERIM) {
            return PERIM / perimeter;
        }
        return 0;
    }

    private static long recurse(int[] start) {
        long sum = getTilesInMultiples(start);
//        System.out.println(sum + " contributed by " + start[0]+", "+start[1]+", "+start[2]);
        int[] next = new int[3];
        if (getPerimeter(start) < PERIM) {
            multiply(M1, start, next);
            sum += recurse(next);
            multiply(M2, start, next);
            sum += recurse(next);
            multiply(M3, start, next);
            sum += recurse(next);
        }
        return sum;
    }

    private static final long getPerimeter(int[] arr) {
        long s = 0;
        for (int i = 0; i < arr.length; i++) {
            s += arr[i];
        }
        return s;
    }
}
