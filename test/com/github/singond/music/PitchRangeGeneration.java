package com.github.singond.music;

import static org.junit.Assert.*;
import static com.github.singond.music.PitchClass.*;

import java.util.Arrays;
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

	private void check(Set<PitchClass> pcs, Pitch start, Pitch end, Pitch... expected) {
		List<Pitch> generated = Pitches.range(pcs, start, end);
		List<Pitch> exp = Arrays.asList(expected);
		System.out.println("expected:  " + exp);
		System.out.println("generated: " + generated);
		assertEquals("Wrong pitch sequence", exp, generated);
		separator();
	}
	
	private static void separator() {
		System.out.println("----------------");
	}
}
