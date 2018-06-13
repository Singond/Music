package com.github.singond.music;

import static com.github.singond.music.PitchClass.*;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ChordGeneration {

	@Test
	public void simple() {
		check(TypedChord.ofBass(C, SmallChordType.MAJOR_TRIAD),
		      Arrays.asList(C, E, G), C, 0);
		check(TypedChord.ofBass(E, SmallChordType.MAJOR_TRIAD.invert(1)),
		      Arrays.asList(E, G, C), C, 1);
		check(TypedChord.ofBass(G, SmallChordType.MAJOR_TRIAD.invert(2)),
		      Arrays.asList(G, C, E), C, 2);

		check(TypedChord.ofBass(G, SmallChordType.MAJOR_TRIAD),
		      Arrays.asList(G, B, D), G, 0);

		check(TypedChord.ofBass(C_FLAT, SmallChordType.MINOR_TRIAD),
		      Arrays.asList(C_FLAT, E_DBL_FLAT, G_FLAT), C_FLAT, 0);
		check(TypedChord.ofBass(E_DBL_FLAT, SmallChordType.MINOR_TRIAD.invert(1)),
		      Arrays.asList(E_DBL_FLAT, G_FLAT, C_FLAT), C_FLAT, 1);

		check(TypedChord.ofBass(D, SmallChordType.DOMINANT_7),
		      Arrays.asList(D, F_SHARP, A, C), D, 0);
		check(TypedChord.ofBass(F, SmallChordType.DOMINANT_7),
		      Arrays.asList(F, A, C, E_FLAT), F, 0);
	}

	/**
	 * @param ch the chord under test
	 * @param root expected value of the root index
	 * @param structure expected interval structure of the chord
	 */
	private void check(Chord ch, List<PitchClass> notes, PitchClass root,
	                   int inversion) {
		System.out.format("%3s %-20s: %s%n", ch.root(), ch.type(), ch.notes());
		assertEquals("Unexpected note structure: ", notes, ch.notes());
		assertEquals("Unexpected root: ", root, ch.root());
		assertEquals("Unexpected bass: ", notes.get(0), ch.bass());
		assertEquals("Unexpected size: ", notes.size(), ch.size());
		assertEquals("Unexpected inversion: ", inversion, ch.inversion());
	}
}
