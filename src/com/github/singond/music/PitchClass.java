package com.github.singond.music;

import static com.github.singond.music.Accidental.*;
import static com.github.singond.music.BasePitchClass.*;

import java.util.EnumMap;
import java.util.Map;

/**
 * A set of all pitches which are a perfect octave apart.
 * <p>
 * Instances of this class are immutable.
 *
 * @author Singon
 */
public class PitchClass {

	private final BasePitchClass base;
	private final Accidental accidental;
	
	private static final int SEMITONES = 12;
	
	private static final Map<BasePitchClass, PitchClass> doubleFlats;
	private static final Map<BasePitchClass, PitchClass> flats;
	private static final Map<BasePitchClass, PitchClass> naturals;
	private static final Map<BasePitchClass, PitchClass> sharps;
	private static final Map<BasePitchClass, PitchClass> doubleSharps;
	static {
		doubleFlats = new EnumMap<>(BasePitchClass.class);
		doubleFlats.put(C, new PitchClass(C, DOUBLE_FLAT));
		doubleFlats.put(D, new PitchClass(D, DOUBLE_FLAT));
		doubleFlats.put(E, new PitchClass(E, DOUBLE_FLAT));
		doubleFlats.put(F, new PitchClass(F, DOUBLE_FLAT));
		doubleFlats.put(G, new PitchClass(G, DOUBLE_FLAT));
		doubleFlats.put(A, new PitchClass(A, DOUBLE_FLAT));
		doubleFlats.put(B, new PitchClass(B, DOUBLE_FLAT));
		
		flats = new EnumMap<>(BasePitchClass.class);
		flats.put(C, new PitchClass(C, FLAT));
		flats.put(D, new PitchClass(D, FLAT));
		flats.put(E, new PitchClass(E, FLAT));
		flats.put(F, new PitchClass(F, FLAT));
		flats.put(G, new PitchClass(G, FLAT));
		flats.put(A, new PitchClass(A, FLAT));
		flats.put(B, new PitchClass(B, FLAT));
		
		naturals = new EnumMap<>(BasePitchClass.class);
		naturals.put(C, new PitchClass(C, NATURAL));
		naturals.put(D, new PitchClass(D, NATURAL));
		naturals.put(E, new PitchClass(E, NATURAL));
		naturals.put(F, new PitchClass(F, NATURAL));
		naturals.put(G, new PitchClass(G, NATURAL));
		naturals.put(A, new PitchClass(A, NATURAL));
		naturals.put(B, new PitchClass(B, NATURAL));
		
		sharps = new EnumMap<>(BasePitchClass.class);
		sharps.put(C, new PitchClass(C, SHARP));
		sharps.put(D, new PitchClass(D, SHARP));
		sharps.put(E, new PitchClass(E, SHARP));
		sharps.put(F, new PitchClass(F, SHARP));
		sharps.put(G, new PitchClass(G, SHARP));
		sharps.put(A, new PitchClass(A, SHARP));
		sharps.put(B, new PitchClass(B, SHARP));
		
		doubleSharps = new EnumMap<>(BasePitchClass.class);
		doubleSharps.put(C, new PitchClass(C, DOUBLE_SHARP));
		doubleSharps.put(D, new PitchClass(D, DOUBLE_SHARP));
		doubleSharps.put(E, new PitchClass(E, DOUBLE_SHARP));
		doubleSharps.put(F, new PitchClass(F, DOUBLE_SHARP));
		doubleSharps.put(G, new PitchClass(G, DOUBLE_SHARP));
		doubleSharps.put(A, new PitchClass(A, DOUBLE_SHARP));
		doubleSharps.put(B, new PitchClass(B, DOUBLE_SHARP));
	}

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
	 * @return
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
		if (obj == null) {
			return false;
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
}
