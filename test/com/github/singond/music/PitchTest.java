package com.github.singond.music;

import static com.github.singond.music.Accidental.*;
import static com.github.singond.music.BasePitchClass.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class PitchTest {

	@Test
	public void basic() {
		Pitch p = Pitch.of(C, NATURAL, 4);
		assertEquals(60, p.midiNumber());
		assertEquals(Pitch.of(C, NATURAL, 4), p);
	}

	@Test
	public void enharmonicity() {
		enharmonic(Pitch.of(C, SHARP, 2), Pitch.of(D, FLAT, 2));
		nenharmonic(Pitch.of(C, SHARP, 2), Pitch.of(D, FLAT, 3));
		
		enharmonic(Pitch.of(C, NATURAL, 5), Pitch.of(B, SHARP, 4));
		nenharmonic(Pitch.of(C, NATURAL, 5), Pitch.of(B, SHARP, 5));
		enharmonic(Pitch.of(C, FLAT, 5), Pitch.of(B, NATURAL, 4));
		nenharmonic(Pitch.of(C, FLAT, 5), Pitch.of(B, NATURAL, 5));
		
		enharmonic(Pitch.of(G, SHARP, 4), Pitch.of(A, FLAT, 4));
		enharmonic(Pitch.of(C, SHARP, 3), Pitch.of(B, DOUBLE_SHARP, 2));
	}
	
	private void enharmonic(Pitch first, Pitch second) {
		assertTrue("False positive on enharmonicity of "
				+ first + " and " + second, first.isEnharmonicWith(second));
	}
	
	private void nenharmonic(Pitch first, Pitch second) {
		assertFalse("Missed enharmonicity of "
				+ first + " and " + second, first.isEnharmonicWith(second));
	}
	
	@Test
	public void sortingInOctave() {
		List<Pitch> pitches = pitchesInOctave();
		Collections.shuffle(pitches);
		
		System.out.println("Sorting a shuffled list of pitches");
		System.out.println(pitches);
		Collections.sort(pitches);
		System.out.println(pitches);
	}
	
	private static List<Pitch> pitchesInOctave() {
		List<Pitch> pitches = new ArrayList<>();
		
		pitches.add(Pitch.of(C, DOUBLE_FLAT, 4));
		pitches.add(Pitch.of(C, FLAT, 4));
		pitches.add(Pitch.of(C, NATURAL, 4));
		pitches.add(Pitch.of(C, SHARP, 4));
		pitches.add(Pitch.of(C, DOUBLE_SHARP, 4));
		
		pitches.add(Pitch.of(D, DOUBLE_FLAT, 4));
		pitches.add(Pitch.of(D, FLAT, 4));
		pitches.add(Pitch.of(D, NATURAL, 4));
		pitches.add(Pitch.of(D, SHARP, 4));
		pitches.add(Pitch.of(D, DOUBLE_SHARP, 4));
		
		pitches.add(Pitch.of(E, DOUBLE_FLAT, 4));
		pitches.add(Pitch.of(E, FLAT, 4));
		pitches.add(Pitch.of(E, NATURAL, 4));
		pitches.add(Pitch.of(E, SHARP, 4));
		pitches.add(Pitch.of(E, DOUBLE_SHARP, 4));
		
		pitches.add(Pitch.of(F, DOUBLE_FLAT, 4));
		pitches.add(Pitch.of(F, FLAT, 4));
		pitches.add(Pitch.of(F, NATURAL, 4));
		pitches.add(Pitch.of(F, SHARP, 4));
		pitches.add(Pitch.of(F, DOUBLE_SHARP, 4));
		
		pitches.add(Pitch.of(G, DOUBLE_FLAT, 4));
		pitches.add(Pitch.of(G, FLAT, 4));
		pitches.add(Pitch.of(G, NATURAL, 4));
		pitches.add(Pitch.of(G, SHARP, 4));
		pitches.add(Pitch.of(G, DOUBLE_SHARP, 4));
		
		pitches.add(Pitch.of(A, DOUBLE_FLAT, 4));
		pitches.add(Pitch.of(A, FLAT, 4));
		pitches.add(Pitch.of(A, NATURAL, 4));
		pitches.add(Pitch.of(A, SHARP, 4));
		pitches.add(Pitch.of(A, DOUBLE_SHARP, 4));
		
		pitches.add(Pitch.of(B, DOUBLE_FLAT, 4));
		pitches.add(Pitch.of(B, FLAT, 4));
		pitches.add(Pitch.of(B, NATURAL, 4));
		pitches.add(Pitch.of(B, SHARP, 4));
		pitches.add(Pitch.of(B, DOUBLE_SHARP, 4));
		
		return pitches;
	}
}
