package com.github.singond.music;

import java.util.List;

/**
 * A skeletal implementation of the {@code Key} interface.
 *
 * @author Singon
 */
abstract class AbstractKey implements Key {

	@Override
	public List<Pitch> scale(Pitch start) {
		return scale(start, start.transposeUp(SimpleInterval.PERFECT_OCTAVE));
	}
}
