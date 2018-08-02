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

public class PitchClassOrdering {
	
	private static final List<PitchClass> pitchClasses
			= Collections.unmodifiableList(PitchClass.commonPitchClasses());

	private Random random = new Random();
	
	@Test
	public void simpleComparison() {
		assertTrue(C.compareTo(C) == 0);
		assertTrue(C.compareTo(D) < 0);
		assertTrue(C.compareTo(C_SHARP) < 0);
		assertTrue(C.compareTo(D_DBL_FLAT) < 0);
		assertTrue(C_FLAT.compareTo(B_SHARP) < 0);
		
		assertTrue(G.compareTo(F_SHARP) > 0);
		assertTrue(G.compareTo(F_DBL_SHARP) > 0);
		assertTrue(G.compareTo(A_DBL_FLAT) < 0);
//		separator();
	}
	
	@Test(expected = NullPointerException.class)
	public void comparisonWithNull() {
		C.compareTo(null);
	}
	
	@Test
	public void orderingWithoutEnharmonics() {
		shuffleAndSort(C_DBL_FLAT, C_FLAT, C, C_SHARP, C_DBL_SHARP,
		               D_SHARP, E, B);
	}
	
	@Test
	public void orderingWithEnharmonics() {
		shuffleAndSort(C_DBL_FLAT, C_FLAT, C, D_DBL_FLAT, C_SHARP, D_FLAT,
		               C_DBL_SHARP, D, E_DBL_FLAT,
		               D_SHARP, E_FLAT, F_DBL_FLAT, D_DBL_SHARP, E, F_FLAT);
	}
	
	/**
	 * @param pitchClasses the expected order
	 */
	private void shuffleAndSort(PitchClass... pitchClasses) {
		List<PitchClass> expected = Arrays.asList(pitchClasses);
		List<PitchClass> random = new ArrayList<>(expected);
		System.out.println("Expected: " + expected);
		Collections.shuffle(random);
		System.out.println("Shuffled: " + random);
		Collections.sort(random);
		System.out.println("Sorted:   " + random);
		separator();
		assertEquals("Unexpected order of pitch classes after sorting",
		             expected, random);
	}
	
//	@Test
	public void consistency1() {
		consistency(B_SHARP, C);
		consistency(C, D_DBL_FLAT);
	}
	
	@Test
	public void consistencyRandom() {
		for (int i = 0; i < 100000; i++) {
			consistency(randomPitchClass(), randomPitchClass());
		}
	}
	
	private void consistency(PitchClass p1, PitchClass p2) {
		if (p1.compareTo(p2) == 0) {
			assertTrue("Comparison inconsistent with equals", p1.equals(p2));
		} else {
			assertTrue(Integer.signum(p1.compareTo(p2))
			           == -Integer.signum(p2.compareTo(p1)));
			assertFalse("Comparison inconsistent with equals", p1.equals(p2));
		}
		
		Comparator<PitchClass> cmp = PitchClass.enharmonicComparator();
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
	
	private PitchClass randomPitchClass() {
		return pitchClasses.get(random.nextInt(pitchClasses.size()));
	}
	
	private final void separator() {
		System.out.println("----------------");
	}
}
