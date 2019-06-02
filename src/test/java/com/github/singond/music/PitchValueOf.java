package com.github.singond.music;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PitchValueOf {

	@Test
	public void convertPitchClass() {
		assertEquals(Pitch.CBB4, Pitch.valueOf("Cbb4"));
		assertEquals(Pitch.DBB4, Pitch.valueOf("Dbb4"));
		assertEquals(Pitch.EBB4, Pitch.valueOf("Ebb4"));
		assertEquals(Pitch.FBB4, Pitch.valueOf("Fbb4"));
		assertEquals(Pitch.GBB4, Pitch.valueOf("Gbb4"));
		assertEquals(Pitch.ABB4, Pitch.valueOf("Abb4"));
		assertEquals(Pitch.BBB4, Pitch.valueOf("Bbb4"));

		assertEquals(Pitch.CB4, Pitch.valueOf("Cb4"));
		assertEquals(Pitch.DB4, Pitch.valueOf("Db4"));
		assertEquals(Pitch.EB4, Pitch.valueOf("Eb4"));
		assertEquals(Pitch.FB4, Pitch.valueOf("Fb4"));
		assertEquals(Pitch.GB4, Pitch.valueOf("Gb4"));
		assertEquals(Pitch.AB4, Pitch.valueOf("Ab4"));
		assertEquals(Pitch.BB4, Pitch.valueOf("Bb4"));

		assertEquals(Pitch.C4, Pitch.valueOf("C4"));
		assertEquals(Pitch.D4, Pitch.valueOf("D4"));
		assertEquals(Pitch.E4, Pitch.valueOf("E4"));
		assertEquals(Pitch.F4, Pitch.valueOf("F4"));
		assertEquals(Pitch.G4, Pitch.valueOf("G4"));
		assertEquals(Pitch.A4, Pitch.valueOf("A4"));
		assertEquals(Pitch.B4, Pitch.valueOf("B4"));

		assertEquals(Pitch.CS4, Pitch.valueOf("C#4"));
		assertEquals(Pitch.DS4, Pitch.valueOf("D#4"));
		assertEquals(Pitch.ES4, Pitch.valueOf("E#4"));
		assertEquals(Pitch.FS4, Pitch.valueOf("F#4"));
		assertEquals(Pitch.GS4, Pitch.valueOf("G#4"));
		assertEquals(Pitch.AS4, Pitch.valueOf("A#4"));
		assertEquals(Pitch.BS4, Pitch.valueOf("B#4"));

		assertEquals(Pitch.CX4, Pitch.valueOf("Cx4"));
		assertEquals(Pitch.DX4, Pitch.valueOf("Dx4"));
		assertEquals(Pitch.EX4, Pitch.valueOf("Ex4"));
		assertEquals(Pitch.FX4, Pitch.valueOf("Fx4"));
		assertEquals(Pitch.GX4, Pitch.valueOf("Gx4"));
		assertEquals(Pitch.AX4, Pitch.valueOf("Ax4"));
		assertEquals(Pitch.BX4, Pitch.valueOf("Bx4"));
	}

	@Test
	public void convertOctave() {
		assertEquals(Pitch.CBB1, Pitch.valueOf("Cbb1"));
		assertEquals(Pitch.CB1, Pitch.valueOf("Cb1"));
		assertEquals(Pitch.C1, Pitch.valueOf("C1"));
		assertEquals(Pitch.CS1, Pitch.valueOf("C#1"));
		assertEquals(Pitch.CX1, Pitch.valueOf("Cx1"));

		assertEquals(Pitch.CBB2, Pitch.valueOf("Cbb2"));
		assertEquals(Pitch.CB2, Pitch.valueOf("Cb2"));
		assertEquals(Pitch.C2, Pitch.valueOf("C2"));
		assertEquals(Pitch.CS2, Pitch.valueOf("C#2"));
		assertEquals(Pitch.CX2, Pitch.valueOf("Cx2"));

		assertEquals(Pitch.CBB3, Pitch.valueOf("Cbb3"));
		assertEquals(Pitch.CB3, Pitch.valueOf("Cb3"));
		assertEquals(Pitch.C3, Pitch.valueOf("C3"));
		assertEquals(Pitch.CS3, Pitch.valueOf("C#3"));
		assertEquals(Pitch.CX3, Pitch.valueOf("Cx3"));

		assertEquals(Pitch.CBB4, Pitch.valueOf("Cbb4"));
		assertEquals(Pitch.CB4, Pitch.valueOf("Cb4"));
		assertEquals(Pitch.C4, Pitch.valueOf("C4"));
		assertEquals(Pitch.CS4, Pitch.valueOf("C#4"));
		assertEquals(Pitch.CX4, Pitch.valueOf("Cx4"));

		assertEquals(Pitch.CBB5, Pitch.valueOf("Cbb5"));
		assertEquals(Pitch.CB5, Pitch.valueOf("Cb5"));
		assertEquals(Pitch.C5, Pitch.valueOf("C5"));
		assertEquals(Pitch.CS5, Pitch.valueOf("C#5"));
		assertEquals(Pitch.CX5, Pitch.valueOf("Cx5"));

		assertEquals(Pitch.CBB6, Pitch.valueOf("Cbb6"));
		assertEquals(Pitch.CB6, Pitch.valueOf("Cb6"));
		assertEquals(Pitch.C6, Pitch.valueOf("C6"));
		assertEquals(Pitch.CS6, Pitch.valueOf("C#6"));
		assertEquals(Pitch.CX6, Pitch.valueOf("Cx6"));

		assertEquals(Pitch.CBB7, Pitch.valueOf("Cbb7"));
		assertEquals(Pitch.CB7, Pitch.valueOf("Cb7"));
		assertEquals(Pitch.C7, Pitch.valueOf("C7"));
		assertEquals(Pitch.CS7, Pitch.valueOf("C#7"));
		assertEquals(Pitch.CX7, Pitch.valueOf("Cx7"));

		assertEquals(Pitch.CBB8, Pitch.valueOf("Cbb8"));
		assertEquals(Pitch.CB8, Pitch.valueOf("Cb8"));
		assertEquals(Pitch.C8, Pitch.valueOf("C8"));
		assertEquals(Pitch.CS8, Pitch.valueOf("C#8"));
		assertEquals(Pitch.CX8, Pitch.valueOf("Cx8"));
	}

	@Test(expected = FormatException.class)
	public void inconvertibleEmpty() {
		Pitch.valueOf("");
	}

	@Test(expected = FormatException.class)
	public void inconvertibleFormat() {
		Pitch.valueOf("C");
	}
}
