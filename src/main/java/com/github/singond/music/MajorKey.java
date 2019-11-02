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
 * The common major key; that is a key with semitones between the 3rd and 4th
 * and the 7th and 1st degree.
 * This corresponds to the Ionian mode in modern Western mode system.
 *
 * @author Singon
 */
class MajorKey extends SimpleKey implements Key {

	/** The interval structure of this key (non-cumulative). */
	private static final List<Interval> INTERVALS = Arrays.<Interval>asList(
			SimpleInterval.MAJOR_SECOND,
			SimpleInterval.MAJOR_THIRD,
			SimpleInterval.PERFECT_FOURTH,
			SimpleInterval.PERFECT_FIFTH,
			SimpleInterval.MAJOR_SIXTH,
			SimpleInterval.MAJOR_SEVENTH);

	/** The major key type. */
	public static final MajorKeyType TYPE = new MajorKeyType();

	public static final MajorKey C_FLAT  = new MajorKey(PitchClass.C_FLAT);
	public static final MajorKey G_FLAT  = new MajorKey(PitchClass.G_FLAT);
	public static final MajorKey D_FLAT  = new MajorKey(PitchClass.D_FLAT);
	public static final MajorKey A_FLAT  = new MajorKey(PitchClass.A_FLAT);
	public static final MajorKey E_FLAT  = new MajorKey(PitchClass.E_FLAT);
	public static final MajorKey B_FLAT  = new MajorKey(PitchClass.B_FLAT);
	public static final MajorKey F       = new MajorKey(PitchClass.F);
	public static final MajorKey C       = new MajorKey(PitchClass.C);
	public static final MajorKey G       = new MajorKey(PitchClass.G);
	public static final MajorKey D       = new MajorKey(PitchClass.D);
	public static final MajorKey A       = new MajorKey(PitchClass.A);
	public static final MajorKey E       = new MajorKey(PitchClass.E);
	public static final MajorKey B       = new MajorKey(PitchClass.B);
	public static final MajorKey F_SHARP = new MajorKey(PitchClass.F_SHARP);
	public static final MajorKey C_SHARP = new MajorKey(PitchClass.C_SHARP);

	private static final Map<PitchClass, MajorKey> commonKeys;
	static {
		commonKeys = new HashMap<>();
		commonKeys.put(PitchClass.C_FLAT,  MajorKey.C_FLAT);
		commonKeys.put(PitchClass.G_FLAT,  MajorKey.G_FLAT);
		commonKeys.put(PitchClass.D_FLAT,  MajorKey.D_FLAT);
		commonKeys.put(PitchClass.A_FLAT,  MajorKey.A_FLAT);
		commonKeys.put(PitchClass.E_FLAT,  MajorKey.E_FLAT);
		commonKeys.put(PitchClass.B_FLAT,  MajorKey.B_FLAT);
		commonKeys.put(PitchClass.F,       MajorKey.F);
		commonKeys.put(PitchClass.C,       MajorKey.C);
		commonKeys.put(PitchClass.G,       MajorKey.G);
		commonKeys.put(PitchClass.D,       MajorKey.D);
		commonKeys.put(PitchClass.A,       MajorKey.A);
		commonKeys.put(PitchClass.E,       MajorKey.E);
		commonKeys.put(PitchClass.B,       MajorKey.B);
		commonKeys.put(PitchClass.F_SHARP, MajorKey.F_SHARP);
		commonKeys.put(PitchClass.C_SHARP, MajorKey.C_SHARP);
	}

	private MajorKey(PitchClass tonic) {
		super(tonic, INTERVALS);
	}

	/**
	 * Returns a major key with the given tonic.
	 *
	 * @param tonic the tonic of the key
	 * @return the major key in {@code tonic}
	 */
	public static MajorKey in(PitchClass tonic) {
		if (commonKeys.containsKey(tonic))
			return commonKeys.get(tonic);
		else return new MajorKey(tonic);
	}

	@Override
	public KeyType type() {
		return TYPE;
	}

	@Override
	public String toString() {
		return tonic() + " major";
	}

	/**
	 * Major key type.
	 */
	private static class MajorKeyType extends SimpleKeyType {

		MajorKeyType() {
			super(INTERVALS);
		}

		@Override
		public Key in(PitchClass tonic) {
			return MajorKey.in(tonic);
		}

	}
}
