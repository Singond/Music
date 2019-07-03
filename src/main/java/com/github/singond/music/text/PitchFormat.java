package com.github.singond.music.text;

import com.github.singond.music.Pitch;

/**
 * An interface for classes which can transform a pitch into a textual
 * representation.
 *
 * @author Singon
 */
public interface PitchFormat {

	/**
	 * Returns a textual representation of the given pitch.
	 *
	 * @param pitch the pitch to be converted to text
	 * @return a character sequence representing the value of {@code pitch}
	 */
	CharSequence format(Pitch pitch);
}
