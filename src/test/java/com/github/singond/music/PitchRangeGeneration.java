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
import static com.github.singond.music.PitchClass.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class PitchRangeGeneration {

	@Test
	public void simpleScale1() {
		check(new HashSet<>(Arrays.asList(D, E, F_SHARP, G, A, B, C_SHARP)),
		      Pitch.of(A, 3), Pitch.of(G, 5),
		      Pitch.of(A, 3),
		      Pitch.of(B, 3),
		      Pitch.of(C_SHARP, 4),
		      Pitch.of(D, 4),
		      Pitch.of(E, 4),
		      Pitch.of(F_SHARP, 4),
		      Pitch.of(G, 4),
		      Pitch.of(A, 4),
		      Pitch.of(B, 4),
		      Pitch.of(C_SHARP, 5),
		      Pitch.of(D, 5),
		      Pitch.of(E, 5),
		      Pitch.of(F_SHARP, 5),
		      Pitch.of(G, 5)
		);
	}

	@Test
	public void scaleWithEnharmonics1() {
		check(new HashSet<>(Arrays.asList(D, E_SHARP, F, G, A_SHARP, B_FLAT, C)),
		      Pitch.of(A, 3), Pitch.of(G, 5),
		      Pitch.of(A_SHARP, 3),
		      Pitch.of(B_FLAT, 3),
		      Pitch.of(C, 4),
		      Pitch.of(D, 4),
		      Pitch.of(E_SHARP, 4),
		      Pitch.of(F, 4),
		      Pitch.of(G, 4),
		      Pitch.of(A_SHARP, 4),
		      Pitch.of(B_FLAT, 4),
		      Pitch.of(C, 5),
		      Pitch.of(D, 5),
		      Pitch.of(E_SHARP, 5),
		      Pitch.of(F, 5),
		      Pitch.of(G, 5)
		);
	}

	@Test
	public void scaleWithEnharmonics2() {
		check(new HashSet<>(Arrays.asList(D, E_SHARP, F_FLAT, G, A_SHARP, B_DBL_FLAT, C)),
		      Pitch.of(D, 4), Pitch.of(D, 5),
		      Pitch.of(D, 4),
		      Pitch.of(F_FLAT, 4),
		      Pitch.of(E_SHARP, 4),
		      Pitch.of(G, 4),
		      Pitch.of(B_DBL_FLAT, 4),
		      Pitch.of(A_SHARP, 4),
		      Pitch.of(C, 5),
		      Pitch.of(D, 5)
		);
	}

	@Test
	public void reverse() {
		Set<PitchClass> pcs = new HashSet<>();
		pcs.add(C); pcs.add(D); pcs.add(E); pcs.add(F);
		pcs.add(G); pcs.add(A); pcs.add(B);
		List<Pitch> gen = Pitches.allBetween(Pitch.of(G, 5), Pitch.of(D, 4), pcs);
		List<Pitch> exp = Arrays.asList(Pitch.of(G, 5), Pitch.of(F, 5),
				Pitch.of(E, 5), Pitch.of(D, 5), Pitch.of(C, 5),
				Pitch.of(B, 4), Pitch.of(A, 4), Pitch.of(G, 4),
				Pitch.of(F, 4), Pitch.of(E, 4), Pitch.of(D, 4));
		assertEquals("Wrong reverse scale", exp, gen);
	}

	private void check(Set<PitchClass> pcs, Pitch start, Pitch end, Pitch... expected) {
		List<Pitch> generated = Pitches.allBetween(start, end, pcs);
		List<Pitch> exp = Arrays.asList(expected);
		System.out.println("expected:  " + exp);
		System.out.println("generated: " + generated);
		assertEquals("Wrong pitch sequence", exp, generated);

		// Reverse
		generated = Pitches.allBetween(end, start, pcs);
		Collections.reverse(exp);
		System.out.println("expected reverse:  " + exp);
		System.out.println("generated reverse: " + generated);
		assertEquals("Wrong pitch sequence", exp, generated);
		separator();
	}

	private static void separator() {
		System.out.println("----------------");
	}
}
