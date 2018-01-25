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
}
