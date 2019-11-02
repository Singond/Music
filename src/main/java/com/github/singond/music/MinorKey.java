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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The common minor key; that is a key with semitones between the 2nd and 3rd
 * and the 5th and 6th degree.
 * This corresponds to the Aeolian mode in modern Western mode system.
 *
 * @author Singon
 */
class MinorKey extends SimpleKey implements Key {

	/** The interval structure of this key (non-cumulative). */
	private static final List<Interval> INTERVALS = Arrays.<Interval>asList(
			SimpleInterval.MAJOR_SECOND,
			SimpleInterval.MINOR_THIRD,
			SimpleInterval.PERFECT_FOURTH,
			SimpleInterval.PERFECT_FIFTH,
			SimpleInterval.MINOR_SIXTH,
			SimpleInterval.MINOR_SEVENTH);

	/** The minor key type. */
	public static final MinorKeyType TYPE = new MinorKeyType();

	public static final MinorKey A_FLAT  = new MinorKey(PitchClass.A_FLAT);
	public static final MinorKey E_FLAT  = new MinorKey(PitchClass.E_FLAT);
	public static final MinorKey B_FLAT  = new MinorKey(PitchClass.B_FLAT);
	public static final MinorKey F       = new MinorKey(PitchClass.F);
	public static final MinorKey C       = new MinorKey(PitchClass.C);
	public static final MinorKey G       = new MinorKey(PitchClass.G);
	public static final MinorKey D       = new MinorKey(PitchClass.D);
	public static final MinorKey A       = new MinorKey(PitchClass.A);
	public static final MinorKey E       = new MinorKey(PitchClass.E);
	public static final MinorKey B       = new MinorKey(PitchClass.B);
	public static final MinorKey F_SHARP = new MinorKey(PitchClass.F_SHARP);
	public static final MinorKey C_SHARP = new MinorKey(PitchClass.C_SHARP);
	public static final MinorKey G_SHARP = new MinorKey(PitchClass.G_SHARP);
	public static final MinorKey D_SHARP = new MinorKey(PitchClass.D_SHARP);
	public static final MinorKey A_SHARP = new MinorKey(PitchClass.A_SHARP);

	private static final Map<PitchClass, MinorKey> commonKeys;
	static {
		commonKeys = new HashMap<>();
		commonKeys.put(PitchClass.A_FLAT,  MinorKey.A_FLAT);
		commonKeys.put(PitchClass.E_FLAT,  MinorKey.E_FLAT);
		commonKeys.put(PitchClass.B_FLAT,  MinorKey.B_FLAT);
		commonKeys.put(PitchClass.F,       MinorKey.F);
		commonKeys.put(PitchClass.C,       MinorKey.C);
		commonKeys.put(PitchClass.G,       MinorKey.G);
		commonKeys.put(PitchClass.D,       MinorKey.D);
		commonKeys.put(PitchClass.A,       MinorKey.A);
		commonKeys.put(PitchClass.E,       MinorKey.E);
		commonKeys.put(PitchClass.B,       MinorKey.B);
		commonKeys.put(PitchClass.F_SHARP, MinorKey.F_SHARP);
		commonKeys.put(PitchClass.C_SHARP, MinorKey.C_SHARP);
		commonKeys.put(PitchClass.G_SHARP, MinorKey.G_SHARP);
		commonKeys.put(PitchClass.D_SHARP, MinorKey.D_SHARP);
		commonKeys.put(PitchClass.A_SHARP, MinorKey.A_SHARP);
	}

	private MinorKey(PitchClass tonic) {
		super(tonic, INTERVALS);
	}

	/**
	 * Returns a minor key with the given tonic.
	 *
	 * @param tonic the tonic of the key
	 * @return the minor key in {@code tonic}
	 */
	public static MinorKey in(PitchClass tonic) {
		if (commonKeys.containsKey(tonic))
			return commonKeys.get(tonic);
		else return new MinorKey(tonic);
	}

	@Override
	public KeyType type() {
		return TYPE;
	}

	@Override
	public String toString() {
		return tonic() + " minor";
	}

	/**
	 * Minor key type.
	 */
	private static class MinorKeyType extends SimpleKeyType {

		MinorKeyType() {
			super(INTERVALS);
		}

		@Override
		public Key in(PitchClass tonic) {
			return MinorKey.in(tonic);
		}

	}
}
