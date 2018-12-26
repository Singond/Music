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
//		range();
//		chord();
//		chordRoot();
		chordBass();
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

	private static void chord() {
		Chord c = Chords.chordAtRoot(PitchClass.E_FLAT, Chords.MAJOR_TRIAD);
		System.out.println(c);
	}

	private static void chordRoot() {
		// Root position:
		PitchClass root = PitchClass.E_FLAT;
		InvertibleChordType type = Chords.MAJOR_TRIAD;
		InvertibleChord chord = Chords.chordAtRoot(root, type);
		System.out.println(chord);

		// First inversion:
		chord = chord.invert(1);
		System.out.println(chord);

		// Alternatively, invert the chord type:
		type = type.invert(1);
		chord = Chords.chordAtRoot(root, type);
		System.out.println(chord);
	}

	private static void chordBass() {
		PitchClass bass = PitchClass.B_FLAT;
		ChordType type = Chords.MAJOR_TRIAD.invert(2);
		Chord chord = Chords.chordAtBass(bass, type);
		System.out.println(chord);
	}
}
