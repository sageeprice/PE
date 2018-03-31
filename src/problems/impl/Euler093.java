package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;


/**
 * Nothing clever here, brute force generation of all possibilities.
 *
 * Answer: 1258
 */
public class Euler093 implements Problem {

    private static final BiFunction<Double, Double, Double> ADD = (a, b) -> a + b;
    private static final BiFunction<Double, Double, Double> SUB = (a, b) -> a - b;
    private static final BiFunction<Double, Double, Double> MUL = (a, b) -> a * b;
    private static final BiFunction<Double, Double, Double> DIV = (a, b) -> a / b;

    private static final List<BiFunction<Double, Double, Double>> OPS = Arrays.asList(ADD, SUB, MUL, DIV);

    @Override
    public String solve() {
        List<List<BiFunction<Double, Double, Double>>> opTriples = getOpTriples();
        List<List<Double>> quads = generateQuads();


        // Initialize empty sets in map.
        Map<String, Set<Integer>> quadValues = new HashMap<>();
        for (List<Double> quad : quads) {
            quadValues.put(listToSortedString(quad), new HashSet<>());
        }
        for (double i = 1; i <= 9; i++) {
            for (double j = 1; j <= 9; j++) {
                for (double k = 1; k <= 9; k++) {
                    for (double l = 1; l <= 9; l++) {
                        List<Double> quad = new ArrayList<>();
                        quad.add(i);
                        quad.add(j);
                        quad.add(k);
                        quad.add(l);

                        if (quad.stream().distinct().count() == 4) {
                            Set<Integer> values = evaluateFunctions(quad, opTriples);
                            Set<Integer> existing = quadValues.get(listToSortedString(quad));
                            existing.addAll(values);
                        }
                    }
                }
            }
        }

        int best = 0;
        String bestString = null;
        for (Map.Entry<String, Set<Integer>> result : quadValues.entrySet()) {
            int seqBest = longestSequenceFromOne(result.getValue().stream().sorted().collect(Collectors.toList()));
            if (seqBest > best) {
                best = seqBest;
                bestString = result.getKey();
            }
        }
        return bestString;
    }

    private static String listToSortedString(List<Double> quad) {
        List<Double> sortedQuad = quad.stream().sorted().collect(Collectors.toList());
        return String.valueOf(Math.round(sortedQuad.get(0))) +
                Math.round(sortedQuad.get(1)) +
                Math.round(sortedQuad.get(2)) +
                Math.round(sortedQuad.get(3));
    }

    /** Returns all possible quadruples of digits. */
    private static List<List<Double>> generateQuads() {
        List<List<Double>> quadruples = new ArrayList<>();
        recursivelyGenerate(1, new ArrayList<>(), quadruples);
        return quadruples;
    }

    /** Recursive helper for {@link #generateQuads}. */
    private static void recursivelyGenerate(double curDigit, List<Double> curNumbers, List<List<Double>> quads) {
        // End early if all digits are used or accumulator is full.
        if (curDigit > 9 || curNumbers.size() > 3)
            return;
        curNumbers.add(curDigit);
        if (curNumbers.size() == 4) {
            List<Double> quad = new ArrayList<>(curNumbers);
            Collections.copy(quad, curNumbers);
            quads.add(quad);
        } else {
            recursivelyGenerate(curDigit + 1, curNumbers, quads);
        }
        curNumbers.remove(curNumbers.size() - 1);
        recursivelyGenerate(curDigit + 1, curNumbers, quads);
    }

    private static List<List<BiFunction<Double, Double, Double>>> getOpTriples() {
        List<List<BiFunction<Double, Double, Double>>> opTriples = new ArrayList<>();

        for (BiFunction<Double, Double, Double> op1 : OPS) {
            for (BiFunction<Double, Double, Double> op2 : OPS) {
                for (BiFunction<Double, Double, Double> op3 : OPS) {
                    opTriples.add(Arrays.asList(op1, op2, op3));
                }
            }
        }
        return opTriples;
    }

