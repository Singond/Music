package com.github.singond.music.text;

import com.github.singond.music.Accidental;
import com.github.singond.music.PitchClass;

/**
 * Returns a string with the scientific notation of a given pitch class.
 * A pitch class format which uses symbols to represent sharps and flats.
 *
 * @author Singon
 */
class SymbolicPitchClassFormat implements PitchClassFormat {

	private final AccidentalSymbols accidentalSymbols;

	public SymbolicPitchClassFormat(AccidentalSymbols symbols) {
		this.accidentalSymbols = symbols;
	}

	@Override
	public CharSequence format(PitchClass pc) {
		StringBuilder sb = new StringBuilder(pc.basePitchClass().toString());
		sb.append(accidentalSymbol(pc.accidental()));
		return sb;
	}

	/**
	 * Returns the textual symbol of an accidental.
	 *
	 * @param acc the accidental
	 * @return a string representation of the accidental
	 */
	private CharSequence accidentalSymbol(Accidental acc) {
		int steps = acc.stepsAboveNatural();
		if (steps == 0) {
			return accidentalSymbols.getNatural();
		} else if (steps == -1) {
			return accidentalSymbols.getFlat();
		} else if (steps == 1) {
			return accidentalSymbols.getSharp();
		} else if (steps == -2) {
			return accidentalSymbols.getDoubleFlat();
		} else if (steps == 2) {
			return accidentalSymbols.getDoubleSharp();
		} else {
			String symb;
			if (steps < 0) {
				steps = -steps;
				symb = accidentalSymbols.getFlat();
			} else {
				symb = accidentalSymbols.getSharp();
			}
			assert steps > 0 : steps;
			StringBuilder value = new StringBuilder();
			for (int i = 0; i < steps; i++) {
				value.append(symb);
			}
			return value;
		}
	}
}
