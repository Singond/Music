package com.github.singond.music;

import static com.github.singond.music.PitchClass.*;

import java.util.Arrays;
import java.util.List;

/**
 * The major key (the Ionian mode in modern Western mode system).
 *
 * @author Singon
 */
public class MajorKey extends SimpleKey implements Key {

	private static final List<Interval> INTERVALS = Arrays.<Interval>asList(
			SimpleInterval.MAJOR_SECOND,
			SimpleInterval.MAJOR_SECOND,
			SimpleInterval.MINOR_SECOND,
			SimpleInterval.MAJOR_SECOND,
			SimpleInterval.MAJOR_SECOND,
			SimpleInterval.MAJOR_SECOND);

	public static final MajorKey C_FLAT_MAJOR = new MajorKey(C_FLAT);
	public static final MajorKey G_FLAT_MAJOR = new MajorKey(G_FLAT);
	public static final MajorKey D_FLAT_MAJOR = new MajorKey(D_FLAT);
	public static final MajorKey A_FLAT_MAJOR = new MajorKey(A_FLAT);
	public static final MajorKey E_FLAT_MAJOR = new MajorKey(E_FLAT);
	public static final MajorKey B_FLAT_MAJOR = new MajorKey(B_FLAT);
	public static final MajorKey F_MAJOR = new MajorKey(F);
	public static final MajorKey C_MAJOR = new MajorKey(C);
	public static final MajorKey G_MAJOR = new MajorKey(G);
	public static final MajorKey D_MAJOR = new MajorKey(D);
	public static final MajorKey A_MAJOR = new MajorKey(A);
	public static final MajorKey E_MAJOR = new MajorKey(E);
	public static final MajorKey B_MAJOR = new MajorKey(B);
	public static final MajorKey F_SHARP_MAJOR = new MajorKey(F_SHARP);
	public static final MajorKey C_SHARP_MAJOR = new MajorKey(C_SHARP);

	public MajorKey(PitchClass tonic) {
		super(tonic, INTERVALS);
	}

	@Override
	public String toString() {
		return tonic() + " major";
	}
}
