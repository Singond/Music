package cz.slanyj.music;

import static cz.slanyj.music.OrientedInterval.*;
import static cz.slanyj.music.Chord.Type.*;

public enum Cadence {
	MAJOR ( new ChordPattern (C1_UP, MAJ, 0),
			new ChordPattern (C5_DOWN, MAJ, 2),
			new ChordPattern (C4_DOWN, MAJ, 1),
			new ChordPattern (C1_UP, MAJ, 0)),
	MINOR ( new ChordPattern (C1_UP, MIN, 0),
			new ChordPattern (C5_DOWN, MIN, 2),
			new ChordPattern (C4_DOWN, MAJ, 1),
			new ChordPattern (C1_UP, MIN, 0));
	
	private final ChordPattern[] pattern;
	
	private Cadence (ChordPattern... cps) {
		pattern = cps;
	}
	
	/**
	 * 
	 * @param tonic
	 * @param crotchet Duration of crotchet in ticks
	 * @return
	 */
	public ChordVoicing[] getCadence(Note tonic) {
		// Voice the chords from the pattern and feed them to array
		ChordVoicing[] chords = new ChordVoicing[pattern.length];
		int i=0;
		for (ChordPattern chord : pattern) {
			Note bass = tonic.transpose(chord.bass); 
			ChordVoicing newChord = new ChordVoicing(bass, chord.type, chord.inversion);
			chords[i] = newChord;
			i++;
		}
		return chords;
	}
	
	
	private static class ChordPattern {
		/** Position of bass note relative to tonic */ 
		final OrientedInterval bass;
		final Chord.Type type;
		final int inversion;
		private ChordPattern(OrientedInterval bass, Chord.Type type, int inversion) {
			this.bass = bass;
			this.type = type;
			this.inversion = inversion;
		}
		private ChordPattern(OrientedInterval bass, Chord.Type type) {
			this(bass, type, 0);
		}
	}
}
