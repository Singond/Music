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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A pitch class format employing the Helmholtz pitch notation, using Unicode
 * characters where possible. Note that this format has certain limitations
 * arising from Unicode not providing all necessary characters, namely:
 * <ul>
 * <li>Only one to four upper primes are supported properly as single character.
 *     Higher octaves are marked by repeating the single prime.
 * <li>The sub-primes are represented by Unicode {@code U+0375}
 *     (GREEK LOWER NUMERAL SIGN), which looks bad in some fonts, because the
 *     marks are placed below the letter, rather than before/after it
 *     and multiple marks may appear as one due to overlapping.
 * </ul>
 * If either of these limitations is unacceptable, it is recommended to use
 * the ASCII approximation of Helmholtz pitch notation provided by
 * {@code AsciiPrimeHelmholtzPitchFormat}.
 *
 * @author Singon
 */
class UnicodePrimeHelmholtzPitchFormat extends BasicHelmholtzPitchFormat {

	// Wikipedia uses GREEK LOWER NUMERAL SIGN (U+0375) for the sub-prime
	private static final CharSequence lowOctaveMark = "\u0375";
	private static final CharSequence highOctaveMark = "\u2032";
	private static final Map<Integer, CharSequence> octaveMarks;
	static {
		Map<Integer, CharSequence> marks = new HashMap<>();
		marks.put(2, "\u2033");
		marks.put(3, "\u2034");
		marks.put(4, "\u2057");
		octaveMarks = Collections.unmodifiableMap(marks);
	}

	public UnicodePrimeHelmholtzPitchFormat(PitchClassFormat pcFmt,
			boolean prefix) {
		super(pcFmt, "", "", prefix);
	}

	@Override
	protected CharSequence lowOctaveMark(int number) {
		if (octaveMarks.containsKey(-number)) {
			return octaveMarks.get(-number);
		} else {
			return TextUtil.repeat(lowOctaveMark, number);
		}
	}

	@Override
	protected CharSequence highOctaveMark(int number) {
		if (octaveMarks.containsKey(number)) {
			return octaveMarks.get(number);
		} else {
			return TextUtil.repeat(highOctaveMark, number);
		}
	}

}
