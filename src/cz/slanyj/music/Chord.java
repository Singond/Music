package cz.slanyj.music;

import static cz.slanyj.music.Interval.*;

/**
 * A musical chord specified generally, using only pitch classes (using Tone objects).
 * 
 * @author Sorondil
 *
 * @param size (int) The number of tones or notes.
 * @param tones (Tone[]) The tones comprising the chord.
 * @param voicing (Note[]) The voicing of the chord in exact notes.
 * @param hasVoicing (boolean) Indicates whether voicing has been specified, ie. whether <strong>notes</strong> is defined.
 *
 */

public class Chord {
	
	/** Preset chord types. */
	public enum Type {
		MAJ ("", "major triad", V3, M3),
		MIN ("", "minor triad", M3, V3);
		
		String symbol;
		String name;
		Interval[] intervals;
		private int size;
		
		Type(String symbol, String name, Interval... intervals) {
			this.symbol = symbol;
			this.name = name;
			this.intervals = intervals;
			this.size = intervals.length + 1;
		}
		
	}
	
	protected int size;
	/** General tones comprising this chord. */
	Tone[] tones;

	
	/**
	 * Constructs a general chord without specifying its pitch or voicing.
	 * Contains only a list of pitch classes comprising the chord.
	 * 
	 * @param base (Tone) The base pitch class of the chord.
	 * @param type (Chord.Type) The type of the chord.
	 */
	public Chord(Tone base, Type type) {
		this.size = type.size;
		tones = new Tone[size];
		tones[0] = base;
		for (int i=1; i<size; i++) {
			tones[i] = tones[i-1].up(type.intervals[i-1]);
		}
	}
	
	/*public Chord getInversion(int inversion) {
		//if (inversion < 0 && inversion > )
		
	}*/
	
	// TODO Implement toString();
	// Output tones into string
	private String listTones() {
		StringBuilder string = new StringBuilder();
		for (int i=0; i<tones.length-1; i++) {
			string.append(tones[i].name);
			string.append(", ");
		}
		string.append(tones[tones.length-1].name);
		return string.toString();
	}
}
