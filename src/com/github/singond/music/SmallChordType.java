package com.github.singond.music;

import static com.github.singond.music.SimpleInterval.MAJOR_THIRD;
import static com.github.singond.music.SimpleInterval.MINOR_THIRD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * An implementation of the {@code Chord} interface applicable to chords
 * whose span does not exceed one octave.
 *
 * @author Singon
 */
class SmallChordType implements ChordType {

	private final List<Interval> structure;

	private final int root;

	private final String name;

	private transient Interval span;

	public static final ChordType
			MAJOR_TRIAD, MAJOR_TRIAD_6, MAJOR_TRIAD_64,
			MINOR_TRIAD, MINOR_TRIAD_6, MINOR_TRIAD_64,
			DIMINISHED_TRIAD, DIMINISHED_TRIAD_6, DIMINISHED_TRIAD_64,
			MAJOR_7, MINOR_7, DOMINANT_7,
			DIMINISHED_7, HALF_DIMINISHED_7, MINOR_MAJOR_7, AUGMENTED_MAJOR_7;

	static {
		List<PreInvertedChord> inversions;
		inversions = PreInvertedChord.inversionsOf(
				Arrays.asList(MAJOR_THIRD, MINOR_THIRD), "major triad", "");
		MAJOR_TRIAD    = inversions.get(0);
		MAJOR_TRIAD_6  = inversions.get(1);
		MAJOR_TRIAD_64 = inversions.get(2);

		inversions = PreInvertedChord.inversionsOf(
				Arrays.asList(MINOR_THIRD, MAJOR_THIRD), "minor triad", "m");
		MINOR_TRIAD    = inversions.get(0);
		MINOR_TRIAD_6  = inversions.get(1);
		MINOR_TRIAD_64 = inversions.get(2);

		inversions = PreInvertedChord.inversionsOf(
				Arrays.asList(MINOR_THIRD, MINOR_THIRD), "dimished triad", "m5-");
		DIMINISHED_TRIAD    = inversions.get(0);
		DIMINISHED_TRIAD_6  = inversions.get(1);
		DIMINISHED_TRIAD_64 = inversions.get(2);

		MAJOR_7 = PreInvertedChord.of(
				Arrays.asList(MAJOR_THIRD, MINOR_THIRD, MAJOR_THIRD),
				"major 7th", "maj7");
		DOMINANT_7 = PreInvertedChord.of(
				Arrays.asList(MAJOR_THIRD, MINOR_THIRD, MINOR_THIRD),
				"dominant 7th", "7");
		MINOR_7 = PreInvertedChord.of(
				Arrays.asList(MINOR_THIRD, MAJOR_THIRD, MINOR_THIRD),
				"minor 7th", "m7");
		MINOR_MAJOR_7 = PreInvertedChord.of(
				Arrays.asList(MINOR_THIRD, MAJOR_THIRD, MAJOR_THIRD),
				"minor major 7th", "m maj7");
		HALF_DIMINISHED_7 = PreInvertedChord.of(
				Arrays.asList(MINOR_THIRD, MINOR_THIRD, MAJOR_THIRD),
				"half-diminished 7th", "7/5-");
		DIMINISHED_7 = PreInvertedChord.of(
				Arrays.asList(MINOR_THIRD, MINOR_THIRD, MINOR_THIRD),
				"diminished 7th", "dim");
		AUGMENTED_MAJOR_7 = PreInvertedChord.of(
				Arrays.asList(MAJOR_THIRD, MAJOR_THIRD, MINOR_THIRD),
				"augmented major 7th", "7/5+");
	}

	private SmallChordType(List<Interval> structure, int root,
	                       String name, String symbol) {
		if (structure == null) {
			throw new NullPointerException("The interval structure is null");
		} else if (structure.isEmpty()) {
			throw new IllegalArgumentException("The interval structure is empty");
		} else if (root < 0 || root > structure.size()) {
			throw new IndexOutOfBoundsException
					("The index of the root note is not valid: " + root);
		}

		final Interval span = calculateSpan(structure);
		// TODO Use interval comparison with octave
		if (span.semitones() >= 12) {
			throw new IllegalArgumentException
					("The sum of intervals must be less than one octave: "
					 + "sum of (" + structure + ") is " + span);
		}

		this.structure = new ArrayList<>(structure);
		this.root = root;
		this.name = name;
		this.span = span;
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

	private static Interval calculateSpan(List<Interval> intervals) {
		return Intervals.sum(intervals);
	}

	@Override
	public Interval heightAboveBass(int note) {
		return Intervals.sum(structure.subList(0, note));
	}

	@Override
	public boolean invertible() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("This method has not been implemented yet");
	}

