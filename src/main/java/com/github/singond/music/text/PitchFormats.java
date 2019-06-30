package com.github.singond.music.text;

public class PitchFormats {

	/**
	 * A format representing the scientific pitch notation.
	 */
	public static final PitchFormat SCIENTIFIC = new NumberingPitchFormat(
			PitchClassFormats.ENGLISH_UNICODE, "", 0);

	private PitchFormats() {
		throw new AssertionError("Non-instantiable class");
	}

}
