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

/**
 * A {@code Chord} rendered into a group of pitches.
 * <p>
 * Throughout this class, the pitches and pitch classes comprising this chord
 * are also referred to as "notes".
 * The lowest of the pitches is called the <em>bass</em>.
 * In the general case, the bass does not coincide with the root.
 *
 * @author Singon
 */
public interface ChordVoicing extends PitchGroup {

	/**
	 * Returns the notes of this chord sorted from the lowest to highest.
	 *
	 * @return the pitches forming this chord
	 */
	@Override
	List<Pitch> pitches();

	/**
	 * Returns the root note of this chord.
	 *
	 * @return the root note
	 */
	PitchClass root();

	/**
	 * Returns the bass note of this chord.
	 * This is equivalent to calling {@code pitches().get(0)}.
	 *
	 * @return the bass note
	 */
	Pitch bass();

	/**
	 * Returns the number of notes in this chord.
	 *
	 * @return the number of notes
	 */
	@Override
	int size();

	/**
	 * Checks whether this chord has any non-zero inversions,
	 * ie. whether {{@link #invert} can be called without throwing
	 * {@code UnsupportedOperationException}.
	 *
	 * @return {@code true}, if calling {@link #invert} will not result
	 *         in {@code UnsupportedOperationException}
	 */
	boolean invertible();

	/**
	 * Returns the <em>n</em>-th inversion of this chord, where <em>n</em>
	 * is a number between 0 (inclusive) and the chord size (exclusive).
	 * The <em>n</em>-th inversion of a chord is such a rearrangement of
	 * its notes that the <em>n</em>-th note (counting from the root,
	 * which is assigned the index 0) is in the bass.
	 * <p>
	 * Apart from this requirement for the bass note, the exact positions
	 * of remaining notes are un-specified, and the implementations are
	 * free to choose the exact form of the inversion.
	 *
	 * @param n the inversion number <em>n</em> (see above)
	 * @return an inversion of this chord with the {@code n}-th note
	 *         in the bass
	 * @throws IndexOutOfBoundsException if {@code n} is not between
	 *         0 (inclusive) and {@code size()} (exclusive)
	 * @throws UnsupportedOperationException if this chord has no inversions
	 */
	ChordVoicing invert(int n);

	/**
	 * Returns the number of inversion of this chord.
	 * If the notes in the chord are indexed from 0 starting with the
	 * <em>root</em> in ascending order and wrapping down to base upon
	 * reaching the top note, the inversion number is the index
	 * of the <em>bass</em> note.
	 * <p>
	 * Equivalently, this is the argument to {@link #invert(int)} needed
	 * to produce this chord type.
	 *
	 * @return the index of the bass note
	 */
	int inversion();

	/**
	 * Returns the root position of this chord.
	 * The root position of a chord is the position in which the root
	 * note is equal to the bass note.
	 * This is equivalent to calling {@code invert(0)}.
	 *
	 * @return the root position of this chord
	 */
	ChordVoicing rootPosition();

	/**
	 * Returns the chord type of this chord.
	 *
	 * @return the chord type
	 */
	ChordType type();
}
