package com.github.singond.music;

import java.util.Comparator;

/**
 * An exact pitch; that is a pitch class and an octave.
 * <p>
 * This class has a natural ordering which represents the ascending order
 * from lower pitches to higher pitches.
 * This ordering is consistent with {@code equals}.
 * <p>
 * Instances of this class are immutable.
 *
 * @author Singon
 */
public final class Pitch implements Comparable<Pitch> {

	/** The pitch class */
	private final PitchClass pitchClass;

	/**
	 * The octave number in scientific pitch notation.
	 * In scientific pitch notation, the octaves are numbered in ascending
	 * sequence, starting with 0 for the sub-contra octave and proceding up.
	 * In this notation, the octave starting at middle C has the number 4.
	 */
	private final int octave;

	/**
	 * The absolute pitch number, here defined as the number of semitones
	 * above C0 in scientific notation.
	 */
	private final transient int pitch;

	/** The number of semitones in an octave */
	private static final int SEMITONES = 12;

	private static final Comparator<Pitch> STRICT_COMPARATOR
	= new StrictComparator();

	private static final Comparator<Pitch> ENHARMONIC_COMPARATOR
			= new EnharmonicComparator();

	private Pitch(PitchClass pitchClass, int octave) {
		this.pitchClass = pitchClass;
		this.octave = octave;
		this.pitch = (octave * SEMITONES) + pitchClass.stepsAboveReference();
	}

	public static Pitch of(PitchClass pitchClass, int octave) {
		return new Pitch(pitchClass, octave);
	}

	public static Pitch of(BasePitchClass base, Accidental accidental,
	                       int octave) {
		return new Pitch(PitchClass.of(base, accidental), octave);
	}

	private static Pitch ofAbsolutePitch(PitchClass pitchClass,
	                                     int pitchNumber) {
		int difference = pitchNumber - pitchClass.stepsAboveReference();
		if (difference % SEMITONES != 0) {
			throw new IllegalArgumentException("The pitch class of "
					+ pitchNumber + " is not " + pitchClass + ", as desired");
		}
		int octave = difference / SEMITONES;	// No remainder
		return Pitch.of(pitchClass, octave);
	}

	/**
	 * Returns the lowest pitch of a given pitch class which is strictly
	 * higher than a specified pitch.
	 *
	 * @param target the target pitch class of the pitch to be returned
	 * @param lowerBound the pitch which must be exceeded by the
	 *        returned pitch
	 * @return the lowest higher pitch of {@code target} pitch class
	 */
	public static Pitch nearestAbove(PitchClass target, Pitch lowerBound) {
		int absDifference = target.stepsAboveReference()
				- lowerBound.pitchClass.stepsAboveReference();
		int modDifference = Util.floorMod(absDifference, SEMITONES);
		if (modDifference == 0) modDifference += SEMITONES;
		return Pitch.ofAbsolutePitch(target, lowerBound.pitch + modDifference);
	}

	/**
	 * Returns the lowest pitch of a given pitch class which is higher
	 * than or equal to a specified pitch.
	 *
	 * @param target the target pitch class of the pitch to be returned
	 * @param lowerBound the lowest allowable pitch to be returned
	 * @return the lowest higher or equal pitch of {@code target} pitch class
	 */
	public static Pitch nearestAboveOrEqual(PitchClass target, Pitch lowerBound) {
		if (lowerBound.pitchClass.equals(target)) {
			return lowerBound;
		} else {
			return nearestAbove(target, lowerBound);
		}
	}

	/**
	 * Returns the lowest pitch of a given pitch class which is higher
	 * than or enharmonic to a specified pitch.
	 *
	 * @param target the target pitch class of the pitch to be returned
	 * @param lowerBound the lowest allowable pitch to be returned
	 * @return the lowest higher or enharmonic pitch of {@code target} pitch class
	 */
	public static Pitch nearestAboveOrEnharmonic(PitchClass target, Pitch lowerBound) {
		if (lowerBound.pitchClass.isEnharmonicWith(target)) {
			return Pitch.ofAbsolutePitch(target, lowerBound.pitch);
		} else {
			return nearestAbove(target, lowerBound);
		}
	}

	/**
	 * Returns the highest pitch of a given pitch class which is strictly
	 * lower than a specified pitch.
	 *
	 * @param target the target pitch class of the pitch to be returned
	 * @param upperBound the pitch which must be higher than the
	 *        returned pitch
	 * @return the highest lower pitch of {@code target} pitch class
	 */
	public static Pitch nearestBelow(PitchClass target, Pitch upperBound) {
		int absDifference = upperBound.pitchClass.stepsAboveReference()
				- target.stepsAboveReference();
		int modDifference = Util.floorMod(absDifference, SEMITONES);
		if (modDifference == 0) modDifference += SEMITONES;
		return Pitch.ofAbsolutePitch(target, upperBound.pitch - modDifference);
	}

