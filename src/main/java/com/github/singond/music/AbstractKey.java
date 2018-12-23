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

	@Override
	public PitchClass degree(int degree) {
		return degrees().get(degree - 1);
	}

	@Override
	public PitchClass degree(Degree degree) {
		PitchClass pc = degree(degree.base());
		int shift = degree.shift();
		if (shift > 0) {
			return pc.transposeUp(SimpleInterval.valueOf(0, shift));
		} else if (shift < 0) {
			return pc.transposeDown(SimpleInterval.valueOf(0, -shift));
		} else {
			return pc;
		}
	}
}
