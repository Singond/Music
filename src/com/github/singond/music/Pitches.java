package com.github.singond.music;

import java.util.ArrayList;
import java.util.Collections;
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
	 * that is from lowest to highest pitch, if the start pitch is lower
	 * than the end pitch. If the start pitch is greater than end pitch,
	 * this order is reversed.
	 *
	 * @param start the starting point (inclusive, if contained in
	 *        {@code pitchClasses})
	 * @param end the end point (inclusive, if contained in
	 *        {@code pitchClasses})
	 * @param pitchClasses the set of allowed pitch classes
	 * @return a list of pitches of {@code pitchClasses} between
	 *         {@code start} and {@code end}, sorted in natural order
	 *         (or reverse thereof, if start pitch is above end)
	 */
	static List<Pitch> allBetween(Pitch start, Pitch end,
			Set<PitchClass> pitchClasses) {
		if (start.compareTo(end) < 0) {
			return ascending(pitchClasses, start, end);
		} else if (start.compareTo(end) > 0) {
			return descending(pitchClasses, start, end);
		} else {
			return Collections.singletonList(start);
		}
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
	 *        {@code pitchClasses}). Should be higher than {@code start}.
	 * @return a list of pitches of {@code pitchClasses} between
	 *         {@code start} and {@code end}, sorted from lowest to highest
	 */
	private static List<Pitch> ascending(Set<PitchClass> pitchClasses,
	                                     Pitch start, Pitch end) {
		List<Pitch> result = new ArrayList<>();
		// The pattern of pitch classes to apply
		NavigableSet<PitchClass> pattern = new TreeSet<>(pitchClasses);

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

	/**
	 * Given a set of pitch classes, generates a sequence of all pitches
	 * of those pitch classes, which lie between the specified endpoints.
	 * The endpoints are inclusive, ie. both may appear in the resulting
	 * sequence, if their respective pitch class is contained in the set.
	 * The returned sequence is sorted in the reverse of the natural pitch
	 * ordering, that is from highest to lowest pitch.
	 *
	 * @param pitchClasses the set of allowed pitch classes
	 * @param start the starting point (inclusive, if contained in
	 *        {@code pitchClasses})
	 * @param end the end point (inclusive, if contained in
	 *        {@code pitchClasses}). Should be lower than {@code start}.
	 * @return a list of pitches of {@code pitchClasses} between
	 *         {@code start} and {@code end}, sorted from lowest to highest
	 */
	private static List<Pitch> descending(Set<PitchClass> pitchClasses,
	                                      Pitch start, Pitch end) {
		List<Pitch> result = new ArrayList<>();
		// The pattern of pitch classes to apply
		NavigableSet<PitchClass> pattern = new TreeSet<>(pitchClasses);

		PitchClass current = start.pitchClass();
		Pitch currentPitch = start;
		if (!pattern.contains(current)) current = pattern.lower(current);
		while (currentPitch.compareTo(end) > 0) {
			currentPitch = Pitch.nearestBelowOrEnharmonic(current, currentPitch);
			if (currentPitch.compareTo(end) >= 0) {
				result.add(currentPitch);
			}
			if (current.equals(pattern.first())) {
				current = pattern.last();
			} else {
				current = pattern.lower(current);
			}
		}

		return result;
	}
}
