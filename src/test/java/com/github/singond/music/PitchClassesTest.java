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

import static com.github.singond.music.Accidental.*;
import static com.github.singond.music.BasePitchClass.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PitchClassesTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void symbolAndPitch() {
		PitchClass pc;
		pc = PitchClass.of(C, DOUBLE_FLAT);
		assertEquals("Cbb", pc.toString());
		assertEquals(-2, pc.stepsAboveReference());
		pc = PitchClass.of(C, FLAT);
		assertEquals("Cb", pc.toString());
		assertEquals(-1, pc.stepsAboveReference());
		pc = PitchClass.of(C, NATURAL);
		assertEquals("C", pc.toString());
		assertEquals(0, pc.stepsAboveReference());
		pc = PitchClass.of(C, SHARP);
		assertEquals("C#", pc.toString());
		assertEquals(1, pc.stepsAboveReference());
		pc = PitchClass.of(C, DOUBLE_SHARP);
		assertEquals("Cx", pc.toString());
		assertEquals(2, pc.stepsAboveReference());
		
		pc = PitchClass.of(D, DOUBLE_FLAT);
		assertEquals("Dbb", pc.toString());
		assertEquals(0, pc.stepsAboveReference());
		pc = PitchClass.of(D, FLAT);
		assertEquals("Db", pc.toString());
		assertEquals(1, pc.stepsAboveReference());
		pc = PitchClass.of(D, NATURAL);
		assertEquals("D", pc.toString());
		assertEquals(2, pc.stepsAboveReference());
		pc = PitchClass.of(D, SHARP);
		assertEquals("D#", pc.toString());
		assertEquals(3, pc.stepsAboveReference());
		pc = PitchClass.of(D, DOUBLE_SHARP);
		assertEquals("Dx", pc.toString());
		assertEquals(4, pc.stepsAboveReference());
		
		pc = PitchClass.of(E, DOUBLE_FLAT);
		assertEquals("Ebb", pc.toString());
		assertEquals(2, pc.stepsAboveReference());
		pc = PitchClass.of(E, FLAT);
		assertEquals("Eb", pc.toString());
		assertEquals(3, pc.stepsAboveReference());
		pc = PitchClass.of(E, NATURAL);
		assertEquals("E", pc.toString());
		assertEquals(4, pc.stepsAboveReference());
		pc = PitchClass.of(E, SHARP);
		assertEquals("E#", pc.toString());
		assertEquals(5, pc.stepsAboveReference());
		pc = PitchClass.of(E, DOUBLE_SHARP);
		assertEquals("Ex", pc.toString());
		assertEquals(6, pc.stepsAboveReference());
		
		pc = PitchClass.of(F, DOUBLE_FLAT);
		assertEquals("Fbb", pc.toString());
		assertEquals(3, pc.stepsAboveReference());
		pc = PitchClass.of(F, FLAT);
		assertEquals("Fb", pc.toString());
		assertEquals(4, pc.stepsAboveReference());
		pc = PitchClass.of(F, NATURAL);
		assertEquals("F", pc.toString());
		assertEquals(5, pc.stepsAboveReference());
		pc = PitchClass.of(F, SHARP);
		assertEquals("F#", pc.toString());
		assertEquals(6, pc.stepsAboveReference());
		pc = PitchClass.of(F, DOUBLE_SHARP);
		assertEquals("Fx", pc.toString());
		assertEquals(7, pc.stepsAboveReference());
		
		pc = PitchClass.of(G, DOUBLE_FLAT);
		assertEquals("Gbb", pc.toString());
		assertEquals(5, pc.stepsAboveReference());
		pc = PitchClass.of(G, FLAT);
		assertEquals("Gb", pc.toString());
		assertEquals(6, pc.stepsAboveReference());
		pc = PitchClass.of(G, NATURAL);
		assertEquals("G", pc.toString());
		assertEquals(7, pc.stepsAboveReference());
		pc = PitchClass.of(G, SHARP);
		assertEquals("G#", pc.toString());
		assertEquals(8, pc.stepsAboveReference());
		pc = PitchClass.of(G, DOUBLE_SHARP);
		assertEquals("Gx", pc.toString());
		assertEquals(9, pc.stepsAboveReference());
		
		pc = PitchClass.of(A, DOUBLE_FLAT);
		assertEquals("Abb", pc.toString());
		assertEquals(7, pc.stepsAboveReference());
		pc = PitchClass.of(A, FLAT);
		assertEquals("Ab", pc.toString());
		assertEquals(8, pc.stepsAboveReference());
		pc = PitchClass.of(A, NATURAL);
		assertEquals("A", pc.toString());
		assertEquals(9, pc.stepsAboveReference());
		pc = PitchClass.of(A, SHARP);
		assertEquals("A#", pc.toString());
		assertEquals(10, pc.stepsAboveReference());
		pc = PitchClass.of(A, DOUBLE_SHARP);
		assertEquals("Ax", pc.toString());
		assertEquals(11, pc.stepsAboveReference());
		
		pc = PitchClass.of(B, DOUBLE_FLAT);
		assertEquals("Bbb", pc.toString());
		assertEquals(9, pc.stepsAboveReference());
		pc = PitchClass.of(B, FLAT);
		assertEquals("Bb", pc.toString());
		assertEquals(10, pc.stepsAboveReference());
		pc = PitchClass.of(B, NATURAL);
		assertEquals("B", pc.toString());
		assertEquals(11, pc.stepsAboveReference());
		pc = PitchClass.of(B, SHARP);
		assertEquals("B#", pc.toString());
		assertEquals(12, pc.stepsAboveReference());
		pc = PitchClass.of(B, DOUBLE_SHARP);
		assertEquals("Bx", pc.toString());
		assertEquals(13, pc.stepsAboveReference());
	}
	
	@Test
	public void enharmonicity() {
		enharmonic(PitchClass.of(C, NATURAL), PitchClass.of(B, SHARP));
		enharmonic(PitchClass.of(C, NATURAL), PitchClass.of(D, DOUBLE_FLAT));
		nenharmonic(PitchClass.of(C, NATURAL), PitchClass.of(E, DOUBLE_FLAT));
		enharmonic(PitchClass.of(C, SHARP), PitchClass.of(B, DOUBLE_SHARP));
		
		enharmonic(PitchClass.of(A, SHARP), PitchClass.of(B, FLAT));
		nenharmonic(PitchClass.of(B, SHARP), PitchClass.of(A, FLAT));
	}
	
	private void enharmonic(PitchClass first, PitchClass second) {
		assertTrue("False positive on enharmonicity of "
				+ first + " and " + second, first.isEnharmonicWith(second));
	}
	
	private void nenharmonic(PitchClass first, PitchClass second) {
		assertFalse("Missed enharmonicity of "
				+ first + " and " + second, first.isEnharmonicWith(second));
	}

	@Test
	public void sequence() {
		int count = 22;
		int step = 1;
		
		BasePitchClass pitchClass = G;
		System.out.println("This should print the white keys of a piano spanning three octaves from G to G, going up:");
		for (int i = 0; i < count; i++) {
			System.out.print(pitchClass.toString() + ",");
			pitchClass = pitchClass.advance(step);
		}
		System.out.println(System.lineSeparator());
		
		step = -1;
		pitchClass = G;
		System.out.println("This should print the white keys of a piano spanning three octaves from G to G, going down:");
		for (int i = 0; i < count; i++) {
			System.out.print(pitchClass.toString() + ",");
			pitchClass = pitchClass.advance(step);
		}
	}
	
	@Test
	public void equality() {
		PitchClass first, second;
		
		first = PitchClass.of(F, SHARP);
		second = PitchClass.of(F, Accidental.ofSteps(1));
		assertEquals(first, second);
		
		first = PitchClass.of(F, SHARP);
		second = PitchClass.of(G, Accidental.ofSteps(-1));
		assertNotEquals(first, second);
	}
	
	@Test
	public void relativeOctaveNumber() {
		relativeOct(PitchClass.C_DBL_FLAT, -1);
		relativeOct(PitchClass.C_FLAT, -1);
		relativeOct(PitchClass.C, 0);
		relativeOct(PitchClass.D_DBL_FLAT, 0);
		relativeOct(PitchClass.D_FLAT, 0);
		relativeOct(PitchClass.B_FLAT, 0);
		relativeOct(PitchClass.B, 0);
		relativeOct(PitchClass.B_SHARP, 1);
		relativeOct(PitchClass.B_DBL_SHARP, 1);
	}
	
	private void relativeOct(PitchClass pc, int expected) {
		assertEquals("Bad relative octave", expected, pc.relativeOctave());
	}
}
