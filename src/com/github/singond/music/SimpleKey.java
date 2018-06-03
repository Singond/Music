package com.github.singond.music;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
abstract class SimpleKey implements Key {
	
	/**
	 * The list of all pitch classes in this key.
	 */
	private final List<PitchClass> pitchClasses;
	
	/**
	 * The list of mutual distances of the notes in ascending scale.
	 */
	private final List<Interval> intervals;
	
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
		for (Interval interval : intervals) {
			degree = degree.transposeUp(interval);
			pitchClasses.add(degree);
		}
		
		this.intervals = new ArrayList<>(intervals);
	}

	@Override
	public PitchClass tonic() {
		return pitchClasses.get(0);
	}

	/**
	 * {@inheritDoc}
	 * The returned list is unmodifiable.
	 */
	@Override
	public List<PitchClass> scale() {
		return Collections.unmodifiableList(pitchClasses);
	}
}
