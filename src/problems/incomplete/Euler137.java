package problems.incomplete;

import problems.Problem;

/**
 * Wound up resorting to OEIS here after generating the initial sequence via
 * the commented out code. Performing some manipulation on the sequence
 * (multiply by x, then subtract, then see what can be expressed in terms of
 * original sequence) eventually yields a quadratic equation. Solve for the
 * roots and you find that the radical part is sqrt(5S^2 + 2S + 1) where S is
 * the original sequence. So the result is rational whenever 5S*S+2S+1 = k^2
 * for some integer k. This is also equivalent to (S+1)^2 + (2S)^2 = k^2, so
 * this can be approached as a Diophantine equation or a problem of Pythagorean
 * triples.
 *
 * Answer: 1120149658760
 */
public class Euler137 implements Problem {

  @Override
  public String solve() {

    long[] fibonacci = new long[100];
    fibonacci[0] = 0;
    fibonacci[1] = 1;
    for (int i = 2; i < fibonacci.length; i++) {
      fibonacci[i] = fibonacci[i-1] + fibonacci[i-2];
    }
    return String.valueOf(fibonacci[30] * fibonacci[31]);
//    long count = 0;
//    for (long i = 2; i < 100_000_000_000L; i++) {
//      long x = 5 * i * i + 2 * i + 1;
//      long k = (long) Math.sqrt(x);
//      if (k * k == x) {
//        count++;
//        System.out.println(count + ": " + i);
//      }
//      if (count == 15) {
//        return String.valueOf(i);
//      }
//    }
//    return null;
  }
}
