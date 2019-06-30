package com.github.singond.music.text;

import static com.github.singond.music.text.AccidentalSymbols.ASCII;
import static com.github.singond.music.text.AccidentalSymbols.UNICODE;
import static com.github.singond.music.text.SymbolicPitchClassFormat.ENGLISH_NOTES;
import static com.github.singond.music.text.SymbolicPitchClassFormat.ITALIAN_NOTES_SI;
import static com.github.singond.music.text.SymbolicPitchClassFormat.ITALIAN_NOTES_TI;

/**
 * A utility class for formatting pitch classes. Provides several commonly
 * used formats.
 *
 * @author Singon
 */
public class PitchClassFormats {

	/**
	 * A pitch class format with notes named {@code C, D, E, F, G, A, B}
	 * and accidentals in ASCII approximation.
	 *
	 * Examples include {@code D#} for D-sharp, {@code B} for B-natural
	 * and {@code Bb} for B-flat.
	 */
	public static final PitchClassFormat ENGLISH_ASCII
			= new SymbolicPitchClassFormat(ENGLISH_NOTES, ASCII);

	/**
	 * A pitch class format with notes named {@code C, D, E, F, G, A, B}
	 * and Unicode accidentals.
	 */
	public static final PitchClassFormat ENGLISH_UNICODE
			= new SymbolicPitchClassFormat(ENGLISH_NOTES, UNICODE);

	/**
	 * A pitch class format with notes named {@code C, D, E, F, G, A, H},
	 * using {@code Bb} for B flat and accidentals in ASCII approximation.
	 *
	 * Examples include {@code D#} for D-sharp, {@code H} for B-natural
	 * and {@code Bb} for B-flat.
	 */
	public static final PitchClassFormat GERMAN_ASCII
			= new GermanSymbolicPitchClassFormat(ASCII);

	/**
	 * A pitch class format with notes named {@code C, D, E, F, G, A, H},
	 * using {@code Bb} for B flat and Unicode accidentals.
	 */
	public static final PitchClassFormat GERMAN_UNICODE
			= new GermanSymbolicPitchClassFormat(UNICODE);

	/**
	 * A pitch class format with notes named {@code Do, Re, Mi, Fa, Sol, La, Si}
	 * and accidentals in ASCII approximation.
	 *
	 * Examples include {@code Re#} for D-sharp, {@code Si} for B-natural
	 * and {@code Sib} for B-flat.
	 */
	public static final PitchClassFormat ITALIAN_SI_ASCII
			= new SymbolicPitchClassFormat(ITALIAN_NOTES_SI, ASCII);

	/**
	 * A pitch class format with notes named {@code Do, Re, Mi, Fa, Sol, La, Si}
	 * and Unicode accidentals.
	 */
	public static final PitchClassFormat ITALIAN_SI_UNICODE
			= new SymbolicPitchClassFormat(ITALIAN_NOTES_SI, UNICODE);

	/**
	 * A pitch class format with notes named {@code Do, Re, Mi, Fa, Sol, La, Ti}
	 * and accidentals in ASCII approximation.
	 *
	 * Examples include {@code Re#} for D-sharp, {@code Ti} for B-natural
	 * and {@code Tib} for B-flat.
	 */
	public static final PitchClassFormat ITALIAN_TI_ASCII
			= new SymbolicPitchClassFormat(ITALIAN_NOTES_TI, ASCII);

	/**
	 * A pitch class format with notes named {@code Do, Re, Mi, Fa, Sol, La, Ti}
	 * and Unicode accidentals.
	 */
	public static final PitchClassFormat ITALIAN_TI_UNICODE
			= new SymbolicPitchClassFormat(ITALIAN_NOTES_TI, UNICODE);

	private PitchClassFormats() {
		throw new AssertionError("Non-instantiable class");
	}

}
