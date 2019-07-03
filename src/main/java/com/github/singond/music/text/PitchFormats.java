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
