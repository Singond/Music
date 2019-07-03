package com.github.singond.music.text;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.singond.music.Accidental;
import com.github.singond.music.BasePitchClass;
import com.github.singond.music.PitchClass;

public class PitchClassFormatting {

	private PitchClassFormat engAscii = PitchClassFormats.ENGLISH_ASCII;
	private PitchClassFormat engUnicode = PitchClassFormats.ENGLISH_UNICODE;
	private PitchClassFormat gerAscii = PitchClassFormats.GERMAN_ASCII;
	private PitchClassFormat gerUnicode = PitchClassFormats.GERMAN_UNICODE;
	private PitchClassFormat siAscii = PitchClassFormats.ITALIAN_SI_ASCII;
	private PitchClassFormat siUnicode = PitchClassFormats.ITALIAN_SI_UNICODE;
	private PitchClassFormat tiAscii = PitchClassFormats.ITALIAN_TI_ASCII;
	private PitchClassFormat tiUnicode = PitchClassFormats.ITALIAN_TI_UNICODE;

	@Test
	public void englishAscii() {
		System.out.println("Formatting with English names and ASCII symbols:");
		testFormat(engAscii, PitchClass.C_DBL_FLAT, "Cbb");
		testFormat(engAscii, PitchClass.D_DBL_FLAT, "Dbb");
		testFormat(engAscii, PitchClass.E_DBL_FLAT, "Ebb");
		testFormat(engAscii, PitchClass.F_DBL_FLAT, "Fbb");
		testFormat(engAscii, PitchClass.G_DBL_FLAT, "Gbb");
		testFormat(engAscii, PitchClass.A_DBL_FLAT, "Abb");
		testFormat(engAscii, PitchClass.B_DBL_FLAT, "Bbb");

		testFormat(engAscii, PitchClass.C_FLAT, "Cb");
		testFormat(engAscii, PitchClass.D_FLAT, "Db");
		testFormat(engAscii, PitchClass.E_FLAT, "Eb");
		testFormat(engAscii, PitchClass.F_FLAT, "Fb");
		testFormat(engAscii, PitchClass.G_FLAT, "Gb");
		testFormat(engAscii, PitchClass.A_FLAT, "Ab");
		testFormat(engAscii, PitchClass.B_FLAT, "Bb");

		testFormat(engAscii, PitchClass.C, "C");
		testFormat(engAscii, PitchClass.D, "D");
		testFormat(engAscii, PitchClass.E, "E");
		testFormat(engAscii, PitchClass.F, "F");
		testFormat(engAscii, PitchClass.G, "G");
		testFormat(engAscii, PitchClass.A, "A");
		testFormat(engAscii, PitchClass.B, "B");

		testFormat(engAscii, PitchClass.C_SHARP, "C#");
		testFormat(engAscii, PitchClass.D_SHARP, "D#");
		testFormat(engAscii, PitchClass.E_SHARP, "E#");
		testFormat(engAscii, PitchClass.F_SHARP, "F#");
		testFormat(engAscii, PitchClass.G_SHARP, "G#");
		testFormat(engAscii, PitchClass.A_SHARP, "A#");
		testFormat(engAscii, PitchClass.B_SHARP, "B#");

		testFormat(engAscii, PitchClass.C_DBL_SHARP, "Cx");
		testFormat(engAscii, PitchClass.D_DBL_SHARP, "Dx");
		testFormat(engAscii, PitchClass.E_DBL_SHARP, "Ex");
		testFormat(engAscii, PitchClass.F_DBL_SHARP, "Fx");
		testFormat(engAscii, PitchClass.G_DBL_SHARP, "Gx");
		testFormat(engAscii, PitchClass.A_DBL_SHARP, "Ax");
		testFormat(engAscii, PitchClass.B_DBL_SHARP, "Bx");

		testFormat(engAscii, PitchClass.of(BasePitchClass.D,
				Accidental.ofSteps(-4)), "Dbbbb");
		testFormat(engAscii, PitchClass.of(BasePitchClass.A,
				Accidental.ofSteps(3)), "A###");
		System.out.println();
	}

