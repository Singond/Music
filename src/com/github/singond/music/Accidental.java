package com.github.singond.music;

import java.util.Collections;

public class Accidental {

	// Preset most widely-used values
	public static final Accidental DOUBLE_FLAT = new Accidental(-2);
	public static final Accidental FLAT = new Accidental(-1);
	public static final Accidental NATURAL = new Accidental(0);
	public static final Accidental SHARP = new Accidental(1);
	public static final Accidental DOUBLE_SHARP = new Accidental(2);

	/**
	 * The number of semitones above the natural pitch.
	 */
	private final int shift;

	/** The symbol string */
	private final String symbolAscii;

	private Accidental(int shift) {
		this.shift = shift;
		this.symbolAscii = makeAsciiSymbol(shift);
	}

	/**
	 * Returns an accidental object which represents the given number
	 * of semitone steps above the natural pitch.
	 * @param shift number of semitones above natural pitch
	 * @return for the most commonly used values (double flat, flat,
	 *         natural, sharp and double sharp) returns a cached object,
	 *         otherwise may create a new object
	 */
	public static Accidental ofSteps(int shift) {
		switch (shift) {
			case -2:
				return DOUBLE_FLAT;
			case -1:
				return FLAT;
			case 0:
				return NATURAL;
			case 1:
				return SHARP;
			case 2:
				return DOUBLE_SHARP;
			default:
				return new Accidental(shift);
		}
	}

	/**
	 * Returns the number of steps above natural pitch that this accidental
	 * represents.
	 */
	public int stepsAboveNatural() {
		return shift;
	}

	/**
	 * Returns the ascii approximation of the common symbol for this
	 * accidental.
	 *
	 * @return "{@code bb}", "{@code b}", "", "{@code #}", "{@code x}",
	 *         for double flat, flat, natural, sharp and double sharp,
	 *         respectively
	 */
	public String symbolAscii() {
		return symbolAscii;
	}

	/**
	 * Returns the ascii approximation of the common symbol for the
	 * accidental given by its number of steps.
	 * The basic five are "bb", "b", "", "#", "x".
	 * @param steps
	 * @return
	 */
	private static String makeAsciiSymbol(int steps) {
		if (steps == 0) {
			return "";
		} else if (steps == 2) {
			return "x";
		} else {
			String symb;
			if (steps < 0) {
				steps = -steps;
				symb = "b";
			} else {
				symb = "#";
			}
			assert steps > 0 : steps;
			return String.join("", Collections.nCopies(steps, symb));
		}
	}

	@Override
	public int hashCode() {
		return shift;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Accidental)) {
			return false;
		}
		Accidental other = (Accidental) obj;
		if (shift != other.shift) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		switch (shift) {
			case -2:
				return "double flat";
			case -1:
				return "flat";
			case 0:
				return "natural";
			case 1:
				return "sharp";
			case 2:
				return "double sharp";
			default:
				return shift + " steps";
		}
	}
}
