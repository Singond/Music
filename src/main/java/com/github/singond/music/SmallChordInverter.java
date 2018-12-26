package com.github.singond.music;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmallChordInverter implements ChordInverter {

	@Override
	public boolean isInvertible(ChordType chord) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ChordType invert(ChordType chord, int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChordType rootPosition(ChordType chord) {
		// TODO Auto-generated method stub
		return null;
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
	private static List<ChordType> inversionsOf(List<Interval> intervals,
			String name, String symbol) {
		// Complete the list of structural intervals to make one octave
		List<Interval> intv = new ArrayList<>(intervals.size() + 1);
		intv.addAll(intervals);
		intv.add(octaveComplement(Intervals.sum(intervals)));
		List<Interval> curInt = intv.subList(0, intv.size() - 1);

		List<ChordType> inversions = new ArrayList<>(intv.size());
		for (int i = 0; i < intv.size(); i++) {
			int root = Util.floorMod(-i, intv.size());
			String invName = name;
			if (i != 0) {
				invName = name + " (inv. " + i + ")";
			}
			inversions.add(DefaultChordType.of(curInt, root, inversions, i, invName, symbol));
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
}
