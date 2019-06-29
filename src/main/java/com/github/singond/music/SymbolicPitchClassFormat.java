package com.github.singond.music;

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
			return accidentalSymbols.natural;
		} else if (steps == -1) {
			return accidentalSymbols.flat;
		} else if (steps == 1) {
			return accidentalSymbols.sharp;
		} else if (steps == -2) {
			return accidentalSymbols.dblFlat;
		} else if (steps == 2) {
			return accidentalSymbols.dblSharp;
		} else {
			String symb;
			if (steps < 0) {
				steps = -steps;
				symb = accidentalSymbols.flat;
			} else {
				symb = accidentalSymbols.sharp;
			}
			assert steps > 0 : steps;
			StringBuilder value = new StringBuilder();
			for (int i = 0; i < steps; i++) {
				value.append(symb);
			}
			return value;
		}
	}

	public enum AccidentalSymbols {
		ASCII("bb", "b", "", "#", "x"),
		UNICODE("\uD834\uDD2B", "\u266D", "", "\u266F", "\uD834\uDD2A");

		private String dblFlat;
		private String flat;
		private String natural;
		private String sharp;
		private String dblSharp;

		AccidentalSymbols(String dblFlat, String flat,
				String natural, String sharp,String dblSharp) {
			this.dblFlat = dblFlat;
			this.flat = flat;
			this.natural = natural;
			this.sharp = sharp;
			this.dblSharp = dblSharp;
		}
	}
}
