package com.github.singond.music;

public class Chords {

	/** A major triad, for example [C, E, G]. */
	public static final InvertibleChordType MAJOR_TRIAD = SmallChordType.MAJOR_TRIAD;
	/** First inversion of a major triad, for example [E, G, C]. */
	public static final InvertibleChordType MAJOR_TRIAD_6 = SmallChordType.MAJOR_TRIAD_6;
	/** Second inversion of a major triad, for example [G, C, E]. */
	public static final InvertibleChordType MAJOR_TRIAD_64 = SmallChordType.MAJOR_TRIAD_64;
	/** A minor triad, for example [C, Eb, G]. */
	public static final InvertibleChordType MINOR_TRIAD = SmallChordType.MINOR_TRIAD;
	/** First inversion of a minor triad, for example [Eb, G, C]. */
	public static final InvertibleChordType MINOR_TRIAD_6 = SmallChordType.MINOR_TRIAD_6;
	/** Second inversion of a minor triad, for example [G, C, Eb]. */
	public static final InvertibleChordType MINOR_TRIAD_64 = SmallChordType.MINOR_TRIAD_64;
	/** A diminished triad, for example [C, Eb, Gb]. */
	public static final InvertibleChordType DIMINISHED_TRIAD = SmallChordType.DIMINISHED_TRIAD;
	/** First inversion of a diminished triad, for example [Eb, Gb, C]. */
	public static final InvertibleChordType DIMINISHED_TRIAD_6 = SmallChordType.DIMINISHED_TRIAD_6;
	/** Second inversion of a diminished triad, for example [Gb, C, Eb]. */
	public static final InvertibleChordType DIMINISHED_TRIAD_64 = SmallChordType.DIMINISHED_TRIAD_64;
	/** An augmented triad, for example [C, E, G#]. */
	public static final ChordType AUGMENTED_TRIAD = DefaultChordType.AUGMENTED_TRIAD;

	/** A dominant seventh chord, for example [C, E, G, Bb]. */
	public static final InvertibleChordType DOMINANT_7 = SmallChordType.DOMINANT_7;
	/** A major seventh chord, for example [C, E, G, B]. */
	public static final InvertibleChordType MAJOR_7 = SmallChordType.MAJOR_7;
	/** A minor seventh chord, for example [C, Eb, G, Bb]. */
	public static final InvertibleChordType MINOR_7 = SmallChordType.MINOR_7;
	/** A minor-major seventh chord, for example [C, Eb, G, B]. */
	public static final InvertibleChordType MINOR_MAJOR_7 = SmallChordType.MINOR_MAJOR_7;
	/** A half-diminished seventh chord, for example [C, Eb, Gb, Bb]. */
	public static final InvertibleChordType HALF_DIMINISHED_7 = SmallChordType.HALF_DIMINISHED_7;
	/** A diminished seventh chord, for example [C, Eb, Gb, Bbb]. */
	public static final ChordType DIMINISHED_7 = DefaultChordType.DIMINISHED_7;
	/** An augmented major seventh chord, for example [C, E, G#, B]. */
	public static final InvertibleChordType AUGMENTED_MAJOR_7 = SmallChordType.AUGMENTED_MAJOR_7;

	private Chords() {
		throw new UnsupportedOperationException("Non-instantiable class");
	}

	public static final Chord chordAtBass(PitchClass bass, ChordType type) {
		return TypedChord.ofBass(bass, type);
	}

	public static final TypedChord chordAtRoot(PitchClass root, ChordType type) {
		return TypedChord.ofRoot(root, type);
	}
}
