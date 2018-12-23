package com.github.singond.music;

import java.util.Arrays;
import java.util.Collections;
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
		UNISON             = new SimpleInterval(0, 0,  "unison",             Quality.PERFECT);
		MINOR_SECOND       = new SimpleInterval(1, 1,  "minor second",       Quality.MINOR);
		MAJOR_SECOND       = new SimpleInterval(1, 2,  "major second",       Quality.MAJOR);
		MINOR_THIRD        = new SimpleInterval(2, 3,  "minor third",        Quality.MINOR);
		MAJOR_THIRD        = new SimpleInterval(2, 4,  "major third",        Quality.MAJOR);
		PERFECT_FOURTH     = new SimpleInterval(3, 5,  "perfect fourth",     Quality.PERFECT);
		PERFECT_FIFTH      = new SimpleInterval(4, 7,  "perfect fifth",      Quality.PERFECT);
		MINOR_SIXTH        = new SimpleInterval(5, 8,  "minor sixth",        Quality.MINOR);
		MAJOR_SIXTH        = new SimpleInterval(5, 9,  "major sixth",        Quality.MAJOR);
		MINOR_SEVENTH      = new SimpleInterval(6, 10, "minor seventh",      Quality.MINOR);
		MAJOR_SEVENTH      = new SimpleInterval(6, 11, "major seventh",      Quality.MAJOR);
		PERFECT_OCTAVE     = new SimpleInterval(7, 12, "perfect octave",     Quality.PERFECT);

		AUGMENTED_UNISON   = new SimpleInterval(0, 1,  "augmented unison",   Quality.AUGMENTED);
		DIMINISHED_SECOND  = new SimpleInterval(1, 0,  "diminished second",  Quality.DIMINISHED);
		AUGMENTED_SECOND   = new SimpleInterval(1, 3,  "augmented second",   Quality.AUGMENTED);
		DIMINISHED_THIRD   = new SimpleInterval(2, 2,  "diminished third",   Quality.DIMINISHED);
		AUGMENTED_THIRD    = new SimpleInterval(2, 5,  "augmented third",    Quality.AUGMENTED);
		DIMINISHED_FOURTH  = new SimpleInterval(3, 4,  "diminished fourth",  Quality.DIMINISHED);
		AUGMENTED_FOURTH   = new SimpleInterval(3, 6,  "augmented fourth",   Quality.AUGMENTED);
		DIMINISHED_FIFTH   = new SimpleInterval(4, 6,  "diminished fifth",   Quality.DIMINISHED);
		AUGMENTED_FIFTH    = new SimpleInterval(4, 8,  "augmented fifth",    Quality.AUGMENTED);
		DIMINISHED_SIXTH   = new SimpleInterval(5, 7,  "diminished sixth",   Quality.DIMINISHED);
		AUGMENTED_SIXTH    = new SimpleInterval(5, 10, "augmented sixth",    Quality.AUGMENTED);
		DIMINISHED_SEVENTH = new SimpleInterval(6, 9,  "diminished seventh", Quality.DIMINISHED);
		AUGMENTED_SEVENTH  = new SimpleInterval(6, 12, "augmented seventh",  Quality.AUGMENTED);
		DIMINISHED_OCTAVE  = new SimpleInterval(7, 11, "diminished octave",  Quality.DIMINISHED);
		AUGMENTED_OCTAVE   = new SimpleInterval(7, 13, "augmented octave",   Quality.AUGMENTED);
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
	private final Quality quality;
	private final String name;
	private final String symbol;

	/**
	 * @param degrees the number of degree steps spanned
	 * @param width the width in semitones
	 */
	private SimpleInterval(int degrees, int semitones,
	                       String name, Quality quality) {
		this.degrees = degrees;
		this.semitones = semitones;
		this.quality = quality;
		this.name = name;
		this.symbol = quality.symbol + (degrees + 1);
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

	/**
	 * Returns the quality of this interval (ie. minor, major etc.).
	 *
	 * @return the quality of this interval
	 */
	Quality quality() {
		return quality;
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

	public static List<SimpleInterval> values() {
		return Collections.unmodifiableList(values);
	}

	static final class Quality {

		public static final Quality MINOR, MAJOR, PERFECT,
				DIMINISHED, AUGMENTED;
		static {
			MINOR      = new Quality("minor",      "m");
			MAJOR      = new Quality("major",      "M");
			PERFECT    = new Quality("perfect",    "P");
			DIMINISHED = new Quality("diminished", "d");
			AUGMENTED  = new Quality("augmented",  "A");
		}

		private final String symbol;
		private final String name;

		private Quality(String name, String symbol) {
			this.name = name;
			this.symbol = symbol;
		}

		public String name() {
			return name;
		}

		public String symbol() {
			return symbol;
		}
	}
}
