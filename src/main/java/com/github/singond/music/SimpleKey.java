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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A skeletal implementation of a simple type of musical key,
 * in which the pitches repeat with a period of one octave.
 * This generalizes the concept of the most common keys like major key
 * or minor keys into any key, in which the set of pitches belonging
 * to that key are the same in each octave
 * (for example, if a key contains the pitch C4, then <em>all</em>
 * other C pitches like C0, C1... etc. also belong to that key).
 *
 * @author Singon
 */
abstract class SimpleKey extends AbstractKey implements Key {

	/**
	 * All pitch classes in this key, sorted in order they appear in the
	 * scale of this key.
	 */
	private final List<PitchClass> pitchClasses;

	/**
	 * Creates a new key from the given pitch classes.
	 * The pitch classes must be sorted into the order they appear
	 * in the scale of this key.
	 *
	 * @param pitchClasses pitch classes from the key's scale
	 */
	private SimpleKey(List<PitchClass> pitchClasses) {
		this.pitchClasses = new ArrayList<>(pitchClasses);
	}

	/**
	 * Constructs a new key with the given tonic and with subsequent notes
	 * created by sorting the given intervals from smallest to largest
	 * and adding them one by one to the tonic.
	 *
	 * @param tonic the pitch class to start at. This will become the tonic
	 *        of the key.
	 * @param intervals the intervals of the notes in this key above the tonic.
	 *        The tonic should not be represented in this list
	 *        (ie. there should be no unison in this list)
	 * @return list of pitch classes created by adding each of
	 *         sorted {@code intervals} to {@code tonic}
	 * @throws NullPointerException if either {@code tonic} or
	 *         {@code intervals} is null
	 * @throws IllegalArgumentException if one of the given
	 *         intervals is equal to or greater than one octave
	 */
	protected SimpleKey(PitchClass tonic, List<Interval> intervals) {
		this(byAbsoluteIntervals(tonic, intervals));
	}

	/**
	 * Returns a list of pitch classes with the given start pitch and with
	 * subsequent steps created by adding the given intervals one by one
	 * to the previously created note.
	 *
	 * @param tonic the pitch class to start at. This will become the tonic
	 *        of the key.
	 * @param intervals the intervals to be applied in that order in order
	 *        to build the full scale of the key
	 * @throws NullPointerException if either {@code tonic} or
	 *         {@code intervals} is null
	 * @throws IllegalArgumentException if the total width of given
	 *         intervals is equal to or greater than one octave
	 */
	@SuppressWarnings("unused")
	private static final List<PitchClass> byCumulativeIntervals
			(final PitchClass tonic, List<Interval> intervals) {
		if (tonic == null) {
			throw new NullPointerException("The tonic is null");
		} else if (intervals == null) {
			throw new NullPointerException("The list of intervals is null");
		}

		// Construct the scale
		List<PitchClass> pitchClasses;
		pitchClasses = new ArrayList<>(1 + intervals.size());
		pitchClasses.add(tonic);
		PitchClass degree = tonic;
		int totalInterval = 0;
		for (Interval interval : intervals) {
			totalInterval += interval.semitones();
			degree = degree.transposeUp(interval);
			pitchClasses.add(degree);
		}
		if (totalInterval >=12) {
			throw new IllegalArgumentException("The given intervals do not fit in one octave");
		}

		return pitchClasses;
	}

	/**
	 * Returns a list of pitch classes with the given start pitch and with
	 * subsequent steps created by sorting the given intervals from smallest
	 * to largest and adding them one by one to the start pitch.
	 *
	 * @param tonic the pitch class to start at. This will become the first
	 *        element in the returned list.
	 * @param intervals the intervals of the notes in this key above the tonic.
	 *        The tonic should not be represented in this list
	 *        (ie. there should be no unison)
	 * @return list of pitch classes created by adding each of
	 *         sorted {@code intervals} to {@code tonic}
	 * @throws NullPointerException if either {@code tonic} or
	 *         {@code intervals} is null
	 * @throws IllegalArgumentException if one of the given
	 *         intervals is equal to or greater than one octave
	 */
	private static final List<PitchClass> byAbsoluteIntervals
			(final PitchClass tonic, List<Interval> intervals) {
		if (tonic == null) {
			throw new NullPointerException("The tonic is null");
		} else if (intervals == null) {
			throw new NullPointerException("The list of intervals is null");
		}

		// Construct the scale
		Collections.sort(intervals);
		List<PitchClass> pitchClasses;
		pitchClasses = new ArrayList<>(1 + intervals.size());
		pitchClasses.add(tonic);
		for (Interval interval : intervals) {
			if (interval.compareTo(SimpleInterval.PERFECT_OCTAVE) >= 0) {
				throw new IllegalArgumentException(
						"The given intervals is larger than a perfect octave: " + interval);
			}
			pitchClasses.add(tonic.transposeUp(interval));
		}

		return pitchClasses;
	}

	@Override
	public PitchClass tonic() {
		return pitchClasses.get(0);
	}

	/**
	 * {@inheritDoc}
	 * @return a new set on every invocation
	 */
	@Override
	public Set<PitchClass> pitchClasses() {
		Set<PitchClass> result = new HashSet<>();
		result.addAll(pitchClasses);
		return result;
	}

	/**
	 * {@inheritDoc}
	 * The returned list is unmodifiable.
	 */
	@Override
	public List<PitchClass> degrees() {
		return Collections.unmodifiableList(pitchClasses);
	}

	@Override
	public List<Pitch> scale(Pitch start, Pitch end) {
		return Pitches.allBetween(start, end, pitchClasses());
	}
}
