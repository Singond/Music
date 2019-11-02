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

import java.util.List;

/**
 * A type of a musical key (like major or minor),
 * that is the mutual relationships of all notes in a musical key without
 * any reference given.
 * The degrees of the key are refered to by the interval they make above
 * the first degree (the tonic).
 *
 * @author Singon
 */
public interface KeyType {

	/**
	 * Returns all degrees in this key as intervals above the tonic.
	 * The degrees are sorted in ascending order.
	 *
	 * @return an ascending scale of degrees in this key, represented by
	 *         the intervals they make with the tonic below
	 */
	List<Interval> degrees();

	/**
	 * Returns the given degree of this key as an interval above the tonic.
	 * This is equal to calling {@code degrees().get(degree - 1)}.
	 *
	 * @param degree the one-based index of the degree
	 * @return the degree at (one-based) index {@code degree}
	 * @throws IndexOutOfBoundsException if the number does not lie
	 *         between 1 and the number of degrees in the key (inclusive)
	 */
	Interval degree(int degree);

	/**
	 * Returns a musical key of this key type and with the given tonic.
	 *
	 * @param tonic the tonic of the key
	 * @return this type of key in {@code tonic}
	 */
	Key in(PitchClass tonic);
}
