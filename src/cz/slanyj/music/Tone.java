package cz.slanyj.music;

import cz.slanyj.collections.CircList;

/**
 * A pitch class. This class represents musical tone without the notion of octaves,
 * e.g. C1 and C2 are the same.
 * 
 * @author Sorondil
 *
 */

public enum Tone {

	/* ENUM CONSTANTS */
	// Define each tone and its accidentals (double flat, flat, sharp, double sharp; in this order)
	CIS	(1, "C#"),
	DIS	(3, "D#"),
	EIS	(5, "E#"),
	FIS	(6, "F#"),
	GIS	(8, "G#"),
	AIS	(10, "A#"),
	HIS	(12, "H#"),
	
	CES	(-1, "Cb"),
	DES	(1, "Db"),
	ES	(3, "Eb"),
	FES	(4, "Fb"),
	GES	(6, "Gb"),
	AS	(8, "Ab"),
	/** Equivalent to Bb in English-speaking world. */
	B	(10, "Bb"),
	
	CESES	(-2, "Cbb"),
	DESES	(0, "Dbb"),
	ESES	(2, "Ebb"),
	FESES	(3, "Fbb"),
	GESES	(5, "Gbb"),
	ASAS	(7, "Abb"),
	HESES	(9, "Hbb"),
	
	CISIS	(2, "Cx"),
	DISIS	(4, "Dx"),
	EISIS	(6, "Ex"),
	FISIS	(7, "Fx"),
	GISIS	(9, "Gx"),
	AISIS	(11, "Ax"),
	HISIS	(13, "Hx"),
	
	C	(CESES, CES, CIS, CISIS,	0, "C"),
	D	(DESES, DES, DIS, DISIS,	2, "D"),
	E	(ESES, ES, EIS, EISIS,		4, "E"),
	F	(FESES, FES, FIS, FISIS,	5, "F"),
	G	(GESES, GES, GIS, GISIS,	7, "G"),
	A	(ASAS, AS, AIS, AISIS,		9, "A"),
	/** Equivalent to B in English-speaking world. */
	H	(HESES, B, HIS, HISIS,		11, "H");
	
	/* FIELDS */
	final Tone doubleFlat;
	final Tone flat;
	Tone natural;		// Can't make it final because I need to assign it in initializer, not in constructor
	final Tone sharp;
	final Tone doubleSharp;
	public final int pitchClass;
	public final String name;
	
	private static CircList<Tone> tones = new CircList<Tone>(new Tone[] {C, D, E, F, G, A, H});
	private static Tone[] oneSharp = {CIS, DIS, EIS, FIS, GIS, AIS, HIS};
	@SuppressWarnings("unused")
	private static Tone[] twoSharp = {CISIS, DISIS, EISIS, FISIS, GISIS, AISIS, HISIS};
	
	/* CONSTRUCTORS */
	Tone(Tone doubleFlat, Tone flat, Tone sharp, Tone doubleSharp, int step, String name) {
		this.doubleFlat = doubleFlat;
		this.flat = flat;
		this.sharp = sharp;
		this.doubleSharp = doubleSharp;
		this.pitchClass = step;
		this.name = name;	
	}
	Tone(int step, String name) {
		this(null, null, null, null, step, name);
	}
	
	/* INITIALIZER */
	static {
		for (Tone tone : Tone.values()) {
	 		char nat = tone.name.charAt(0);
			switch (nat) {
				case 'C':	tone.natural = C;
							break;
				case 'D':	tone.natural = D;
							break;
				case 'E':	tone.natural = E;
							break;
				case 'F':	tone.natural = F;
							break;
				case 'G':	tone.natural = G;
							break;
				case 'A':	tone.natural = A;
							break;
				case 'B':
				case 'H':	tone.natural = H;
							break;
			}
		}
		// OK, this is ugly, but simplest I have found so far
	}
	
	/* METHODS */

	/** Transpose this tone up by the given interval.
	 * 
	 * @param interval
	 * @return
	 */
	public Tone up(Interval interval) {
		Tone end;						// The base whole tone (ie. no accidentals)
		// Move n steps up from the natural of this tone (n is specified as "interval.steps")
		CircList<Tone>.Return<Tone> newTone = tones.moveAndCount(this.natural, interval.steps);
		end = (Tone) newTone.element;
		
		// The current interval (in semitones) to the end tone
		int currentInterval = (end.pitchClass - this.pitchClass) + 12*newTone.wrapCount;
		// The desired interval (in semitones) to the end tone
		int desiredInterval = interval.semitones;
		// Correction to be performed by accidentals
		int shift = desiredInterval - currentInterval;
		// The final next tone
		end = end.shift(shift);
		
		return end;
	}
	