    private static Set<Integer> evaluateFunctions(
            List<Double> quad,
            List<List<BiFunction<Double, Double, Double>>> opTriples) {
        Set<Integer> values = new HashSet<>();
        for (List<BiFunction<Double, Double, Double>> opTriple : opTriples) {
            BiFunction<Double, Double, Double> op1 = opTriple.get(0);
            BiFunction<Double, Double, Double> op2 = opTriple.get(1);
            BiFunction<Double, Double, Double> op3 = opTriple.get(2);
            evaluate1(quad, op1, op2, op3).ifPresent(values::add);
            evaluate2(quad, op1, op2, op3).ifPresent(values::add);
            evaluate3(quad, op1, op2, op3).ifPresent(values::add);
            evaluate4(quad, op1, op2, op3).ifPresent(values::add);
            evaluate5(quad, op1, op2, op3).ifPresent(values::add);
        }
        return values;
    }

    /** (op)op(op) */
    private static Optional<Integer> evaluate1(
            List<Double> quad,
            BiFunction<Double, Double, Double> op1,
            BiFunction<Double, Double, Double> op2,
            BiFunction<Double, Double, Double> op3) {
        double a = quad.get(0);
        double b = quad.get(1);
        double c = quad.get(2);
        double d = quad.get(3);

        return validate(op2.apply(op1.apply(a, b), op3.apply(c, d)));
    }

    /** (op(op))op */
    private static Optional<Integer> evaluate2(
            List<Double> quad,
            BiFunction<Double, Double, Double> op1,
            BiFunction<Double, Double, Double> op2,
            BiFunction<Double, Double, Double> op3) {
        double a = quad.get(0);
        double b = quad.get(1);
        double c = quad.get(2);
        double d = quad.get(3);

        return validate(op3.apply(op1.apply(a, op2.apply(b, c)), d));
    }

    /** ((op)op)op */
    private static Optional<Integer> evaluate3(
            List<Double> quad,
            BiFunction<Double, Double, Double> op1,
            BiFunction<Double, Double, Double> op2,
            BiFunction<Double, Double, Double> op3) {
        double a = quad.get(0);
        double b = quad.get(1);
        double c = quad.get(2);
        double d = quad.get(3);

        return validate(op3.apply(op2.apply(op1.apply(a, b), c), d));
    }

    /** op(op(op)) */
    private static Optional<Integer> evaluate4(
            List<Double> quad,
            BiFunction<Double, Double, Double> op1,
            BiFunction<Double, Double, Double> op2,
            BiFunction<Double, Double, Double> op3) {
        double a = quad.get(0);
        double b = quad.get(1);
        double c = quad.get(2);
        double d = quad.get(3);

        return validate(op1.apply(a, op2.apply(b, op3.apply(c, d))));
    }

    /** op((op)op) */
    private static Optional<Integer> evaluate5(
            List<Double> quad,
            BiFunction<Double, Double, Double> op1,
            BiFunction<Double, Double, Double> op2,
            BiFunction<Double, Double, Double> op3) {
        double a = quad.get(0);
        double b = quad.get(1);
        double c = quad.get(2);
        double d = quad.get(3);

        return validate(op1.apply(a, op3.apply(op2.apply(b, c), d)));
    }

    /**
     * Return {@link Optional} with value when {@code x} is within a small epsilon of an integer. Otherwise return
     * empty.
     *
     * Note: this is only needed because of the case where division followed by multiplication still yields an integer,
     * for example 2 / 3 * 6 = 4.
     */
    private static Optional<Integer> validate(double x) {
        if (Math.abs(x - Math.round(x)) < .0001 && x > 0) {
            return Optional.of((int) Math.round(x));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Returns the length of the successive sequence of integers in the input beginning at 1. Takes advantage of the
     * sequence starting at 1, and assumes the sequence is already sorted.
     */
    private static int longestSequenceFromOne(List<Integer> sequence) {
        int prior = 0;

        for (int i : sequence) {
            if (i != prior + 1) {
                break;
            }
            prior = i;
        }
        return prior;
    }
}
