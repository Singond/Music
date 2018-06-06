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
	 * @return
	 */
	List<Interval> structure();

	/**
	 * Returns the number of notes in this chord type.
	 *
	 * @return the number of notes
	 */
	int size();

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
	 * Returns the root position of this chord type.
	 * The root position of a chord is the position in which the root
	 * note is equal to the bass note, ie. where {@code rootIndex() == 0}.
	 *
	 * @return
	 */
	ChordType rootPosition();
}
