package com.github.singond.music;

import java.util.Arrays;
import java.util.List;

/**
 * The major key (the Ionian mode in modern Western mode system).
 *
 * @author Singon
 */
public class MajorKey extends AbstractKey implements Key {

	private static final List<Interval> INTERVALS = Arrays.asList(
			SimpleInterval.MAJOR_SECOND,
			SimpleInterval.MAJOR_SECOND,
			SimpleInterval.MINOR_SECOND,
			SimpleInterval.MAJOR_SECOND,
			SimpleInterval.MAJOR_SECOND,
			SimpleInterval.MAJOR_SECOND);
	
	public MajorKey(PitchClass tonic) {
		super(tonic, INTERVALS);
	}

	@Override
	public String toString() {
		return tonic() + " major";
	}
}
