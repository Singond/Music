package com.github.singond.music;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A basic implementation of {@code KeyType} which stores degrees directly
 * as intervals above tonic.
 *
 * @author Singon
 */
class SimpleKeyType implements KeyType {

	/**
	 * Key degrees as the intervals above the tonic.
	 */
	private final List<Interval> degrees;

	/**
	 * @param degrees degrees of the key as intervals above tonic
	 */
	public SimpleKeyType(List<? extends Interval> degrees) {
		this.degrees = new ArrayList<>(degrees);
	}

	@Override
	public List<Interval> degrees() {
		return Collections.unmodifiableList(degrees);
	}

	@Override
	public Interval degree(int degree) {
		return degrees.get(degree - 1);
	}

}
