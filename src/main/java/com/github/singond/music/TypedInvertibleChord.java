package com.github.singond.music;

import java.util.List;

/**
 * An implementation of the {@code InvertibleChord} interface which corresponds
 * directly to a {@code InvertibleChordType} built on top of a given
 * {@code PitchClass}.
 *
 * @author Singon
 */
class TypedInvertibleChord extends AbstractTypedChord
                           implements InvertibleChord {

	private final PitchClass root;
	private final InvertibleChordType type;

	/**
	 * Creates a new {@code TypedChord} with the given attributes.
	 * This constructor directly stores the given references, meaning
	 * it must stay private in order to ensure immutability.
	 *
	 * @param root the root note
	 * @param notes the pitch classes of the chord in ascending order
	 * @param type the chord type
	 */
	private TypedInvertibleChord(PitchClass root, List<PitchClass> notes,
	                             InvertibleChordType type) {
		super(root, notes, type);
		this.root = root;
		this.type = type;
	}

	/**
	 * Creates a chord of the given type with the given pitch class as its
	 * bass note.
	 *
	 * @param bass the bass note of the chord (as a {@code PitchClass})
	 * @param type the type of chord to be created
	 * @return a chord with the given {@code bass} and {@code type}
	 */
	public static final TypedInvertibleChord ofBass(
			PitchClass bass, InvertibleChordType type) {
		if (bass == null) {
			throw new NullPointerException("The chord bass is null");
		} else if (type == null) {
			throw new NullPointerException("The chord type is null");
		}
		// TODO Return preset constant if available
		List<PitchClass> notes = fromBass(bass, type);
		PitchClass root = notes.get(type.rootIndex());
		return new TypedInvertibleChord(root, notes, type);
	}

	/**
	 * Creates a chord of the given type with the given pitch class as its
	 * root note.
	 *
	 * @param root the root note of the chord (as a {@code PitchClass})
	 * @param type the type of chord to be created
	 * @return a chord with the given {@code root} and {@code type}
	 */
	public static final TypedInvertibleChord ofRoot(
			PitchClass root, InvertibleChordType type) {
		if (root == null) {
			throw new NullPointerException("The chord root is null");
		} else if (type == null) {
			throw new NullPointerException("The chord type is null");
		}
		// TODO Return preset constant if available
		List<PitchClass> notes = fromRoot(root, type);
		return new TypedInvertibleChord(root, notes, type);
	}

	@Override
	public InvertibleChord invert(int n) {
		if (type.inversion() == n) {
			return this;
		} else {
			InvertibleChordType type = this.type.invert(n);
			return new TypedInvertibleChord(root, fromRoot(root, type), type);
		}
	}

	@Override
	public InvertibleChord rootPosition() {
		if (type.inversion() == 0) {
			return this;
		} else {
			InvertibleChordType type = this.type.rootPosition();
			return new TypedInvertibleChord(root, fromRoot(root, type), type);
		}
	}
}
