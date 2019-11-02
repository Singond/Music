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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DegreeTest {

	@Test
	public void values() {
		assertEquals(Degree.of(1, 0),  Degree.I);
		assertEquals(Degree.of(1, -1), Degree.I_LOWERED);
		assertEquals(Degree.of(1, 1),  Degree.I_RAISED);
		assertEquals(Degree.of(2, 0),  Degree.II);
		assertEquals(Degree.of(2, -1), Degree.II_LOWERED);
		assertEquals(Degree.of(2, 1),  Degree.II_RAISED);
		assertEquals(Degree.of(3, 0),  Degree.III);
		assertEquals(Degree.of(3, -1), Degree.III_LOWERED);
		assertEquals(Degree.of(3, 1),  Degree.III_RAISED);
		assertEquals(Degree.of(4, 0),  Degree.IV);
		assertEquals(Degree.of(4, -1), Degree.IV_LOWERED);
		assertEquals(Degree.of(4, 1),  Degree.IV_RAISED);
		assertEquals(Degree.of(5, 0),  Degree.V);
		assertEquals(Degree.of(5, -1), Degree.V_LOWERED);
		assertEquals(Degree.of(5, 1),  Degree.V_RAISED);
		assertEquals(Degree.of(6, 0),  Degree.VI);
		assertEquals(Degree.of(6, -1), Degree.VI_LOWERED);
		assertEquals(Degree.of(6, 1),  Degree.VI_RAISED);
		assertEquals(Degree.of(7, 0),  Degree.VII);
		assertEquals(Degree.of(7, -1), Degree.VII_LOWERED);
		assertEquals(Degree.of(7, 1),  Degree.VII_RAISED);
	}

	@Test
	public void creation() {
		System.out.println("Creating basic instances:");
		creation(1, 0);
		creation(1, -1);
		creation(1, 1);
		creation(2, 0);
		creation(2, -1);
		creation(2, 1);
		creation(3, 0);
		creation(3, -1);
		creation(3, 1);
		creation(4, 0);
		creation(4, -1);
		creation(4, 1);
		creation(5, 0);
		creation(5, -1);
		creation(5, 1);
		creation(6, 0);
		creation(6, -1);
		creation(6, 1);
		creation(7, 0);
		creation(7, -1);
		creation(7, 1);
		System.out.println();
	}

	private void creation(int base, int shift) {
		Degree degree = Degree.of(base, shift);
		assertEquals(base, degree.base());
		assertEquals(shift, degree.shift());
		System.out.format("Degree %d shifted by %d: %s%n",
				base, shift, degree.toString());
	}

	@Test
	public void keyDegrees() {
		System.out.println("Degree of a key:");
		deg(Keys.D_MAJOR, Degree.III_LOWERED, PitchClass.F);
		deg(Keys.D_MAJOR, Degree.III, PitchClass.F_SHARP);
		deg(Keys.D_MAJOR, Degree.III_RAISED, PitchClass.F_DBL_SHARP);
		deg(Keys.G_SHARP_MINOR, Degree.III_RAISED, PitchClass.B_SHARP);
		deg(Keys.G_SHARP_MINOR, Degree.IV_LOWERED, PitchClass.C);
		deg(Keys.G_SHARP_MINOR, Degree.IV, PitchClass.C_SHARP);
		deg(Keys.G_SHARP_MINOR, Degree.IV_RAISED, PitchClass.C_DBL_SHARP);
		System.out.println();
	}

	private void deg(Key key, Degree deg, PitchClass exp) {
		PitchClass pc = key.degree(deg);
		System.out.format("%s of %s: %s%n", deg, key, pc);
		assertEquals(exp, pc);
	}
}
