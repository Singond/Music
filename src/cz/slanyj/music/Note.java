package cz.slanyj.music;


/**
 * A pitch. This class represents a musical tone including octave information.
 * 
 * @author Sorondil
 *
 * @param pitch (int) The MIDI pitch.
 * @param octave (int) The octave number in scientific notation
 * (C4 = middle C = c' in Helmholtz notation).
 * @param tone (Tone) The musical tone.
 */

public class Note {
	
	public final int pitch;
	public final int octave;
	public final Tone tone;
	
	/* CONSTRUCTORS */
	/** Create a new Note object of a given tone in a given octave.
	 * @param tone (Tone)	A Tone object
	 * @param octave (int)	The octave number in scientific notation (C4 = middle C = c' in Helmholtz notation).
	 */
	public Note(Tone tone, int octave) {		// In scientfic notation | 4 (scientific) = 1 line (Helmholtz)
		int octaveMIDI = octave + 1;		// For some reason, 0 (MIDI) = sub-subcontra (Helmholtz)
		pitch = tone.pitchClass+octaveMIDI*12;	// Midi pitch
		this.octave = octave;
		this.tone = tone;
	}
	/** Create a new Note instance given only pitch.<p>
	 * This constructor cannot distinguish enharmonic tones and returns the first tone with corresponding pitch.
	 * Eg. for a pitch C#/Db, will always return C#. */
	public Note(int pitch) {
		this.pitch = pitch;
		int twelve = 12;
		int octaveMIDI = pitch/twelve;		// int/int is treated as integer division (returns only integer part)
		this.octave = octaveMIDI-1;
		int pitchClass = pitch%12;			// pitch mod 12
		tone = Tone.find(pitchClass);
	}
	/** Create a new Note object from pitch and base note.<p>
	 * The base note is modified by accidentals to meet the desired pitch.
	 * For example, given a pitch for C#/Db; create C# when given C as a base note
	 * and create Db when given D.
	 * @param pitch (int)	MIDI pitch
	 * @param base (Tone)	The base tone. Will be converted to natural if entered with accidentals.
	 */
	public Note(int pitch, Tone base) {
		this.pitch = pitch;
		int twelve = 12;
		int octaveMIDI = pitch/twelve;		// int/int is treated as integer division (returns only integer part)
		this.octave = octaveMIDI-1;
		int pitchClass = pitch%12;			// pitch mod 12
		base = base.natural;
		tone = Tone.find(pitchClass, base);
	}
	
	/* METHODS */
	/** Transpose this note up by a given interval.
	 * 
	 * @param interval
	 * @return The endpoint of the interval.
	 */
	public Note up(Interval interval) {
		int newPitch = this.pitch + interval.semitones;
		Tone newTone = this.tone.up(interval);
		try {
			Note end = new Note(newPitch, newTone);
			return end;
		} catch (IllegalArgumentException e) {
			System.out.println("Note transposition failed: "+this.name()+" by "+interval.name());
			e.printStackTrace();
		}
		return this;
	}
	/** Transpose this note down by a given interval.
	 * 
	 * @param interval
	 * @return The endpoint of the interval.
	 */
	public Note down(Interval interval) {
		int newPitch = this.pitch - interval.semitones;
		Tone newTone = this.tone.down(interval);
		Note end = new Note(newPitch, newTone);
		return end;
	}
	/** Transpose this note up or down by the given oriented interval.
	 * 
	 * @param orientedInterval
	 * @return
	 */
	public Note transpose(OrientedInterval orientedInterval) {
		return orientedInterval.direction == OrientedInterval.Direction.UP ?
				up(orientedInterval.interval) : down(orientedInterval.interval);
	}

	/**
	 * Returns next higher note of the given pitch class.
	 * If an ehnarmonic tone is to be produced, it is always higher, ie transposed by an octave up.
	 * @param tone
	 * @param skip (int) Number of octaves to skip before looking for the matching pitch class.
	 * E.g. from C4 to G6, skip = 1.
	 * @return
	 */
	public Note higher(Tone tone, int skip) {
		// Create new Note of the given Tone in the same octave as this
		Note firstTry = new Note(tone, octave+skip);
		// Compare the pitches
		int interval = firstTry.pitch - this.pitch;
		// Calculate how many octaves the first try needs to be shifted in order to fit the interval into an octave
		// (The numerator is decreased by 1 in otder to make 12 produce 0 and 0 produce -1
		int octaveCorrection = Math.floorDiv(interval-1, 12);
		return new Note(tone, octave-octaveCorrection+skip);
	}
	/**
	 * Returns next higher note of the given pitch class.
	 * If an ehnarmonic tone is to be produced, it is always higher, ie shifted by an octave up.<p>
	 * This is the same method as higher(tone, skip), with skip = 0.
	 * @param tone
	 * @return
	 */
	public Note higher(Tone tone) {
		return higher(tone, 0);
	}
	
	/** Return the scientific name of the tone.
	 * 
	 * @return
	 */
	public String name() {
		return this.tone.name + this.octave;
	}
}
