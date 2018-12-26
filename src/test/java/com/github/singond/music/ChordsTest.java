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

import static com.github.singond.music.PitchClass.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ChordsTest {

	@Test
	public void byBass() {
		System.out.println("Creating chords by bass note:");
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
		check(TypedChord.ofBass(D, SmallChordType.MAJOR_7),
				Arrays.asList(D, F_SHARP, A, C_SHARP), D, 0);
		check(TypedChord.ofBass(D, SmallChordType.MINOR_7),
				Arrays.asList(D, F, A, C), D, 0);
		check(TypedChord.ofBass(D, DefaultChordType.DIMINISHED_7),
				Arrays.asList(D, F, A_FLAT, C_FLAT), D, 0);
		check(TypedChord.ofBass(F, SmallChordType.DOMINANT_7),
				Arrays.asList(F, A, C, E_FLAT), F, 0);
		System.out.println();
	}

	@Test
	public void byRoot() {
		System.out.println("Creating chords by root note:");
		check(TypedChord.ofRoot(C, SmallChordType.MAJOR_TRIAD),
				Arrays.asList(C, E, G), C, 0);
		check(TypedChord.ofRoot(C, SmallChordType.MAJOR_TRIAD.invert(1)),
				Arrays.asList(E, G, C), C, 1);
		check(TypedChord.ofRoot(C, SmallChordType.MAJOR_TRIAD.invert(2)),
				Arrays.asList(G, C, E), C, 2);

		check(TypedChord.ofRoot(E, SmallChordType.MAJOR_TRIAD.invert(1)),
				Arrays.asList(G_SHARP, B, E), E, 1);

		check(TypedChord.ofRoot(G, SmallChordType.MAJOR_TRIAD),
				Arrays.asList(G, B, D), G, 0);
		check(TypedChord.ofRoot(G, SmallChordType.MAJOR_TRIAD.invert(2)),
				Arrays.asList(D, G, B), G, 2);

		check(TypedChord.ofRoot(C_FLAT, SmallChordType.MINOR_TRIAD),
				Arrays.asList(C_FLAT, E_DBL_FLAT, G_FLAT), C_FLAT, 0);
		check(TypedChord.ofRoot(C_FLAT, SmallChordType.MINOR_TRIAD.invert(1)),
				Arrays.asList(E_DBL_FLAT, G_FLAT, C_FLAT), C_FLAT, 1);

		check(TypedChord.ofRoot(D, SmallChordType.DOMINANT_7),
				Arrays.asList(D, F_SHARP, A, C), D, 0);
		check(TypedChord.ofRoot(F, SmallChordType.DOMINANT_7),
				Arrays.asList(F, A, C, E_FLAT), F, 0);
		System.out.println();
	}

	@Test
	public void inversions() {
		System.out.println("Checking inversions:");
		Chord chord = TypedChord.ofRoot(C, SmallChordType.MAJOR_TRIAD);
		check(chord.rootPosition(), Arrays.asList(C, E, G), C, 0);
		check(chord.invert(0), Arrays.asList(C, E, G), C, 0);
		check(chord.invert(1), Arrays.asList(E, G, C), C, 1);
		check(chord.invert(2), Arrays.asList(G, C, E), C, 2);

		chord = TypedChord.ofRoot(C, SmallChordType.MAJOR_TRIAD_64);
		check(chord.rootPosition(), Arrays.asList(C, E, G), C, 0);
		check(chord.invert(0), Arrays.asList(C, E, G), C, 0);
		check(chord.invert(1), Arrays.asList(E, G, C), C, 1);
		check(chord.invert(2), Arrays.asList(G, C, E), C, 2);

		chord = TypedChord.ofBass(C, SmallChordType.MAJOR_TRIAD);
		check(chord.rootPosition(), Arrays.asList(C, E, G), C, 0);
		check(chord.invert(0), Arrays.asList(C, E, G), C, 0);
		check(chord.invert(1), Arrays.asList(E, G, C), C, 1);
		check(chord.invert(2), Arrays.asList(G, C, E), C, 2);

		chord = TypedChord.ofBass(E, SmallChordType.MAJOR_TRIAD_6);
		check(chord.rootPosition(), Arrays.asList(C, E, G), C, 0);
		check(chord.invert(0), Arrays.asList(C, E, G), C, 0);
		check(chord.invert(1), Arrays.asList(E, G, C), C, 1);
		check(chord.invert(2), Arrays.asList(G, C, E), C, 2);

		chord = TypedChord.ofBass(A, SmallChordType.MAJOR_TRIAD);
		check(chord.rootPosition(), Arrays.asList(A, C_SHARP, E), A, 0);
		check(chord.invert(0), Arrays.asList(A, C_SHARP, E), A, 0);
		check(chord.invert(1), Arrays.asList(C_SHARP, E, A), A, 1);
		check(chord.invert(2), Arrays.asList(E, A, C_SHARP), A, 2);

		chord = TypedChord.ofRoot(E, SmallChordType.MAJOR_7);
		check(chord.rootPosition(), Arrays.asList(E, G_SHARP, B, D_SHARP), E, 0);
		check(chord.invert(0), Arrays.asList(E, G_SHARP, B, D_SHARP), E, 0);
		check(chord.invert(1), Arrays.asList(G_SHARP, B, D_SHARP, E), E, 1);
		check(chord.invert(2), Arrays.asList(B, D_SHARP, E, G_SHARP), E, 2);
		System.out.println();
	}

	/**
	 * @param ch the chord under test
	 * @param expected the expected notes in the chord, from bass to top
	 * @param root expected value of the root note
	 * @param inversion expected inversion number of the chord
	 */
	private void check(Chord ch, List<PitchClass> expected, PitchClass root,
	                   int inversion) {
		System.out.format("%3s %-20s: %s%n", ch.root(), ch.type(), ch.notes());

		assertEquals("Unexpected note structure: ", expected, ch.notes());
		assertEquals("Unexpected root: ", root, ch.root());
		assertEquals("Unexpected bass: ", expected.get(0), ch.bass());
		assertEquals("Unexpected size: ", expected.size(), ch.size());
		assertEquals("Unexpected inversion: ", inversion, ch.inversion());

		// Check iterator
		List<PitchClass> iterated = new ArrayList<>();
		for (PitchClass pc : ch) iterated.add(pc);
		assertEquals("Bad iterator: ", iterated, expected);
	}
}