	/**
	 * Returns the highest pitch of a given pitch class which is lower
	 * than or equal to a specified pitch.
	 *
	 * @param target the target pitch class of the pitch to be returned
	 * @param upperBound the highest allowable pitch to be returned
	 * @return the highest lower or equal pitch of {@code target} pitch class
	 */
	public static Pitch nearestBelowOrEqual(PitchClass target, Pitch upperBound) {
		if (upperBound.pitchClass.equals(target)) {
			return upperBound;
		} else {
			return nearestBelow(target, upperBound);
		}
	}

	/**
	 * Returns the highest pitch of a given pitch class which is lower
	 * than or enharmonic to a specified pitch.
	 *
	 * @param target the target pitch class of the pitch to be returned
	 * @param upperBound the highest allowable pitch to be returned
	 * @return the highest lower or enharmonic pitch of {@code target} pitch class
	 */
	public static Pitch nearestBelowOrEnharmonic(PitchClass target, Pitch upperBound) {
		if (upperBound.pitchClass.isEnharmonicWith(target)) {
			return Pitch.ofAbsolutePitch(target, upperBound.pitch);
		} else {
			return nearestBelow(target, upperBound);
		}
	}

	/**
	 * Returns the pitch class of this pitch.
	 *
	 * @return the pitch class of this object
	 */
	public PitchClass pitchClass() {
		return pitchClass;
	}

	/**
	 * Returns the octave number of the natural of this pitch in scientific
	 * pitch notation. Note that this may not be the actual sounding octave,
	 * for example the pitch Bx3 (B double sharp in octave 3) returns 3,
	 * while the actual pitch is higher than C4.
	 * <p>
	 * In scientific pitch notation, the octaves are numbered in ascending
	 * order, starting with 0 for the sub-contra octave and proceeding up.
	 * The octave starting at middle C is assigned the number 4.
	 *
	 * @return the octave number
	 */
	public int octave() {
		return octave;
	}

	/**
	 * Returns the number of the octave (in scientific pitch notation)
	 * containing the absolute pitch of this pitch object.
	 * Exactly speaking, this method returns the octave of the highest
	 * C pitch which is lower than or equal to this pitch.
	 *
	 * @return the sounding octave number
	 */
//	int enharmonicOctave() {
//		return Math.floorDiv(pitch, SEMITONES);
//	}

	/**
	 * Returns the MIDI number of the pitch.
	 * The MIDI number of a pitch is the number of semitones above C-1
	 * in scientific notation. That is, C0 is assigned the MIDI number 12.
	 * @return a number representing this pitch in MIDI
	 */
	public int midiNumber() {
		return pitch + 12;
	}

	/**
	 * Checks whether the given pitch is the same number of semitones
	 * above the reference as this pitch.
	 *
	 * @param other the pitch to be compared with this one
	 * @return {@code true} if the difference of the semitones above
	 *         reference of the two pitches is 0
	 */
	public boolean isEnharmonicWith(Pitch other) {
		return this.pitch == other.pitch;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + octave;
		result = prime * result + ((pitchClass == null) ? 0 : pitchClass.hashCode());
		return result;
	}

	/**
	 * Indicates whether a given object is equal to this object.
	 * Two pitches are considered equal if and only if they are formed from
	 * the same base pitch class by the same accidental and have the same
	 * octave.
	 * Enharmonic pitches are <strong>not</strong> considered to be equal,
	 * for example D#4 is not equal to Eb4.
	 *
	 * @param obj {@inheritDoc}
	 * @return {@code true} if {@code obj} is also a {@code Pitch} and if
	 *         both {@code this} and {@code obj} have equal pitch class and
	 *         the same octave.
	 *         Pitch classes are only considered equal if they have equal
	 *         names; that is, enharmonic pitch classes are not necessarily
	 *         equal. For more information see {@link PitchClass#equals}.
	 * @see PitchClass#equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Pitch)) {
			return false;
		}
		Pitch other = (Pitch) obj;
		if (octave != other.octave) {
			return false;
		}
		if (pitchClass == null) {
			if (other.pitchClass != null) {
				return false;
			}
		} else if (!pitchClass.equals(other.pitchClass)) {
			return false;
		}
		return true;
	}

	/**
	 * Returns a pitch which is the specified amount above this pitch.
	 * The original object remains unchanged.
	 *
	 * @param interval the interval between this pitch and the (higher)
	 *        pitch to be returned
	 * @return the pitch resulting from transposing this pitch up by
	 *         {@code interval}
	 */
	public Pitch transposeUp(Interval interval) {
		PitchClass newPitchClass = pitchClass.transposeUp(interval);
		int targetPitch = pitch + interval.semitones();
		return ofAbsolutePitch(newPitchClass, targetPitch);
	}

