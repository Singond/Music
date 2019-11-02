/*
 * Copyright 2019 Jan Slany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
