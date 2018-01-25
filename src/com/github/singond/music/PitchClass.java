package com.github.singond.music;

public class PitchClass {

	private final BasePitchClass base;
	private final Accidental accidental;
	
	private PitchClass(BasePitchClass base, Accidental accidental) {
		this.base = base;
		this.accidental = accidental;
	}
	
	public BasePitchClass getBasePitchClass() {
		return base;
	}
	
	public Accidental getAccidental() {
		return accidental;
	}
	
	@Override
	public String toString() {
		return base + accidental.symbolAscii();
	}
}
