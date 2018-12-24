package com.github.singond.music;

import static com.github.singond.music.Pitch.*;
import static com.github.singond.music.PitchClass.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ChordVoicingTest {

	@Test
	public void byBass() {
		System.out.println("Voicing chords by bass note:");
		check(TypedChordVoicing.ofBass(C4, Chords.MAJOR_TRIAD),
				Arrays.asList(C4, E4, G4), C, 0);
		check(TypedChordVoicing.ofBass(E4, Chords.MAJOR_TRIAD.invert(1)),
				Arrays.asList(E4, G4, C5), C, 1);
		check(TypedChordVoicing.ofBass(G3, Chords.MAJOR_TRIAD.invert(2)),
				Arrays.asList(G3, C4, E4), C, 2);

		check(TypedChordVoicing.ofBass(G4, Chords.MAJOR_TRIAD),
				Arrays.asList(G4, B4, D5), G, 0);

		check(TypedChordVoicing.ofBass(CB3, Chords.MINOR_TRIAD),
				Arrays.asList(CB3, EBB3, GB3), C_FLAT, 0);
		check(TypedChordVoicing.ofBass(EBB4, Chords.MINOR_TRIAD.invert(1)),
				Arrays.asList(EBB4, GB4, CB5), C_FLAT, 1);
//
		check(TypedChordVoicing.ofBass(D5, Chords.DOMINANT_7),
				Arrays.asList(D5, FS5, A5, C6), D, 0);
		check(TypedChordVoicing.ofBass(D5, Chords.MAJOR_7),
				Arrays.asList(D5, FS5, A5, CS6), D, 0);
		check(TypedChordVoicing.ofBass(D5, Chords.MINOR_7),
				Arrays.asList(D5, F5, A5, C6), D, 0);
		check(TypedChordVoicing.ofBass(D5, Chords.DIMINISHED_7),
				Arrays.asList(D5, F5, AB5, CB6), D, 0);
		check(TypedChordVoicing.ofBass(F3, Chords.DOMINANT_7),
				Arrays.asList(F3, A3, C4, EB4), F, 0);
		System.out.println();
	}

	@Test
	public void byRoot() {
		System.out.println("Creating chords by root note:");
		check(TypedChordVoicing.ofRoot(C5, Chords.MAJOR_TRIAD),
				Arrays.asList(C5, E5, G5), C, 0);
		check(TypedChordVoicing.ofRoot(C5, Chords.MAJOR_TRIAD.invert(1)),
				Arrays.asList(E4, G4, C5), C, 1);
		check(TypedChordVoicing.ofRoot(C5, Chords.MAJOR_TRIAD.invert(2)),
				Arrays.asList(G4, C5, E5), C, 2);

		check(TypedChordVoicing.ofRoot(E4, Chords.MAJOR_TRIAD.invert(1)),
				Arrays.asList(GS3, B3, E4), E, 1);

		check(TypedChordVoicing.ofRoot(G4, Chords.MAJOR_TRIAD),
				Arrays.asList(G4, B4, D5), G, 0);
		check(TypedChordVoicing.ofRoot(G4, Chords.MAJOR_TRIAD.invert(2)),
				Arrays.asList(D4, G4, B4), G, 2);

		check(TypedChordVoicing.ofRoot(CB2, Chords.MINOR_TRIAD),
				Arrays.asList(CB2, EBB2, GB2), C_FLAT, 0);
		check(TypedChordVoicing.ofRoot(CB2, Chords.MINOR_TRIAD.invert(1)),
				Arrays.asList(EBB1, GB1, CB2), C_FLAT, 1);

		check(TypedChordVoicing.ofRoot(D3, Chords.DOMINANT_7),
				Arrays.asList(D3, FS3, A3, C4), D, 0);
		check(TypedChordVoicing.ofRoot(F3, Chords.DOMINANT_7),
				Arrays.asList(F3, A3, C4, EB4), F, 0);
		System.out.println();
	}

	// TODO: Failing, fix it. Needs redefining the absolute position of inversions.
	@Test
	public void inversions() {
		System.out.println("Checking inversions:");
		ChordVoicing chord = TypedChordVoicing.ofRoot(C4, Chords.MAJOR_TRIAD);
		check(chord.rootPosition(), Arrays.asList(C4, E4, G4), C, 0);
		check(chord.invert(0), Arrays.asList(C4, E4, G4), C, 0);
		check(chord.invert(1), Arrays.asList(E4, G4, C5), C, 1);
		check(chord.invert(2), Arrays.asList(G4, C5, E5), C, 2);

		chord = TypedChordVoicing.ofRoot(C4, Chords.MAJOR_TRIAD_64);
		check(chord.rootPosition(), Arrays.asList(C3, E3, G3), C, 0);
		check(chord.invert(0), Arrays.asList(C3, E3, G3), C, 0);
		check(chord.invert(1), Arrays.asList(E3, G3, C4), C, 1);
		check(chord.invert(2), Arrays.asList(G3, C4, E4), C, 2);

//		chord = TypedChordVoicing.ofBass(C, Chords.MAJOR_TRIAD);
//		check(chord.rootPosition(), Arrays.asList(C, E, G), C, 0);
//		check(chord.invert(0), Arrays.asList(C, E, G), C, 0);
//		check(chord.invert(1), Arrays.asList(E, G, C), C, 1);
//		check(chord.invert(2), Arrays.asList(G, C, E), C, 2);
//
//		chord = TypedChordVoicing.ofBass(E, Chords.MAJOR_TRIAD_6);
//		check(chord.rootPosition(), Arrays.asList(C, E, G), C, 0);
//		check(chord.invert(0), Arrays.asList(C, E, G), C, 0);
//		check(chord.invert(1), Arrays.asList(E, G, C), C, 1);
//		check(chord.invert(2), Arrays.asList(G, C, E), C, 2);
//
//		chord = TypedChordVoicing.ofBass(A, Chords.MAJOR_TRIAD);
//		check(chord.rootPosition(), Arrays.asList(A, C_SHARP, E), A, 0);
//		check(chord.invert(0), Arrays.asList(A, C_SHARP, E), A, 0);
//		check(chord.invert(1), Arrays.asList(C_SHARP, E, A), A, 1);
//		check(chord.invert(2), Arrays.asList(E, A, C_SHARP), A, 2);
//
//		chord = TypedChordVoicing.ofRoot(E, Chords.MAJOR_7);
//		check(chord.rootPosition(), Arrays.asList(E, G_SHARP, B, D_SHARP), E, 0);
//		check(chord.invert(0), Arrays.asList(E, G_SHARP, B, D_SHARP), E, 0);
//		check(chord.invert(1), Arrays.asList(G_SHARP, B, D_SHARP, E), E, 1);
//		check(chord.invert(2), Arrays.asList(B, D_SHARP, E, G_SHARP), E, 2);
		System.out.println();
	}

	/**
	 * @param ch the chord under test
	 * @param expected the expected notes in the chord, from bass to top
	 * @param root expected value of the root note
	 * @param inversion expected inversion number of the chord
	 */
	private void check(ChordVoicing ch, List<Pitch> expected,
	                   PitchClass root, int inversion) {
		System.out.format("%3s %-20s: %s%n", ch.root(), ch.type(), ch.pitches());

		assertEquals("Unexpected note structure: ", expected, ch.pitches());
		assertEquals("Unexpected root: ", root, ch.root());
		assertEquals("Unexpected bass: ", expected.get(0), ch.bass());
		assertEquals("Unexpected size: ", expected.size(), ch.size());
		assertEquals("Unexpected inversion: ", inversion, ch.inversion());

		// Check iterator
		List<Pitch> iterated = new ArrayList<>();
		for (Pitch p : ch) iterated.add(p);
		assertEquals("Bad iterator: ", iterated, expected);
	}
}
