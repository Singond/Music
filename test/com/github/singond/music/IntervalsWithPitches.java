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

public class IntervalsWithPitches {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void simplePitchTranspositionUp() {
		up(C, NATURAL, 1, PERFECT_FIFTH, G, NATURAL, 1);
		up(A, NATURAL, 4, PERFECT_FOURTH, D, NATURAL, 5);
		
		// Now let's be nasty
		up(G, NATURAL, -1, MAJOR_SIXTH, E, NATURAL, 0);
		up(B, FLAT, -5, MAJOR_SECOND, C, NATURAL, -4);
		System.out.println();
	}

//	@Test
//	public void simplePitchTranspositionDown() {
//		down(C, NATURAL, PERFECT_FIFTH, F, NATURAL);
//		down(C, NATURAL, MINOR_THIRD, A, NATURAL);
//		down(C, NATURAL, AUGMENTED_SECOND, B, DOUBLE_FLAT);
//		down(C, SHARP, AUGMENTED_SECOND, B, FLAT);
//		down(C, NATURAL, DIMINISHED_FOURTH, G, SHARP);
//		down(C, NATURAL, MAJOR_THIRD, A, FLAT);
//		down(C, NATURAL, PERFECT_OCTAVE, C, NATURAL);
//
//		down(B, NATURAL, MINOR_SECOND, A, SHARP);
//		down(B, NATURAL, MAJOR_SECOND, A, NATURAL);
//		down(B, FLAT, MAJOR_SECOND, A, FLAT);
//		down(B, FLAT, AUGMENTED_SECOND, A, DOUBLE_FLAT);
//
//		down(D, NATURAL, DIMINISHED_FIFTH, G, SHARP);
//		down(D, NATURAL, AUGMENTED_FOURTH, A, FLAT);
//		System.out.println();
//	}
	
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
	
//	private void down(BasePitchClass startBase, Accidental startAccidental,
//		              int startOctave, Interval interval,
//		              BasePitchClass endBase, Accidental endAccidental,
//		              int endOctave) {
//		Pitch original = Pitch.of(startBase, startAccidental, startOctave);
//		Pitch expected = Pitch.of(endBase, endAccidental, endOctave);
//		Pitch actual = original.transposeDown(interval);
//		assertEquals(expected, actual);
//		System.out.format("The %s below %s is %s%n", interval, original, actual);
//	}

}
