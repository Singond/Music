package com.github.singond.music;

import static org.junit.Assert.*;
import static com.github.singond.music.PitchClass.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class PitchOrdering {
	
	private static final List<PitchClass> pitchClasses
			= Collections.unmodifiableList(PitchClass.commonPitchClasses());

	private Random random = new Random();
	
	@Test
	public void simpleComparison() {
		assertTrue(Pitch.of(C, 4).compareTo(Pitch.of(D, 4)) < 0);
//		separator();
	}
	
	@Test(expected = NullPointerException.class)
	public void comparisonWithNull() {
		Pitch.of(C, 4).compareTo(null);
	}
	
	@Test
	public void orderingWithoutEnharmonics() {
		shuffleAndSort(Pitch.of(A_DBL_FLAT, 3),
		               Pitch.of(B_FLAT, 3),
		               Pitch.of(C_FLAT, 4),
		               Pitch.of(C, 4),
		               Pitch.of(D_DBL_FLAT, 4),
		               Pitch.of(C_SHARP, 4),
		               Pitch.of(D, 4));
	}
	
	@Test
	public void orderingWithEnharmonics() {
		shuffleAndSort(Pitch.of(A_SHARP, 3),
		               Pitch.of(B_FLAT, 3),
		               Pitch.of(C_DBL_FLAT, 4),
		               Pitch.of(A_DBL_SHARP, 3),
		               Pitch.of(B, 3),
		               Pitch.of(C_FLAT, 4),
		               Pitch.of(C, 4),
		               Pitch.of(D_DBL_FLAT, 4),
		               Pitch.of(C_SHARP, 4),
		               Pitch.of(D_FLAT, 4),
		               Pitch.of(D, 4));
	}
	
	/**
	 * @param pitches the expected order
	 */
	private void shuffleAndSort(Pitch... pitches) {
		List<Pitch> expected = Arrays.asList(pitches);
		List<Pitch> random = new ArrayList<>(expected);
		System.out.println("Expected: " + expected);
		Collections.shuffle(random);
		System.out.println("Shuffled: " + random);
		Collections.sort(random);
		System.out.println("Sorted:   " + random);
		separator();
		assertEquals("Unexpected order of pitches after sorting", expected, random);
	}
	
	@Test
	public void consistency1() {
		consistency(Pitch.of(B_SHARP, 3), Pitch.of(C, 4));
		consistency(Pitch.of(C, 4), Pitch.of(D_DBL_FLAT, 4));
	}
	
	@Test
	public void consistencyRandom() {
		for (int i = 0; i < 100000; i++) {
			consistency(randomPitch(), randomPitch());
		}
	}
	
	private void consistency(Pitch p1, Pitch p2) {
		if (p1.compareTo(p2) == 0) {
			assertTrue("Comparison inconsistent with equals", p1.equals(p2));
		} else {
			assertTrue(Integer.signum(p1.compareTo(p2))
			           == -Integer.signum(p2.compareTo(p1)));
			assertFalse("Comparison inconsistent with equals", p1.equals(p2));
		}
		
		Comparator<Pitch> cmp = Pitch.enharmonicComparator();
		if (cmp.compare(p1, p2) == 0) {
			assertTrue("Comparison inconsistent with isEnharmonicWith",
			           p1.isEnharmonicWith(p2));
		} else {
			assertTrue(Integer.signum(cmp.compare(p1, p2))
			           == -Integer.signum(cmp.compare(p2, p1)));
			assertFalse("Comparison inconsistent with isEnharmonicWith",
			            p1.equals(p2));
		}
	}
	
	private Pitch randomPitch() {
		PitchClass pc = pitchClasses.get(random.nextInt(pitchClasses.size()));
		int octave = random.nextInt(10);
		return Pitch.of(pc, octave);
	}
	
	private final void separator() {
		System.out.println("----------------");
	}
}
