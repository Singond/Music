package cz.slanyj.music;

import java.util.Arrays;

/**
 * A musical chord. Can be specified generally, using only pitch classes (using Tone objects),
 * or exactly, ie. with exact pitches (using Note objects).
 * 
 * @author Sorondil
 *
 * @param size (int) The number of tones or notes.
 * @param voicing (Note[]) The voicing of the chord in exact notes.
 * @param hasVoicing (boolean) Indicates whether voicing has been specified, ie. whether <strong>notes</strong> is defined.
 *
 */

public class ChordVoicing {
	
	/** The chord represented by this voicing */
	private final Chord chord;
	
	/** The voicing of this chord. */
	private final Note[] voicing;
	
	/**
	 * Voices a given Chord on top of the given bass note. If the bass note
	 * argument matches the bass note of the chord, it becomes the bass note
	 * of the voicing and the voicing has as many notes as there are in the chord.
	 * If the bass note is different from the bass note of the chord, the bass
	 * tone remains and the voicing starts with the next higher tone which matches
	 * the chord bass. In the latter case, the voicing has one tone more than
	 * the chord.
	 * 
	 * @param base Note The bass note.
	 * @param chord The pattern of Tones to build the voicing from.
	 */
	public ChordVoicing(Note bass, Chord chord) {
		this.chord = chord;
		int size = chord.getSize();
		int lowestNonBass = 0;
		if (bass.tone == chord.getBass()) {
			voicing = new Note[size];
			lowestNonBass = 1;
		} else {
			voicing = new Note[size+1];
		}
		voicing[0] = bass;
		// Fill successive pitches
		for (int i=lowestNonBass; i<size; i++) {
			voicing[i] = voicing[i].higher(chord.get(i));
		}
	}
	
	/**
	 * Constructs an exact chord with voicing specified by a known preset.
	 * 
	 * @param bass The pitch of the chord.
	 * @param type The type of the chord.
	 */
	public ChordVoicing(Note bass, Chord.Type type) {
		this(bass, new Chord(bass.tone, type));
	}
	/**
	 * Constructs an exact chord with voicing specified by a known preset.
	 * The chord contains a list of the exact pitches as well as a general list of pitch classes.
	 * 
	 * @param base (Note) The pitch of the chord.
	 * @param type (Chord.Type) The type of the chord.
	 * @param inversion (int) Inversion of the chord
	 */
	public ChordVoicing(Note bass, Chord.Type type, int inversion) {
		this(bass, new Chord(bass.tone, type).makeInversion(inversion));
	}
	
	public ChordVoicing(ChordVoicing voicing) {
		this.chord = voicing.chord;
		this.voicing = voicing.voicing;
	}
	
	public Chord getChord() {
		return chord;
	}
	
	public Note[] voicing() {
		return voicing.clone();
	}
	
	public int getSize() {
		return voicing.length;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(voicing);
	}
}
