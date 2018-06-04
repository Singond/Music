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
}
