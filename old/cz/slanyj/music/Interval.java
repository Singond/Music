package cz.slanyj.music;

/**
 * This class represents musical intervals. It contains a method to calculate
 * a tone a specified interval apart from a given note. This method will be used
 * in generating scales and chords.
 * 
 * @author Sorondil
 *
 */

public enum Interval {
	// Using Czech notation: C = perfect, M = minor, V = major; ZM = diminished, ZV = augmented.
	// Basic (perfect, minor, major)
	C1	(0,	0),
	M2	(1, 1),
	V2	(1, 2),
	M3	(2, 3),
	V3	(2, 4),
	C4	(3, 5),
	C5	(4, 7),
	M6	(5, 8),
	V6	(5, 9),
	M7	(6, 10),
	V7	(6, 11),
	C8	(7, 12),
	// Additional (diminished, augmented)
	ZM1	(0, -1),
	ZV1	(0, 1),
	ZM2	(1, 0),
	ZV2	(1, 3),
	ZM3	(2, 2),
	ZV3	(2, 5),
	ZM4	(3, 4),
	ZV4	(3, 6),
	ZM5	(4, 6),
	ZV5	(4, 8),
	ZM6	(5, 7),
	ZV6	(5, 10),
	ZM7	(6, 9),
	ZV7	(6, 12),
	ZM8	(7, 11),
	ZV8	(7, 13);
	
	
	final int steps;				// Number of steps from the basic tone
	final int semitones;			// Number of semitones from the basic tone

	Interval(int steps, int semitones) {
		this.steps = steps;
		this.semitones = semitones;
	}
	
}
