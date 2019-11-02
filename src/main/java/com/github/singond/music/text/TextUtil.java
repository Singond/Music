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

package com.github.singond.music.text;

class TextUtil {

	/**
	 * Repeats a given text a given number of times.
	 *
	 * @param text the text to be repeated
	 * @param times how many times to repeat {@code text}
	 * @return {@code text} repeated {@code times}.
	 *         If {@code times==0}, returns empty string.
	 *         If {@code times==1}, returns {@code text} argument directly.
	 */
	public static CharSequence repeat(CharSequence text, int times) {
		if (times < 0) {
			throw new IllegalArgumentException(
					"The number of repeats must be non-negative");
		} else if (text == null) {
			throw new NullPointerException(
					"The text to be repeated must not be null");
		}

		if (times == 0) {
			return "";
		} else if (times == 1) {
			return text;
		} else {
    		StringBuilder value = new StringBuilder();
    		for (int i = 0; i < times; i++) {
    			value.append(text);
    		}
    		return value;
		}
	}
}
