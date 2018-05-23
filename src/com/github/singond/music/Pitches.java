package com.github.singond.music;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * A utility class for working with instances of {@code Pitch}.
 *
 * @author Singon
 */
public final class Pitches {

	private Pitches() {
		throw new UnsupportedOperationException(
				"This class cannot be instantiated");
	}
	
	/**
	 * Given a set of pitch classes, generates a sequence of all pitches
	 * of those pitch classes, which lie between the specified endpoints.
	 * The endpoints are inclusive, ie. both may appear in the resulting
	 * sequence, if their respective pitch class is contained in the set.
	 * The returned sequence is sorted in the natural pitch ordering,
	 * that is from lowest to highest pitch.
	 *
	 * @param pitchClasses the set of allowed pitch classes
	 * @param start the starting point (inclusive, if contained in
	 *        {@code pitchClasses})
	 * @param end the end point (inclusive, if contained in
	 *        {@code pitchClasses})
	 * @return a list of pitches of {@code pitchClasses} between
	 *         {@code start} and {@code end}, sorted from lowest to highest
	 */
	static List<Pitch> range(Set<PitchClass> pitchClasses, Pitch start, Pitch end) {
		List<Pitch> result = new ArrayList<>();
		// The pattern of pitch classes to apply
		NavigableSet<PitchClass> pattern = new TreeSet<>(pitchClasses);
		
		// Find the position of the starting pitch class between the pattern
		// by adding it to the pattern, sorting it and finding the position.
//		final PitchClass startPitchClass = start.pitchClass();
//		NavigableSet<PitchClass> sorted = new TreeSet<>(pitchClasses);
//		sorted.add(startPitchClass);
		
		PitchClass current = start.pitchClass();
		Pitch currentPitch = start;
		if (!pattern.contains(current)) current = pattern.higher(current);
		while (currentPitch.compareTo(end) < 0) {
			currentPitch = Pitch.nearestAboveOrEnharmonic(current, currentPitch);
			if (currentPitch.compareTo(end) <= 0) {
				result.add(currentPitch);
			}
			if (current.equals(pattern.last())) {
				current = pattern.first();
			} else {
				current = pattern.higher(current);
			}
		}
		
		return result;
	}
}
