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
