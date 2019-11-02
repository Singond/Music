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

package com.github.singond.music.text;

import com.github.singond.music.Pitch;

/**
 * A format, wherein a pitch is represented by a pitch class symbol
 * suffixed with an octave number.
 *
 * @author Singon
 */
class NumberingPitchFormat implements PitchFormat {

	private final PitchClassFormat pitchClassFmt;
	private final CharSequence separator;
	private final int octaveOffset;

	/**
	 * Constructs a new {@code NumberingPitchFormat} instance whose behaviour
	 * follows the parameters given.
	 *
	 * @param pcFmt a pitch class format to be used
	 * @param separator separator between pitch class and octave number
	 * @param octaveOffset number to be added to the octave number in
	 *        scientific pitch notation. For example, to make middle C
	 *        (C4 in scientific notation) appear as C5, use the value of 1.
	 */
	public NumberingPitchFormat(PitchClassFormat pcFmt,
			CharSequence separator, int octaveOffset) {
		this.pitchClassFmt = pcFmt;
		this.separator = separator;
		this.octaveOffset = octaveOffset;
	}

	@Override
	public CharSequence format(Pitch pitch) {
		if (pitch == null) {
			throw new NullPointerException("The pitch is null");
		}
		StringBuilder result = new StringBuilder(
				pitchClassFmt.format(pitch.pitchClass()));
		result.append(separator);
		result.append(Integer.toString(pitch.octave() + octaveOffset));
		return result;
	}

}
