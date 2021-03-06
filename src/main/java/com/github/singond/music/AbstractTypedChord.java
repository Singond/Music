package com.github.singond.music;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * A skeletal implementation of the {@code Chord} interface which corresponds
 * directly to a {@code ChordType} built on top of a given {@code PitchClass}.
 *
 * @author Singon
 */
abstract class AbstractTypedChord implements Chord {

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
	protected AbstractTypedChord(PitchClass root, List<PitchClass> notes,
	                             ChordType type) {
		this.root = root;
		this.notes = notes;
		this.type = type;
	}

	protected static final List<PitchClass> fromBass(
			PitchClass bass, ChordType type) {
		List<PitchClass> result = new ArrayList<>(type.size());
		PitchClass note = bass;
		result.add(note);
		for (Interval i : type.structure()) {
			note = note.transposeUp(i);
			result.add(note);
		}
		return result;
	}

	protected static final List<PitchClass> fromRoot(
			PitchClass root, ChordType type) {
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
	public int inversion() {
		return type.inversion();
	}

	@Override
	public ChordType type() {
		return type;
	}

	@Override
	public Iterator<PitchClass> iterator() {
		return notes().iterator();
	}

	@Override
	public String toString() {
		return notes.toString();
	}
}