	@Test
	public void englishUnicode() {
		System.out.println("Formatting with English names and Unicode symbols:");
		testFormat(engUnicode, PitchClass.C_DBL_FLAT, "C\uD834\uDD2B");
		testFormat(engUnicode, PitchClass.D_DBL_FLAT, "D\uD834\uDD2B");
		testFormat(engUnicode, PitchClass.E_DBL_FLAT, "E\uD834\uDD2B");
		testFormat(engUnicode, PitchClass.F_DBL_FLAT, "F\uD834\uDD2B");
		testFormat(engUnicode, PitchClass.G_DBL_FLAT, "G\uD834\uDD2B");
		testFormat(engUnicode, PitchClass.A_DBL_FLAT, "A\uD834\uDD2B");
		testFormat(engUnicode, PitchClass.B_DBL_FLAT, "B\uD834\uDD2B");

		testFormat(engUnicode, PitchClass.C_FLAT, "C\u266D");
		testFormat(engUnicode, PitchClass.D_FLAT, "D\u266D");
		testFormat(engUnicode, PitchClass.E_FLAT, "E\u266D");
		testFormat(engUnicode, PitchClass.F_FLAT, "F\u266D");
		testFormat(engUnicode, PitchClass.G_FLAT, "G\u266D");
		testFormat(engUnicode, PitchClass.A_FLAT, "A\u266D");
		testFormat(engUnicode, PitchClass.B_FLAT, "B\u266D");

		testFormat(engUnicode, PitchClass.C, "C");
		testFormat(engUnicode, PitchClass.D, "D");
		testFormat(engUnicode, PitchClass.E, "E");
		testFormat(engUnicode, PitchClass.F, "F");
		testFormat(engUnicode, PitchClass.G, "G");
		testFormat(engUnicode, PitchClass.A, "A");
		testFormat(engUnicode, PitchClass.B, "B");

		testFormat(engUnicode, PitchClass.C_SHARP, "C\u266F");
		testFormat(engUnicode, PitchClass.D_SHARP, "D\u266F");
		testFormat(engUnicode, PitchClass.E_SHARP, "E\u266F");
		testFormat(engUnicode, PitchClass.F_SHARP, "F\u266F");
		testFormat(engUnicode, PitchClass.G_SHARP, "G\u266F");
		testFormat(engUnicode, PitchClass.A_SHARP, "A\u266F");
		testFormat(engUnicode, PitchClass.B_SHARP, "B\u266F");

		testFormat(engUnicode, PitchClass.C_DBL_SHARP, "C\uD834\uDD2A");
		testFormat(engUnicode, PitchClass.D_DBL_SHARP, "D\uD834\uDD2A");
		testFormat(engUnicode, PitchClass.E_DBL_SHARP, "E\uD834\uDD2A");
		testFormat(engUnicode, PitchClass.F_DBL_SHARP, "F\uD834\uDD2A");
		testFormat(engUnicode, PitchClass.G_DBL_SHARP, "G\uD834\uDD2A");
		testFormat(engUnicode, PitchClass.A_DBL_SHARP, "A\uD834\uDD2A");
		testFormat(engUnicode, PitchClass.B_DBL_SHARP, "B\uD834\uDD2A");

		testFormat(engUnicode, PitchClass.of(BasePitchClass.D,
				Accidental.ofSteps(-4)), "D\u266D\u266D\u266D\u266D");
		testFormat(engUnicode, PitchClass.of(BasePitchClass.A,
				Accidental.ofSteps(3)), "A\u266F\u266F\u266F");
		System.out.println();
	}

