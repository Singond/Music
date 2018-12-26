package com.github.singond.music;

import static com.github.singond.music.SimpleInterval.MAJOR_THIRD;
import static com.github.singond.music.SimpleInterval.MINOR_THIRD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * An implementation of the {@code ChordType} interface applicable to
 * invertible chords whose span does not exceed one octave.
 * <p>
 * Instances of this class pre-compute its inversions on initialization.
 *
 * @author Singon
 */
class DefaultChordType implements ChordType {

	/** Interval structure of this chord type. */
	private final List<Interval> structure;
	/** Index of the root note. */
	private final int root;
	/** Simple name of this chord type. */
	private final String name;
	/** Symbol of this chord type. */
	private final String symbol;
	/** Total interval span of this chord type. Must be less than one octave. */
	private transient Interval span;
	/** The index of this instance in {@code inversions}. */
	private final int inversionNumber;

	public static final ChordType
			MAJOR_TRIAD, MAJOR_TRIAD_6, MAJOR_TRIAD_64,
			MINOR_TRIAD, MINOR_TRIAD_6, MINOR_TRIAD_64,
			DIMINISHED_TRIAD, DIMINISHED_TRIAD_6, DIMINISHED_TRIAD_64,
			MAJOR_7, MINOR_7, DOMINANT_7,
			HALF_DIMINISHED_7, MINOR_MAJOR_7, AUGMENTED_MAJOR_7;

	static {
		List<DefaultChordType> inversions;
		inversions = DefaultChordType.inversionsOf(Arrays.<Interval>asList(
				MAJOR_THIRD, MINOR_THIRD), "major triad", "");
		MAJOR_TRIAD    = inversions.get(0);
		MAJOR_TRIAD_6  = inversions.get(1);
		MAJOR_TRIAD_64 = inversions.get(2);

		inversions = DefaultChordType.inversionsOf(Arrays.<Interval>asList(
				MINOR_THIRD, MAJOR_THIRD), "minor triad", "m");
		MINOR_TRIAD    = inversions.get(0);
		MINOR_TRIAD_6  = inversions.get(1);
		MINOR_TRIAD_64 = inversions.get(2);

		inversions = DefaultChordType.inversionsOf(Arrays.<Interval>asList(
				MINOR_THIRD, MINOR_THIRD), "dimished triad", "m5-");
		DIMINISHED_TRIAD    = inversions.get(0);
		DIMINISHED_TRIAD_6  = inversions.get(1);
		DIMINISHED_TRIAD_64 = inversions.get(2);

		MAJOR_7 = DefaultChordType.inversionsOf(
				Arrays.<Interval>asList(MAJOR_THIRD, MINOR_THIRD, MAJOR_THIRD),
				"major 7th", "maj7").get(0);
		DOMINANT_7 = DefaultChordType.inversionsOf(
				Arrays.<Interval>asList(MAJOR_THIRD, MINOR_THIRD, MINOR_THIRD),
				"dominant 7th", "7").get(0);
		MINOR_7 = DefaultChordType.inversionsOf(
				Arrays.<Interval>asList(MINOR_THIRD, MAJOR_THIRD, MINOR_THIRD),
				"minor 7th", "m7").get(0);
		MINOR_MAJOR_7 = DefaultChordType.inversionsOf(
				Arrays.<Interval>asList(MINOR_THIRD, MAJOR_THIRD, MAJOR_THIRD),
				"minor major 7th", "m maj7").get(0);
		HALF_DIMINISHED_7 = DefaultChordType.inversionsOf(
				Arrays.<Interval>asList(MINOR_THIRD, MINOR_THIRD, MAJOR_THIRD),
				"half-diminished 7th", "7/5-").get(0);
		AUGMENTED_MAJOR_7 = DefaultChordType.inversionsOf(
				Arrays.<Interval>asList(MAJOR_THIRD, MAJOR_THIRD, MINOR_THIRD),
				"augmented major 7th", "7/5+").get(0);
	}

	private DefaultChordType(List<Interval> structure, int root,
			int inversionNumber, String name, String symbol) {
		if (structure == null) {
			throw new NullPointerException("The interval structure is null");
		} else if (structure.isEmpty()) {
			throw new IllegalArgumentException("The interval structure is empty");
		} else if (root < 0 || root > structure.size()) {
			throw new IndexOutOfBoundsException
					("The index of the root note is not valid: " + root);
		} else if (inversionNumber < 0 || inversionNumber > structure.size()) {
			throw new IndexOutOfBoundsException
					("The inversion number is not valid: " + inversionNumber);
		}

		final Interval span = calculateSpan(structure);
		if (span.compareTo(SimpleInterval.PERFECT_OCTAVE) > 0) {
			throw new IllegalArgumentException
					("The sum of intervals must be less than one octave: "
					 + "sum of (" + structure + ") is " + span);
		}

		this.structure = new ArrayList<>(structure);
		this.root = root;
		this.name = name != null ? name : "";
		this.symbol = symbol != null ? symbol : "";
		this.span = span;
		this.inversionNumber = inversionNumber;
	}

	public static DefaultChordType inversionsOf
			(List<Interval> intervals, int root, int inversion,
			String name, String symbol) {
		return new DefaultChordType(intervals, root, inversion, name, symbol);
	}

	public static DefaultChordType inversionsOf
			(List<Interval> intervals, int root, int inversion) {
		return new DefaultChordType(intervals, root, inversion, "", "");
	}

	private static Interval calculateSpan(List<Interval> intervals) {
		return Intervals.sum(intervals);
	}

	@Override
	public List<Interval> structure() {
		return Collections.unmodifiableList(structure);
	}

	@Override
	public int rootIndex() {
		return root;
	}

	@Override
	public int size() {
		return structure.size() + 1;
	}

	@Override
	public Interval span() {
		return span;
	}

	@Override
	public Interval heightAboveBass(int note) {
		return Intervals.sum(structure.subList(0, note));
	}

	@Override
	public int inversion() {
		return inversionNumber;
	}

	/**
	 * Returns a simple name of this chord type.
	 *
	 * @return a simple chord name, like "minor triad"
	 */
	public String name() {
		return name;
	}

	/**
	 * Returns a symbol of this chord type.
	 *
	 * @return a chord symbol, like "m" for a minor triad
	 */
	public String symbol() {
		return symbol;
	}

	public final String printStructure() {
		StringBuilder sb = new StringBuilder();
		int index = 0;
		for (Interval i : structure) {
			sb.append(index == rootIndex() ? "O" : "o");
			sb.append("-" + i + "-");
			index++;
		}
		sb.append(index == rootIndex() ? "O" : "o");
		return sb.toString();
	}

	@Override
	public String toString() {
		return name;
	}
}
