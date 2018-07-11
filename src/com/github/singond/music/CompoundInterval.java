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
public class CompoundInterval extends AbstractInterval implements Interval {

	private final SimpleInterval simple;
	private final int octaves;

	private static final int OCTAVE_DEGREES = 7;
	private static final int OCTAVE_SEMITONES = 12;

	private CompoundInterval(SimpleInterval simple, int octaves) {
		this.simple = simple;
		this.octaves = octaves;
	}

	@Override
	public int degrees() {
		return simple.degrees() + octaves * OCTAVE_DEGREES;
	}

	@Override
	public int semitones() {
		return simple.semitones() + octaves * OCTAVE_SEMITONES;
	}
}
