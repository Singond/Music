package com.github.singond.music.text;

import com.github.singond.music.Pitch;

/**
 * Partial implementation of the most common form of Helmholtz pitch notation,
 * which uses a single letter (possibly with accidentals) to represent
 * the pitch class and a combination of letter case and either numbers
 * or repeated (sub)primes to represent octave.
 * Note that the variant wherein the lower octaves are represented by
 * repeating the pitch class (for example, {@code CC} for contra-C,
 * ie. {@code C1} in scientific pitch notation), is not supported.
 *
 * @author Singon
 */
abstract class BasicHelmholtzPitchFormat implements PitchFormat {

	private final PitchClassFormat pitchClassFmt;
	private final CharSequence prefixSeparator;
	private final CharSequence suffixSeparator;
	private final boolean prefixLower;

	/**
	 * Constructs a new instance of {@code BasicHelmholtzPitchFormat}.
	 *
	 * @param pitchClassFormat the format of pitch class to be used
	 * @param prefixSeparator separator between leading octave mark
	 *        and pitch class
	 * @param suffixSeparator separator between pitch class and trailing
	 *        octave mark
	 * @param leadingLowOctaveMark {@code true}, if the octave mark
	 *        should be placed <em>before</em> the pitch class for contra
	 *        octave and lower
	 */
	protected BasicHelmholtzPitchFormat(PitchClassFormat pitchClassFormat,
			CharSequence prefixSeparator, CharSequence suffixSeparator,
			boolean leadingLowOctaveMark) {
		this.pitchClassFmt = pitchClassFormat;
		this.prefixSeparator = prefixSeparator;
		this.suffixSeparator = suffixSeparator;
		this.prefixLower = leadingLowOctaveMark;
	}

	@Override
	public CharSequence format(Pitch pitch) {
		CharSequence pc = pitchClassFmt.format(pitch.pitchClass());
		// Check that the pitch class is formatted to non-empty string
		if (pc.length() <= 0) {
			throw new IllegalStateException(
					"The formatted pitch class is empty");
		}
		if (pitch.octave() < 3 && Character.isLowerCase(pc.charAt(0))) {
			StringBuilder sb = new StringBuilder(pc);
			sb.setCharAt(0, Character.toUpperCase(pc.charAt(0)));
			pc = sb;
		} else if (pitch.octave() > 2 && Character.isUpperCase(pc.charAt(0))) {
			StringBuilder sb = new StringBuilder(pc);
			sb.setCharAt(0, Character.toLowerCase(pc.charAt(0)));
			pc = sb;
		}
		int octave = pitch.octave();
		StringBuilder result = new StringBuilder();
		if (octave < 2) {
			if (prefixLower) {
				result.append(lowOctaveMark(2 - octave));
				result.append(prefixSeparator);
				result.append(pc);
			} else {
				result.append(pc);
				result.append(suffixSeparator);
				result.append(lowOctaveMark(2 - octave));
			}
		} else if (octave > 3) {
			result.append(pc);
			result.append(suffixSeparator);
			result.append(highOctaveMark(octave - 3));
		} else {
			result.append(pc);
		}
		return result;
	}

	protected abstract CharSequence lowOctaveMark(int number);
	protected abstract CharSequence highOctaveMark(int number);
}
