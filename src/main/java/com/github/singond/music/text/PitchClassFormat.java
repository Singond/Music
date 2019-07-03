package com.github.singond.music.text;

import com.github.singond.music.PitchClass;

/**
 * An interface for classes which can transform a pitch class into a textual
 * representation.
 *
 * @author Singon
 */
public interface PitchClassFormat {

	/**
	 * Returns a textual representation of the given pitch class.
	 *
	 * @param pitchClass the pitch class to be converted to text
	 * @return a character sequence representing the value of {@code pitchClass}
	 */
	CharSequence format(PitchClass pitchClass);
}
