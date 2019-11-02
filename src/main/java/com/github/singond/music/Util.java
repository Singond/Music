/*
 * Copyright 2019 Jan Slany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
