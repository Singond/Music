package com.github.singond.music.text;

/**
 * A set of symbols to be used for representing sharps and flats.
 * This specifies the symbols for natural, flat, sharp, double flat
 * and double sharp pitch, which are used in constructing the text
 * representations of a given pitch (or pitch class).
 */
class AccidentalSymbols {

	/**
	 * An ASCII approximation of accidental symbols.
	 *
	 * This uses {@code b} ({@code U+0062}) for flats,
	 * {@code #} ({@code U+0023}) for sharps
	 * and {@code x} ({@code U+0078}) for double sharps.
	 * Natural pitch is unarked.
	 */
	public static final AccidentalSymbols ASCII = new AccidentalSymbols(
			"bb", "b", "", "#", "x");

	/**
	 * A set of accidental symbols represented by proper Unicode characters.
	 *
	 * This uses {@code U+266D} ({@code MUSIC FLAT SIGN}) for flat,
	 * {@code U+266F} ({@code MUSIC SHARP SIGN}) for sharp,
	 * {@code U+1D12B} ({@code MUSICAL SYMBOL DOUBLE FLAT}) for double flat
	 * and {@code U+1D12A} ({@code MUSICAL SYMBOL DOUBLE SHARP}) for double
	 * sharp. Natural pitch is unarked.
	 */
	public static final AccidentalSymbols UNICODE = new AccidentalSymbols(
			"\uD834\uDD2B", "\u266D", "", "\u266F", "\uD834\uDD2A");

	private final String dblFlat;
	private final String flat;
	private final String natural;
	private final String sharp;
	private final String dblSharp;

	/**
	 * Constructs a new instance representing the given accidental symbols.
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