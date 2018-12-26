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

import static com.github.singond.music.SimpleInterval.*;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ChordTypes {

	@Test
	public void majorTriads() {
		List<Interval> struct = Arrays.<Interval>asList
				(MAJOR_THIRD, MINOR_THIRD);
		checkAll(0, struct, PERFECT_FIFTH, 3, 0,
		         SmallChordType.MAJOR_TRIAD,
		         SmallChordType.MAJOR_TRIAD.invert(0),
		         SmallChordType.MAJOR_TRIAD_6.invert(0),
		         SmallChordType.MAJOR_TRIAD_64.invert(0),
		         SmallChordType.MAJOR_TRIAD.rootPosition(),
		         SmallChordType.MAJOR_TRIAD_6.rootPosition(),
		         SmallChordType.MAJOR_TRIAD_64.rootPosition());

		struct = Arrays.<Interval>asList(MINOR_THIRD, PERFECT_FOURTH);
		checkAll(2, struct, MINOR_SIXTH, 3, 1,
		         SmallChordType.MAJOR_TRIAD_6,
		         SmallChordType.MAJOR_TRIAD.invert(1),
		         SmallChordType.MAJOR_TRIAD_6.invert(1),
		         SmallChordType.MAJOR_TRIAD_64.invert(1));

		struct = Arrays.<Interval>asList(PERFECT_FOURTH, MAJOR_THIRD);
		checkAll(1, struct, MAJOR_SIXTH, 3, 2,
		         SmallChordType.MAJOR_TRIAD_64,
		         SmallChordType.MAJOR_TRIAD.invert(2),
		         SmallChordType.MAJOR_TRIAD_6.invert(2),
		         SmallChordType.MAJOR_TRIAD_64.invert(2));
	}

	@Test
	public void minorTriads() {
		List<Interval> struct = Arrays.<Interval>asList
				(MINOR_THIRD, MAJOR_THIRD);
		checkAll(0, struct, PERFECT_FIFTH, 3, 0,
		         SmallChordType.MINOR_TRIAD,
		         SmallChordType.MINOR_TRIAD.invert(0),
		         SmallChordType.MINOR_TRIAD_6.invert(0),
		         SmallChordType.MINOR_TRIAD_64.invert(0),
		         SmallChordType.MINOR_TRIAD.rootPosition(),
		         SmallChordType.MINOR_TRIAD_6.rootPosition(),
		         SmallChordType.MINOR_TRIAD_64.rootPosition());

		struct = Arrays.<Interval>asList(MAJOR_THIRD, PERFECT_FOURTH);
		checkAll(2, struct, MAJOR_SIXTH, 3, 1,
		         SmallChordType.MINOR_TRIAD_6,
		         SmallChordType.MINOR_TRIAD.invert(1),
		         SmallChordType.MINOR_TRIAD_6.invert(1),
		         SmallChordType.MINOR_TRIAD_64.invert(1));

		struct = Arrays.<Interval>asList(PERFECT_FOURTH, MINOR_THIRD);
		checkAll(1, struct, MINOR_SIXTH, 3, 2,
		         SmallChordType.MINOR_TRIAD_64,
		         SmallChordType.MINOR_TRIAD.invert(2),
		         SmallChordType.MINOR_TRIAD_6.invert(2),
		         SmallChordType.MINOR_TRIAD_64.invert(2));
	}

	@Test
	public void diminishedTriads() {
		List<Interval> struct = Arrays.<Interval>asList
				(MINOR_THIRD, MINOR_THIRD);
		checkAll(0, struct, DIMINISHED_FIFTH, 3, 0,
		         SmallChordType.DIMINISHED_TRIAD,
		         SmallChordType.DIMINISHED_TRIAD.invert(0),
		         SmallChordType.DIMINISHED_TRIAD_6.invert(0),
		         SmallChordType.DIMINISHED_TRIAD_64.invert(0),
		         SmallChordType.DIMINISHED_TRIAD.rootPosition(),
		         SmallChordType.DIMINISHED_TRIAD_6.rootPosition(),
		         SmallChordType.DIMINISHED_TRIAD_64.rootPosition());

		struct = Arrays.<Interval>asList(MINOR_THIRD, AUGMENTED_FOURTH);
		checkAll(2, struct, MAJOR_SIXTH, 3, 1,
		         SmallChordType.DIMINISHED_TRIAD_6,
		         SmallChordType.DIMINISHED_TRIAD.invert(1),
		         SmallChordType.DIMINISHED_TRIAD_6.invert(1),
		         SmallChordType.DIMINISHED_TRIAD_64.invert(1));

		struct = Arrays.<Interval>asList(AUGMENTED_FOURTH, MINOR_THIRD);
		checkAll(1, struct, MAJOR_SIXTH, 3, 2,
		         SmallChordType.DIMINISHED_TRIAD_64,
		         SmallChordType.DIMINISHED_TRIAD.invert(2),
		         SmallChordType.DIMINISHED_TRIAD_6.invert(2),
		         SmallChordType.DIMINISHED_TRIAD_64.invert(2));
	}

	@Test
	public void augmentedTriads() {
		List<Interval> struct = Arrays.<Interval>asList
				(MAJOR_THIRD, MAJOR_THIRD);
		checkAll(0, struct, AUGMENTED_FIFTH, 3, 0,
		         DefaultChordType.AUGMENTED_TRIAD,
		         DefaultChordType.AUGMENTED_TRIAD.rootPosition());
	}


	@Test
	public void dominantSevenths() {
		List<Interval> struct = Arrays.<Interval>asList
				(MAJOR_THIRD, MINOR_THIRD, MINOR_THIRD);
		checkAll(0, struct, MINOR_SEVENTH, 4, 0,
		         SmallChordType.DOMINANT_7,
		         SmallChordType.DOMINANT_7.invert(0),
		         SmallChordType.DOMINANT_7.rootPosition());

		struct = Arrays.<Interval>asList
				(MINOR_THIRD, MINOR_THIRD, MAJOR_SECOND);
		checkAll(3, struct, MINOR_SIXTH, 4, 1,
		         SmallChordType.DOMINANT_7.invert(1));

		struct = Arrays.<Interval>asList
				(MINOR_THIRD, MAJOR_SECOND, MAJOR_THIRD);
		checkAll(2, struct, MAJOR_SIXTH, 4, 2,
		         SmallChordType.DOMINANT_7.invert(2));

		struct = Arrays.<Interval>asList
				(MAJOR_SECOND, MAJOR_THIRD, MINOR_THIRD);
		checkAll(1, struct, MAJOR_SIXTH, 4, 3,
		         SmallChordType.DOMINANT_7.invert(3));
	}

	/**
	 * @param ch the chord under test
	 * @param root expected value of the root index
	 * @param structure expected interval structure of the chord
	 */
	private void check(ChordType ch, int root, List<Interval> structure,
	                   Interval span, int size, int inversion) {
		assertEquals("Unexpected root index of " + ch + ":", ch.rootIndex(), root);
		assertEquals("Unexpected structure of " + ch + ":", ch.structure(), structure);
		assertEquals("Unexpected span of "  + ch + ":", ch.span(), span);
		assertEquals("Unexpected size of " + ch + ":", ch.size(), size);
		assertEquals("Unexpected inversion of " + ch + ":", ch.inversion(), inversion);
	}

	/**
	 * @param ch the chord under test
	 * @param root expected value of the root index
	 * @param structure expected interval structure of the chord
	 */
	private void checkAll(int root, List<Interval> structure, Interval span,
	                      int size, int inversion, ChordType... chs) {
		for (ChordType ch : chs) {
			check(ch, root, structure, span, size, inversion);
		}
	}

}
