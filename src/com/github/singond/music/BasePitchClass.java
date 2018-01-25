package com.github.singond.music;

/**
 * The set of natural pitch classes (that is, pitch classes with simple
 * names not involving any accidental).
 * These are the pitches that correspond to the white keys on the piano.
 * This package uses the English-world convention, where the seventh
 * degree of the C major scale is called "B".
 *
 * @author Singon
 */
public enum BasePitchClass {
	C (0),
	D (2),
	E (4),
	F (5),
	G (7),
	A (9),
	B (11);
	
	/**
	 * The number of semitone steps above the reference tone
	 */
	private final int steps;
	
	/**
	 * @param steps the number of semitone steps above the reference tone
	 */
	private BasePitchClass(int steps) {
		this.steps = steps;
	}
	
	/**
	 * Returns the number of semitone steps above the reference pitch class
	 * (which is C).
	 * @return
	 */
	int stepsAboveReference() {
		return steps;
	}
	
	/**
	 * Returns the name of the pitch class in upper case, e.g. "C".
	 */
	public String toString() {
		return name();
	}
}
