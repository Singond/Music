package com.github.singond.music;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class AccidentalOrdering {

	@Test
	public void simpleComparison() {
		assertTrue(Accidental.DOUBLE_FLAT.compareTo(Accidental.FLAT) < 0);
		assertTrue(Accidental.FLAT.compareTo(Accidental.NATURAL) < 0);
		assertTrue(Accidental.NATURAL.compareTo(Accidental.SHARP) < 0);
		assertTrue(Accidental.SHARP.compareTo(Accidental.DOUBLE_SHARP) < 0);
		
		assertTrue(Accidental.DOUBLE_SHARP.compareTo(Accidental.SHARP) > 0);
		assertTrue(Accidental.SHARP.compareTo(Accidental.NATURAL) > 0);
		assertTrue(Accidental.NATURAL.compareTo(Accidental.FLAT) > 0);
		assertTrue(Accidental.FLAT.compareTo(Accidental.DOUBLE_FLAT) > 0);
	}
	
	@Test
	public void ordering() {
		List<Accidental> acc = Arrays.asList(
				Accidental.DOUBLE_SHARP,
				Accidental.DOUBLE_FLAT,
				Accidental.FLAT,
				Accidental.NATURAL,
				Accidental.SHARP);
		final List<Accidental> expected = Arrays.asList(
				Accidental.DOUBLE_FLAT,
				Accidental.FLAT,
				Accidental.NATURAL,
				Accidental.SHARP,
				Accidental.DOUBLE_SHARP);
		Collections.shuffle(acc);
		System.out.println("Unsorted: " + acc);
		Collections.sort(acc);
		System.out.println("Sorted:   " + acc);
		assertEquals("Accidentals are not being ordered properly", expected, acc);
	}
}