	@Test
	public void germanAscii() {
		System.out.println("Formatting with German names and ASCII symbols:");
		testFormat(gerAscii, PitchClass.C_DBL_FLAT, "Cbb");
		testFormat(gerAscii, PitchClass.D_DBL_FLAT, "Dbb");
		testFormat(gerAscii, PitchClass.E_DBL_FLAT, "Ebb");
		testFormat(gerAscii, PitchClass.F_DBL_FLAT, "Fbb");
		testFormat(gerAscii, PitchClass.G_DBL_FLAT, "Gbb");
		testFormat(gerAscii, PitchClass.A_DBL_FLAT, "Abb");
		testFormat(gerAscii, PitchClass.B_DBL_FLAT, "Hbb");

		testFormat(gerAscii, PitchClass.C_FLAT, "Cb");
		testFormat(gerAscii, PitchClass.D_FLAT, "Db");
		testFormat(gerAscii, PitchClass.E_FLAT, "Eb");
		testFormat(gerAscii, PitchClass.F_FLAT, "Fb");
		testFormat(gerAscii, PitchClass.G_FLAT, "Gb");
		testFormat(gerAscii, PitchClass.A_FLAT, "Ab");
		testFormat(gerAscii, PitchClass.B_FLAT, "Bb");

		testFormat(gerAscii, PitchClass.C, "C");
		testFormat(gerAscii, PitchClass.D, "D");
		testFormat(gerAscii, PitchClass.E, "E");
		testFormat(gerAscii, PitchClass.F, "F");
		testFormat(gerAscii, PitchClass.G, "G");
		testFormat(gerAscii, PitchClass.A, "A");
		testFormat(gerAscii, PitchClass.B, "H");

		testFormat(gerAscii, PitchClass.C_SHARP, "C#");
		testFormat(gerAscii, PitchClass.D_SHARP, "D#");
		testFormat(gerAscii, PitchClass.E_SHARP, "E#");
		testFormat(gerAscii, PitchClass.F_SHARP, "F#");
		testFormat(gerAscii, PitchClass.G_SHARP, "G#");
		testFormat(gerAscii, PitchClass.A_SHARP, "A#");
		testFormat(gerAscii, PitchClass.B_SHARP, "H#");

		testFormat(gerAscii, PitchClass.C_DBL_SHARP, "Cx");
		testFormat(gerAscii, PitchClass.D_DBL_SHARP, "Dx");
		testFormat(gerAscii, PitchClass.E_DBL_SHARP, "Ex");
		testFormat(gerAscii, PitchClass.F_DBL_SHARP, "Fx");
		testFormat(gerAscii, PitchClass.G_DBL_SHARP, "Gx");
		testFormat(gerAscii, PitchClass.A_DBL_SHARP, "Ax");
		testFormat(gerAscii, PitchClass.B_DBL_SHARP, "Hx");

		testFormat(gerAscii, PitchClass.of(BasePitchClass.D,
				Accidental.ofSteps(-4)), "Dbbbb");
		testFormat(gerAscii, PitchClass.of(BasePitchClass.A,
				Accidental.ofSteps(3)), "A###");
		System.out.println();
	}

