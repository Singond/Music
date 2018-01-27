package com.github.singond.music;

import static com.github.singond.music.Accidental.*;
import static com.github.singond.music.BasePitchClass.*;
import static com.github.singond.music.SimpleInterval.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class IntervalsWithPitches {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void simplePitchTranspositionUp() {
		up(C, NATURAL, 1, PERFECT_FIFTH, G, NATURAL, 1);
		up(A, NATURAL, 4, PERFECT_FOURTH, D, NATURAL, 5);
		up(D, DOUBLE_SHARP, 5, MINOR_SEVENTH, C, DOUBLE_SHARP, 6);
		up(F, SHARP, 2, UNISON, F, SHARP, 2);
		
		// Now let's be nasty
		up(G, NATURAL, -1, MAJOR_SIXTH, E, NATURAL, 0);
		up(B, FLAT, -5, MAJOR_SECOND, C, NATURAL, -4);
		System.out.println();
	}

	@Test
	public void simplePitchTranspositionDown() {
		down(C, NATURAL, 4, PERFECT_FIFTH, F, NATURAL, 3);
		down(C, NATURAL, 3, MINOR_THIRD, A, NATURAL, 2);
		down(C, NATURAL, 0, AUGMENTED_SECOND, B, DOUBLE_FLAT, -1);
		down(C, SHARP, 4, AUGMENTED_SECOND, B, FLAT, 3);
		down(C, NATURAL, 6, DIMINISHED_FOURTH, G, SHARP, 5);
		down(C, NATURAL, 0, MAJOR_THIRD, A, FLAT, -1);
		down(C, NATURAL, 5, PERFECT_OCTAVE, C, NATURAL, 4);
		down(C, NATURAL, 5, UNISON, C, NATURAL, 5);

		down(B, NATURAL, 5, MINOR_SECOND, A, SHARP, 5);
		down(B, NATURAL, 4, MAJOR_SECOND, A, NATURAL, 4);
		down(B, FLAT, 4, MAJOR_SECOND, A, FLAT, 4);
		down(B, FLAT, 6, AUGMENTED_SECOND, A, DOUBLE_FLAT, 6);

		down(D, NATURAL, 4, DIMINISHED_FIFTH, G, SHARP, 3);
		down(D, NATURAL, 3, AUGMENTED_FOURTH, A, FLAT, 2);
		System.out.println();
	}
	
	private void up(BasePitchClass startBase, Accidental startAccidental,
	                int startOctave, Interval interval,
	                BasePitchClass endBase, Accidental endAccidental,
	                int endOctave) {
		Pitch original = Pitch.of(startBase, startAccidental, startOctave);
		Pitch expected = Pitch.of(endBase, endAccidental, endOctave);
		Pitch actual = original.transposeUp(interval);
		assertEquals(expected, actual);
		System.out.format("The %s above %s is %s%n", interval, original, actual);
	}
	
	private void down(BasePitchClass startBase, Accidental startAccidental,
	                  int startOctave, Interval interval,
	                  BasePitchClass endBase, Accidental endAccidental,
	                  int endOctave) {
		Pitch original = Pitch.of(startBase, startAccidental, startOctave);
		Pitch expected = Pitch.of(endBase, endAccidental, endOctave);
		Pitch actual = original.transposeDown(interval);
		assertEquals(expected, actual);
		System.out.format("The %s below %s is %s%n", interval, original, actual);
	}
}
