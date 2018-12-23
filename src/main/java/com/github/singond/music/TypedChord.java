package com.github.singond.music;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TypedChord implements Chord {

	private final PitchClass root;
	private final List<PitchClass> notes;
	private final ChordType type;

	/**
	 * Creates a new {@code TypedChord} with the given attributes.
	 * This constructor directly stores the given references, meaning
	 * it must stay private in order to ensure immutability.
	 *
	 * @param root the root note
	 * @param notes the pitch classes of the chord in ascending order
	 * @param type the chord type
	 */
	private TypedChord(PitchClass root, List<PitchClass> notes,
	                   ChordType type) {
		this.root = root;
		this.notes = notes;
		this.type = type;
	}

	public static final TypedChord ofBass(PitchClass bass, ChordType type) {
		if (bass == null) {
			throw new NullPointerException("The chord bass is null");
		} else if (type == null) {
			throw new NullPointerException("The chord type is null");
		}
		// TODO Return preset constant if available
		List<PitchClass> notes = fromBass(bass, type);
		PitchClass root = notes.get(type.rootIndex());
		return new TypedChord(root, notes, type);
	}

	public static final TypedChord ofRoot(PitchClass root, ChordType type) {
		if (root == null) {
			throw new NullPointerException("The chord root is null");
		} else if (type == null) {
			throw new NullPointerException("The chord type is null");
		}
		// TODO Return preset constant if available
		List<PitchClass> notes = fromRoot(root, type);
		return new TypedChord(root, notes, type);
	}

	private static List<PitchClass> fromBass(PitchClass bass, ChordType type) {
		List<PitchClass> result = new ArrayList<>(type.size());
		PitchClass note = bass;
		result.add(note);
		for (Interval i : type.structure()) {
			note = note.transposeUp(i);
			result.add(note);
		}
		return result;
	}

	private static List<PitchClass> fromRoot(PitchClass root, ChordType type) {
		List<PitchClass> result = new ArrayList<>(type.size());
		Interval rootInt = type.heightAboveBass(type.rootIndex());
		PitchClass note = root.transposeDown(rootInt);
		result.add(note);
		for (Interval i : type.structure()) {
			note = note.transposeUp(i);
			result.add(note);
		}
		return result;
	}

	@Override
	public List<Interval> structure() {
		return type.structure();
	}

	@Override
	public List<PitchClass> notes() {
		return Collections.unmodifiableList(notes);
	}

	@Override
	public PitchClass root() {
		return root;
	}

	@Override
	public PitchClass bass() {
		return notes.get(0);
	}

	@Override
	public int size() {
		return type.size();
	}

	@Override
	public Interval span() {
		return type.span();
	}

	@Override
	public boolean invertible() {
		return type.invertible();
	}

	@Override
	public Chord invert(int n) {
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
	public Chord rootPosition() {
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


}
