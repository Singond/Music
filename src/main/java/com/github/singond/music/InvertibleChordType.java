package com.github.singond.music;

/**
 * An extension of {@code ChordType} which is aware of its inversions.
 * Chords implementing this interface expose their pre-defined inversions
 * through their interface.
 *
 * @author Singon
 */
public interface InvertibleChordType extends ChordType {

	/**
	 * Returns the <em>n</em>-th inversion of this chord, where <em>n</em>
	 * is a number between 0 (inclusive) and the chord size (exclusive).
	 * The <em>n</em>-th inversion of a chord is such a rearrangement of
	 * its notes that the <em>n</em>-th note (counting from the root,
	 * which is assigned the index 0) is in the bass.
	 * <p>
	 * Apart from this requirement for the bass note, the exact positions
	 * of remaining notes are un-specified, and the implementations are
	 * free to choose the exact form of the inversion.
	 *
	 * @param n the inversion number <em>n</em> (see above)
	 * @return an inversion of this chord with the {@code n}-th note
	 *         in the bass
	 * @throws IndexOutOfBoundsException if {@code n} is not between
	 *         0 (inclusive) and {@code size()} (exclusive)
	 * @throws UnsupportedOperationException if this chord has no inversions
	 */
	InvertibleChordType invert(int n);

	/**
	 * Returns the root position of this chord type.
	 * The root position of a chord is the position in which the root
	 * note is equal to the bass note, ie. where {@code rootIndex() == 0}.
	 * This is equivalent to calling {@code invert(0)}.
	 *
	 * @return the root position of this chord
	 */
	InvertibleChordType rootPosition();

	/**
	 * Returns the number of octaves between the root note of this chord and
	 * the root note of the root position of this chord, assuming that the bass
	 * note of the {@code (n+1)}th inversion is always than the bass note of
	 * the {@code n}th inversion for all {@code n}.
	 *
	 * @return the number of octaves the root has moved from root position
	 */
	int rootOctave();
}
