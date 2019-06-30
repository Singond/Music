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
