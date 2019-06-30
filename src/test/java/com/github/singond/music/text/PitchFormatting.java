package com.github.singond.music.text;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.singond.music.Pitch;
import com.github.singond.music.PitchClass;

public class PitchFormatting {

	private PitchFormat spn = PitchFormats.SCIENTIFIC;

	@Test
	public void scientific() {
		System.out.println("Formatting with scientific pitch notation:");
		testFormat(spn, Pitch.of(PitchClass.C, 4), "C4");
		testFormat(spn, Pitch.of(PitchClass.B_DBL_SHARP, 4), "B\uD834\uDD2A4");
		System.out.println();
	}

	private void testFormat(PitchFormat fmt, Pitch pc, String exp) {
		CharSequence formatted = fmt.format(pc);
		System.out.format("%-5s: %s\n", pc, formatted);
		assertEquals("", exp, formatted.toString());
	}
}
