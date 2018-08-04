package com.github.singond.music;

import java.util.Arrays;
import java.util.List;

/**
 * The common minor key; that is a key with semitones between the 2nd and 3rd
 * and the 5th and 6th degree.
 * This corresponds to the Aeolian mode in modern Western mode system.
 *
 * @author Singon
 */
class MinorKey extends SimpleKey implements Key {

	/** The interval structure of this key (non-cumulative). */
	private static final List<Interval> INTERVALS = Arrays.<Interval>asList(
			SimpleInterval.MAJOR_SECOND,
			SimpleInterval.MINOR_THIRD,
			SimpleInterval.PERFECT_FOURTH,
			SimpleInterval.PERFECT_FIFTH,
			SimpleInterval.MINOR_SIXTH,
			SimpleInterval.MINOR_SEVENTH);

	/** The minor key type. */
	public static final MinorKeyType TYPE = new MinorKeyType();

	public static final MinorKey A_FLAT  = new MinorKey(PitchClass.A_FLAT);
	public static final MinorKey E_FLAT  = new MinorKey(PitchClass.E_FLAT);
	public static final MinorKey B_FLAT  = new MinorKey(PitchClass.B_FLAT);
	public static final MinorKey F       = new MinorKey(PitchClass.F);
	public static final MinorKey C       = new MinorKey(PitchClass.C);
	public static final MinorKey G       = new MinorKey(PitchClass.G);
	public static final MinorKey D       = new MinorKey(PitchClass.D);
	public static final MinorKey A       = new MinorKey(PitchClass.A);
	public static final MinorKey E       = new MinorKey(PitchClass.E);
	public static final MinorKey B       = new MinorKey(PitchClass.B);
	public static final MinorKey F_SHARP = new MinorKey(PitchClass.F_SHARP);
	public static final MinorKey C_SHARP = new MinorKey(PitchClass.C_SHARP);
	public static final MinorKey G_SHARP = new MinorKey(PitchClass.G_SHARP);
	public static final MinorKey D_SHARP = new MinorKey(PitchClass.D_SHARP);
	public static final MinorKey A_SHARP = new MinorKey(PitchClass.A_SHARP);

	private MinorKey(PitchClass tonic) {
		super(tonic, INTERVALS);
	}

	/**
	 * Returns a minor key with the given tonic.
	 *
	 * @param tonic the tonic of the key
	 * @return the minor key in {@code tonic}
	 */
	public static MinorKey in(PitchClass tonic) {
		// Cache frequently-used instances
		return new MinorKey(tonic);
	}

	@Override
	public KeyType type() {
		return TYPE;
	}

	@Override
	public String toString() {
		return tonic() + " minor";
	}

	/**
	 * Minor key type.
	 */
	private static class MinorKeyType extends SimpleKeyType {

		MinorKeyType() {
			super(INTERVALS);
		}

		@Override
		public Key in(PitchClass tonic) {
			return MajorKey.in(tonic);
		}

	}
}
