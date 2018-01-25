package cz.slanyj.music;

import cz.slanyj.collections.CircList;

import static cz.slanyj.music.Interval.*;
import static cz.slanyj.music.Tone.*;
import static cz.slanyj.music.Key.Mode.*;

import java.util.HashSet;


/**
 * A musical key, that is a selection of tones (pitch classes).
 * 
 * @author Sorondil
 *
 */


public class Key {
	
	/**
	 * A musical mode, ie. a relative pattern of tones. 
	 * @author Sorondil
	 */
	public static class Mode {
		// Define common modes
		// (Express the scale in terms of intervals up from the base)
		public static final Mode MAJOR = new Mode ("major", Cadence.MAJOR, C1, V2, V3, C4, C5, V6, V7);
		public static final Mode MINOR = new Mode ("minor", Cadence.MINOR, C1, V2, M3, C4, C5, M6, M7);
		
		public final Interval[] structure;
		public final Cadence cadence;
		public final String name;
		
		Mode(String name, Cadence cadence, Interval... structure) {
			this.structure = structure;
			this.cadence = cadence;
			this.name = name;
		}
	}
	
	/** The tonic of this key. */
	public final Tone tonic;
	/** Mode of this key. */
	public final Mode mode;
	/** A simple octave scale in the key containing only pitch classes. Implemented as an array of Tones. */
	public CircList<Tone> scale;
	/** A custom scale with exact pitches, implemented as an array of Notes. */ 
	public Note[] longScale;

	public static HashSet<Key> common = new HashSet<Key>();
	public static HashSet<Key> all = new HashSet<Key>();
	
	/* COMMON KEYS */
	// Major
	public static final Key CES_MAJ = new Key(CES, MAJOR, common);
	public static final Key GES_MAJ = new Key(GES, MAJOR, common);
	public static final Key DES_MAJ = new Key(DES, MAJOR, common);
	public static final Key AS_MAJ = new Key(AS, MAJOR, common);
	public static final Key ES_MAJ = new Key(ES, MAJOR, common);
	public static final Key BB_MAJ = new Key(B, MAJOR, common);
	public static final Key F_MAJ = new Key(F, MAJOR, common);
	public static final Key C_MAJ = new Key(C, MAJOR, common);
	public static final Key G_MAJ = new Key(G, MAJOR, common);
	public static final Key D_MAJ = new Key(D, MAJOR, common);
	public static final Key A_MAJ = new Key(A, MAJOR, common);
	public static final Key E_MAJ = new Key(E, MAJOR, common);
	public static final Key H_MAJ = new Key(H, MAJOR, common);
	public static final Key FIS_MAJ = new Key(FIS, MAJOR, common);
	public static final Key CIS_MAJ = new Key(CIS, MAJOR, common);
	// Minor
	public static final Key AS_MIN = new Key(AS, MINOR, common);
	public static final Key ES_MIN = new Key(ES, MINOR, common);
	public static final Key BB_MIN = new Key(B, MINOR, common);
	public static final Key F_MIN = new Key(F, MINOR, common);
	public static final Key C_MIN = new Key(C, MINOR, common);
	public static final Key G_MIN = new Key(G, MINOR, common);
	public static final Key D_MIN = new Key(D, MINOR, common);
	public static final Key A_MIN = new Key(A, MINOR, common);
	public static final Key E_MIN = new Key(E, MINOR, common);
	public static final Key H_MIN = new Key(H, MINOR, common);
	public static final Key FIS_MIN = new Key(FIS, MINOR, common);
	public static final Key CIS_MIN = new Key(CIS, MINOR, common);
	public static final Key GIS_MIN = new Key(GIS, MINOR, common);
	public static final Key DIS_MIN = new Key(DIS, MINOR, common);
	public static final Key AIS_MIN = new Key(AIS, MINOR, common);
	

	/* CONSTRUCTORS AND INITIALIZERS */
	/**
	 * Constsructs a new key and adds it to specified sets.
	 * 
	 * @param tonic (Tone) Tonic of the key.
	 * @param mode (Mode) Key mode.
	 * @param sets (HashSet<Key>... A list of sets to which this key will be added.
	 */
	@SafeVarargs
	public Key(Tone tonic, Mode mode, HashSet<Key>... sets) {
		this.tonic = tonic;
		this.mode = mode;
		this.createScale();
		for (HashSet<Key> set : sets) {set.add(this);}
		all.add(this);
	}
	
	/* METHODS */
	/** Returns a name of this key. */
	public String name() {
		return tonic.name +" "+ mode.name;
	}
	
	/**
	 * Creates a scale in this key going from a specified step to the end step. Can span multiple octaves.
	 * 
	 * @param from The lower limit of the scale to be created.
	 * Specified as an integer number of the scale counting up from tonic = 1.
	 * @param to The step of this key to finish on (inclusive), specified in the same way as <b>start</b>.
	 * @param fromOctave The MIDI octave of the starting tone.
	 * @param octaves How many times the tonic is crossed. Positive when going up, negative when going down.
	 * @return The scale as an array of Note objects.
	 */
	public Note[] scale(int from, int to, int fromOctave, int octaves) {
		int start;
		int end;

		to += octaves*scale.size();
		
		if (from<to) {
			// In the case when "from" is lower, just change the counting base from 1 to 0.
			start = from-1;
			end = to-1;
		} else if (from>to) {
			// If "from" is higher, swap the two limits and change the counting base.
			start = to-1;
			end = from-1;
		} else {
			// If "from" and "to" are equal, return empty array.
			return new Note[0];
		}
		int length = end - start + 1;
		Note[] newScale = new Note[length];
		newScale[0] = new Note(scale.get(start), fromOctave);
		for (int i=1; i<length; i++) {
			// Get the next note in the scale (scale.get(start+i)) and find a note of this pitch class higher than the current one.
			newScale[i] = newScale[i-1].higher(scale.get(start+i));
		}
		return newScale;
	}
	
	
	/* PRIVATE METHODS */
	
	/** Set the scale variable to be a single octave scale in the key. */
	private void createScale() {
		// Initialize an array to hold the scale tones
		Tone[] scaleArr = new Tone[mode.structure.length];
		// Set the base
		scaleArr[0] = tonic;
//		System.out.println("Adding "+scale[0].name+" to scale.");
		// Fill subsequent steps
		for (int i=1; i<mode.structure.length; i++) {
			scaleArr[i] = tonic.up(mode.structure[i]);
//			System.out.println("Adding "+scale[i].name+" to scale.");
		}
		scale = new CircList<Tone>(scaleArr);
	}
	
	
}
