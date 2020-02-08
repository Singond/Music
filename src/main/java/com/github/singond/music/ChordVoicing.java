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

import java.util.Iterator;
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
public interface ChordVoicing extends Iterable<Pitch> {

	/**
	 * Returns the notes of this chord sorted from the lowest to highest.
	 *
	 * @return the pitches forming this chord
	 */
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
	int size();

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
	 * Returns the chord type of this chord.
	 *
	 * @return the chord type
	 */
	ChordType type();

	/**
	 * Returns an iterator over the pitches in this voicing in ascending order
	 * starting from the bass note.
	 * The returned iterator does not allow modifications to this voicing.
	 * Attempts to modify it will result in an
	 * {@code UnsupportedOperationException}.
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	Iterator<Pitch> iterator();
}
