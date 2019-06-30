package com.github.singond.music.text;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.singond.music.Accidental;
import com.github.singond.music.BasePitchClass;
import com.github.singond.music.PitchClass;
import com.github.singond.music.text.PitchClassFormat;
import com.github.singond.music.text.SymbolicPitchClassFormat;

public class PitchClassFormatting {

	private PitchClassFormat symbAscii = new SymbolicPitchClassFormat(
			SymbolicPitchClassFormat.AccidentalSymbols.ASCII);
	private PitchClassFormat symbUnicode = new SymbolicPitchClassFormat(
			SymbolicPitchClassFormat.AccidentalSymbols.UNICODE);

	@Test
	public void symbAscii() {
		System.out.println("Formatting with ASCII symbols:");
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
		System.out.println();
	}

	@Test
	public void symbUnicode() {
		System.out.println("Formatting with Unicode symbols");
		testFormat(symbUnicode, PitchClass.C_DBL_FLAT, "C\uD834\uDD2B");
		testFormat(symbUnicode, PitchClass.D_DBL_FLAT, "D\uD834\uDD2B");
		testFormat(symbUnicode, PitchClass.E_DBL_FLAT, "E\uD834\uDD2B");
		testFormat(symbUnicode, PitchClass.F_DBL_FLAT, "F\uD834\uDD2B");
		testFormat(symbUnicode, PitchClass.G_DBL_FLAT, "G\uD834\uDD2B");
		testFormat(symbUnicode, PitchClass.A_DBL_FLAT, "A\uD834\uDD2B");
		testFormat(symbUnicode, PitchClass.B_DBL_FLAT, "B\uD834\uDD2B");

		testFormat(symbUnicode, PitchClass.C_FLAT, "C\u266D");
		testFormat(symbUnicode, PitchClass.D_FLAT, "D\u266D");
		testFormat(symbUnicode, PitchClass.E_FLAT, "E\u266D");
		testFormat(symbUnicode, PitchClass.F_FLAT, "F\u266D");
		testFormat(symbUnicode, PitchClass.G_FLAT, "G\u266D");
		testFormat(symbUnicode, PitchClass.A_FLAT, "A\u266D");
		testFormat(symbUnicode, PitchClass.B_FLAT, "B\u266D");

		testFormat(symbUnicode, PitchClass.C, "C");
		testFormat(symbUnicode, PitchClass.D, "D");
		testFormat(symbUnicode, PitchClass.E, "E");
		testFormat(symbUnicode, PitchClass.F, "F");
		testFormat(symbUnicode, PitchClass.G, "G");
		testFormat(symbUnicode, PitchClass.A, "A");
		testFormat(symbUnicode, PitchClass.B, "B");

		testFormat(symbUnicode, PitchClass.C_SHARP, "C\u266F");
		testFormat(symbUnicode, PitchClass.D_SHARP, "D\u266F");
		testFormat(symbUnicode, PitchClass.E_SHARP, "E\u266F");
		testFormat(symbUnicode, PitchClass.F_SHARP, "F\u266F");
		testFormat(symbUnicode, PitchClass.G_SHARP, "G\u266F");
		testFormat(symbUnicode, PitchClass.A_SHARP, "A\u266F");
		testFormat(symbUnicode, PitchClass.B_SHARP, "B\u266F");

		testFormat(symbUnicode, PitchClass.C_DBL_SHARP, "C\uD834\uDD2A");
		testFormat(symbUnicode, PitchClass.D_DBL_SHARP, "D\uD834\uDD2A");
		testFormat(symbUnicode, PitchClass.E_DBL_SHARP, "E\uD834\uDD2A");
		testFormat(symbUnicode, PitchClass.F_DBL_SHARP, "F\uD834\uDD2A");
		testFormat(symbUnicode, PitchClass.G_DBL_SHARP, "G\uD834\uDD2A");
		testFormat(symbUnicode, PitchClass.A_DBL_SHARP, "A\uD834\uDD2A");
		testFormat(symbUnicode, PitchClass.B_DBL_SHARP, "B\uD834\uDD2A");

		testFormat(symbUnicode, PitchClass.of(BasePitchClass.D,
				Accidental.ofSteps(-4)), "D\u266D\u266D\u266D\u266D");
		testFormat(symbUnicode, PitchClass.of(BasePitchClass.A,
				Accidental.ofSteps(3)), "A\u266F\u266F\u266F");
		System.out.println();
	}

	private void testFormat(PitchClassFormat fmt, PitchClass pc, String exp) {
		CharSequence formatted = fmt.format(pc);
		System.out.format("%-5s: %s\n", pc, formatted);
		assertEquals("", exp, formatted.toString());
	}
}
