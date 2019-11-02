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
 * A group of pitch classes with specified relative distances,
 * and with a basic note, which is called the <em>root</em>.
 * <p>
 * Throughout this class, the pitch classes comprising this chord
 * are also referred to as "notes".
 * The lowest of the notes is called the <em>bass</em>.
 * In the general case, the bass does not coincide with the root.
 *
 * @author Singon
 */
public interface Chord {

	/**
	 * Returns the interval structure of this chord.
	 * This is the sequence of intervals between adjacent notes of this
	 * chord, starting with the lowest pair of notes and moving up.
	 *
	 * @return the intervals between adjacent notes
	 */
	List<Interval> structure();

	/**
	 * Returns the notes of this chord sorted from the lowest to highest.
	 * Note that, in the general case, the pitch classes themselves may not
	 * be sufficient to correctly identify this chord, because there is no
	 * information about the octave spacing of the pitch classes.
	 *
	 * @return the pitch classes forming this chord
	 */
	List<PitchClass> notes();

	/**
	 * Returns the root note of this chord.
	 *
	 * @return the root note
	 */
	PitchClass root();

	/**
	 * Returns the bass note of this chord.
	 * This is equivalent to calling {@code notes().get(0)}.
	 *
	 * @return the bass note
	 */
	PitchClass bass();

	/**
	 * Returns the number of notes in this chord.
	 *
	 * @return the number of notes
	 */
	int size();

	/**
	 * Returns the interval between the lowest and the highest note of this
	 * chord. This is equal to the sum of all structural chords.
	 *
	 * @return the interval between the lowest and the highest note
	 */
	Interval span();

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
	Chord invert(int n);

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
	 * Returns the root position of this chord type.
	 * The root position of a chord is the position in which the root
	 * note is equal to the bass note.
	 * This is equivalent to calling {@code invert(0)}.
	 *
	 * @return the root position of this chord
	 */
	Chord rootPosition();

	/**
	 * Returns the chord type of this chord.
	 *
	 * @return the chord type
	 */
	ChordType type();
}
