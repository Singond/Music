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
 * A group of notes with specified relative distances between them,
 * but without any absolute positioning, and with a basic note,
 * which is called the <em>root</em>.
 * The lowest of the pitches is called the <em>bass</em>.
 * In the general case, the bass does not coincide with the root.
 *
 * @author Singon
 */
// TODO Define equals()
public interface ChordType {

	/**
	 * Returns the interval structure of this chord.
	 * This is the sequence of intervals between adjacent notes of this
	 * chord, starting with the lowest pair of notes and moving up.
	 *
	 * @return the intervals between adjacent notes
	 */
	List<Interval> structure();

	/**
	 * Returns the zero-based index of the root note in this chord.
	 * If the notes of this chord were numbered in ascending order
	 * starting from zero, the returned value would be the index
	 * assigned to the root note.
	 * <p>
	 * Alternatively, this is the number of structural intervals
	 * one must sequentially add to the bass note in order to obtain
	 * the root note, provided the intervals are applied in the order
	 * given by {@link #structure()}.
	 * A value of {@code 0} means the chord is in root position.
	 *
	 * @return the index of the root note
	 */
	int rootIndex();

	/**
	 * Returns the number of inversion of this chord.
	 * If the notes in the chord are indexed from 0 starting with the
	 * <em>root</em> in ascending order and wrapping down to base upon
	 * reaching the top note, the inversion number is the index
	 * of the <em>bass</em> note.
	 *
	 * @return the index of the bass note
	 */
	int inversion();

	/**
	 * Returns the number of notes in this chord type.
	 * This is equivalent to calling {@code structure().size()}.
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
	 * Returns the relative height of a note above the bass of this chord.
	 * The note is given by its index in the ascending sequence of all
	 * notes of this chord, where the index 0 designates the bass.
	 *
	 * @param note the zero-based index of the note in this chord
	 * @return the interval between bass and {@code note}
	 * @throws IndexOutOfBoundsException if the argument does not lie
	 *         between 0 (inclusive) and {@code size()} (exclusive)
	 */
	Interval heightAboveBass(int note);
}