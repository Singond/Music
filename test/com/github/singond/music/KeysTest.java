package com.github.singond.music;

import static com.github.singond.music.PitchClass.*;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
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
	public void gMajorScale() {
		System.out.println("G major scale between D3 and F5:");
		System.out.println(Keys.G_MAJOR.scale(Pitch.of(D, 3), Pitch.of(F, 5)));
		System.out.println();
	}

	@Test
	public void eFlatMajorScale() {
		System.out.println("Eb major scale between G3 and Eb5:");
		System.out.println(Keys.E_FLAT_MAJOR.scale(Pitch.of(G, 3), Pitch.of(E_FLAT, 5)));
		System.out.println();
	}

	@Test
	public void eFlatMajorScaleDown() {
		System.out.println("Eb major scale between Eb5 and G3:");
		List<Pitch> generated = Keys.E_FLAT_MAJOR.scale(Pitch.of(E_FLAT, 5), Pitch.of(G, 3));
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
}
