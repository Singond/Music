package com.github.singond.music;

import java.util.List;
import java.util.Set;

/**
 * Code snippets in the README.
 *
 * @author Singon
 */
public class Snippets {

	public static void main(String[] args) {
//		key();
		range();
		chord();
	}

	private static void key() {
		Key key = Keys.A_MAJOR;
		System.out.println(key);

		List<PitchClass> scale = key.degrees();
		System.out.println(scale);

		PitchClass tonic = key.tonic();
		System.out.println(tonic);

		PitchClass secondRaised = key.degree(Degree.II_RAISED);
		System.out.println(secondRaised);
	}

	private static void range() {
		Set<PitchClass> pcs = Keys.E_MAJOR.pitchClasses();
		List<Pitch> range = Pitches.allBetween(Pitch.A3, Pitch.D5, pcs);
		System.out.println(range);
	}

	// TODO Create and use public API
	// TODO And implement toString
	private static void chord() {
		Chord c = TypedChord.ofRoot(PitchClass.E_FLAT, SmallChordType.MAJOR_TRIAD);
		System.out.println(c);
	}
}