	@Override
	public ChordType invert(int n) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("This method has not been implemented yet");
	}

	@Override
	public int inversion() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("This method has not been implemented yet");
	}

	@Override
	public ChordType rootPosition() {
		return invert(0);
	}

	private static Interval octaveComplement(Interval interval) {
		// TODO Implement using interval subtraction
		int degrees = 7 - interval.degrees();
		int semitones = 12 - interval.semitones();
		return SimpleInterval.valueOf(degrees, semitones);
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

	/**
	 * An extension of {@code SmallChordType} which pre-computes its
	 * inversions on initialization.
	 *
	 * @author Singon
	 */
	private static final class PreInvertedChord extends SmallChordType {

		private final List<PreInvertedChord> inversions;
		private final int inversionNumber;

		private PreInvertedChord(int root, List<Interval> intervals,
				List<PreInvertedChord> inversions, int inversionNumber,
				String name, String symbol) {
			super(intervals, root, name, symbol);
			this.inversions = inversions;
			this.inversionNumber = inversionNumber;
		}

		/**
		 * Returns a chord formed from the given interval structure.
		 * The position is taken as the chord's root position.
		 * This is equivalent to {@code inversionsOf(intervals).get(0)}.
		 *
		 * @param intervals the interval structure of the chord
		 * @return chord generated from {@code intervals}
		 */
		static PreInvertedChord of(List<Interval> intervals,
		                           String name, String symbol) {
			return inversionsOf(intervals, name, symbol).get(0);
		}

		/**
		 * Returns a chord formed from the given interval structure.
		 * The position is taken as the chord's root position.
		 * This is equivalent to {@code inversionsOf(intervals).get(0)}.
		 *
		 * @param intervals the interval structure of the chord
		 * @return chord generated from {@code intervals}
		 */
//		static PreInvertedChord of(Interval... intervals) {
//			return inversionsOf(intervals).get(0);
//		}

		/**
		 * Returns all inversions of the root chord formed from the given
		 * interval structure.
		 *
		 * @param intervals the interval structure of the root chord
		 * @params names simple names of all inversions
		 * @return all inversions of the root chord generated from
		 *         {@code intervals}
		 */
		static List<PreInvertedChord> inversionsOf(List<Interval> intervals,
				final String name, String symbol) {
			// Complete the list of structural intervals to make one octave
			List<Interval> intv = new ArrayList<>(intervals.size() + 1);
			intv.addAll(intervals);
			intv.add(octaveComplement(Intervals.sum(intervals)));
			List<Interval> curInt = intv.subList(0, intv.size() - 1);

			List<PreInvertedChord> inversions = new ArrayList<>(intv.size());
			for (int i = 0; i < intv.size(); i++) {
				int root = Math.floorMod(-i, intv.size());
				String invName = name;
				if (i != 0) {
					invName = name + " (inv. " + i + ")";
				}
				inversions.add(new PreInvertedChord
						(root, curInt, inversions, i, invName, symbol));
				Collections.rotate(intv, -1);	// Shift list left
			}
			// To protect immutability of instances, do not expose
			// the "inversions" list reference, use a copy instead
			return new ArrayList<>(inversions);
		}

		/**
		 * Returns all inversions of the root chord formed from the given
		 * interval structure.
		 *
		 * @param intervals the interval structure of the root chord
		 * @return all inversions of the root chord generated from
		 *         {@code intervals}
		 */
//		static List<PreInvertedChord> inversionsOf(Interval... intervals) {
//			return inversionsOf(Arrays.asList(intervals));
//		}


		/**
		 * @return always {@code true}
		 */
		@Override
		public boolean invertible() {
			return true;
		}

		/**
		 * {@inheritDoc}
		 * <p>
		 * This implementation always returns a precomputed result.
		 */
		@Override
		public ChordType invert(int n) {
			return inversions.get(n);
		}

		@Override
		public int inversion() {
			return inversionNumber;
		}
	}
}
