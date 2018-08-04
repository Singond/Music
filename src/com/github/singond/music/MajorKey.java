package com.github.singond.music;

import java.util.Arrays;
import java.util.List;

/**
 * The common major key; that is a key with semitones between the 3rd and 4th
 * and the 7th and 1st degree.
 * This corresponds to the Ionian mode in modern Western mode system.
 *
 * @author Singon
 */
class MajorKey extends SimpleKey implements Key {

	/** The interval structure of this key (non-cumulative). */
	private static final List<Interval> INTERVALS = Arrays.<Interval>asList(
			SimpleInterval.MAJOR_SECOND,
			SimpleInterval.MAJOR_THIRD,
			SimpleInterval.PERFECT_FOURTH,
			SimpleInterval.PERFECT_FIFTH,
			SimpleInterval.MAJOR_SIXTH,
			SimpleInterval.MAJOR_SEVENTH);

	/** The major key type. */
	public static final MajorKeyType TYPE = new MajorKeyType();

	public static final MajorKey C_FLAT = new MajorKey(PitchClass.C_FLAT);
	public static final MajorKey G_FLAT = new MajorKey(PitchClass.G_FLAT);
	public static final MajorKey D_FLAT = new MajorKey(PitchClass.D_FLAT);
	public static final MajorKey A_FLAT = new MajorKey(PitchClass.A_FLAT);
	public static final MajorKey E_FLAT = new MajorKey(PitchClass.E_FLAT);
	public static final MajorKey B_FLAT = new MajorKey(PitchClass.B_FLAT);
	public static final MajorKey F = new MajorKey(PitchClass.F);
	public static final MajorKey C = new MajorKey(PitchClass.C);
	public static final MajorKey G = new MajorKey(PitchClass.G);
	public static final MajorKey D = new MajorKey(PitchClass.D);
	public static final MajorKey A = new MajorKey(PitchClass.A);
	public static final MajorKey E = new MajorKey(PitchClass.E);
	public static final MajorKey B = new MajorKey(PitchClass.B);
	public static final MajorKey F_SHARP = new MajorKey(PitchClass.F_SHARP);
	public static final MajorKey C_SHARP = new MajorKey(PitchClass.C_SHARP);

	private MajorKey(PitchClass tonic) {
		super(tonic, INTERVALS);
	}

	/**
	 * Returns a major key with the given tonic.
	 *
	 * @param tonic the tonic of the key
	 * @return the major key in {@code tonic}
	 */
	public static MajorKey in(PitchClass tonic) {
		// Cache frequently-used instances
		return new MajorKey(tonic);
	}

	@Override
	public KeyType type() {
		return TYPE;
	}

	@Override
	public String toString() {
		return tonic() + " major";
	}

	private static class MajorKeyType extends SimpleKeyType {

		MajorKeyType() {
			super(INTERVALS);
		}

		@Override
		public Key in(PitchClass tonic) {
			return MajorKey.in(tonic);
		}

	}
}
