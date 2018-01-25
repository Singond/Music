package com.github.singond.music;

public class PitchClass {

	private final BasePitchClass base;
	private final Accidental accidental;

	private PitchClass(BasePitchClass base, Accidental accidental) {
		this.base = base;
		this.accidental = accidental;
	}

	public static PitchClass of(BasePitchClass base, Accidental accidental) {
		return new PitchClass(base, accidental);
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
		return (stepsAboveReference() - other.stepsAboveReference()) % 12 == 0;
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
