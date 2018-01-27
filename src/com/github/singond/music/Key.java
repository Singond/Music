package com.github.singond.music;

import java.util.List;

public interface Key {

	/**
	 * Returns the tonic (the first degree) of the key.
	 *
	 * @return the tonic pitch class
	 */
	PitchClass tonic();
	
	/**
	 * Returns a list of <em>pitch classes</em> containing the scale
	 * of this key in ascending order.
	 *
	 * @return an ascending scale of pitch classes in this key,
	 *         starting with the tonic
	 */
	List<PitchClass> scale();
}
