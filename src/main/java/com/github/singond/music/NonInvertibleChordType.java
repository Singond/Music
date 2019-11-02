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
 * An implementation of the {@code ChordType} interface applicable to chords
 * with no inversions (apart from root position) defined.
 *
 * @author Singon
 */
class NonInvertibleChordType implements ChordType {

	private final List<Interval> structure;

	private transient Interval span;

	public static final ChordType AUGMENTED_TRIAD = new NonInvertibleChordType
			(Arrays.<Interval>asList(MAJOR_THIRD, MAJOR_THIRD));

	public static final ChordType DIMINISHED_7 = new NonInvertibleChordType
			(Arrays.<Interval>asList(MINOR_THIRD, MINOR_THIRD, MINOR_THIRD));

	private NonInvertibleChordType(List<Interval> structure) {
		if (structure == null) {
			throw new NullPointerException("The interval structure is null");
		} else if (structure.isEmpty()) {
			throw new IllegalArgumentException("The interval structure is empty");
		}

		this.structure = new ArrayList<>(structure);
		this.span = calculateSpan(structure);
	}

	@Override
	public List<Interval> structure() {
		return Collections.unmodifiableList(structure);
	}

	@Override
	public int rootIndex() {
		return 0;
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
		return false;
	}

	@Override
	public ChordType invert(int n) {
		throw new UnsupportedOperationException("This chord type has no inversions");
	}

	@Override
	public int inversion() {
		return 0;
	}

	@Override
	public ChordType rootPosition() {
		return this;
	}

	@Override
	public String toString() {
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
}
