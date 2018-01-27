package com.github.singond.music;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class AbstractKey implements Key {
	
	/**
	 * The list of all pitch classes in this key.
	 */
	private final List<PitchClass> pitchClasses;
	
	/**
	 * The list of mutual distances of the notes in ascending scale.
	 */
	private final List<Interval> intervals;
	
	public AbstractKey(final PitchClass tonic, List<Interval> intervals) {
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
