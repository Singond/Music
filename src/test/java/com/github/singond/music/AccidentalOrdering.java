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

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class AccidentalOrdering {

	@Test
	public void simpleComparison() {
		assertTrue(Accidental.DOUBLE_FLAT.compareTo(Accidental.FLAT) < 0);
		assertTrue(Accidental.FLAT.compareTo(Accidental.NATURAL) < 0);
		assertTrue(Accidental.NATURAL.compareTo(Accidental.SHARP) < 0);
		assertTrue(Accidental.SHARP.compareTo(Accidental.DOUBLE_SHARP) < 0);
		
		assertTrue(Accidental.DOUBLE_SHARP.compareTo(Accidental.SHARP) > 0);
		assertTrue(Accidental.SHARP.compareTo(Accidental.NATURAL) > 0);
		assertTrue(Accidental.NATURAL.compareTo(Accidental.FLAT) > 0);
		assertTrue(Accidental.FLAT.compareTo(Accidental.DOUBLE_FLAT) > 0);
	}
	
	@Test
	public void ordering() {
		List<Accidental> acc = Arrays.asList(
				Accidental.DOUBLE_SHARP,
				Accidental.DOUBLE_FLAT,
				Accidental.FLAT,
				Accidental.NATURAL,
				Accidental.SHARP);
		final List<Accidental> expected = Arrays.asList(
				Accidental.DOUBLE_FLAT,
				Accidental.FLAT,
				Accidental.NATURAL,
				Accidental.SHARP,
				Accidental.DOUBLE_SHARP);
		Collections.shuffle(acc);
		System.out.println("Unsorted: " + acc);
		Collections.sort(acc);
		System.out.println("Sorted:   " + acc);
		assertEquals("Accidentals are not being ordered properly", expected, acc);
	}
}
