package com.github.singond.music;

import static com.github.singond.music.Accidental.*;
import static com.github.singond.music.BasePitchClass.*;
import static com.github.singond.music.SimpleInterval.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class IntervalCalculation {

	private static final List<PitchClass> circleOfFifths;
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
	
	private void up(BasePitchClass startBase, Accidental startAccidental,
	               Interval interval,
	               BasePitchClass endBase, Accidental endAccidental) {
		PitchClass original = PitchClass.of(startBase, startAccidental);
		PitchClass expected = PitchClass.of(endBase, endAccidental);
		PitchClass actual = original.transposeUp(interval);
		assertEquals(expected, actual);
		System.out.format("The %s above %s is %s%n", interval, original, actual);
	}
	
	@Test
	public void circleOfFifthsUp() {
		PitchClass start = PitchClass.of(D, FLAT);
		PitchClass current = start;
		List<PitchClass> circle = new ArrayList<>();
		
		do {
			circle.add(current);
			current = current.transposeUp(PERFECT_FIFTH);
		} while (!current.isEnharmonicWith(start));
		
		assertEquals(circleOfFifths, circle);
		System.out.println("This is the circle of fifths:");
		System.out.println(circle);
		System.out.println();
	}

}
