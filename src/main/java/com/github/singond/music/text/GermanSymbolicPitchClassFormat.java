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
