package cz.slanyj.music;

import static cz.slanyj.music.OrientedInterval.*;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

import static cz.slanyj.music.Chord.Type.*;
import static cz.slanyj.music.Interval.C4;

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
	public Sequence getCadence(Note tonic, int crotchet) {
		// Voice the chords from the pattern and feed them to array
		Chord[] chords = new Chord[pattern.length];
		int i=0;
		for (ChordPattern chord : pattern) {
			Note bass = tonic.transpose(chord.bass); 
			Chord newChord = new Chord(bass, chord.type, chord.inversion);
			chords[i] = newChord;
			i++;
		}
		try {
			// Create a blank sequence with resolution specified in the Sound static class
			// and a length of a quarter note and one track.
			Sequence sequence = new Sequence(Sequence.PPQ, crotchet, 1);
			// Get the first track
			Track tr1 = sequence.getTracks()[0];

			i = 0;
			for (Chord chord : chords) {
				chord.addToTrack(tr1, i*crotchet, crotchet);
				i++;
			}		
			return sequence;
		} catch (InvalidMidiDataException e) {
			return null;
		}
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
