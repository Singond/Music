package com.github.singond.music;

/**
 * An object for calculating chord inversions.
 *
 * @author Singon
 */
public interface ChordInverter {

	/**
	 * Checks whether the given chord has any inversions, that is whether
	 * {@link #invert} can be called with the given chord without throwing
	 * an {@code UnsupportedOperationException}.
	 *
	 * @param chord the chord to be tested
	 * @return {@code true}, if calling {@code invert(chord, n)} will not
	 *         result in {@code UnsupportedOperationException}
	 */
	boolean isInvertible(ChordType chord);

	/**
	 * Returns the <em>n</em>-th inversion of the given chord.
	 * The <em>n</em>-th inversion of a chord is such a rearrangement of
	 * its notes that the <em>n</em>-th note (counting from the root,
	 * which is assigned the index 0) is in the bass.
	 * Here, the number <em>n</em> must be smaller than the size of the chord.
	 * <p>
	 * Apart from the requirement for the bass note, the exact positions
	 * of remaining notes are un-specified, and implementations are free to
	 * choose the exact form of the inversion.
	 *
	 * @param chord the chord to be inverted
	 * @param n the inversion number <em>n</em> (see above)
	 * @return an inversion of {@code chord} with the {@code n}-th note
	 *         in the bass
	 * @throws IndexOutOfBoundsException if {@code n} is not between
	 *         0 (inclusive) and {@code chord.size()} (exclusive)
	 * @throws UnsupportedOperationException if {@code chord} has no inversions
	 */
	ChordType invert(ChordType chord, int n);

	/**
	 * Returns the root position of the given chord type.
	 * The root position of a chord is the position in which the root
	 * note is equal to the bass note, ie. where {@code rootIndex() == 0}.
	 * This is equivalent to calling {@code invert(chord, 0)}.
	 *
	 * @param chord the chord to be put into root position
	 * @return the root position of this chord
	 */
	ChordType rootPosition(ChordType chord);
}
