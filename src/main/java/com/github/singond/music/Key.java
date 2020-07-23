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

public interface Key {

	/**
	 * Returns the tonic (the first degree) of the key.
	 *
	 * @return the tonic pitch class
	 */
	PitchClass tonic();

	/**
	 * Returns the set of all unique <em>pitch classes</em>
	 * comprising this key, without any particular order.
	 *
	 * @return the set of all pitch classes forming this key
	 */
	Set<PitchClass> pitchClasses();

	/**
	 * Returns all degrees in this key sorted in ascending order starting
	 * with the first degree.
	 * This is basically the list of pitch classes sorted to form a scale.
	 *
	 * @return an ascending scale of pitch classes in this key,
	 *         starting with the tonic
	 */
	List<PitchClass> degrees();

	/**
	 * Returns the given degree of this key, with degree 1 being the tonic.
	 * This is equal to calling {@code degrees().get(degree - 1)}.
	 *
	 * @param degree the one-based index of the degree
	 * @return the degree number {@code degree}
	 * @throws IndexOutOfBoundsException if {@code number} does not lie
	 *         between 1 and the number of degrees in the key (inclusive)
	 */
	PitchClass degree(int degree);

	/**
	 * Returns the given degree of this key.
	 * Unlike {@link #degree(int)}, this method supports raised and lowered
	 * degrees.
	 *
	 * @param degree the degree
	 * @return the degree number {@code degree}
	 */
	PitchClass degree(Degree degree);

	/**
	 * Generates a scale of this key between the given endpoints.
	 * The endpoints are both inclusive, provided they are part of ths scale.
	 *
	 * @param start the starting point (inclusive, if part of the key)
	 * @param end the end point (inclusive, if part of the key)
	 * @return a list of all pitches belonging to this key which are greater
	 *         than or equal to {@code start} and lower than or equal to
	 *         {@code end}
	 */
	List<Pitch> scale(Pitch start, Pitch end);

	/**
	 * Generates a scale of this key in the length of one octave,
	 * starting at the given pitch.
	 *
	 * @param start the starting point (inclusive, if part of the key)
	 * @return a list of all pitches belonging to this key which are greater
	 *         than or equal to {@code start} and lower than or equal to
	 *         {@code start + octave}
	 */
	List<Pitch> scale(Pitch start);

	/**
	 * Returns the type of this key (e.g. for G major returns an object
	 * representing any major key).
	 *
	 * @return the type of this key
	 */
	KeyType type();
}
