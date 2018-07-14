package com.github.singond.music;

import java.util.Comparator;

/**
 * A skeletal implementation of the {@code Interval} interface.
 *
 * @author Singon
 */
public abstract class AbstractInterval implements Interval {

	private static final Comparator<Interval> STRICT_COMPARATOR
			= new StrictComparator();
	private static final Comparator<Interval> ENHARMONIC_COMPARATOR
			= new EnharmonicComparator();

	@Override
	public int intervalNumber() {
		return degrees() + 1;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param o {@inheritDoc}
	 * @return  {@inheritDoc}
	 * @throws  NullPointerException {@inheritDoc}
	 */
	@Override
	public int compareTo(Interval o) {
		return STRICT_COMPARATOR.compare(this, o);
	}

	@Override
	public boolean isEnharmonicWith(Interval other) {
		return ENHARMONIC_COMPARATOR.compare(this, other) == 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Interval)) {
			return false;
		}
		Interval other = (Interval) obj;
		if (degrees() != other.degrees()) {
			return false;
		}
		if (semitones() != other.semitones()) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + degrees();
		result = prime * result + semitones();
		return result;
	}

	/**
	 * A comparator which compares intervals based on both their width
	 * and the number of diatonic degrees they span.
	 * This class first orders the intervals from the smallest to largest
	 * and then further orders enharmonic intervals in the order given
	 * by their number of diatonic degrees, so that for example augmented
	 * third is sorted before perfect fourth.
	 * <p>
	 * This comparator does not permit null arguments.
	 */
	private static class StrictComparator implements Comparator<Interval> {

		@Override
		public int compare(Interval i1, Interval i2) {
			if (i1 == null || i2 == null) {
				throw new NullPointerException("One of the intervals is null");
			}
			int comparison = Integer.compare(i1.semitones(), i2.semitones());
			if (comparison != 0) return comparison;
			return Integer.compare(i1.degrees(), i2.degrees());
		}
	}

	/**
	 * A comparator which compares intervals based on their width only,
	 * treating enharmonic intervals as equal.
	 * For example, comparing augmented third and perfect fourth will
	 * return {@code 0}.
	 * <p>
	 * This comparator does not permit null arguments.
	 */
	private static class EnharmonicComparator implements Comparator<Interval> {

		@Override
		public int compare(Interval i1, Interval i2) {
			if (i1 == null || i2 == null) {
				throw new NullPointerException("One of the intervals is null");
			}
			return Integer.compare(i1.semitones(), i2.semitones());
		}
	}
}
