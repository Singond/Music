package com.github.singond.music.text;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.singond.music.Pitch;
import com.github.singond.music.PitchClass;

public class PitchFormatting {

	@Test
	public void scientific() {
		System.out.println("Formatting with scientific pitch notation:");
		PitchFormat fmt = PitchFormats.SCIENTIFIC;
		testFormat(fmt, Pitch.of(PitchClass.C, 4), "C4");
		testFormat(fmt, Pitch.of(PitchClass.B_DBL_SHARP, 4), "B\uD834\uDD2A4");
		System.out.println();
	}

	@Test
	public void helmholtzGermanAscii() {
		System.out.println("Formatting with Helmholtz pitch notation in ASCII:");
		PitchFormat fmt = PitchFormats.HELMHOLTZ_H_ASCII;
		testFormat(fmt, Pitch.of(PitchClass.C,  0), ",,C");
		testFormat(fmt, Pitch.of(PitchClass.C,  1), ",C");
		testFormat(fmt, Pitch.of(PitchClass.C,  2), "C");
		testFormat(fmt, Pitch.of(PitchClass.C,  3), "c");
		testFormat(fmt, Pitch.of(PitchClass.C,  4), "c'");
		testFormat(fmt, Pitch.of(PitchClass.C,  5), "c''");
		testFormat(fmt, Pitch.of(PitchClass.C,  6), "c'''");
		testFormat(fmt, Pitch.of(PitchClass.C,  7), "c''''");
		testFormat(fmt, Pitch.of(PitchClass.C,  8), "c'''''");
		testFormat(fmt, Pitch.of(PitchClass.C,  9), "c''''''");
		testFormat(fmt, Pitch.of(PitchClass.C, 10), "c'''''''");

		testFormat(fmt, Pitch.of(PitchClass.B_DBL_SHARP, 4), "hx'");
		testFormat(fmt, Pitch.of(PitchClass.E_FLAT, 2), "Eb");
		testFormat(fmt, Pitch.of(PitchClass.E_FLAT, 5), "eb''");
		System.out.println();
	}

	@Test
	public void helmholtzGermanUnicode() {
		System.out.println("Formatting with Helmholtz pitch notation:");
		PitchFormat fmt = PitchFormats.HELMHOLTZ_H_UNICODE;
		testFormat(fmt, Pitch.of(PitchClass.C,  0), "\u0375\u0375C");
		testFormat(fmt, Pitch.of(PitchClass.C,  1), "\u0375C");
		testFormat(fmt, Pitch.of(PitchClass.C,  2), "C");
		testFormat(fmt, Pitch.of(PitchClass.C,  3), "c");
		testFormat(fmt, Pitch.of(PitchClass.C,  4), "c\u2032");
		testFormat(fmt, Pitch.of(PitchClass.C,  5), "c\u2033");
		testFormat(fmt, Pitch.of(PitchClass.C,  6), "c\u2034");
		testFormat(fmt, Pitch.of(PitchClass.C,  7), "c\u2057");
		testFormat(fmt, Pitch.of(PitchClass.C,  8), "c\u2032\u2032\u2032\u2032\u2032");
		testFormat(fmt, Pitch.of(PitchClass.C,  9), "c\u2032\u2032\u2032\u2032\u2032\u2032");
		testFormat(fmt, Pitch.of(PitchClass.C, 10), "c\u2032\u2032\u2032\u2032\u2032\u2032\u2032");
		System.out.println();
	}

	private void testFormat(PitchFormat fmt, Pitch pc, String exp) {
		CharSequence formatted = fmt.format(pc);
		System.out.format("%-5s: %s\n", pc, formatted);
		assertEquals("", exp, formatted.toString());
	}
}
