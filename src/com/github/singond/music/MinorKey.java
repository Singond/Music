package com.github.singond.music;

import java.util.Arrays;
import java.util.List;

/**
 * The minor key (the Aeolian mode in modern Western mode system).
 *
 * @author Singon
 */
public class MinorKey extends AbstractKey implements Key {

	private static final List<Interval> INTERVALS = Arrays.asList(
			SimpleInterval.MAJOR_SECOND,
			SimpleInterval.MINOR_SECOND,
			SimpleInterval.MAJOR_SECOND,
			SimpleInterval.MAJOR_SECOND,
			SimpleInterval.MINOR_SECOND,
			SimpleInterval.MAJOR_SECOND);
	
	public MinorKey(PitchClass tonic) {
		super(tonic, INTERVALS);
	}

	@Override
	public String toString() {
		return tonic() + " minor";
	}
}