	@Test
	public void germanUnicode() {
		System.out.println("Formatting with German names and Unicode symbols:");
		testFormat(gerUnicode, PitchClass.C_DBL_FLAT, "C\uD834\uDD2B");
		testFormat(gerUnicode, PitchClass.D_DBL_FLAT, "D\uD834\uDD2B");
		testFormat(gerUnicode, PitchClass.E_DBL_FLAT, "E\uD834\uDD2B");
		testFormat(gerUnicode, PitchClass.F_DBL_FLAT, "F\uD834\uDD2B");
		testFormat(gerUnicode, PitchClass.G_DBL_FLAT, "G\uD834\uDD2B");
		testFormat(gerUnicode, PitchClass.A_DBL_FLAT, "A\uD834\uDD2B");
		testFormat(gerUnicode, PitchClass.B_DBL_FLAT, "H\uD834\uDD2B");

		testFormat(gerUnicode, PitchClass.C_FLAT, "C\u266D");
		testFormat(gerUnicode, PitchClass.D_FLAT, "D\u266D");
		testFormat(gerUnicode, PitchClass.E_FLAT, "E\u266D");
		testFormat(gerUnicode, PitchClass.F_FLAT, "F\u266D");
		testFormat(gerUnicode, PitchClass.G_FLAT, "G\u266D");
		testFormat(gerUnicode, PitchClass.A_FLAT, "A\u266D");
		testFormat(gerUnicode, PitchClass.B_FLAT, "B\u266D");

		testFormat(gerUnicode, PitchClass.C, "C");
		testFormat(gerUnicode, PitchClass.D, "D");
		testFormat(gerUnicode, PitchClass.E, "E");
		testFormat(gerUnicode, PitchClass.F, "F");
		testFormat(gerUnicode, PitchClass.G, "G");
		testFormat(gerUnicode, PitchClass.A, "A");
		testFormat(gerUnicode, PitchClass.B, "H");

		testFormat(gerUnicode, PitchClass.C_SHARP, "C\u266F");
		testFormat(gerUnicode, PitchClass.D_SHARP, "D\u266F");
		testFormat(gerUnicode, PitchClass.E_SHARP, "E\u266F");
		testFormat(gerUnicode, PitchClass.F_SHARP, "F\u266F");
		testFormat(gerUnicode, PitchClass.G_SHARP, "G\u266F");
		testFormat(gerUnicode, PitchClass.A_SHARP, "A\u266F");
		testFormat(gerUnicode, PitchClass.B_SHARP, "H\u266F");

		testFormat(gerUnicode, PitchClass.C_DBL_SHARP, "C\uD834\uDD2A");
		testFormat(gerUnicode, PitchClass.D_DBL_SHARP, "D\uD834\uDD2A");
		testFormat(gerUnicode, PitchClass.E_DBL_SHARP, "E\uD834\uDD2A");
		testFormat(gerUnicode, PitchClass.F_DBL_SHARP, "F\uD834\uDD2A");
		testFormat(gerUnicode, PitchClass.G_DBL_SHARP, "G\uD834\uDD2A");
		testFormat(gerUnicode, PitchClass.A_DBL_SHARP, "A\uD834\uDD2A");
		testFormat(gerUnicode, PitchClass.B_DBL_SHARP, "H\uD834\uDD2A");

		testFormat(gerUnicode, PitchClass.of(BasePitchClass.D,
				Accidental.ofSteps(-4)), "D\u266D\u266D\u266D\u266D");
		testFormat(gerUnicode, PitchClass.of(BasePitchClass.A,
				Accidental.ofSteps(3)), "A\u266F\u266F\u266F");
		System.out.println();
	}

	@Test
	public void italianSiAscii() {
		System.out.println("Formatting with Italian names (ending with 'Si') "
				+ "and ASCII symbols:");
		testFormat(siAscii, PitchClass.C_DBL_FLAT, "Dobb");
		testFormat(siAscii, PitchClass.D_DBL_FLAT, "Rebb");
		testFormat(siAscii, PitchClass.E_DBL_FLAT, "Mibb");
		testFormat(siAscii, PitchClass.F_DBL_FLAT, "Fabb");
		testFormat(siAscii, PitchClass.G_DBL_FLAT, "Solbb");
		testFormat(siAscii, PitchClass.A_DBL_FLAT, "Labb");
		testFormat(siAscii, PitchClass.B_DBL_FLAT, "Sibb");

		testFormat(siAscii, PitchClass.C_FLAT, "Dob");
		testFormat(siAscii, PitchClass.D_FLAT, "Reb");
		testFormat(siAscii, PitchClass.E_FLAT, "Mib");
		testFormat(siAscii, PitchClass.F_FLAT, "Fab");
		testFormat(siAscii, PitchClass.G_FLAT, "Solb");
		testFormat(siAscii, PitchClass.A_FLAT, "Lab");
		testFormat(siAscii, PitchClass.B_FLAT, "Sib");

		testFormat(siAscii, PitchClass.C, "Do");
		testFormat(siAscii, PitchClass.D, "Re");
		testFormat(siAscii, PitchClass.E, "Mi");
		testFormat(siAscii, PitchClass.F, "Fa");
		testFormat(siAscii, PitchClass.G, "Sol");
		testFormat(siAscii, PitchClass.A, "La");
		testFormat(siAscii, PitchClass.B, "Si");

		testFormat(siAscii, PitchClass.C_SHARP, "Do#");
		testFormat(siAscii, PitchClass.D_SHARP, "Re#");
		testFormat(siAscii, PitchClass.E_SHARP, "Mi#");
		testFormat(siAscii, PitchClass.F_SHARP, "Fa#");
		testFormat(siAscii, PitchClass.G_SHARP, "Sol#");
		testFormat(siAscii, PitchClass.A_SHARP, "La#");
		testFormat(siAscii, PitchClass.B_SHARP, "Si#");

		testFormat(siAscii, PitchClass.C_DBL_SHARP, "Dox");
		testFormat(siAscii, PitchClass.D_DBL_SHARP, "Rex");
		testFormat(siAscii, PitchClass.E_DBL_SHARP, "Mix");
		testFormat(siAscii, PitchClass.F_DBL_SHARP, "Fax");
		testFormat(siAscii, PitchClass.G_DBL_SHARP, "Solx");
		testFormat(siAscii, PitchClass.A_DBL_SHARP, "Lax");
		testFormat(siAscii, PitchClass.B_DBL_SHARP, "Six");

		testFormat(siAscii, PitchClass.of(BasePitchClass.D,
				Accidental.ofSteps(-4)), "Rebbbb");
		testFormat(siAscii, PitchClass.of(BasePitchClass.A,
				Accidental.ofSteps(3)), "La###");
		System.out.println();
	}

