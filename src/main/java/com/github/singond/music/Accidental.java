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

package com.github.singond.music;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * A modifier which changes the pitch of a note relative to its basic
 * (or "natural") pitch. This encompasses the notion of sharps and flats,
 * while allowing for more unusual accidentals like double or triple sharps.
 * <p>
 * Instances of this class are immutable.
 *
 * @author Singon
 */
public class Accidental implements Comparable<Accidental> {

	// Preset most widely-used values
	public static final Accidental DOUBLE_FLAT = new Accidental(-2);
	public static final Accidental FLAT = new Accidental(-1);
	public static final Accidental NATURAL = new Accidental(0);
	public static final Accidental SHARP = new Accidental(1);
	public static final Accidental DOUBLE_SHARP = new Accidental(2);

	private static final Pattern FLAT_PATTERN = Pattern.compile("b+");
	private static final Pattern SHARP_PATTERN = Pattern.compile("#+");

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
	 *
	 * @param shift number of semitones above natural pitch
	 * @return for the most commonly used values (double flat, flat,
	 *         natural, sharp and double sharp) returns a cached object,
	 *         otherwise this method may create a new object
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
	 * For example, a sharp ('#') returns {@code +1}, because a note like
	 * C sharp ("C#") is one step higher that its natural, C.
	 *
	 * @return the number of steps above natural
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
	 *
	 * @param steps
	 * @return
	 */
	private static String makeAsciiSymbol(int steps) {
		if (steps == 0) {
			return "";
		} else if (steps == 2) {
			return "x";
		} else {
			char symb;
			if (steps < 0) {
				steps = -steps;
				symb = 'b';
			} else {
				symb = '#';
			}
			assert steps > 0 : steps;
			char[] value = new char[steps];
			Arrays.fill(value, symb);
			return new String(value).intern();
		}
	}

	@Override
	public int hashCode() {
		return shift;
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 * In order for the other object to be equal to this one, it must be
	 * also an {@code Accidental} ant it must represent the same relative
	 * position to the respective natural pitch.
	 *
	 * @return {@code true}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
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

	public static Accidental valueOf(String s) {
		if (s == null) {
			throw new NullPointerException("The argument is null");
		} else if (s.equals("bb") || s.equals("double flat")) {
			return DOUBLE_FLAT;
		} else if (s.equals("b") || s.equals("flat")) {
			return FLAT;
		} else if (s.equals("") || s.equals("natural")) {
			return NATURAL;
		} else if (s.equals("#") || s.equals("sharp")) {
			return SHARP;
		} else if (s.equals("x") || s.equals("double sharp")) {
			return DOUBLE_SHARP;
		} else if (SHARP_PATTERN.matcher(s).matches()) {
			return ofSteps(s.length());
		} else if (FLAT_PATTERN.matcher(s).matches()) {
			return ofSteps(-s.length());
		} else {
			throw new FormatException("Illegal accidental format: " + s);
		}
	}

	/**
	 * Compares this object with another accidental for size.
	 * An accidental is "greater than" another accidental if it is "sharper";
	 * that is, when applied to a given note, a "greater" accidental
	 * produces a higher resulting pitch.
	 *
	 * @return a negative integer if this object is less than {@code o},
	 *         {@code 0} if they are the same, and a positive integer otherwise
	 */
	@Override
	public int compareTo(Accidental o) {
		return Integer.compare(this.shift, o.shift);
	}
}
