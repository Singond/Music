package com.github.singond.music;

import static org.junit.Assert.*;
import static com.github.singond.music.PitchClass.*;

import org.junit.Test;

public class PitchCalculations {

	@Test
	public void relativeOctaveNumbering() {
		assertEquals("Bad relative octave", 0, C.relativeOctave());
		assertEquals("Bad relative octave", -1, C_DBL_FLAT.relativeOctave());
	}
	
	@Test
	public void relativeReferenceHigher() {
		relativeHigher(Pitch.of(C_DBL_FLAT, 4), B, Pitch.of(B, 3));
		
		relativeHigher(Pitch.of(G, 4), G, Pitch.of(G, 5));
		relativeHigher(Pitch.of(G, 4), A, Pitch.of(A, 4));
		relativeHigher(Pitch.of(G, 4), C, Pitch.of(C, 5));
		relativeHigher(Pitch.of(G, 4), F, Pitch.of(F, 5));
		relativeHigher(Pitch.of(G, 4), F_SHARP, Pitch.of(F_SHARP, 5));
		
		relativeHigher(Pitch.of(A_FLAT, 4), G, Pitch.of(G, 5));
		relativeHigher(Pitch.of(A_FLAT, 4), G_SHARP, Pitch.of(G_SHARP, 5));
		relativeHigher(Pitch.of(A_FLAT, 4), G_DBL_SHARP, Pitch.of(G_DBL_SHARP, 4));
		relativeHigher(Pitch.of(A_FLAT, 4), A, Pitch.of(A, 4));
		
		relativeHigher(Pitch.of(B, 4), B, Pitch.of(B, 5));
		relativeHigher(Pitch.of(B_SHARP, 4), B_FLAT, Pitch.of(B_FLAT, 5));
		relativeHigher(Pitch.of(B_SHARP, 4), C, Pitch.of(C, 6));
		relativeHigher(Pitch.of(B_DBL_SHARP, 4), C, Pitch.of(C, 6));
	}
	
	@Test
	public void relativeReferenceLower() {
		relativeLower(Pitch.of(B, 3), C_DBL_FLAT, Pitch.of(C_DBL_FLAT, 4));
		
		relativeLower(Pitch.of(G, 5), G, Pitch.of(G, 4));
		relativeLower(Pitch.of(A, 4), G, Pitch.of(G, 4));
		relativeLower(Pitch.of(C, 5), G, Pitch.of(G, 4));
		relativeLower(Pitch.of(F, 5), G, Pitch.of(G, 4));
		relativeLower(Pitch.of(F_SHARP, 5), G, Pitch.of(G, 4));

		relativeLower(Pitch.of(G, 5), A_FLAT, Pitch.of(A_FLAT, 4));
		relativeLower(Pitch.of(G_SHARP, 5), A_FLAT, Pitch.of(A_FLAT, 4));
		relativeLower(Pitch.of(G_DBL_SHARP, 4), G_SHARP, Pitch.of(G_SHARP, 4));
		relativeLower(Pitch.of(A, 4), A_FLAT, Pitch.of(A_FLAT, 4));

		relativeLower(Pitch.of(B, 5), B, Pitch.of(B, 4));
		relativeLower(Pitch.of(B_FLAT, 5), B_SHARP, Pitch.of(B_SHARP, 4));
		relativeLower(Pitch.of(C, 6), B_SHARP, Pitch.of(B_SHARP, 4));
		relativeLower(Pitch.of(C, 6), B_DBL_SHARP, Pitch.of(B_DBL_SHARP, 4));
	}
	
	private void relativeHigher(Pitch base, PitchClass pc, Pitch expected) {
		Pitch actual = Pitch.nearestAbove(pc, base);
		assertEquals("Failed nearestAbove()", expected, actual);
	}
	
	private void relativeLower(Pitch base, PitchClass pc, Pitch expected) {
		Pitch actual = Pitch.nearestBelow(pc, base);
		assertEquals("Failed nearestBelow()", expected, actual);
	}
}
