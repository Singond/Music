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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public class PitchClassValueOf {

	Pattern pattern;

	@Before
	public void initPattern() throws IllegalArgumentException,
			IllegalAccessException, NoSuchFieldException, SecurityException {
		Field f = PitchClass.class.getDeclaredField("STRING_PATTERN");
		f.setAccessible(true);
		pattern = (Pattern) f.get(null);
	}

	@Test
	public void pattern() {
		assertTrue(testPattern("C"));
		assertTrue(testPattern("H"));
		assertTrue(testPattern("Bb"));
		assertTrue(testPattern("Cx"));
		assertTrue(testPattern("Gb"));
		assertTrue(testPattern("Gbbb"));
		assertTrue(testPattern("D#"));

		assertFalse(testPattern("Gp"));
		assertFalse(testPattern("Fis"));
		assertFalse(testPattern("F4"));
		assertFalse(testPattern(" C"));
		assertFalse(testPattern("C "));
	}

	private boolean testPattern(String s) {
		return pattern.matcher(s).matches();
	}

	@Test
	public void convertStandard() {
		assertEquals(PitchClass.C_DBL_FLAT, PitchClass.valueOf("Cbb"));
		assertEquals(PitchClass.D_DBL_FLAT, PitchClass.valueOf("Dbb"));
		assertEquals(PitchClass.E_DBL_FLAT, PitchClass.valueOf("Ebb"));
		assertEquals(PitchClass.F_DBL_FLAT, PitchClass.valueOf("Fbb"));
		assertEquals(PitchClass.G_DBL_FLAT, PitchClass.valueOf("Gbb"));
		assertEquals(PitchClass.A_DBL_FLAT, PitchClass.valueOf("Abb"));
		assertEquals(PitchClass.B_DBL_FLAT, PitchClass.valueOf("Bbb"));

		assertEquals(PitchClass.C_FLAT, PitchClass.valueOf("Cb"));
		assertEquals(PitchClass.D_FLAT, PitchClass.valueOf("Db"));
		assertEquals(PitchClass.E_FLAT, PitchClass.valueOf("Eb"));
		assertEquals(PitchClass.F_FLAT, PitchClass.valueOf("Fb"));
		assertEquals(PitchClass.G_FLAT, PitchClass.valueOf("Gb"));
		assertEquals(PitchClass.A_FLAT, PitchClass.valueOf("Ab"));
		assertEquals(PitchClass.B_FLAT, PitchClass.valueOf("Bb"));

		assertEquals(PitchClass.C, PitchClass.valueOf("C"));
		assertEquals(PitchClass.D, PitchClass.valueOf("D"));
		assertEquals(PitchClass.E, PitchClass.valueOf("E"));
		assertEquals(PitchClass.F, PitchClass.valueOf("F"));
		assertEquals(PitchClass.G, PitchClass.valueOf("G"));
		assertEquals(PitchClass.A, PitchClass.valueOf("A"));
		assertEquals(PitchClass.B, PitchClass.valueOf("B"));

		assertEquals(PitchClass.C_SHARP, PitchClass.valueOf("C#"));
		assertEquals(PitchClass.D_SHARP, PitchClass.valueOf("D#"));
		assertEquals(PitchClass.E_SHARP, PitchClass.valueOf("E#"));
		assertEquals(PitchClass.F_SHARP, PitchClass.valueOf("F#"));
		assertEquals(PitchClass.G_SHARP, PitchClass.valueOf("G#"));
		assertEquals(PitchClass.A_SHARP, PitchClass.valueOf("A#"));
		assertEquals(PitchClass.B_SHARP, PitchClass.valueOf("B#"));

		assertEquals(PitchClass.C_DBL_SHARP, PitchClass.valueOf("Cx"));
		assertEquals(PitchClass.D_DBL_SHARP, PitchClass.valueOf("Dx"));
		assertEquals(PitchClass.E_DBL_SHARP, PitchClass.valueOf("Ex"));
		assertEquals(PitchClass.F_DBL_SHARP, PitchClass.valueOf("Fx"));
		assertEquals(PitchClass.G_DBL_SHARP, PitchClass.valueOf("Gx"));
		assertEquals(PitchClass.A_DBL_SHARP, PitchClass.valueOf("Ax"));
		assertEquals(PitchClass.B_DBL_SHARP, PitchClass.valueOf("Bx"));
	}

	@Test
	public void convertGeneral() {
		assertEquals(PitchClass.of(BasePitchClass.C, Accidental.ofSteps(3)),
				PitchClass.valueOf("C###"));
	}

	@Test(expected = FormatException.class)
	public void inconvertibleEmpty() {
		PitchClass.valueOf("");
	}

	@Test(expected = FormatException.class)
	public void inconvertibleFormat() {
		PitchClass.valueOf("b");
	}
}
