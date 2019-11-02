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
import static com.github.singond.music.SimpleInterval.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class IntervalsWithPitchClasses {

	private static final List<PitchClass> circleOfFifths;
	private static final List<PitchClass> circleOfFourths;
	static {
		circleOfFifths = new ArrayList<>(12);
		circleOfFifths.add(PitchClass.of(D, FLAT));
		circleOfFifths.add(PitchClass.of(A, FLAT));
		circleOfFifths.add(PitchClass.of(E, FLAT));
		circleOfFifths.add(PitchClass.of(B, FLAT));
		circleOfFifths.add(PitchClass.of(F, NATURAL));
		circleOfFifths.add(PitchClass.of(C, NATURAL));
		circleOfFifths.add(PitchClass.of(G, NATURAL));
		circleOfFifths.add(PitchClass.of(D, NATURAL));
		circleOfFifths.add(PitchClass.of(A, NATURAL));
		circleOfFifths.add(PitchClass.of(E, NATURAL));
		circleOfFifths.add(PitchClass.of(B, NATURAL));
		circleOfFifths.add(PitchClass.of(F, SHARP));
		
		circleOfFourths = new ArrayList<>(circleOfFifths);
		Collections.reverse(circleOfFourths);
	}
	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void simplePitchClassesTranspositionUp() {
		up(C, NATURAL, PERFECT_FIFTH, G, NATURAL);
		up(C, NATURAL, MINOR_THIRD, E, FLAT);
		up(C, NATURAL, AUGMENTED_SECOND, D, SHARP);
		up(C, SHARP, AUGMENTED_SECOND, D, DOUBLE_SHARP);
		up(C, NATURAL, DIMINISHED_FOURTH, F, FLAT);
		up(C, NATURAL, MAJOR_THIRD, E, NATURAL);
		up(C, NATURAL, PERFECT_OCTAVE, C, NATURAL);
		
		up(B, NATURAL, MINOR_SECOND, C, NATURAL);
		up(B, FLAT, MAJOR_SECOND, C, NATURAL);
		up(B, FLAT, AUGMENTED_SECOND, C, SHARP);
		
		up(D, NATURAL, DIMINISHED_FIFTH, A, FLAT);
		up(D, NATURAL, AUGMENTED_FOURTH, G, SHARP);
		System.out.println();
	}

	@Test
	public void simplePitchClassesTranspositionDown() {
		down(C, NATURAL, PERFECT_FIFTH, F, NATURAL);
		down(C, NATURAL, MINOR_THIRD, A, NATURAL);
		down(C, NATURAL, AUGMENTED_SECOND, B, DOUBLE_FLAT);
		down(C, SHARP, AUGMENTED_SECOND, B, FLAT);
		down(C, NATURAL, DIMINISHED_FOURTH, G, SHARP);
		down(C, NATURAL, MAJOR_THIRD, A, FLAT);
		down(C, NATURAL, PERFECT_OCTAVE, C, NATURAL);

		down(B, NATURAL, MINOR_SECOND, A, SHARP);
		down(B, NATURAL, MAJOR_SECOND, A, NATURAL);
		down(B, FLAT, MAJOR_SECOND, A, FLAT);
		down(B, FLAT, AUGMENTED_SECOND, A, DOUBLE_FLAT);

		down(D, NATURAL, DIMINISHED_FIFTH, G, SHARP);
		down(D, NATURAL, AUGMENTED_FOURTH, A, FLAT);
		System.out.println();
	}
	
	private void up(BasePitchClass startBase, Accidental startAccidental,
	                Interval interval,
	                BasePitchClass endBase, Accidental endAccidental) {
		PitchClass original = PitchClass.of(startBase, startAccidental);
		PitchClass expected = PitchClass.of(endBase, endAccidental);
		PitchClass actual = original.transposeUp(interval);
		assertEquals(expected, actual);
		System.out.format("The %s above %s is %s%n", interval, original, actual);
	}
	
	private void down(BasePitchClass startBase, Accidental startAccidental,
	                 Interval interval,
	                 BasePitchClass endBase, Accidental endAccidental) {
		PitchClass original = PitchClass.of(startBase, startAccidental);
		PitchClass expected = PitchClass.of(endBase, endAccidental);
		PitchClass actual = original.transposeDown(interval);
		assertEquals(expected, actual);
		System.out.format("The %s below %s is %s%n", interval, original, actual);
	}
	
	@Test
	public void circleOfFifthsUp() {
		PitchClass start = PitchClass.of(D, FLAT);
		PitchClass current = start;
		List<PitchClass> circleForward = new ArrayList<>();
		List<PitchClass> circleBackward = new ArrayList<>();
		
		do {
			circleForward.add(current);
			current = current.transposeUp(PERFECT_FIFTH);
		} while (!current.isEnharmonicWith(start));
		
		current = start;
		do {
			circleBackward.add(current);
			current = current.transposeDown(PERFECT_FOURTH);
		} while (!current.isEnharmonicWith(start));
		
		assertEquals(circleOfFifths, circleForward);
		System.out.println("This is the circle of fifths going up:");
		System.out.println(circleForward);
		
		assertEquals(circleOfFifths, circleBackward);
		System.out.println("This is the circle of fourths going down:");
		System.out.println(circleBackward);
		
		assertEquals(circleForward, circleBackward);
		System.out.println("The two circles are equal");
		System.out.println();
	}
	
	@Test
	public void circleOfFifthsDown() {
		PitchClass start = PitchClass.of(F, SHARP);
		PitchClass current = start;
		List<PitchClass> circleForward = new ArrayList<>();
		List<PitchClass> circleBackward = new ArrayList<>();
		
		do {
			circleForward.add(current);
			current = current.transposeDown(PERFECT_FIFTH);
		} while (!current.isEnharmonicWith(start));
		
		current = start;
		do {
			circleBackward.add(current);
			current = current.transposeUp(PERFECT_FOURTH);
		} while (!current.isEnharmonicWith(start));
		
		assertEquals(circleOfFourths, circleForward);
		System.out.println("This is the circle of fifths going down:");
		System.out.println(circleForward);
		
		assertEquals(circleOfFourths, circleBackward);
		System.out.println("This is the circle of fourths going up:");
		System.out.println(circleBackward);
		
		assertEquals(circleForward, circleBackward);
		System.out.println("The two circles are equal");
		System.out.println();
	}

}
