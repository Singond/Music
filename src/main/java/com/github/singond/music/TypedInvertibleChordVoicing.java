package com.github.singond.music;

import java.util.List;

/**
 * An implementation of the {@code ChordVoicing} interface which corresponds
 * directly to a {@code InvertibleChordType} built on top of a given
 * {@code Pitch}.
 *
 * @author Singon
 */
class TypedInvertibleChordVoicing extends AbstractTypedChordVoicing
                                  implements InvertibleChordVoicing {

	private final Pitch root;
	private final InvertibleChordType type;

	/**
	 * Creates a new {@code TypedChordVoicing} with the given attributes.
	 * This constructor directly stores the given references, meaning
	 * it must stay private in order to ensure immutability.
	 *
	 * @param root the root note
	 * @param notes the pitches of the chord in ascending order
	 * @param type the chord type
	 */
	private TypedInvertibleChordVoicing(
			Pitch root, List<Pitch> notes, InvertibleChordType type) {
		super(root, notes, type);
		this.root = root;
		this.type = type;
	}

	/**
	 * Creates a chord voicing of the given type with the given pitch as its
	 * bass note.
	 *
	 * @param bass the bass note of the chord (as a {@code Pitch})
	 * @param type the type of chord to be created
	 * @return a chord with the given {@code bass} and {@code type}
	 */
	public static final TypedInvertibleChordVoicing ofBass(
			Pitch bass, InvertibleChordType type) {
		if (bass == null) {
			throw new NullPointerException("The chord bass is null");
		} else if (type == null) {
			throw new NullPointerException("The chord type is null");
		}
		// TODO Return preset constant if available
		List<Pitch> notes = fromBass(bass, type);
		Pitch root = notes.get(type.rootIndex());
		return new TypedInvertibleChordVoicing(root, notes, type);
	}

	/**
	 * Creates a chord voicing of the given type with the given pitch as its
	 * root note.
	 *
	 * @param root the root note of the chord (as a {@code Pitch})
	 * @param type the type of chord to be created
	 * @return a chord with the given {@code root} and {@code type}
	 */
	public static final TypedInvertibleChordVoicing ofRoot(
			Pitch root, InvertibleChordType type) {
		if (root == null) {
			throw new NullPointerException("The chord root is null");
		} else if (type == null) {
			throw new NullPointerException("The chord type is null");
		}
		// TODO Return preset constant if available
		List<Pitch> notes = fromRoot(root, type);
		return new TypedInvertibleChordVoicing(root, notes, type);
	}

	@Override
	public InvertibleChordVoicing invert(int n) {
		if (type.inversion() == n) {
			return this;
		} else {
			InvertibleChordType invType = type.invert(n);
			int shift = invType.rootOctave() - type.rootOctave();
			Pitch invRoot = Pitch.of(root.pitchClass(), root.octave() + shift);
			return ofRoot(invRoot, invType);
		}
	}

	@Override
	public InvertibleChordVoicing rootPosition() {
		if (type.inversion() == 0) {
			return this;
		} else {
			return invert(0);
		}
	}
}