	@Test
	public void italianSiUnicode() {
		System.out.println("Formatting with Italian names (ending with 'Si') "
				+ "and Unicode symbols:");
		testFormat(siUnicode, PitchClass.C_DBL_FLAT, "Do\uD834\uDD2B");
		testFormat(siUnicode, PitchClass.D_DBL_FLAT, "Re\uD834\uDD2B");
		testFormat(siUnicode, PitchClass.E_DBL_FLAT, "Mi\uD834\uDD2B");
		testFormat(siUnicode, PitchClass.F_DBL_FLAT, "Fa\uD834\uDD2B");
		testFormat(siUnicode, PitchClass.G_DBL_FLAT, "Sol\uD834\uDD2B");
		testFormat(siUnicode, PitchClass.A_DBL_FLAT, "La\uD834\uDD2B");
		testFormat(siUnicode, PitchClass.B_DBL_FLAT, "Si\uD834\uDD2B");

		testFormat(siUnicode, PitchClass.C_FLAT, "Do\u266D");
		testFormat(siUnicode, PitchClass.D_FLAT, "Re\u266D");
		testFormat(siUnicode, PitchClass.E_FLAT, "Mi\u266D");
		testFormat(siUnicode, PitchClass.F_FLAT, "Fa\u266D");
		testFormat(siUnicode, PitchClass.G_FLAT, "Sol\u266D");
		testFormat(siUnicode, PitchClass.A_FLAT, "La\u266D");
		testFormat(siUnicode, PitchClass.B_FLAT, "Si\u266D");

		testFormat(siUnicode, PitchClass.C, "Do");
		testFormat(siUnicode, PitchClass.D, "Re");
		testFormat(siUnicode, PitchClass.E, "Mi");
		testFormat(siUnicode, PitchClass.F, "Fa");
		testFormat(siUnicode, PitchClass.G, "Sol");
		testFormat(siUnicode, PitchClass.A, "La");
		testFormat(siUnicode, PitchClass.B, "Si");

		testFormat(siUnicode, PitchClass.C_SHARP, "Do\u266F");
		testFormat(siUnicode, PitchClass.D_SHARP, "Re\u266F");
		testFormat(siUnicode, PitchClass.E_SHARP, "Mi\u266F");
		testFormat(siUnicode, PitchClass.F_SHARP, "Fa\u266F");
		testFormat(siUnicode, PitchClass.G_SHARP, "Sol\u266F");
		testFormat(siUnicode, PitchClass.A_SHARP, "La\u266F");
		testFormat(siUnicode, PitchClass.B_SHARP, "Si\u266F");

		testFormat(siUnicode, PitchClass.C_DBL_SHARP, "Do\uD834\uDD2A");
		testFormat(siUnicode, PitchClass.D_DBL_SHARP, "Re\uD834\uDD2A");
		testFormat(siUnicode, PitchClass.E_DBL_SHARP, "Mi\uD834\uDD2A");
		testFormat(siUnicode, PitchClass.F_DBL_SHARP, "Fa\uD834\uDD2A");
		testFormat(siUnicode, PitchClass.G_DBL_SHARP, "Sol\uD834\uDD2A");
		testFormat(siUnicode, PitchClass.A_DBL_SHARP, "La\uD834\uDD2A");
		testFormat(siUnicode, PitchClass.B_DBL_SHARP, "Si\uD834\uDD2A");

		testFormat(siUnicode, PitchClass.of(BasePitchClass.D,
				Accidental.ofSteps(-4)), "Re\u266D\u266D\u266D\u266D");
		testFormat(siUnicode, PitchClass.of(BasePitchClass.A,
				Accidental.ofSteps(3)), "La\u266F\u266F\u266F");
		System.out.println();
	}

