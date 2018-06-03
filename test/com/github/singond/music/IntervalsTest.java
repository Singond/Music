package com.github.singond.music;

import static org.junit.Assert.assertEquals;

import static com.github.singond.music.SimpleInterval.*;

import org.junit.Test;

public class IntervalsTest {

	@Test
	public void simpleIntervalSearch() {
		simpleIntervalSearch(0, -1, null);
		simpleIntervalSearch(0, 0, SimpleInterval.UNISON);
		simpleIntervalSearch(2, 3, SimpleInterval.MINOR_THIRD);
		simpleIntervalSearch(2, 4, SimpleInterval.MAJOR_THIRD);
		simpleIntervalSearch(7, 12, SimpleInterval.PERFECT_OCTAVE);
	}
	
	/**
	 * @param d number of degrees
	 * @param s width in semitones
	 * @param exp expected value
	 */
	private void simpleIntervalSearch(int d, int s, SimpleInterval exp) {
		SimpleInterval actual = SimpleInterval.valueOf(d, s);
		assertEquals("Searching interval failed.", exp, actual);
	}
	
	@Test
	public void simpleSums() {
		sumTest(PERFECT_FIFTH, MAJOR_THIRD, MINOR_THIRD);
		sumTest(PERFECT_OCTAVE, PERFECT_FOURTH, PERFECT_FIFTH);
		sumTest(PERFECT_OCTAVE, MAJOR_THIRD, MAJOR_THIRD, DIMINISHED_FOURTH);
		sumTest(9, 16, PERFECT_OCTAVE, MAJOR_THIRD);
		sumTest(10, 16, PERFECT_OCTAVE, MAJOR_THIRD, DIMINISHED_SECOND);
	}
	
	private void sumTest(int expDeg, int expSteps, Interval a, Interval b) {
		Interval sum = Intervals.sum(a, b);
		assertEquals(expDeg, sum.degrees());
		assertEquals(expSteps, sum.semitones());
	}
	
	private void sumTest(int expDeg, int expSteps, Interval a, Interval b,
	                     Interval... addends) {
		Interval sum = Intervals.sum(a, b, addends);
		assertEquals(expDeg, sum.degrees());
		assertEquals(expSteps, sum.semitones());
	}
	
	private void sumTest(Interval expected, Interval a, Interval b) {
		Interval sum = Intervals.sum(a, b);
		assertEquals(expected, sum);
	}
	
	private void sumTest(Interval expected, Interval a, Interval b,
	                     Interval... addends) {
		Interval sum = Intervals.sum(a, b, addends);
		assertEquals(expected, sum);
	}
}