	/** Transpose this tone down by the given interval.
	 * 
	 * @param interval
	 * @return
	 */
	public Tone down(Interval interval) {
		Tone end;						// The base whole tone (ie. no accidentals)
		// Move n steps up from the natural of this tone (n is specified as "interval.steps")
		CircList<Tone>.Return<Tone> newTone = tones.moveAndCount(this.natural, -interval.steps);
		end = (Tone) newTone.element;
		
		// The current interval (in semitones) to the end tone
		int currentInterval = (this.pitchClass - end.pitchClass) - 12*newTone.wrapCount;
		// The desired interval (in semitones) to the end tone
		int desiredInterval = interval.semitones;
		// Correction to be performed by accidentals
		int shift = currentInterval - desiredInterval;
		// The final next tone
		end = end.shift(shift);
		
		return end;
	}
	/** Transpose this tone up or down by the given oriented interval.
	 * 
	 * @param oInterval
	 * @return
	 */
	public Tone transpose(OrientedInterval oInterval) {
		return oInterval.direction == OrientedInterval.Direction.UP ?
				up(oInterval.interval) : down(oInterval.interval);
	}
	/** Shift the natural of this tone by a given number of semitones.<p>
	 * The shifted tone is of the same base as the original, eg. base C shifted by +2 will be C## (double sharp).
	 * Only the natural of the input is used, so shifting Cb, C and C# will all yield the same result.
	 * @param shift (int)	Number of semitones to shift by. Positive numbers shift up, negative down.
	 * @return (Tone)		The shifted tone.
	 * @throws IllegalArgumentException Thrown when theshift required is too big to be achieved by accidentals.
	 * Currently a maximum of a double accidental is supported.
	 */
	private Tone shift(int shift) throws IllegalArgumentException {
		Tone out = this.natural;	// Should be redundant
		switch (shift) {
			case -2: case 10: case -14:
				out = out.doubleFlat;
				break;
			case -1: case 11: case -13:
				out = out.flat;
				break;
			case 0: case 12: case -12:
				break;
			case 1: case 13: case -11:
				out = out.sharp;
				break;
			case 2: case 14: case -10:
				out = out.doubleSharp;
				break;
			default:
				out = this;
				throw new IllegalArgumentException("Cannot shift "+out.name+" by "+shift+".");
		}
		return out;
	}
	
	/** Finds a Tone with matching pitch class.
	 * Basic tones are preferred, if none matches, a matching sharp tone is returned.
	 * An exception is made for A#/Bb, which is returned as Bb.
	 * 
	 * @param pitchClass
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static Tone find(int pitchClass) throws IllegalArgumentException {
		// Check basic tones
		for(Tone tone : tones) {			// For each constant in enum Tone
			if (tone.pitchClass == pitchClass) {
				return tone;
			} else {
				continue;
			}
		}
		// Make an exception for Bb
		if(pitchClass == B.pitchClass) {return B;}
		// If here, check sharp tones
		for(Tone tone : oneSharp) {
			if (tone.pitchClass == pitchClass) {
				return tone;
			} else {
				continue;
			}
		}
		throw new IllegalArgumentException("Could not find correct tone: Pitch class is too big.");
	}
	/** Find the Tone with matching pitch and matching base.
	 * The shifted tone is of the same base as the original, eg. base C shifted by +2 will be C## (double sharp).
	 * Only the natural of the input is used, so shifting Cb, C and C# will all yield the same result.
	 * @param pitchClass (int) The pitch class (C = 0, C#/Db = 1 etc.);
	 * @param base
	 * @return
	 */
	public static Tone find(int pitchClass, Tone base) throws IllegalArgumentException {
//		pitchClass = Math.floorMod(pitchClass, 12);
		base = base.natural;	// Make sure it's natural
		int basePitch = base.pitchClass;
		int shift = pitchClass - basePitch;
		try {
			base = base.shift(shift);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Cannot shift tone "+base.name+" to pitch class "+pitchClass+".");
		}
		return base;
	}
	
	@Override
	public String toString() {
		return this.name();
	}
}
