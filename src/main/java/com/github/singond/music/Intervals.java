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

import java.util.List;

public final class Intervals {

	private Intervals() {
		throw new UnsupportedOperationException("This class cannot be instantiated");
	}

	/**
	 * Sums the given intervals.
	 * Returns an interval whose number of diatonic degrees is the sum
	 * of the diatonic degrees of all summands and whose width in
	 * semitones is the sum of semitone widths of all summands.
	 *
	 * @param a interval to be added
	 * @param b interval to be added
	 * @return the sum of {@code a} and {@code b}
	 * @throws IllegalArgumentException if one of the arguments is null
	 */
	public static Interval sum(Interval a, Interval b) {
		if (a == null || b == null) {
			throw new IllegalArgumentException("One of the summands is null");
		}
		int degrees = a.degrees() + b.degrees();
		int semitones = a.semitones() + b.semitones();
		return valueOf(degrees, semitones);
	}

	/**
	 * Sums the given intervals.
	 * Returns an interval whose number of diatonic degrees is the sum
	 * of the diatonic degrees of all summands and whose width in
	 * semitones is the sum of semitone widths of all summands.
	 *
	 * @param a interval to be summed
	 * @param b interval to be summed
	 * @param intervals intervals to be summed (summands)
	 * @return the sum of {@code intervals}
	 * @throws IllegalArgumentException if one of the arguments is null
	 */
	public static Interval sum(Interval a, Interval b, Interval... intervals) {
		if (a == null || b == null) {
			throw new IllegalArgumentException("One of the summands is null");
		}
		int degrees = a.degrees() + b.degrees();
		int semitones = a.semitones() + b.semitones();
		for (Interval i : intervals) {
			if (i == null) {
				throw new IllegalArgumentException("One of the summands is null");
			}
			degrees += i.degrees();
			semitones += i.semitones();
		}
		return valueOf(degrees, semitones);
	}

	/**
	 * Sums the given intervals.
	 * Returns an interval whose number of diatonic degrees is the sum
	 * of the diatonic degrees of all summands and whose width in
	 * semitones is the sum of semitone widths of all summands.
	 *
	 * @param intervals intervals to be summed (addends)
	 * @return the sum of {@code intervals}
	 * @throws IllegalArgumentException if one of the arguments is null
	 */
	public static Interval sum(List<Interval> intervals) {
		if (intervals == null) {
			throw new NullPointerException("The list of intervals is null");
		}
		int degrees = 0;
		int semitones = 0;
		for (Interval i : intervals) {
			if (i == null) {
				throw new IllegalArgumentException("One of the summands is null");
			}
			degrees += i.degrees();
			semitones += i.semitones();
		}
		return valueOf(degrees, semitones);
	}

	private static Interval valueOf(int degrees, int semitones) {
		SimpleInterval simple = SimpleInterval.valueOf(degrees, semitones);
		if (simple != null) {
			return simple;
		} else {
			return new UniversalInterval(degrees, semitones);
		}
	}

	// TODO Use CompoundInterval?
	private static class UniversalInterval extends AbstractInterval {
		private final int degrees;
		private final int semitones;

		UniversalInterval(int degrees, int semitones) {
			this.degrees = degrees;
			this.semitones = semitones;
		}

		@Override
		public int degrees() {
			return degrees;
		}

		@Override
		public int semitones() {
			return semitones;
		}

		@Override
		public String toString() {
			return degrees + " degrees, " + semitones + " semitones";
		}
	}
}
