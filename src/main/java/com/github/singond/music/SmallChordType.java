/*
 * Copyright 2019 Jan Slany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
class SmallChordType implements ChordType {

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
	/** A list of all inversions of this chord type. */
	private final List<SmallChordType> inversions;
	/** The index of this instance in {@code inversions}. */
	private final int inversionNumber;

	public static final ChordType
			MAJOR_TRIAD, MAJOR_TRIAD_6, MAJOR_TRIAD_64,
			MINOR_TRIAD, MINOR_TRIAD_6, MINOR_TRIAD_64,
			DIMINISHED_TRIAD, DIMINISHED_TRIAD_6, DIMINISHED_TRIAD_64,
			MAJOR_7, MINOR_7, DOMINANT_7,
			HALF_DIMINISHED_7, MINOR_MAJOR_7, AUGMENTED_MAJOR_7;

	static {
		List<SmallChordType> inversions;
		inversions = SmallChordType.inversionsOf(Arrays.<Interval>asList(
				MAJOR_THIRD, MINOR_THIRD), "major triad", "");
		MAJOR_TRIAD    = inversions.get(0);
		MAJOR_TRIAD_6  = inversions.get(1);
		MAJOR_TRIAD_64 = inversions.get(2);

		inversions = SmallChordType.inversionsOf(Arrays.<Interval>asList(
				MINOR_THIRD, MAJOR_THIRD), "minor triad", "m");
		MINOR_TRIAD    = inversions.get(0);
		MINOR_TRIAD_6  = inversions.get(1);
		MINOR_TRIAD_64 = inversions.get(2);

		inversions = SmallChordType.inversionsOf(Arrays.<Interval>asList(
				MINOR_THIRD, MINOR_THIRD), "dimished triad", "m5-");
		DIMINISHED_TRIAD    = inversions.get(0);
		DIMINISHED_TRIAD_6  = inversions.get(1);
		DIMINISHED_TRIAD_64 = inversions.get(2);

		MAJOR_7 = SmallChordType.inversionsOf(
				Arrays.<Interval>asList(MAJOR_THIRD, MINOR_THIRD, MAJOR_THIRD),
				"major 7th", "maj7").get(0);
		DOMINANT_7 = SmallChordType.inversionsOf(
				Arrays.<Interval>asList(MAJOR_THIRD, MINOR_THIRD, MINOR_THIRD),
				"dominant 7th", "7").get(0);
		MINOR_7 = SmallChordType.inversionsOf(
				Arrays.<Interval>asList(MINOR_THIRD, MAJOR_THIRD, MINOR_THIRD),
				"minor 7th", "m7").get(0);
		MINOR_MAJOR_7 = SmallChordType.inversionsOf(
				Arrays.<Interval>asList(MINOR_THIRD, MAJOR_THIRD, MAJOR_THIRD),
				"minor major 7th", "m maj7").get(0);
		HALF_DIMINISHED_7 = SmallChordType.inversionsOf(
				Arrays.<Interval>asList(MINOR_THIRD, MINOR_THIRD, MAJOR_THIRD),
				"half-diminished 7th", "7/5-").get(0);
		AUGMENTED_MAJOR_7 = SmallChordType.inversionsOf(
				Arrays.<Interval>asList(MAJOR_THIRD, MAJOR_THIRD, MINOR_THIRD),
				"augmented major 7th", "7/5+").get(0);
	}

	private SmallChordType(List<Interval> structure, int root,
			List<SmallChordType> inversions, int inversionNumber,
			String name, String symbol) {
		if (structure == null) {
			throw new NullPointerException("The interval structure is null");
		} else if (structure.isEmpty()) {
			throw new IllegalArgumentException("The interval structure is empty");
		} else if (root < 0 || root > structure.size()) {
			throw new IndexOutOfBoundsException
					("The index of the root note is not valid: " + root);
		} else if (inversions == null) {
			throw new NullPointerException("The list of inversions is null");
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
		this.inversions = inversions;
	}

	private static Interval calculateSpan(List<Interval> intervals) {
		return Intervals.sum(intervals);
	}

	/**
	 * Returns all inversions of the root chord formed from the given
	 * interval structure.
	 *
	 * @param intervals the interval structure of the root chord
	 * @param name simple name of this chord type
	 * @param symbol symbol of this chord type
	 * @return all inversions of the root chord generated from
	 *         {@code intervals}
	 */
	private static List<SmallChordType> inversionsOf(List<Interval> intervals,
			String name, String symbol) {
		// Complete the list of structural intervals to make one octave
		List<Interval> intv = new ArrayList<>(intervals.size() + 1);
		intv.addAll(intervals);
		intv.add(octaveComplement(Intervals.sum(intervals)));
		List<Interval> curInt = intv.subList(0, intv.size() - 1);

		List<SmallChordType> inversions = new ArrayList<>(intv.size());
		for (int i = 0; i < intv.size(); i++) {
			int root = Util.floorMod(-i, intv.size());
			String invName = name;
			if (i != 0) {
				invName = name + " (inv. " + i + ")";
			}
			inversions.add(new SmallChordType
					(curInt, root, inversions, i, invName, symbol));
			Collections.rotate(intv, -1);	// Shift list left
		}
		// To protect immutability of instances, do not expose
		// the "inversions" list reference, use a copy instead
		return new ArrayList<>(inversions);
	}

	private static Interval octaveComplement(Interval interval) {
		// TODO Implement using interval subtraction
		int degrees = 7 - interval.degrees();
		int semitones = 12 - interval.semitones();
		return SimpleInterval.valueOf(degrees, semitones);
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

	@Override
	public ChordType rootPosition() {
		return invert(0);
	}

	@Override
	public int rootOctave() {
		return inversionNumber == 0 ? 0 : 1;
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
