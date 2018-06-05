package com.github.singond.music;

import static com.github.singond.music.PitchClass.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Keys {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void majorKeys() {
		printScale(new MajorKey(C_FLAT));
		printScale(new MajorKey(G_FLAT));
		printScale(new MajorKey(D_FLAT));
		printScale(new MajorKey(A_FLAT));
		printScale(new MajorKey(E_FLAT));
		printScale(new MajorKey(B_FLAT));
		printScale(new MajorKey(F));
		printScale(new MajorKey(C));
		printScale(new MajorKey(G));
		printScale(new MajorKey(D));
		printScale(new MajorKey(A));
		printScale(new MajorKey(E));
		printScale(new MajorKey(B));
		printScale(new MajorKey(F_SHARP));
		printScale(new MajorKey(C_SHARP));
		System.out.println();
	}
	
	@Test
	public void minorKeys() {
		printScale(new MinorKey(A_FLAT));
		printScale(new MinorKey(E_FLAT));
		printScale(new MinorKey(B_FLAT));
		printScale(new MinorKey(F));
		printScale(new MinorKey(C));
		printScale(new MinorKey(G));
		printScale(new MinorKey(D));
		printScale(new MinorKey(A));
		printScale(new MinorKey(E));
		printScale(new MinorKey(B));
		printScale(new MinorKey(F_SHARP));
		printScale(new MinorKey(C_SHARP));
		printScale(new MinorKey(G_SHARP));
		printScale(new MinorKey(D_SHARP));
		printScale(new MinorKey(A_SHARP));
		System.out.println();
	}

	private void printScale(Key key) {
		System.out.println("This is the " + key + " scale:");
		System.out.println(key.degrees());
	}
	
	@Test
	public void gMajorScale() {
		System.out.println("G major scale between D3 and F5:");
		System.out.println(MajorKey.G_MAJOR.scale(Pitch.of(D, 3), Pitch.of(F, 5)));
		System.out.println();
	}
	
	@Test
	public void eFlatMajorScale() {
		System.out.println("Eb major scale between G3 and Eb5:");
		System.out.println(MajorKey.E_FLAT_MAJOR.scale(Pitch.of(G, 3), Pitch.of(E_FLAT, 5)));
		System.out.println();
	}

}
