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
	 * The list of all pitch classes in this key.
	 */
	private final List<PitchClass> pitchClasses;
	
	/**
	 * The list of mutual distances of the notes in ascending scale.
	 */
	private final List<Interval> intervals;
	
	/**
	 * Constructs a new key with the given tonic and subsequent steps
	 * created by adding the given intervals to the previously created note.
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
	public SimpleKey(final PitchClass tonic, List<Interval> intervals) {
		if (tonic == null) {
			throw new NullPointerException("The tonic is null");
		} else if (intervals == null) {
			throw new NullPointerException("The list of intervals is null");
		}
		
		// Construct the scale
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
		
		this.intervals = new ArrayList<>(intervals);
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
		return Pitches.range(pitchClasses(), start, end);
	}
}
