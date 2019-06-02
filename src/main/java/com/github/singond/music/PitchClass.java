package com.github.singond.music;

import static com.github.singond.music.Accidental.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * A set of all pitches which are a perfect octave apart.
 * <p>
 * This class has a natural ordering which orders the pitch classes first
 * in ascending order from C, and then orders any enharmonic pitches
 * in the order of their naturals. For example, D#4 is placed before Eb4.
 * This ordering is consistent with {@code equals}.
 * <p>
 * Instances of this class are immutable.
 *
 * @author Singon
 */
public final class PitchClass implements Comparable<PitchClass> {

	private final BasePitchClass base;
	private final Accidental accidental;

	private static final int SEMITONES = 12;

	public static final PitchClass
			C_DBL_FLAT, C_FLAT, C, C_SHARP, C_DBL_SHARP,
			D_DBL_FLAT, D_FLAT, D, D_SHARP, D_DBL_SHARP,
			E_DBL_FLAT, E_FLAT, E, E_SHARP, E_DBL_SHARP,
			F_DBL_FLAT, F_FLAT, F, F_SHARP, F_DBL_SHARP,
			G_DBL_FLAT, G_FLAT, G, G_SHARP, G_DBL_SHARP,
			A_DBL_FLAT, A_FLAT, A, A_SHARP, A_DBL_SHARP,
			B_DBL_FLAT, B_FLAT, B, B_SHARP, B_DBL_SHARP;
	static {
		C_DBL_FLAT  = new PitchClass(BasePitchClass.C, DOUBLE_FLAT);
		C_FLAT      = new PitchClass(BasePitchClass.C, FLAT);
		C           = new PitchClass(BasePitchClass.C, NATURAL);
		C_SHARP     = new PitchClass(BasePitchClass.C, SHARP);
		C_DBL_SHARP = new PitchClass(BasePitchClass.C, DOUBLE_SHARP);

		D_DBL_FLAT  = new PitchClass(BasePitchClass.D, DOUBLE_FLAT);
		D_FLAT      = new PitchClass(BasePitchClass.D, FLAT);
		D           = new PitchClass(BasePitchClass.D, NATURAL);
		D_SHARP     = new PitchClass(BasePitchClass.D, SHARP);
		D_DBL_SHARP = new PitchClass(BasePitchClass.D, DOUBLE_SHARP);

		E_DBL_FLAT  = new PitchClass(BasePitchClass.E, DOUBLE_FLAT);
		E_FLAT      = new PitchClass(BasePitchClass.E, FLAT);
		E           = new PitchClass(BasePitchClass.E, NATURAL);
		E_SHARP     = new PitchClass(BasePitchClass.E, SHARP);
		E_DBL_SHARP = new PitchClass(BasePitchClass.E, DOUBLE_SHARP);

		F_DBL_FLAT  = new PitchClass(BasePitchClass.F, DOUBLE_FLAT);
		F_FLAT      = new PitchClass(BasePitchClass.F, FLAT);
		F           = new PitchClass(BasePitchClass.F, NATURAL);
		F_SHARP     = new PitchClass(BasePitchClass.F, SHARP);
		F_DBL_SHARP = new PitchClass(BasePitchClass.F, DOUBLE_SHARP);

		G_DBL_FLAT  = new PitchClass(BasePitchClass.G, DOUBLE_FLAT);
		G_FLAT      = new PitchClass(BasePitchClass.G, FLAT);
		G           = new PitchClass(BasePitchClass.G, NATURAL);
		G_SHARP     = new PitchClass(BasePitchClass.G, SHARP);
		G_DBL_SHARP = new PitchClass(BasePitchClass.G, DOUBLE_SHARP);

		A_DBL_FLAT  = new PitchClass(BasePitchClass.A, DOUBLE_FLAT);
		A_FLAT      = new PitchClass(BasePitchClass.A, FLAT);
		A           = new PitchClass(BasePitchClass.A, NATURAL);
		A_SHARP     = new PitchClass(BasePitchClass.A, SHARP);
		A_DBL_SHARP = new PitchClass(BasePitchClass.A, DOUBLE_SHARP);

		B_DBL_FLAT  = new PitchClass(BasePitchClass.B, DOUBLE_FLAT);
		B_FLAT      = new PitchClass(BasePitchClass.B, FLAT);
		B           = new PitchClass(BasePitchClass.B, NATURAL);
		B_SHARP     = new PitchClass(BasePitchClass.B, SHARP);
		B_DBL_SHARP = new PitchClass(BasePitchClass.B, DOUBLE_SHARP);
	}

