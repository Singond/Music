package com.github.singond.music;

import static com.github.singond.music.PitchClass.*;

import java.util.Arrays;
import java.util.List;

/**
 * The minor key (the Aeolian mode in modern Western mode system).
 *
 * @author Singon
 */
public class MinorKey extends SimpleKey implements Key {

	private static final List<Interval> INTERVALS = Arrays.asList(
			SimpleInterval.MAJOR_SECOND,
			SimpleInterval.MINOR_SECOND,
			SimpleInterval.MAJOR_SECOND,
			SimpleInterval.MAJOR_SECOND,
			SimpleInterval.MINOR_SECOND,
			SimpleInterval.MAJOR_SECOND);
	
	public static final MinorKey A_FLAT_MINOR = new MinorKey(A_FLAT);
	public static final MinorKey E_FLAT_MINOR = new MinorKey(E_FLAT);
	public static final MinorKey B_FLAT_MINOR = new MinorKey(B_FLAT);
	public static final MinorKey F_MINOR = new MinorKey(F);
	public static final MinorKey C_MINOR = new MinorKey(C);
	public static final MinorKey G_MINOR = new MinorKey(G);
	public static final MinorKey D_MINOR = new MinorKey(D);
	public static final MinorKey A_MINOR = new MinorKey(A);
	public static final MinorKey E_MINOR = new MinorKey(E);
	public static final MinorKey B_MINOR = new MinorKey(B);
	public static final MinorKey F_SHARP_MINOR = new MinorKey(F_SHARP);
	public static final MinorKey C_SHARP_MINOR = new MinorKey(C_SHARP);
	public static final MinorKey G_SHARP_MINOR = new MinorKey(G_SHARP);
	public static final MinorKey D_SHARP_MINOR = new MinorKey(D_SHARP);
	public static final MinorKey A_SHARP_MINOR = new MinorKey(A_SHARP);
	
	public MinorKey(PitchClass tonic) {
		super(tonic, INTERVALS);
	}

	@Override
	public String toString() {
		return tonic() + " minor";
	}
}
