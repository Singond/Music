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
 * An implementation of the {@code Chord} interface which corresponds directly
 * to a {@code ChordType} built on top of a given {@code PitchClass}.
 *
 * @author Singon
 */
class TypedChord extends AbstractTypedChord implements Chord {

	/**
	 * Creates a new {@code TypedChord} with the given attributes.
	 * This constructor directly stores the given references, meaning
	 * it must stay private in order to ensure immutability.
	 *
	 * @param root the root note
	 * @param notes the pitch classes of the chord in ascending order
	 * @param type the chord type
	 */
	private TypedChord(PitchClass root, List<PitchClass> notes,
	                   ChordType type) {
		super(root, notes, type);
	}

	/**
	 * Creates a chord of the given type with the given pitch class as its
	 * bass note.
	 *
	 * @param bass the bass note of the chord (as a {@code PitchClass})
	 * @param type the type of chord to be created
	 * @return a chord with the given {@code bass} and {@code type}
	 */
	public static final TypedChord ofBass(PitchClass bass, ChordType type) {
		if (bass == null) {
			throw new NullPointerException("The chord bass is null");
		} else if (type == null) {
			throw new NullPointerException("The chord type is null");
		}
		// TODO Return preset constant if available
		List<PitchClass> notes = fromBass(bass, type);
		PitchClass root = notes.get(type.rootIndex());
		return new TypedChord(root, notes, type);
	}

	/**
	 * Creates a chord of the given type with the given pitch class as its
	 * root note.
	 *
	 * @param root the root note of the chord (as a {@code PitchClass})
	 * @param type the type of chord to be created
	 * @return a chord with the given {@code root} and {@code type}
	 */
	public static final TypedChord ofRoot(PitchClass root, ChordType type) {
		if (root == null) {
			throw new NullPointerException("The chord root is null");
		} else if (type == null) {
			throw new NullPointerException("The chord type is null");
		}
		// TODO Return preset constant if available
		List<PitchClass> notes = fromRoot(root, type);
		return new TypedChord(root, notes, type);
	}
}