	/*
	 * Maps used to cache the most frequent pitch classes.
	 */
	private static final Map<BasePitchClass, PitchClass> doubleFlats;
	private static final Map<BasePitchClass, PitchClass> flats;
	private static final Map<BasePitchClass, PitchClass> naturals;
	private static final Map<BasePitchClass, PitchClass> sharps;
	private static final Map<BasePitchClass, PitchClass> doubleSharps;
	static {
		doubleFlats = new EnumMap<>(BasePitchClass.class);
		doubleFlats.put(BasePitchClass.C, C_DBL_FLAT);
		doubleFlats.put(BasePitchClass.D, D_DBL_FLAT);
		doubleFlats.put(BasePitchClass.E, E_DBL_FLAT);
		doubleFlats.put(BasePitchClass.F, F_DBL_FLAT);
		doubleFlats.put(BasePitchClass.G, G_DBL_FLAT);
		doubleFlats.put(BasePitchClass.A, A_DBL_FLAT);
		doubleFlats.put(BasePitchClass.B, B_DBL_FLAT);

		flats = new EnumMap<>(BasePitchClass.class);
		flats.put(BasePitchClass.C, C_FLAT);
		flats.put(BasePitchClass.D, D_FLAT);
		flats.put(BasePitchClass.E, E_FLAT);
		flats.put(BasePitchClass.F, F_FLAT);
		flats.put(BasePitchClass.G, G_FLAT);
		flats.put(BasePitchClass.A, A_FLAT);
		flats.put(BasePitchClass.B, B_FLAT);

		naturals = new EnumMap<>(BasePitchClass.class);
		naturals.put(BasePitchClass.C, C);
		naturals.put(BasePitchClass.D, D);
		naturals.put(BasePitchClass.E, E);
		naturals.put(BasePitchClass.F, F);
		naturals.put(BasePitchClass.G, G);
		naturals.put(BasePitchClass.A, A);
		naturals.put(BasePitchClass.B, B);

		sharps = new EnumMap<>(BasePitchClass.class);
		sharps.put(BasePitchClass.C, C_SHARP);
		sharps.put(BasePitchClass.D, D_SHARP);
		sharps.put(BasePitchClass.E, E_SHARP);
		sharps.put(BasePitchClass.F, F_SHARP);
		sharps.put(BasePitchClass.G, G_SHARP);
		sharps.put(BasePitchClass.A, A_SHARP);
		sharps.put(BasePitchClass.B, B_SHARP);

		doubleSharps = new EnumMap<>(BasePitchClass.class);
		doubleSharps.put(BasePitchClass.C, C_DBL_SHARP);
		doubleSharps.put(BasePitchClass.D, D_DBL_SHARP);
		doubleSharps.put(BasePitchClass.E, E_DBL_SHARP);
		doubleSharps.put(BasePitchClass.F, F_DBL_SHARP);
		doubleSharps.put(BasePitchClass.G, G_DBL_SHARP);
		doubleSharps.put(BasePitchClass.A, A_DBL_SHARP);
		doubleSharps.put(BasePitchClass.B, B_DBL_SHARP);
	}

	private static final Comparator<PitchClass> STRICT_COMPARATOR
			= new StrictComparator();

	private static final Comparator<PitchClass> ENHARMONIC_COMPARATOR
			= new EnharmonicComparator();

	private static final Pattern STRING_PATTERN
			= Pattern.compile("[CDEFGABH](b*|#*|x?)");

	private PitchClass(BasePitchClass base, Accidental accidental) {
		this.base = base;
		this.accidental = accidental;
	}

	/**
	 * Returns the pitch class of the given natural pitch class and accidental.
	 * This factory always returns a cached result for the pitches with
	 * accidentals ranging from double flat to double sharp (inclusive).
	 *
	 * @param base the natural pitch class (e.g. {@code C} for {@code C#})
	 * @param accidental the accidental relating the pitch class to the natural
	 * @return a {@code PitchClass} with the given {@code base}
	 *         and {@code accidental}
	 */
	public static PitchClass of(BasePitchClass base, Accidental accidental) {
		if (accidental.equals(DOUBLE_FLAT)) {
			return doubleFlats.get(base);
		} else if (accidental.equals(FLAT)) {
			return flats.get(base);
		} else if (accidental.equals(NATURAL)) {
			return naturals.get(base);
		} else if (accidental.equals(SHARP)) {
			return sharps.get(base);
		} else if (accidental.equals(DOUBLE_SHARP)) {
			return doubleSharps.get(base);
		} else {
			return new PitchClass(base, accidental);
		}
	}

