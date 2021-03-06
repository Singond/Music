package com.github.singond.music;

import java.util.List;

/**
 * An implementation of the {@code ChordVoicing} interface which corresponds
 * directly to a {@code ChordType} built on top of a given {@code Pitch}.
 *
 * @author Singon
 */
class TypedChordVoicing extends AbstractTypedChordVoicing
                        implements ChordVoicing {

	/**
	 * Creates a new {@code TypedChordVoicing} with the given attributes.
	 * This constructor directly stores the given references, meaning
	 * it must stay private in order to ensure immutability.
	 *
	 * @param root the root note
	 * @param notes the pitches of the chord in ascending order
	 * @param type the chord type
	 */
	private TypedChordVoicing(Pitch root, List<Pitch> notes, ChordType type) {
		super(root, notes, type);
	}

	/**
	 * Creates a chord voicing of the given type with the given pitch as its
	 * bass note.
	 *
	 * @param bass the bass note of the chord (as a {@code Pitch})
	 * @param type the type of chord to be created
	 * @return a chord with the given {@code bass} and {@code type}
	 */
	public static final TypedChordVoicing ofBass(Pitch bass, ChordType type) {
		if (bass == null) {
			throw new NullPointerException("The chord bass is null");
		} else if (type == null) {
			throw new NullPointerException("The chord type is null");
		}
		// TODO Return preset constant if available
		List<Pitch> notes = fromBass(bass, type);
		Pitch root = notes.get(type.rootIndex());
		return new TypedChordVoicing(root, notes, type);
	}

	/**
	 * Creates a chord voicing of the given type with the given pitch as its
	 * root note.
	 *
	 * @param root the root note of the chord (as a {@code Pitch})
	 * @param type the type of chord to be created
	 * @return a chord with the given {@code root} and {@code type}
	 */
	public static final TypedChordVoicing ofRoot(Pitch root, ChordType type) {
		if (root == null) {
			throw new NullPointerException("The chord root is null");
		} else if (type == null) {
			throw new NullPointerException("The chord type is null");
		}
		// TODO Return preset constant if available
		List<Pitch> notes = fromRoot(root, type);
		return new TypedChordVoicing(root, notes, type);
	}
}
