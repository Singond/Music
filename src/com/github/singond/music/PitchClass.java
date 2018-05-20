package com.github.singond.music;

import static com.github.singond.music.Accidental.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * A set of all pitches which are a perfect octave apart.
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
	 * Returns the natural form of this pitch class,
	 * e.g. for "C#" returns "C".
	 * @return the natural of this pitch class
	 */
	public BasePitchClass naturalPitchClass() {
		return base;
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
	 * Returns the number of semitone steps of this pitch class above the
	 * reference pitch class, which is C.
	 *
	 * @return number of semitones above C
	 */
	public int stepsAboveReference() {
		return base.stepsAboveReference() + accidental.stepsAboveNatural();
	}

	/**
	 * Checks whether the given pitch class is the same number of semitones
	 * above the reference as this pitch class, disregarding any octave
	 * differences.
	 *
	 * @param other the pitch class to be compared with this one
	 * @return {@code true} if the difference of the semitones above
	 *         reference of the two pitch classes is a multiple of 12
	 */
	public boolean isEnharmonicWith(PitchClass other) {
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
		int newAccidental = Math.floorMod(delta, SEMITONES);
		if (newAccidental > SEMITONES/2) {
			newAccidental -= SEMITONES;
		}
		return of(newBase, Accidental.ofSteps(newAccidental));
	}

	@Override
	public String toString() {
		return base + accidental.symbolAscii();
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
	 * ordering of {@code PitchClass}.
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
	 */
	private static class StrictComparator implements Comparator<PitchClass> {
		
		@Override
		public int compare(PitchClass p1, PitchClass p2) {
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
	 */
	private static class EnharmonicComparator implements Comparator<PitchClass> {
		
		@Override
		public int compare(PitchClass p1, PitchClass p2) {
			return Integer.compare(p1.stepsAboveReference(),
			                       p2.stepsAboveReference());
		}
	}

	/**
	 * Returns the list of the basic pitch classes, that is all pitch
	 * classes composed of the natural C, D, E, F, G, A or B,
	 * where the accidental is one of double flat, flat, natural, sharp
	 * or double sharp.
	 *
	 * @return a new list on every invocation
	 */
	public static List<PitchClass> basicPitchClasses() {
		List<PitchClass> list = new ArrayList<>(35);
		list.addAll(naturals.values());
		list.addAll(flats.values());
		list.addAll(sharps.values());
		list.addAll(doubleFlats.values());
		list.addAll(doubleSharps.values());
		return list;
	}
}