	/**
	 * Returns the base of this pitch class,
	 * e.g. for pitch class "C#" returns the base pitch class "C".
	 *
	 * @return the base of this pitch class
	 */
	public BasePitchClass basePitchClass() {
		return base;
	}

	/**
	 * Returns the natural corresponding to this pitch class,
	 * e.g. for pitch class "C#" returns the pitch class "C".
	 * <p>
	 * If {@link #isNatural()} returns {@code true}, this method returns
	 * this pitch class.
	 *
	 * @return the natural of this pitch class, or itself (if natural already)
	 */
	public PitchClass natural() {
		if (isNatural()) {
			return this;
		} else {
			return of(base, Accidental.NATURAL);
		}
	}

	/**
	 * Returns the accidental of this pitch class,
	 * that is, its relation to the natural.
	 * @return the accidental
	 */
	public Accidental accidental() {
		return accidental;
	}

	/**
	 * Checks whether this pitch class has no accidental
	 * (or equivalently, whether its accidental is natural).
	 *
	 * @return {@code true} if the accidental is natural
	 */
	public boolean isNatural() {
		return accidental.equals(NATURAL);
	}

	/**
	 * Returns the number of semitone steps of this pitch class above the
	 * reference pitch class, which is C.
	 *
	 * @return number of semitones above C
	 */
	public int stepsAboveReference() {
		return base.stepsAboveReference() + accidental.stepsAboveNatural();
	}

	/**
	 * Returns the difference between the octave of this pitch class
	 * and the octave of its natural.
	 * For example, the relative octave of most common pitch classes is zero,
	 * but the relative octave of Cb (C flat) is -1, because the Cb sounds
	 * in the octave below the C.
	 * Similarly, the relative octave of Bx (B sharp) is +1.
	 *
	 * @return the octave number relative to natural
	 */
	public int relativeOctave() {
		return Util.floorDiv(stepsAboveReference(), SEMITONES);
	}

	/**
	 * Checks whether the given pitch class is the same number of semitones
	 * above the reference as this pitch class, disregarding any octave
	 * differences.
	 *
	 * @param other the pitch class to be compared with this one
	 * @return {@code true} if the difference of the semitones above
	 *         reference of the two pitch classes is a multiple of 12.
	 *         Returns {@code false} if the argument is {@code null}.
	 */
	public boolean isEnharmonicWith(PitchClass other) {
		if (other == null) return false;
		return (stepsAboveReference() - other.stepsAboveReference())
				% SEMITONES == 0;
	}

	public PitchClass transposeUp(Interval interval) {
		return transposeUp(interval.degrees(), interval.semitones());
	}

	public PitchClass transposeDown(Interval interval) {
		return transposeUp(-interval.degrees(), -interval.semitones());
	}

	private PitchClass transposeUp(int degrees, int semitones) {
		BasePitchClass newBase = base.advance(degrees);
		int steps = stepsAboveReference() + semitones;
		int delta = steps - newBase.stepsAboveReference();
		int newAccidental = Util.floorMod(delta, SEMITONES);
		if (newAccidental > SEMITONES/2) {
			newAccidental -= SEMITONES;
		}
		return of(newBase, Accidental.ofSteps(newAccidental));
	}

	@Override
	public String toString() {
		return base + accidental.symbolAscii();
	}

