package com.github.singond.music;

import static com.github.singond.music.Pitch.*;
import static com.github.singond.music.PitchClass.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class KeysTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void majorKeys() {
		printScale(Keys.C_FLAT_MAJOR);
		printScale(Keys.G_FLAT_MAJOR);
		printScale(Keys.D_FLAT_MAJOR);
		printScale(Keys.A_FLAT_MAJOR);
		printScale(Keys.E_FLAT_MAJOR);
		printScale(Keys.B_FLAT_MAJOR);
		printScale(Keys.F_MAJOR);
		printScale(Keys.C_MAJOR);
		printScale(Keys.G_MAJOR);
		printScale(Keys.D_MAJOR);
		printScale(Keys.A_MAJOR);
		printScale(Keys.E_MAJOR);
		printScale(Keys.B_MAJOR);
		printScale(Keys.F_SHARP_MAJOR);
		printScale(Keys.C_SHARP_MAJOR);
		System.out.println();
	}

	@Test
	public void minorKeys() {
		printScale(Keys.A_FLAT_MINOR);
		printScale(Keys.E_FLAT_MINOR);
		printScale(Keys.B_FLAT_MINOR);
		printScale(Keys.F_MINOR);
		printScale(Keys.C_MINOR);
		printScale(Keys.G_MINOR);
		printScale(Keys.D_MINOR);
		printScale(Keys.A_MINOR);
		printScale(Keys.E_MINOR);
		printScale(Keys.B_MINOR);
		printScale(Keys.F_SHARP_MINOR);
		printScale(Keys.C_SHARP_MINOR);
		printScale(Keys.G_SHARP_MINOR);
		printScale(Keys.D_SHARP_MINOR);
		printScale(Keys.A_SHARP_MINOR);
		System.out.println();
	}

	private void printScale(Key key) {
		System.out.println("This is the " + key + " scale:");
		System.out.println(key.degrees());
	}

	@Test
	public void eFlatMajorScaleDownAndUp() {
		System.out.println("Eb major scale between Eb5 and G3:");
		Pitch from = Pitch.EB5;
		Pitch to = Pitch.G3;
		// Forward
		List<Pitch> forward = Keys.E_FLAT_MAJOR.scale(from, to);
		List<Pitch> forward2 = Keys.MAJOR.in(E_FLAT).scale(from, to);
		System.out.println(forward);
		List<Pitch> expected = Arrays.asList(
				Pitch.of(E_FLAT, 5),
				Pitch.of(D, 5),
				Pitch.of(C, 5),
				Pitch.of(B_FLAT, 4),
				Pitch.of(A_FLAT, 4),
				Pitch.of(G, 4),
				Pitch.of(F, 4),
				Pitch.of(E_FLAT, 4),
				Pitch.of(D, 4),
				Pitch.of(C, 4),
				Pitch.of(B_FLAT, 3),
				Pitch.of(A_FLAT, 3),
				Pitch.of(G, 3));
		assertEquals(expected, forward);
		assertEquals(expected, forward2);
		// Backward
		List<Pitch> backward = Keys.E_FLAT_MAJOR.scale(to, from);
		List<Pitch> backward2 = Keys.MAJOR.in(E_FLAT).scale(to, from);
		Collections.reverse(expected);
		assertEquals(expected, backward);
		assertEquals(expected, backward2);
		System.out.println();
	}

	@Test
	public void twoSharpScales() {
		List<Pitch> exp = Arrays.asList(
				FS3, G3, A3, B3, CS4, D4, E4, FS4, G4, A4, B4);
		scale(Keys.D_MAJOR, FS3, B4, exp);
		scale(Keys.B_MINOR, FS3, B4, exp);
	}

	@Test
	public void fourFlatScales() {
		List<Pitch> exp = Arrays.asList(
				EB5, F5, G5, AB5, BB5, C6, DB6, EB6, F6, G6);
		scale(Keys.A_FLAT_MAJOR, EB5, G6, exp);
		scale(Keys.F_MINOR, EB5, G6, exp);
	}

	private void scale(Key key, Pitch from, Pitch to, List<Pitch> expected) {
		System.out.format("%s scale between %s and %s:%n", key, from, to);
		expected = new ArrayList<>(expected);
		// Forward
		List<Pitch> forward = key.scale(from, to);
		List<Pitch> forward2 = key.type().in(key.tonic()).scale(from, to);
		System.out.println(forward);
		assertEquals(expected, forward);
		assertEquals(expected, forward2);
		// Backward
		List<Pitch> backward = key.scale(to, from);
		List<Pitch> backward2 = key.type().in(key.tonic()).scale(to, from);
		Collections.reverse(expected);
		assertEquals(expected, backward);
		assertEquals(expected, backward2);
		System.out.println();
	}

	@Test
	public void cFlatMinorScaleDown() {
		System.out.println("C minor scale between Eb5 and G3:");
		List<Pitch> generated = Keys.C_MINOR.scale(Pitch.of(E_FLAT, 5), Pitch.of(G, 3));
		System.out.println(generated);
		List<Pitch> expected = Arrays.asList(
				Pitch.of(E_FLAT, 5),
				Pitch.of(D, 5),
				Pitch.of(C, 5),
				Pitch.of(B_FLAT, 4),
				Pitch.of(A_FLAT, 4),
				Pitch.of(G, 4),
				Pitch.of(F, 4),
				Pitch.of(E_FLAT, 4),
				Pitch.of(D, 4),
				Pitch.of(C, 4),
				Pitch.of(B_FLAT, 3),
				Pitch.of(A_FLAT, 3),
				Pitch.of(G, 3));
		assertEquals(expected, generated);
		System.out.println();
	}

	@Test
	public void degrees() {
		assertEquals(G, Keys.C_MAJOR.degree(5));
		assertEquals(C_SHARP, Keys.F_SHARP_MAJOR.degree(5));
		assertEquals(B_FLAT, Keys.C_FLAT_MAJOR.degree(7));
		assertEquals(A, Keys.A_MINOR.degree(1));
		assertEquals(E_SHARP, Keys.D_SHARP_MINOR.degree(2));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void negativeDegree() {
		Keys.B_MINOR.degree(0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void overDegree() {
		Keys.B_MINOR.degree(8);
	}

	@Test
	public void strictIdentity() {
		// This compares instances!
		// For the common keys, this identity is expected to hold.
		assertEquals(Keys.G_MAJOR, MajorKey.G);
		assertEquals(Keys.G_MAJOR, Keys.MAJOR.in(G));
		assertEquals(Keys.F_SHARP_MINOR, MinorKey.F_SHARP);
		assertEquals(Keys.F_SHARP_MINOR, Keys.MINOR.in(F_SHARP));
	}
}
