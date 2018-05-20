package com.github.singond.music;

/**
 * An absolute distance of two pitches.
 * This interval object holds no information of the direction in which
 * the interval is applied, it is merely the distance of the two pitches,
 * expressed as an absolute value.
 * This definition prohibits pitches with negative number of semitones,
 * such as what is sometimes called the "diminished unison".
 *
 * @author Singon
 */
public interface Interval {

	/**
	 * Returns the number of diatonic degrees spanned by this interval,
	 * including the start degree but not the end degree.
	 * If the start degree is {@code a} and the end degree is {@code b},
	 * this method returns the number {@code b-a}.
	 * <p>
	 * When visualized on staff, this is the number of steps a notehead
	 * must make (from a line to the adjacent space or from a space to the
	 * adjacent line) when moving from the start pitch to the end pitch.
	 * <p>
	 * <strong>CAUTION:</strong> This is contrary to the naming convention
	 * of intervals, where both the start degree and end degree are included.
	 * For example, for any interval called "a third" (be it major, minor,
	 * diminished or augmented), this method returns the number <em>2</em>.
	 * <p>
	 * For example, for the interval from C4 to G4, this method returns 4;
	 * for the interval from C#4 to G4 it returns 4 as well;
	 * but for the interval from Db to G4 it returns 3.
	 *
	 * @return the number of diatonic scale degrees including the start
	 *         but not the end degree
	 */
	int degrees();
	
	/**
	 * Returns the absolute width of the interval in semitones.
	 * The width of an interval is the absolute value of the number
	 * of semitone steps between the start and end pitch of the interval.
	 * This implies that the returned value must not be negative.
	 * <p>
	 * For example, for the interval from C4 to G4, this method returns 7;
	 * for the interval from C#4 to G4 it returns 6;
	 * and for the interval from Db to G4 it returns 6 as well.
	 *
	 * @return the width of the interval in semitones (always positive)
	 */
	int semitones();
	
	/**
	 * Returns the number of diatonic degrees spanned by this interval,
	 * including the start degree and the end degree.
	 * This is the number which forms the base of the Latin name of the
	 * interval, and is equivalent to {@code degrees() + 1}.
	 * <p>
	 * As an example, for a major third, this method returns 3.
	 *
	 * @return the number of diatonic degrees including start and end degree
	 */
	int intervalNumber();
	
	/**
	 * Indicates whether a given interval is enharmonic with this interval,
	 * that is, if it spans the same number of semitones.
	 * Two intervals with the same width in semitones are always considered
	 * enharmonic, even if they represent a different number of degrees.
	 * For example, augmented third is enharmonic with perfect fourth.
	 *
	 * @param other the interval to compare with this interval
	 * @return {@code true} if this interval has the same width in semitones
	 *         as {@code other}
	 */
	boolean isEnharmonicWith(Interval other);
}
