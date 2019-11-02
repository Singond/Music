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

public class PitchFormats {

	/**
	 * A format representing the scientific pitch notation.
	 */
	public static final PitchFormat SCIENTIFIC = new NumberingPitchFormat(
			PitchClassFormats.ENGLISH_UNICODE, "", 0);

	public static final PitchFormat HELMHOLTZ_B_ASCII
			= new AsciiPrimeHelmholtzPitchFormat(
					PitchClassFormats.ENGLISH_ASCII, true);

	public static final PitchFormat HELMHOLTZ_B_UNICODE
			= new UnicodePrimeHelmholtzPitchFormat(
					PitchClassFormats.ENGLISH_UNICODE, true);

	public static final PitchFormat HELMHOLTZ_H_ASCII
			= new AsciiPrimeHelmholtzPitchFormat(
					PitchClassFormats.GERMAN_ASCII, true);

	public static final PitchFormat HELMHOLTZ_H_UNICODE
			= new UnicodePrimeHelmholtzPitchFormat(
					PitchClassFormats.GERMAN_UNICODE, true);

	private PitchFormats() {
		throw new AssertionError("Non-instantiable class");
	}

}