	@Test
	public void italianTiAscii() {
		System.out.println("Formatting with Italian names (ending with 'Ti') "
				+ "and ASCII symbols:");
		testFormat(tiAscii, PitchClass.C_DBL_FLAT, "Dobb");
		testFormat(tiAscii, PitchClass.D_DBL_FLAT, "Rebb");
		testFormat(tiAscii, PitchClass.E_DBL_FLAT, "Mibb");
		testFormat(tiAscii, PitchClass.F_DBL_FLAT, "Fabb");
		testFormat(tiAscii, PitchClass.G_DBL_FLAT, "Solbb");
		testFormat(tiAscii, PitchClass.A_DBL_FLAT, "Labb");
		testFormat(tiAscii, PitchClass.B_DBL_FLAT, "Tibb");

		testFormat(tiAscii, PitchClass.C_FLAT, "Dob");
		testFormat(tiAscii, PitchClass.D_FLAT, "Reb");
		testFormat(tiAscii, PitchClass.E_FLAT, "Mib");
		testFormat(tiAscii, PitchClass.F_FLAT, "Fab");
		testFormat(tiAscii, PitchClass.G_FLAT, "Solb");
		testFormat(tiAscii, PitchClass.A_FLAT, "Lab");
		testFormat(tiAscii, PitchClass.B_FLAT, "Tib");

		testFormat(tiAscii, PitchClass.C, "Do");
		testFormat(tiAscii, PitchClass.D, "Re");
		testFormat(tiAscii, PitchClass.E, "Mi");
		testFormat(tiAscii, PitchClass.F, "Fa");
		testFormat(tiAscii, PitchClass.G, "Sol");
		testFormat(tiAscii, PitchClass.A, "La");
		testFormat(tiAscii, PitchClass.B, "Ti");

		testFormat(tiAscii, PitchClass.C_SHARP, "Do#");
		testFormat(tiAscii, PitchClass.D_SHARP, "Re#");
		testFormat(tiAscii, PitchClass.E_SHARP, "Mi#");
		testFormat(tiAscii, PitchClass.F_SHARP, "Fa#");
		testFormat(tiAscii, PitchClass.G_SHARP, "Sol#");
		testFormat(tiAscii, PitchClass.A_SHARP, "La#");
		testFormat(tiAscii, PitchClass.B_SHARP, "Ti#");

		testFormat(tiAscii, PitchClass.C_DBL_SHARP, "Dox");
		testFormat(tiAscii, PitchClass.D_DBL_SHARP, "Rex");
		testFormat(tiAscii, PitchClass.E_DBL_SHARP, "Mix");
		testFormat(tiAscii, PitchClass.F_DBL_SHARP, "Fax");
		testFormat(tiAscii, PitchClass.G_DBL_SHARP, "Solx");
		testFormat(tiAscii, PitchClass.A_DBL_SHARP, "Lax");
		testFormat(tiAscii, PitchClass.B_DBL_SHARP, "Tix");

		testFormat(tiAscii, PitchClass.of(BasePitchClass.D,
				Accidental.ofSteps(-4)), "Rebbbb");
		testFormat(tiAscii, PitchClass.of(BasePitchClass.A,
				Accidental.ofSteps(3)), "La###");
		System.out.println();
	}

