package com.github.singond.music;

/**
 * An interval spanning more than one octave,
 * which can be decomposed into at least one perfect octave and a single
 * simple interval.
 * These component intervals add up to the width of this interval.
 * Instances of this class are immutable.
 *
 * @author Singon
 */
public final class CompoundInterval extends AbstractInterval implements Interval {

	private final SimpleInterval simple;
	private final int octaves;

	private static final int OCTAVE_DEGREES = 7;
	private static final int OCTAVE_SEMITONES = 12;

	private CompoundInterval(SimpleInterval simple, int octaves) {
		this.simple = simple;
		this.octaves = octaves;
	}

	public static final CompoundInterval of(SimpleInterval simple, int octaves) {
		// TODO Cache frequently used values (like up to 15th)?
		return new CompoundInterval(simple, octaves);
	}

	/**
	 * Returns a compound interval which is composed of the given number
	 * of diatonic degrees and has the given width in semitones.
	 * If none of the simple intervals matches the given criteria,
	 * this method returns null.
	 * Note that, unlike {@link #of(SimpleInterval, int)}, this method
	 * is not guaranteed to return a valid result.
	 * For example, calling {@code of(8, 1)} will fail with an exception,
	 * because an interval of 8 diatonic degrees and a width of 1 semitone
	 * cannot be decomposed into an octave part and a simple interval.
	 *
	 * @param degrees number of diatonic degrees spanned by the interval
	 * @param semitones absolute width of the interval in semitones
	 * @return an instance of {@code CompoundInterval} matching the given
	 *         number of degrees and semitones, or {@code null} if none
	 *         of the existing instances match
	 * @throw IllegalArgumentException if a suitable simple interval
	 *        cannot be found
	 */
	public static final CompoundInterval of(int degrees, int semitones) {
		if (degrees < 1) {
			throw new IllegalArgumentException
					("Number of degrees must be a positive mumber");
		}
		if (semitones < 1) {
			throw new IllegalArgumentException
					("Number of degrees must be a positive mumber");
		}
		final int octaves = degrees / OCTAVE_DEGREES;
		int simpleDegrees = degrees % OCTAVE_DEGREES;
		int simpleSemitones = semitones - octaves * OCTAVE_SEMITONES;
		SimpleInterval simple = SimpleInterval.valueOf
				(simpleDegrees, simpleSemitones);
		if (simple == null) {
			throw new IllegalArgumentException("Cannot find a simple interval "
					+ simpleDegrees + " degrees wide spanning "
					+ simpleSemitones + " semitones");
		}
		return CompoundInterval.of(simple, octaves);
	}

	@Override
	public int degrees() {
		return simple.degrees() + octaves * OCTAVE_DEGREES;
	}

	@Override
	public int semitones() {
		return simple.semitones() + octaves * OCTAVE_SEMITONES;
	}

	public String longName() {
		return simple.quality().name() + " "
				+ NamingUtil.ordinal(intervalNumber());
	}

	public String symbol() {
		return simple.quality().symbol() + intervalNumber();
	}

	@Override
	public String toString() {
		return symbol();
	}
}
