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

	/**
	 * A set of symbols to be used for representing sharps and flats.
	 */
	public static class AccidentalSymbols {

		public static final AccidentalSymbols ASCII = new AccidentalSymbols(
				"bb", "b", "", "#", "x");
		public static final AccidentalSymbols UNICODE = new AccidentalSymbols(
				"\uD834\uDD2B", "\u266D", "", "\u266F", "\uD834\uDD2A");

		private final String dblFlat;
		private final String flat;
		private final String natural;
		private final String sharp;
		private final String dblSharp;

		/**
		 * Constructs a new instance representing the given accidental symbols.
		 * This specifies the symbols for natural, flat, sharp, double flat
		 * and double sharp.
		 *
		 * @param dblFlat  the symbol for double flat pitch
		 * @param flat     the symbol for flat pitch
		 * @param natural  the symbol for natural pitch
		 * @param sharp    the symbol for sharp pitch
		 * @param dblSharp the symbol for double sharp pitch
		 */
		public AccidentalSymbols(String dblFlat, String flat,
				String natural, String sharp,String dblSharp) {
			this.dblFlat = dblFlat;
			this.flat = flat;
			this.natural = natural;
			this.sharp = sharp;
			this.dblSharp = dblSharp;
		}

		/**
		 * Returns the symbol for double flat pitch.
		 *
		 * @return the double flat symbol
		 */
		public String getDoubleFlat() {
			return dblFlat;
		}

		/**
		 * Returns the symbol for flat pitch.
		 *
		 * @return the flat symbol
		 */
		public String getFlat() {
			return flat;
		}

		/**
		 * Returns the symbol for natural pitch.
		 *
		 * @return the natural symbol
		 */
		public String getNatural() {
			return natural;
		}

		/**
		 * Returns the symbol for sharp pitch.
		 *
		 * @return the sharp symbol
		 */
		public String getSharp() {
			return sharp;
		}

		/**
		 * Returns the symbol for double sharp pitch.
		 *
		 * @return the double sharp symbol
		 */
		public String getDoubleSharp() {
			return dblSharp;
		}
	}
}
