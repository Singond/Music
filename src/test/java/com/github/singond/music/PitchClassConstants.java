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

import static com.github.singond.music.PitchClass.*;
import static com.github.singond.music.SimpleInterval.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class PitchClassConstants {

	@Test
	public void equality() {
		assertEquals(C, PitchClass.of(BasePitchClass.C, Accidental.NATURAL));
		assertEquals(F_FLAT, PitchClass.of(BasePitchClass.F, Accidental.FLAT));
		assertEquals(B_SHARP, PitchClass.of(BasePitchClass.B, Accidental.SHARP));
	}
	
	@Test
	public void transpositionUp() {
		up(PitchClass.C, PERFECT_FIFTH, PitchClass.G);
		up(PitchClass.C, MINOR_THIRD, PitchClass.E_FLAT);
		up(PitchClass.B, MINOR_SECOND, PitchClass.C);
		up(PitchClass.B_FLAT, MAJOR_SECOND, PitchClass.C);
		up(PitchClass.B_FLAT, AUGMENTED_SECOND, PitchClass.C_SHARP);
		up(PitchClass.D, DIMINISHED_FIFTH, PitchClass.A_FLAT);
		up(PitchClass.D, AUGMENTED_FOURTH, PitchClass.G_SHARP);
		System.out.println();
	}

	@Test
	public void transpositionDown() {
		down(PitchClass.C, PERFECT_FIFTH, PitchClass.F);
		down(PitchClass.C, MINOR_THIRD, PitchClass.A);
		down(PitchClass.C, PERFECT_OCTAVE, PitchClass.C);
		down(PitchClass.B, MINOR_SECOND, PitchClass.A_SHARP);
		down(PitchClass.B_FLAT, AUGMENTED_SECOND, PitchClass.A_DBL_FLAT);
		down(PitchClass.D, DIMINISHED_FIFTH, PitchClass.G_SHARP);
		down(PitchClass.D, AUGMENTED_FOURTH, PitchClass.A_FLAT);
		System.out.println();
	}
	
	private void up(PitchClass original,
	                Interval interval,
	                PitchClass expected) {
		PitchClass actual = original.transposeUp(interval);
		assertEquals(expected, actual);
		System.out.format("The %s above %s is %s%n", interval, original, actual);
	}
	
	private void down(PitchClass original,
	                  Interval interval,
	                  PitchClass expected) {
		PitchClass actual = original.transposeDown(interval);
		assertEquals(expected, actual);
		System.out.format("The %s below %s is %s%n", interval, original, actual);
	}
}
