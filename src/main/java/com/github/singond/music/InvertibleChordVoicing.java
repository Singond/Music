package com.github.singond.music;

/**
 * A {@code Chord} rendered into a group of pitches.
 * <p>
 * Throughout this class, the pitches and pitch classes comprising this chord
 * are also referred to as "notes".
 * The lowest of the pitches is called the <em>bass</em>.
 * In the general case, the bass does not coincide with the root.
 *
 * @author Singon
 */
public interface InvertibleChordVoicing extends ChordVoicing {

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
	 * <p>
	 * Note the relationship between this method and {@link #inversion}:
	 * If a chord voicing is created by calling {@code invert(n)},
	 * its {@code inversion} method should always return the number {@code n}.
	 *
	 * @param n the inversion number <em>n</em> (see above)
	 * @return an inversion of this chord with the {@code n}-th note
	 *         in the bass
	 * @throws IndexOutOfBoundsException if {@code n} is not between
	 *         0 (inclusive) and {@code size()} (exclusive)
	 * @throws UnsupportedOperationException if this chord has no inversions
	 */
	InvertibleChordVoicing invert(int n);

	/**
	 * Returns the root position of this chord.
	 * The root position of a chord is the position in which the root
	 * note is equal to the bass note.
	 * This is equivalent to calling {@code invert(0)}.
	 *
	 * @return the root position of this chord
	 */
	InvertibleChordVoicing rootPosition();

	/**
	 * {@inheritDoc}
	 * <p>
	 * Note the relationship between this method and {@link #invert}:
	 * If a chord voicing is created by calling {@code invert(n)},
	 * this method should always return the number {@code n}.
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	int inversion();
}