	@Test
	public void italianTiUnicode() {
		System.out.println("Formatting with Italian names (ending with 'Ti') "
				+ "and Unicode symbols:");
		testFormat(tiUnicode, PitchClass.C_DBL_FLAT, "Do\uD834\uDD2B");
		testFormat(tiUnicode, PitchClass.D_DBL_FLAT, "Re\uD834\uDD2B");
		testFormat(tiUnicode, PitchClass.E_DBL_FLAT, "Mi\uD834\uDD2B");
		testFormat(tiUnicode, PitchClass.F_DBL_FLAT, "Fa\uD834\uDD2B");
		testFormat(tiUnicode, PitchClass.G_DBL_FLAT, "Sol\uD834\uDD2B");
		testFormat(tiUnicode, PitchClass.A_DBL_FLAT, "La\uD834\uDD2B");
		testFormat(tiUnicode, PitchClass.B_DBL_FLAT, "Ti\uD834\uDD2B");

		testFormat(tiUnicode, PitchClass.C_FLAT, "Do\u266D");
		testFormat(tiUnicode, PitchClass.D_FLAT, "Re\u266D");
		testFormat(tiUnicode, PitchClass.E_FLAT, "Mi\u266D");
		testFormat(tiUnicode, PitchClass.F_FLAT, "Fa\u266D");
		testFormat(tiUnicode, PitchClass.G_FLAT, "Sol\u266D");
		testFormat(tiUnicode, PitchClass.A_FLAT, "La\u266D");
		testFormat(tiUnicode, PitchClass.B_FLAT, "Ti\u266D");

		testFormat(tiUnicode, PitchClass.C, "Do");
		testFormat(tiUnicode, PitchClass.D, "Re");
		testFormat(tiUnicode, PitchClass.E, "Mi");
		testFormat(tiUnicode, PitchClass.F, "Fa");
		testFormat(tiUnicode, PitchClass.G, "Sol");
		testFormat(tiUnicode, PitchClass.A, "La");
		testFormat(tiUnicode, PitchClass.B, "Ti");

		testFormat(tiUnicode, PitchClass.C_SHARP, "Do\u266F");
		testFormat(tiUnicode, PitchClass.D_SHARP, "Re\u266F");
		testFormat(tiUnicode, PitchClass.E_SHARP, "Mi\u266F");
		testFormat(tiUnicode, PitchClass.F_SHARP, "Fa\u266F");
		testFormat(tiUnicode, PitchClass.G_SHARP, "Sol\u266F");
		testFormat(tiUnicode, PitchClass.A_SHARP, "La\u266F");
		testFormat(tiUnicode, PitchClass.B_SHARP, "Ti\u266F");

		testFormat(tiUnicode, PitchClass.C_DBL_SHARP, "Do\uD834\uDD2A");
		testFormat(tiUnicode, PitchClass.D_DBL_SHARP, "Re\uD834\uDD2A");
		testFormat(tiUnicode, PitchClass.E_DBL_SHARP, "Mi\uD834\uDD2A");
		testFormat(tiUnicode, PitchClass.F_DBL_SHARP, "Fa\uD834\uDD2A");
		testFormat(tiUnicode, PitchClass.G_DBL_SHARP, "Sol\uD834\uDD2A");
		testFormat(tiUnicode, PitchClass.A_DBL_SHARP, "La\uD834\uDD2A");
		testFormat(tiUnicode, PitchClass.B_DBL_SHARP, "Ti\uD834\uDD2A");

		testFormat(tiUnicode, PitchClass.of(BasePitchClass.D,
				Accidental.ofSteps(-4)), "Re\u266D\u266D\u266D\u266D");
		testFormat(tiUnicode, PitchClass.of(BasePitchClass.A,
				Accidental.ofSteps(3)), "La\u266F\u266F\u266F");
		System.out.println();
	}

	private void testFormat(PitchClassFormat fmt, PitchClass pc, String exp) {
		CharSequence formatted = fmt.format(pc);
		System.out.format("%-5s: %s\n", pc, formatted);
		assertEquals("", exp, formatted.toString());
	}
}
