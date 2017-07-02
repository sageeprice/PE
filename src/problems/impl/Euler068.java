package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem 68:
 * https://projecteuler.net/problem=68
 *
 * Solved by Sage on 10/30/16.
 */
public class Euler068 implements Problem {

    private static final int SIZE = 5;
    private static final int EMPTY = -1;

    @Override
    public String solve() {

        // Arrays of the usable numbers on the inside/outside
        // Since we want to maximize string, put all big numbers
        // on the outside, small on the inside
        int[] insideNumbers = new int[SIZE];
        int[] outsideNumbers = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            insideNumbers[i] = i + 1;
            outsideNumbers[i] = SIZE + i + 1;
        }

        // Arrays used for generating permutations
        Integer[] insidePermutation = new Integer[SIZE];
        Integer[] outsidePermutation = new Integer[SIZE];

        // List of all permutations generated
        List<Integer[]> insideOrderings = new ArrayList<>();
        List<Integer[]> outsideOrderings = new ArrayList<>();

        // Current index in permutation
        int index = 0;

        generateOrderings(index, insidePermutation, insideOrderings, insideNumbers);
        generateOrderings(index, outsidePermutation, outsideOrderings, outsideNumbers);

        String bestOrdering = "0";

        // Check all combinations of inside and outside, validate, and store best serialized solution
        for (Integer[] outArray : outsideOrderings) {
            for (Integer[] inArray : insideOrderings) {
                if (isValidStructure(outArray, inArray)) {
                    String serialStruct = serializeStructure(outArray, inArray);

                    if (serialStruct.compareTo(bestOrdering) > 0) {
                        bestOrdering = serialStruct;
                    }
                }
            }
        }

        return bestOrdering;
    }

    /**
     * Returns a serialized structure as defined by the problem spec
     * @param arr1 array of integers
     * @param arr2 array of integers
     * @return serialization of arr1 and arr2
     */
    private String serializeStructure(Integer[] arr1, Integer[] arr2) {
        StringBuilder serializer = new StringBuilder();

        // Gotta start with the smallest element of the outer ring
        int start;
        for (start = 0; start < arr1.length; start++) {
            if (arr1[start] == SIZE + 1)
                break;
        }

        for (int i = 0; i < arr1.length; i++) {
            serializer.append(arr1[(start + i) % arr1.length]);
            serializer.append(arr2[(start + i) % arr1.length]);
            serializer.append(arr2[(start + i + 1) % arr1.length]);
        }

        return serializer.toString();
    }

    /**
     * Validate arr1 and arr2 satisfy the specification
     * @param arr1 array of integers
     * @param arr2 array of integers
     * @return arr1 and arr2 generate valid structure
     */
    private boolean isValidStructure(Integer[] arr1, Integer[] arr2) {
        int s = arr1[0] + arr2[0] + arr2[1];

        for (int i = 1; i < arr1.length; i++) {
            if (s != arr1[i] + arr2[i] + arr2[(i + 1) % arr2.length])
                return false;
        }

        return true;
    }

    /**
     * Implements recursive DFS to generate all valid orderings of the integers in options[]
     * @param index current index of element to insert
     * @param array array of elements put in array so far
     * @param orderings all valid arrays generated
     * @param options array of integers that must be placed in array
     */
    private void generateOrderings(int index, Integer[] array, List<Integer[]> orderings, int[] options) {
        if (index == array.length) {
            orderings.add(Arrays.copyOf(array, array.length));
            return;
        }

        for (int i = 0; i < options.length; i++) {
            if (options[i] == EMPTY) {
                continue;
            }

            array[index] = options[i];
            options[i] = EMPTY;
            generateOrderings(index + 1, array, orderings, options);
            options[i] = array[index];
        }
    }
}