	/**
	 * Returns a pitch which is the specified amount below this pitch.
	 * The original object remains unchanged.
	 *
	 * @param interval the interval between this pitch and the (lower)
	 *        pitch to be returned
	 * @return the pitch resulting from transposing this pitch down by
	 *         {@code interval}
	 */
	public Pitch transposeDown(Interval interval) {
		PitchClass newPitchClass = pitchClass.transposeDown(interval);
		int targetPitch = pitch - interval.semitones();
		return ofAbsolutePitch(newPitchClass, targetPitch);
	}

	@Override
	public String toString() {
		return pitchClass.toString() + octave;
	}

	/**
	 * Compares another pitch to this one.
	 * This method orders the pitches in ascending order (from lowest pitch
	 * to highest pitch), and further orders enharmonic pitches
	 * so that for example D#4 is sorted before Eb4.
	 * This ordering is consistent with {@code equals}.
	 *
	 * @param o {@inheritDoc}
	 * @return  {@inheritDoc}
	 * @throws  NullPointerException {@inheritDoc}
	 */
	@Override
	public int compareTo(Pitch o) {
		return STRICT_COMPARATOR.compare(this, o);
	}

	/**
	 * Returns a comparator which compares pitches based on both their
	 * absolute pitch and their pitch class.
	 * The comparator first orders the pitches in ascending order from lowest
	 * pitch to highest pitch, and then further orders enharmonic pitches
	 * so that for example D#4 is sorted before Eb4.
	 * <p>
	 * This ordering is equal to the natural ordering of {@code Pitch}
	 * and is consistent with {@code equals}.
	 * It does not permit {@code null} arguments.
	 *
	 * @return a {@code Comparator} which considers both absolute pitch
	 *         and pitch class
	 */
	public static Comparator<Pitch> strictComparator() {
		return STRICT_COMPARATOR;
	}

	/**
	 * Returns a comparator which compares pitches based on their absolute
	 * pitch only, treating enharmonic pitches as equal
	 * (that is, disregarding the pitch class completely).
	 * For example, comparing C#4 and Db4 will give the result {@code 0}.
	 * <p>
	 * This ordering is inconsistent with {@code equals}.
	 * It does not permit {@code null} arguments.
	 *
	 * @return a {@code Comparator} which considers the absolute pitch only
	 */
	public static Comparator<Pitch> enharmonicComparator() {
		return ENHARMONIC_COMPARATOR;
	}

	/**
	 * A comparator which compares pitches based on both their absolute
	 * pitch and their pitch class.
	 * This class first orders the pitches in ascending order from lowest
	 * pitch to highest pitch, and then further orders enharmonic pitches
	 * so that for example D#4 is sorted before Eb4.
	 * <p>
	 * This comparator does not permit null arguments.
	 */
	private static class StrictComparator implements Comparator<Pitch> {

		@Override
		public int compare(Pitch p1, Pitch p2) {
			if (p1 == null || p2 == null) {
				throw new NullPointerException
						("One of the pitches being compared is null");
			}

			int comparison = Integer.compare(p1.pitch, p2.pitch);
			if (comparison != 0) {
				return comparison;
			}
			/*
			 * The two pitches have equal value (the absolute pitch);
			 * so we proceed to comparing their base notes:
			 * We want e.g. C# to be ordered before Db,
			 * so we *reverse* the order of their respective accidentals.
			 */
			comparison = -p1.pitchClass.accidental()
			              .compareTo(p2.pitchClass.accidental());
			return comparison;
		}
	}

	/**
	 * A comparator which compares pitches based on their absolute
	 * pitch only, treating enharmonic pitches as equal
	 * (that is, disregarding the pitch class completely).
	 * For example, comparing C#4 and Db4 will give the result {@code 0}.
	 * <p>
	 * This comparator does not permit null arguments.
	 */
	private static class EnharmonicComparator implements Comparator<Pitch> {

		@Override
		public int compare(Pitch p1, Pitch p2) {
			if (p1 == null || p2 == null) {
				throw new NullPointerException
						("One of the pitches being compared is null");
			}
			return Integer.compare(p1.pitch, p2.pitch);
		}
	}
}
