package cz.slanyj.music;

import static cz.slanyj.music.Interval.*;

import java.util.Arrays;

/**
 * A musical chord represented generally by pitch classes (Tone objects).
 * <p>This class is immutable, as long as the Tone enum is.</p>
 * 
 * @author Sorondil
 *
 */

public class Chord {
	
	/** The number of tones in this chord */
	private final int size;
	/** Pitch classes comprising this chord. */
	private final Tone[] tones;
	/** The root tone of this chord (not neccessarily bass). */
	private final Tone root;

	/**
	 * Constructs a new Chord from the given tones given root.
	 * 
	 * @param root The root tone.
	 * @param tones The tones comprising the chord. These must include the root,
	 * if it is to be a part of the chord.
	 */
	public Chord(Tone root, Tone[] tones) {
		this.root = root;
		this.tones = tones.clone();
		this.size = tones.length;
	}
	
	/**
	 * Constructs a new Chord from the given tones and sets the
	 * root to the first Tone given.
	 * 
	 * @param size (int) The number of tones.
	 * @param tones (Tone[]) The tones comprising the chord.
	 */
	public Chord(Tone... tones) {
		if (tones.length < 1) {
			throw new IllegalArgumentException("A chord must have at least one Tone.");
		}
		this.root = tones[0];
		this.tones = tones.clone();
		this.size = tones.length;
	}
	
	/**
	 * Constructs a new Chord from the given type on the given root.
	 * 
	 * @param base (Tone) The base pitch class of the chord.
	 * @param type (Chord.Type) The type of the chord.
	 */
	public Chord(Tone root, Type type) {
		this(root, type.getTones(root));
	}
	
	/**
	 * Returns the ith inversion of this chord as a new object. 0th inversion is the
	 * root position, increasing the inversion number by one means moving the lowest
	 * Tone to the top of the chord. 
	 * @param inversion An integer number between 0 and (size-1).
	 * @return A new Chord object.
	 */
	public Chord getInversion(int inversion) {
		if (inversion < 0 && inversion >= size) {
			throw new IllegalArgumentException("The chord"+this+"has only"+size+"inversions.");
		}
		Tone[] inversionTones = new Tone[size];
		for (int i=0; i<size; i++) {
			inversionTones[i] = tones[(size+inversion)%size];
		}
		return new Chord(this.root, inversionTones);
	}
	
	@Override
	public String toString() {
		return Arrays.toString(tones);
	}

	/** Preset chord types. */
	public enum Type {
		MAJ ("", "major triad", V3, M3),
		MIN ("", "minor triad", M3, V3);
		
		private final String symbol;
		private final String name;
		private final Interval[] intervals;
		private final int size;
		
		Type(String symbol, String name, Interval... intervals) {
			this.symbol = symbol;
			this.name = name;
			this.intervals = intervals;
			this.size = intervals.length + 1;
		}
		
		/**
		 * Builds the chord prescribed by this chord type on the given root.
		 * @param root A Tone object representing the root of the desired chord.
		 * @return A new array of Tone objects.
		 */
		public Tone[] getTones(Tone root) {
			Tone[] tones = new Tone[size];
			tones[0] = root;
			for (int i=1; i<size; i++) {
				tones[i] = tones[i-1].up(intervals[i-1]);
			}
			return tones;
		}
		
	}
}
