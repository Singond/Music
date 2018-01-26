package com.github.singond.music;

/**
 * An exact pitch; that is a pitch class and an octave.
 * <p>
 * This class has a natural ordering which represents the ascending order
 * from lower pitches to higher pitches.
 * This ordering is <em>inconsistent with</em> {@code equals}, because
 * it treats enharmonic pitches as being equal, unlike the {@code equals}
 * method.
 *
 * @author Singon
 */
public class Pitch implements Comparable<Pitch> {

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
	 * Note that in MIDI, C0 is assigned the number 12.
	 */
	transient private final int pitch;
	
	private Pitch(PitchClass pitchClass, int octave) {
		this.pitchClass = pitchClass;
		this.octave = octave;
		this.pitch = (octave * 12) + pitchClass.stepsAboveReference();
	}
	
	public static Pitch of(PitchClass pitchClass, int octave) {
		return new Pitch(pitchClass, octave);
	}
	
	public static Pitch of(BasePitchClass base, Accidental accidental,
	                       int octave) {
		return new Pitch(PitchClass.of(base, accidental), octave);
	}
	
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

	/**
	 * Compares another pitch to this one.
	 * This method orders the pitches in ascending order (from lowest pitch
	 * to highest pitch) and treats enharmonic pitches as equal.
	 * Thus, this ordering is <strong>inconsistent with {@code equals}</strong>.
	 */
	@Override
	public int compareTo(Pitch o) {
		return this.pitch - o.pitch;
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
	 *         both {@code this} and {code @obj} have equal pitch class and
	 *         the same octave.
	 *         Pitch classes are only considered equal if they have equal
	 *         names; that is, enharmonic pitch classes are not necessarily
	 *         equal. For more information see {@link PitchClass#equals}.
	 * @see {@link PitchClass#equals}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
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
	
	@Override
	public String toString() {
		return pitchClass.toString() + octave;
	}
}
