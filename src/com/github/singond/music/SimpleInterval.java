package com.github.singond.music;

import java.util.Arrays;
import java.util.List;

/**
 * The set of intervals spanning from one to eight degrees,
 * that is, from unison to octave.
 * The width of these intervals does not exceed 12 steps (a perfect octave),
 * with one exception: There is augmented octave, which spans 13 semitones.
 *
 * @author Singon
 */
public final class SimpleInterval extends AbstractInterval implements Interval {

	/** Diatonic interval */
	public static final SimpleInterval UNISON, MINOR_SECOND, MAJOR_SECOND,
			MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH,
			MINOR_SIXTH, MAJOR_SIXTH, MINOR_SEVENTH, MAJOR_SEVENTH,
			PERFECT_OCTAVE;
	/** Extended interval */
	public static final SimpleInterval AUGMENTED_UNISON,
			DIMINISHED_SECOND, AUGMENTED_SECOND,
			DIMINISHED_THIRD, AUGMENTED_THIRD,
			DIMINISHED_FOURTH, AUGMENTED_FOURTH,
			DIMINISHED_FIFTH, AUGMENTED_FIFTH,
			DIMINISHED_SIXTH, AUGMENTED_SIXTH,
			DIMINISHED_SEVENTH, AUGMENTED_SEVENTH,
			DIMINISHED_OCTAVE, AUGMENTED_OCTAVE;
	static {
		UNISON             = new SimpleInterval(0, 0,  "unison",             "P1");
		MINOR_SECOND       = new SimpleInterval(1, 1,  "minor second",       "m2");
		MAJOR_SECOND       = new SimpleInterval(1, 2,  "major second",       "M2");
		MINOR_THIRD        = new SimpleInterval(2, 3,  "minor third",        "m3");
		MAJOR_THIRD        = new SimpleInterval(2, 4,  "major third",        "M3");
		PERFECT_FOURTH     = new SimpleInterval(3, 5,  "perfect fourth",     "P4");
		PERFECT_FIFTH      = new SimpleInterval(4, 7,  "perfect fifth",      "P5");
		MINOR_SIXTH        = new SimpleInterval(5, 8,  "minor sixth",        "m6");
		MAJOR_SIXTH        = new SimpleInterval(5, 9,  "major sixth",        "M6");
		MINOR_SEVENTH      = new SimpleInterval(6, 10, "minor seventh",      "m7");
		MAJOR_SEVENTH      = new SimpleInterval(6, 11, "major seventh",      "M7");
		PERFECT_OCTAVE     = new SimpleInterval(7, 12, "perfect octave",     "P8");

		AUGMENTED_UNISON   = new SimpleInterval(0, 1,  "augmented unison",   "A1");
		DIMINISHED_SECOND  = new SimpleInterval(1, 0,  "diminished second",  "d2");
		AUGMENTED_SECOND   = new SimpleInterval(1, 3,  "augmented second",   "A2");
		DIMINISHED_THIRD   = new SimpleInterval(2, 2,  "diminished third",   "d3");
		AUGMENTED_THIRD    = new SimpleInterval(2, 5,  "augmented third",    "A3");
		DIMINISHED_FOURTH  = new SimpleInterval(3, 4,  "diminished fourth",  "d4");
		AUGMENTED_FOURTH   = new SimpleInterval(3, 6,  "augmented fourth",   "A4");
		DIMINISHED_FIFTH   = new SimpleInterval(4, 6,  "diminished fifth",   "d5");
		AUGMENTED_FIFTH    = new SimpleInterval(4, 8,  "augmented fifth",    "A5");
		DIMINISHED_SIXTH   = new SimpleInterval(5, 7,  "diminished sixth",   "d6");
		AUGMENTED_SIXTH    = new SimpleInterval(5, 10, "augmented sixth",    "A6");
		DIMINISHED_SEVENTH = new SimpleInterval(6, 9,  "diminished seventh", "d7");
		AUGMENTED_SEVENTH  = new SimpleInterval(6, 12, "augmented seventh",  "A7");
		DIMINISHED_OCTAVE  = new SimpleInterval(7, 11, "diminished octave",  "d8");
		AUGMENTED_OCTAVE   = new SimpleInterval(7, 13, "augmented octave",   "A8");
	}
	private static final List<SimpleInterval> values = Arrays.asList(
			UNISON, MINOR_SECOND, MAJOR_SECOND,
			MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH,
			MINOR_SIXTH, MAJOR_SIXTH, MINOR_SEVENTH, MAJOR_SEVENTH,
			PERFECT_OCTAVE,
			AUGMENTED_UNISON,
			DIMINISHED_SECOND, AUGMENTED_SECOND,
			DIMINISHED_THIRD, AUGMENTED_THIRD,
			DIMINISHED_FOURTH, AUGMENTED_FOURTH,
			DIMINISHED_FIFTH, AUGMENTED_FIFTH,
			DIMINISHED_SIXTH, AUGMENTED_SIXTH,
			DIMINISHED_SEVENTH, AUGMENTED_SEVENTH,
			DIMINISHED_OCTAVE, AUGMENTED_OCTAVE
	);

	private final int degrees;
	private final int semitones;
	private final String name;
	private final String symbol;

	/**
	 * @param degrees the number of degree steps spanned
	 * @param width the width in semitones
	 */
	private SimpleInterval(int degrees, int semitones,
	                       String name, String symbol) {
		this.degrees = degrees;
		this.semitones = semitones;
		this.name = name;
		this.symbol = symbol;
	}

	@Override
	public int degrees() {
		return degrees;
	}

	@Override
	public int semitones() {
		return semitones;
	}

	@Override
	public int intervalNumber() {
		return degrees + 1;
	}

	public String longName() {
		return name;
	}

	public String symbol() {
		return symbol;
	}

	@Override
	public boolean isEnharmonicWith(Interval other) {
		if (other == null) {
			return false;
		}
		return this.semitones == other.semitones();
	}

	@Override
	public String toString() {
		return symbol;
	}

	/**
	 * Returns the simple interval which is composed of the given number
	 * of diatonic degrees and has the given width in semitones.
	 * If none of the simple intervals matches the given criteria,
	 * this method returns null.
	 *
	 * @param degrees number of diatonic degrees spanned by the interval
	 * @param semitones absolute width of the interval in semitones
	 * @return an instance of {@code SimpleInterval} matching the given
	 *         number of degrees and semitones, or {@code null} if none
	 *         of the existing instances match
	 */
	public static final SimpleInterval valueOf(int degrees, int semitones) {
		// A short circuit for impossible numbers of diatonic degrees
		if (degrees < 0 || degrees > 7) {
			return null;
		}

		// Search the available constants
		for (SimpleInterval i : values) {
			if (i.degrees == degrees && i.semitones == semitones) {
				return i;
			}
		}
		return null;
	}
}
