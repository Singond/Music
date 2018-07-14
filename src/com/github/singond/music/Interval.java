package com.github.singond.music;

/**
 * An absolute distance of two pitches.
 * This interval object holds no information of the direction in which
 * the interval is applied, it is merely the distance of the two pitches,
 * expressed as an absolute value.
 * This definition prohibits pitches with negative number of semitones,
 * such as what is sometimes called the "diminished unison".
 *
 * This interface imposes a natural ordering on its implementations.
 * This order sorts the intervals with lower width in semitones before
 * the the wider intervals. If two intervals happen to have the same width,
 * that with higher number of diatonic degrees is sorted last.
 * This natural ordering is consisten with {@code equals}.
 *
 * @author Singon
 */
public interface Interval extends Comparable<Interval> {

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
	 * Compares another interval to this one.
	 * This method first orders the intervals from smallest to largest
	 * and then further orders enharmonic intervals in the order given
	 * by their number of diatonic degrees, so that for example augmented
	 * third is sorted before perfect fourth.
	 * <p>
	 * This method must throw {@code NullPointerException} when invoked
	 * with null argument.
	 *
	 * @param i {@inheritDoc}
	 * @return  {@inheritDoc}
	 * @throws  NullPointerException {@inheritDoc}
	 */
	@Override
	public int compareTo(Interval i);

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

	/**
	 * Indicates whether a given object is equal to this interval, that is,
	 * if the given object is also an interval and spans the same number
	 * of semitones and the same number of diatonic degrees.
	 * Thus, two intervals with the same width in semitones do not necessarily
	 * be equal to each other.
	 * For example, augmented third is not equal to perfect fourth, despite
	 * the fact that both of these intervals have the same width.
	 *
	 * @param object {@inheritDoc}
	 * @return {@code true} if {@code obj} is also an {@code Interval} and if
	 *         both {@code this} and {@code obj} have equal number of semitones
	 *         and equal number of diatonic degrees
	 */
	@Override
	boolean equals(Object object);
}
