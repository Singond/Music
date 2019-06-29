package com.github.singond.music;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PitchClassFormatting {

	private PitchClassFormat symbAscii = new SymbolicPitchClassFormat(
			SymbolicPitchClassFormat.AccidentalSymbols.ASCII);

	@Test
	public void symbAscii() {
		testFormat(symbAscii, PitchClass.C_DBL_FLAT, "Cbb");
		testFormat(symbAscii, PitchClass.D_DBL_FLAT, "Dbb");
		testFormat(symbAscii, PitchClass.E_DBL_FLAT, "Ebb");
		testFormat(symbAscii, PitchClass.F_DBL_FLAT, "Fbb");
		testFormat(symbAscii, PitchClass.G_DBL_FLAT, "Gbb");
		testFormat(symbAscii, PitchClass.A_DBL_FLAT, "Abb");
		testFormat(symbAscii, PitchClass.B_DBL_FLAT, "Bbb");

		testFormat(symbAscii, PitchClass.C_FLAT, "Cb");
		testFormat(symbAscii, PitchClass.D_FLAT, "Db");
		testFormat(symbAscii, PitchClass.E_FLAT, "Eb");
		testFormat(symbAscii, PitchClass.F_FLAT, "Fb");
		testFormat(symbAscii, PitchClass.G_FLAT, "Gb");
		testFormat(symbAscii, PitchClass.A_FLAT, "Ab");
		testFormat(symbAscii, PitchClass.B_FLAT, "Bb");

		testFormat(symbAscii, PitchClass.C, "C");
		testFormat(symbAscii, PitchClass.D, "D");
		testFormat(symbAscii, PitchClass.E, "E");
		testFormat(symbAscii, PitchClass.F, "F");
		testFormat(symbAscii, PitchClass.G, "G");
		testFormat(symbAscii, PitchClass.A, "A");
		testFormat(symbAscii, PitchClass.B, "B");

		testFormat(symbAscii, PitchClass.C_SHARP, "C#");
		testFormat(symbAscii, PitchClass.D_SHARP, "D#");
		testFormat(symbAscii, PitchClass.E_SHARP, "E#");
		testFormat(symbAscii, PitchClass.F_SHARP, "F#");
		testFormat(symbAscii, PitchClass.G_SHARP, "G#");
		testFormat(symbAscii, PitchClass.A_SHARP, "A#");
		testFormat(symbAscii, PitchClass.B_SHARP, "B#");

		testFormat(symbAscii, PitchClass.C_DBL_SHARP, "Cx");
		testFormat(symbAscii, PitchClass.D_DBL_SHARP, "Dx");
		testFormat(symbAscii, PitchClass.E_DBL_SHARP, "Ex");
		testFormat(symbAscii, PitchClass.F_DBL_SHARP, "Fx");
		testFormat(symbAscii, PitchClass.G_DBL_SHARP, "Gx");
		testFormat(symbAscii, PitchClass.A_DBL_SHARP, "Ax");
		testFormat(symbAscii, PitchClass.B_DBL_SHARP, "Bx");

		testFormat(symbAscii, PitchClass.of(BasePitchClass.D,
				Accidental.ofSteps(-4)), "Dbbbb");
		testFormat(symbAscii, PitchClass.of(BasePitchClass.A,
				Accidental.ofSteps(3)), "A###");
	}

	private void testFormat(PitchClassFormat fmt, PitchClass pc, String exp) {
		assertEquals("", exp, fmt.format(pc).toString());
	}
}
