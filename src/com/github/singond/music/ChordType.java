package com.github.singond.music;

import java.util.List;

/**
 * A group of notes with specified relative distances between them,
 * but without any absolute positioning, and with a basic note,
 * which is called the <em>root</em>.
 * The lowest of the pitches is called the <em>bass</em>.
 * In the general case, the bass does not coincide with the root.
 *
 * @author Singon
 */
public interface ChordType {

	/**
	 * Returns the interval structure of this chord.
	 * This is the sequence of intervals between adjacent notes of this
	 * chord, starting with the lowest pair of notes and moving up.
	 *
	 * @return the intervals between adjacent notes
	 */
	List<Interval> structure();

	/**
	 * Returns the zero-based index of the root note in this chord.
	 * If the notes of this chord were numbered in ascending order
	 * starting from zero, the returned value would be the index
	 * assigned to the root note.
	 * <p>
	 * Alternatively, this is the number of structural intervals
	 * one must sequentially add to the bass note in order to obtain
	 * the root note, provided the intervals are applied in the order
	 * given by {@link #structure()}.
	 * A value of {@code 0} means the chord is in root position.
	 *
	 * @return the index of the root note
	 */
	int rootIndex();

	/**
	 * Returns the number of notes in this chord type.
	 *
	 * @return the number of notes
	 */
	int size();

	/**
	 * Returns the interval between the lowest and the highest note of this
	 * chord. This is equal to the sum of all structural chords.
	 *
	 * @return the interval between the lowest and the highest note
	 */
	Interval span();

	/**
	 * Returns the relative height of a note above the bass of this chord.
	 * The note is given by its index in the ascending sequence of all
	 * notes of this chord, where the index 0 designates the bass.
	 *
	 * @param note the zero-based index of the note in this chord
	 * @return the interval between bass and {@code note}
	 * @throws IndexOutOfBoundsException if the argument does not lie
	 *         between 0 (inclusive) and {@code size()} (exclusive)
	 */
	Interval heightAboveBass(int note);

	/**
	 * Checks whether this chord has any non-zero inversions,
	 * ie. whether {{@link #invert} can be called without throwing
	 * {@code UnsupportedOperationException}.
	 *
	 * @return {@code true}, if calling {@link #invert} will not result
	 *         in {@code UnsupportedOperationException}
	 */
	boolean invertible();

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
	ChordType invert(int n);

	/**
	 * Returns the number of inversion of this chord.
	 * If the notes in the chord are indexed from 0 starting with the
	 * <em>root</em> in ascending order and wrapping down to base upon
	 * reaching the top note, the inversion number is the index
	 * of the <em>bass</em> note.
	 * <p>
	 * Equivalently, this is the argument to {@link #invert(int)} needed
	 * to produce this chord type.
	 *
	 * @return the index of the bass note
	 */
	int inversion();

	/**
	 * Returns the root position of this chord type.
	 * The root position of a chord is the position in which the root
	 * note is equal to the bass note, ie. where {@code rootIndex() == 0}.
	 * This is equivalent to calling {@code invert(0)}.
	 *
	 * @return the root position of this chord
	 */
	ChordType rootPosition();
}