	public static PitchClass valueOf(String s) {
		if (s.length() < 1) {
			throw new FormatException("Expecting at least one character");
		}
		String pc = s.substring(0, 1);
		BasePitchClass base;
		try {
			base = BasePitchClass.valueOf(pc);
		} catch (IllegalArgumentException e) {
			throw new FormatException("Illegal pitch class format: " + pc);
		}
		Accidental acc;
		if (s.length() > 1) {
			acc = Accidental.valueOf(s.substring(1));
		} else {
			acc = Accidental.NATURAL;
		}
		return of(base, acc);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accidental == null) ? 0 : accidental.hashCode());
		result = prime * result + ((base == null) ? 0 : base.hashCode());
		return result;
	}

	/**
	 * Indicates whether a given object is equal to this object.
	 * Two pitch classes are considered equal if they are formed from the
	 * same natural pitch class and accidental.
	 * Enharmonic pitch classes are not considered to be equal, for example
	 * D# is <strong>not</strong> equal to Eb.
	 *
	 * @param obj {@inheritDoc}
	 * @return {@code true} if {@code obj} is also a {@code PitchClass} and
	 *         if both {@code this} and {code @obj} are formed from the same
	 *         base pitch class and accidental
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PitchClass)) {
			return false;
		}
		PitchClass other = (PitchClass) obj;
		if (accidental == null) {
			if (other.accidental != null) {
				return false;
			}
		} else if (!accidental.equals(other.accidental)) {
			return false;
		}
		if (base != other.base) {
			return false;
		}
		return true;
	}

	/**
	 * Compares another pitch class to this one.
	 * This method orders the pitch classes in ascending order from C,
	 * and then orders any enharmonic pitches in the order of their naturals.
	 * For example, D#4 is sorted before Eb4.
	 * This ordering is consistent with {@code equals}.
	 *
	 * @param o {@inheritDoc}
	 * @return  {@inheritDoc}
	 * @throws  NullPointerException {@inheritDoc}
	 */
	@Override
	public int compareTo(PitchClass o) {
		return STRICT_COMPARATOR.compare(this, o);
	}

	/**
	 * Returns a comparator which compares pitch classes based on both
	 * their height above the reference pitch class (C) and their naturals.
	 * The comparator first orders the pitches in ascending order from C
	 * and then orders any enharmonic pitches in the order of their naturals.
	 * <p>
	 * The ordering imposed by this comparator is the same as the natural
	 * ordering of {@code PitchClass} and is consistent with {@code equals}.
	 * It does not permit {@code null} arguments.
	 *
	 * @return a comparator taking into account both absolute height
	 *         and base note
	 */
	public static Comparator<PitchClass> strictComparator() {
		return STRICT_COMPARATOR;
	}

	/**
	 * Returns a comparator which compares pitch classes based on their
	 * height above the reference pith class (C) only, treating enharmonic
	 * pitch classes as equal.
	 * For example, comparing C# and Db will return {@code 0}.
	 * <p>
	 * The ordering imposed by this comparator is inconsistent
	 * with {@code equals}.
	 * It does not permit {@code null} arguments.
	 *
	 * @return a comparator taking into account the absolute height only
	 */
	public static Comparator<PitchClass> enharmonicComparator() {
		return ENHARMONIC_COMPARATOR;
	}

	/**
	 * A comparator which compares pitch classes based on both their height
	 * above the reference pitch class (C) and their naturals.
	 * This class first orders the pitches in ascending order from C
	 * and then orders any enharmonic pitches in the order of their naturals.
	 * <p>
	 * This comparator does not permit null arguments.
	 */
	private static class StrictComparator implements Comparator<PitchClass> {

		/**
		 * @throws NullPointerException if one of the arguments is null
		 */
		@Override
		public int compare(PitchClass p1, PitchClass p2) {
			if (p1 == null || p2 == null) {
				throw new NullPointerException
						("One of the pitch classes being compared is null");
			}
			int comparison = Integer.compare(p1.stepsAboveReference(),
			                                 p2.stepsAboveReference());
			if (comparison != 0) {
				return comparison;
			}
			return p1.base.compareTo(p2.base);
		}
	}

	/**
	 * A comparator which compares pitch classes based on their height
	 * above the reference pith class (C) only, treating enharmonic pitch
	 * classes as equal.
	 * For example, comparing C# and Db will return {@code 0}.
	 * <p>
	 * This comparator does not permit null arguments.
	 */
	private static class EnharmonicComparator implements Comparator<PitchClass> {

		/**
		 * @throws NullPointerException if one of the arguments is null
		 */
		@Override
		public int compare(PitchClass p1, PitchClass p2) {
			if (p1 == null || p2 == null) {
				throw new NullPointerException
						("One of the pitch classes being compared is null");
			}
			return Integer.compare(p1.stepsAboveReference(),
			                       p2.stepsAboveReference());
		}
	}

	/**
	 * Returns the list of the most common pitch classes, that is all pitch
	 * classes composed of the base pitch C, D, E, F, G, A or B;
	 * where the accidental is one of double flat, flat, natural, sharp
	 * or double sharp.
	 *
	 * @return a list of all common pitches
	 */
	public static List<PitchClass> commonPitchClasses() {
		List<PitchClass> list = new ArrayList<>(35);
		list.addAll(naturals.values());
		list.addAll(flats.values());
		list.addAll(sharps.values());
		list.addAll(doubleFlats.values());
		list.addAll(doubleSharps.values());
		return list;
	}
}
