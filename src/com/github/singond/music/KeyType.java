package com.github.singond.music;

import java.util.List;

/**
 * A type of a musical key (like major or minor),
 * that is the mutual relationships of all notes in a musical key without
 * any reference given.
 * The degrees of the key are refered to by the interval they make above
 * the first degree (the tonic).
 *
 * @author Singon
 */
public interface KeyType {

	/**
	 * Returns all degrees in this key as intervals above the tonic.
	 * The degrees are sorted in ascending order.
	 *
	 * @return an ascending scale of degrees in this key, represented by
	 *         the intervals they make with the tonic below
	 */
	List<Interval> degrees();

	/**
	 * Returns the given degree of this key as an interval above the tonic.
	 * This is equal to calling {@code degrees().get(degree - 1)}.
	 *
	 * @param degree the one-based index of the degree
	 * @return the degree at (one-based) index {@code degree}
	 * @throws IndexOutOfBoundsException if the number does not lie
	 *         between 1 and the number of degrees in the key (inclusive)
	 */
	Interval degree(int degree);

	/**
	 * Returns a musical key of this key type and with the given tonic.
	 *
	 * @param tonic the tonic of the key
	 * @return this type of key in {@code tonic}
	 */
	Key in(PitchClass tonic);
}
