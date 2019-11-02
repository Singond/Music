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

import com.github.singond.music.PitchClass;

class GermanSymbolicPitchClassFormat extends SymbolicPitchClassFormat {

	private final String bflat;

	public GermanSymbolicPitchClassFormat(AccidentalSymbols accidentals) {
		super(SymbolicPitchClassFormat.GERMAN_NOTES, accidentals);
		this.bflat = "B" + accidentals.getFlat();
	}

	public GermanSymbolicPitchClassFormat() {
		this(AccidentalSymbols.UNICODE);
	}

	@Override
	public CharSequence format(PitchClass pc) {
		if (PitchClass.B_FLAT.equals(pc)) {
			return bflat;
		} else {
			return super.format(pc);
		}
	}

}
