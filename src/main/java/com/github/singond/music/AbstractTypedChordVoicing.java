package com.github.singond.music;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * A skeletal implementation of the {@code ChordVoicing} interface which
 * corresponds directly to a {@code ChordType} built on top of a given
 * {@code Pitch}.
 *
 * @author Singon
 */
abstract class AbstractTypedChordVoicing implements ChordVoicing {

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
	protected AbstractTypedChordVoicing(
			Pitch root, List<Pitch> notes, ChordType type) {
		this.root = root;
		this.notes = notes;
		this.type = type;
	}

	protected static final List<Pitch> fromBass(Pitch bass, ChordType type) {
		List<Pitch> result = new ArrayList<>(type.size());
		Pitch note = bass;
		result.add(note);
		for (Interval i : type.structure()) {
			note = note.transposeUp(i);
			result.add(note);
		}
		return result;
	}

	protected static final List<Pitch> fromRoot(Pitch root, ChordType type) {
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
	public int inversion() {
		return type.inversion();
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
