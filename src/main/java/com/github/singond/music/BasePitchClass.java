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

/**
 * The set of natural pitch classes (that is, pitch classes with simple
 * names not involving any accidental).
 * These are the pitches that correspond to the white keys on the piano.
 * These are referred to by their English names, namely
 * {@code C, D, E, F, G, A, B}.
 * <p>
 * The natural ordering of this enum sorts the constants in the ascending
 * order starting from {@code C}, that is {@code [C, D, E, F, G, A, B]}.
 *
 * @author Singon
 */
public enum BasePitchClass {
	/**
	 * The note "C", also called "Do" or "Ut".
	 */
	C (0),
	/**
	 * The note "D", also called "Re".
	 */
	D (2),
	/**
	 * The note "E", also called "Mi".
	 */
	E (4),
	/**
	 * The note "F", also called "Fa".
	 */
	F (5),
	/**
	 * The note "G", also called "Sol".
	 */
	G (7),
	/**
	 * The note "A", also called "La".
	 */
	A (9),
	/**
	 * The note "B", also called "H" (in German convention), "Si" or "Ti".
	 * This is the note a half-step below C.
	 */
	B (11);

	/**
	 * The number of semitone steps above the reference tone
	 */
	private final int steps;

	/**
	 * @param steps the number of semitone steps above the reference tone
	 */
	private BasePitchClass(int steps) {
		this.steps = steps;
	}

	/**
	 * Returns the number of diatonic degrees above the reference pitch class
	 * (which is C).
	 * @return
	 */
	int degreesAboveReference() {
		return ordinal();
	}

	/**
	 * Returns the number of semitone steps above the reference pitch class
	 * (which is C).
	 * @return
	 */
	int stepsAboveReference() {
		return steps;
	}

	/**
	 * Returns the base pitch class which is the given number of degrees
	 * ahead of this pitch class, wrapping around if necessary.
	 * Considering the available base pitch classes as a circular list
	 * of {@code [C, D, E, F, G, A, B]}, this method walks through the ring
	 * forward by the number of steps given in the argument.
	 * The list is circular, meaning that upon reaching the end (or start)
	 * of the list, the iteration resumes at the start (or end, respectively).
	 * For example, the element directly ahead of {@code B} is {@code C}.
	 *
	 * @param shift the number of degrees to go forward
	 * @return the pitch class {@code shift} degrees ahead of this
	 */
	public BasePitchClass advance(int shift) {
		int thisIndex = ordinal();
		int nextIndex = (((thisIndex + shift) % 7) + 7) % 7;
		return values()[nextIndex];
	}

	/**
	 * Returns the English name of the pitch class in upper case,
	 * for example "C".
	 */
	@Override
	public String toString() {
		return name();
	}
}
