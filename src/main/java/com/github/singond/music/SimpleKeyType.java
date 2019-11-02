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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A basic implementation of {@code KeyType} which stores degrees directly
 * as intervals above tonic.
 *
 * @author Singon
 */
abstract class SimpleKeyType implements KeyType {

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
