package com.github.singond.music;

/**
 * A utility class providing some common methods.
 */
class Util {

	/**
	 * Returns the floored quotient of two numbers.
	 * <p>
	 * This method mimics the similar function {@code java.lang.Math.floorDiv}
	 * available in Java 1.8.
	 *
	 * @param x dividend
	 * @param y divisor
	 * @return the floored quotient
	 */
	public static int floorDiv(int x, int y) {
		int q = x/y;
		if (x*y < 0 && q*y != x) {
			// The result is always rounded towards zero.
			// For negative q, this is the wrong direction: subtract
			// one to correct this (unless the division is exact).
			q -= 1;
		}
		return q;
	}

	/**
	 * Returns the mathematically true modulus of the given numbers.
	 * <p>
	 * This method mimics the similar function {@code java.lang.Math.floorMod}
	 * available in Java 1.8.
	 *
	 * @param x dividend
	 * @param y modulus
	 * @return x mod y
	 */
	public static int floorMod(int x, int y) {
		return x - y*floorDiv(x, y);
	}

}
