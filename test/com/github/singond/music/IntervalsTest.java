package com.github.singond.music;

import static com.github.singond.music.SimpleInterval.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IntervalsTest {

	@Test
	public void listSimple() {
		System.out.println("Simple intervals:");
		for (SimpleInterval i : SimpleInterval.values()) {
			printInterval(i);
		}
	}

	private void printInterval(SimpleInterval i) {
		System.out.format("%-20s %-3s  %2d degrees  %2d semitones%n", i.longName(),
				i.symbol(), i.degrees(), i.semitones());
	}

	private void printInterval(CompoundInterval i) {
		System.out.format("%-20s %-3s  %2d degrees  %2d semitones%n", i.longName(),
				i.symbol(), i.degrees(), i.semitones());
	}

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

	@Test
	public void compoundCreation() {
		createCompound(MINOR_THIRD, 1, 9, 15);
		createCompound(PERFECT_FIFTH, 1, 11, 19);
		createCompound(UNISON, 2, 14, 24);
	}

	private void createCompound(SimpleInterval simple, int octaves,
	                            int degrees, int semitones) {
		CompoundInterval fromSimple = CompoundInterval.of(simple, octaves);
		assertEquals("Wrong number of degrees in compound interval ",
				degrees, fromSimple.degrees());
		assertEquals("Wrong number of semitones in compound interval ",
				semitones, fromSimple.semitones());
		Interval fromNumbers = CompoundInterval.of(degrees, semitones);
		assertEquals("Wrong interval created by CompoundInterval.of(int, int)",
				fromSimple, fromNumbers);
		printInterval(fromSimple);
	}
}
