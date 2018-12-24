package com.github.singond.music;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * An implementation of the {@code ChordVoicing} interface which corresponds
 * directly to a {@code ChordType} built on top of a given {@code Pitch}.
 *
 * @author Singon
 */
class TypedChordVoicing implements ChordVoicing {

	private final Pitch root;
	private final List<Pitch> notes;
	private final ChordType type;

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
		this.root = root;
		this.notes = notes;
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

	private static List<Pitch> fromBass(Pitch bass, ChordType type) {
		List<Pitch> result = new ArrayList<>(type.size());
		Pitch note = bass;
		result.add(note);
		for (Interval i : type.structure()) {
			note = note.transposeUp(i);
			result.add(note);
		}
		return result;
	}

	private static List<Pitch> fromRoot(Pitch root, ChordType type) {
		List<Pitch> result = new ArrayList<>(type.size());
		Interval rootInt = type.heightAboveBass(type.rootIndex());
		Pitch note = root.transposeDown(rootInt);
		result.add(note);
		for (Interval i : type.structure()) {
			note = note.transposeUp(i);
			result.add(note);
		}
		return result;
	}

	@Override
	public List<Pitch> pitches() {
		return Collections.unmodifiableList(notes);
	}

	@Override
	public PitchClass root() {
		return root.pitchClass();
	}

	@Override
	public Pitch bass() {
		return notes.get(0);
	}

	@Override
	public int size() {
		return type.size();
	}

	@Override
	public boolean invertible() {
		return type.invertible();
	}

	@Override
	public ChordVoicing invert(int n) {
		if (type.inversion() == n) {
			return this;
		} else {
			return ofRoot(root, type.invert(n));
		}
	}

	@Override
	public int inversion() {
		return type.inversion();
	}

	@Override
	public ChordVoicing rootPosition() {
		if (type.inversion() == 0) {
			return this;
		} else {
			return ofRoot(root, type.rootPosition());
		}
	}

	@Override
	public ChordType type() {
		return type;
	}

	@Override
	public Iterator<Pitch> iterator() {
		return pitches().iterator();
	}

	@Override
	public String toString() {
		return notes.toString();
	}
}
