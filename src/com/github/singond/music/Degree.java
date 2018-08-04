package com.github.singond.music;

import java.util.Arrays;
import java.util.List;

/**
 * An index of a note within some reference environment like a key.
 * The degree consists of two parts: the <em>base</em>, which is the
 * degree number, and a <em>shift</em>, which further modifies the pitch
 * given by <em>base</em>.
 * Shift of zero means no change in pitch, other values are the number
 * of semitones above the base (for positive values), or below the base
 * (for negative values).
 *
 * @author Singon
 */
public class Degree {

	/** The basic degree (the tonic). */
	public static final Degree I           = new Degree(1, 0);
	/** The lowered first degree. */
	public static final Degree I_LOWERED   = new Degree(1, -1);
	/** The raised first degree. */
	public static final Degree I_RAISED    = new Degree(1, 1);
	/** The second degree. */
	public static final Degree II          = new Degree(2, 0);
	/** The lowered second degree. */
	public static final Degree II_LOWERED  = new Degree(2, -1);
	/** The raised second degree. */
	public static final Degree II_RAISED   = new Degree(2, 1);
	/** The third degree. */
	public static final Degree III         = new Degree(3, 0);
	/** The lowered third degree. */
	public static final Degree III_LOWERED = new Degree(3, -1);
	/** The raised third degree. */
	public static final Degree III_RAISED  = new Degree(3, 1);
	/** The fourth degree (the subdominant). */
	public static final Degree IV          = new Degree(4, 0);
	/** The lowered fourth degree. */
	public static final Degree IV_LOWERED  = new Degree(4, -1);
	/** The raised fourth degree. */
	public static final Degree IV_RAISED   = new Degree(4, 1);
	/** The fifth degree (the dominant). */
	public static final Degree V           = new Degree(5, 0);
	/** The lowered fifth degree. */
	public static final Degree V_LOWERED   = new Degree(5, -1);
	/** The raised fifth degree. */
	public static final Degree V_RAISED    = new Degree(5, 1);
	/** The sixth degree. */
	public static final Degree VI          = new Degree(6, 0);
	/** The lowered sixth degree. */
	public static final Degree VI_LOWERED  = new Degree(6, -1);
	/** The raised sixth degree. */
	public static final Degree VI_RAISED   = new Degree(6, 1);
	/** The seventh degree. */
	public static final Degree VII         = new Degree(7, 0);
	/** The lowered seventh degree. */
	public static final Degree VII_LOWERED = new Degree(7, -1);
	/** The raised seventh degree. */
	public static final Degree VII_RAISED  = new Degree(7, 1);

	public static final List<Degree> basicDegrees;
	public static final List<Degree> loweredDegrees;
	public static final List<Degree> raisedDegrees;
	static {
		basicDegrees = Arrays.asList(I, II, III, IV, V, VI, VII);
		loweredDegrees = Arrays.asList(I_LOWERED, II_LOWERED, III_LOWERED,
				IV_LOWERED, V_LOWERED, VI_LOWERED, VII_LOWERED);
		raisedDegrees = Arrays.asList(I_RAISED, II_RAISED, III_RAISED,
				IV_RAISED, V_RAISED, VI_RAISED, VII_RAISED);
	}

	private static final List<String> romanNumerals = Arrays.asList(
			"I", "II", "III", "IV", "V", "VI", "VII");

	private final int base;
	private final int shift;

	private Degree(int base, int shift) {
		this.base = base;
		this.shift = shift;
	}

	public static Degree of(int base, int shift) {
		if (shift == -1)
			return loweredDegrees.get(base - 1);
		else if (shift == 0)
			return basicDegrees.get(base - 1);
		else if (shift == 1)
			return raisedDegrees.get(base - 1);
		else
			return new Degree(base, shift);
	}

	/**
	 * Returns the index of this degree.
	 *
	 * @return the index of this degree
	 */
	public int base() {
		return base;
	}

	/**
	 * Returns the modification of the basic degree in semitones up.
	 *
	 * @return number of semitones above the unmodified degree
	 */
	public int shift() {
		return shift;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + base;
		result = prime * result + shift;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Degree)) return false;
		Degree other = (Degree) obj;
		if (base != other.base) return false;
		if (shift != other.shift) return false;
		return true;
	}

	private String makeToString() {
		if (base > 0 && base <= 7 && shift >= -1 && shift <= 1) {
			StringBuilder sb = new StringBuilder();
			sb.append(romanNumerals.get(base - 1));
			if (shift == -1)
				sb.append("-");
			else if (shift == 1)
				sb.append("+");
			return sb.toString();
		} else {
			return base + " raised by " + shift;
		}
	}

	@Override
	public String toString() {
		return makeToString();
	}
}