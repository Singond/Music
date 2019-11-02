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

final class NamingUtil {

	private NamingUtil() {
		throw new UnsupportedOperationException("Non-instantiable class");
	}

	public static String ordinal(int i) {
		int last = i % 100;
		if (last == 11 || last == 12 || last == 13) {
			return Integer.toString(i) + "th";
		}
		last = i % 10;
		switch (last) {
			case 1:
				return Integer.toString(i) + "st";
			case 2:
				return Integer.toString(i) + "nd";
			case 3:
				return Integer.toString(i) + "rd";
			default:
				return Integer.toString(i) + "th";
		}
	}
}
