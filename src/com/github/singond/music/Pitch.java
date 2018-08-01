package com.github.singond.music;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * An exact pitch; that is a pitch class and an octave.
 * <p>
 * This class has a natural ordering which represents the ascending order
 * from lower pitches to higher pitches.
 * This ordering is consistent with {@code equals}.
 * <p>
 * Instances of this class are immutable.
 *
 * @author Singon
 */
public final class Pitch implements Comparable<Pitch> {

	/** The pitch class */
	private final PitchClass pitchClass;

	/**
	 * The octave number in scientific pitch notation.
	 * In scientific pitch notation, the octaves are numbered in ascending
	 * sequence, starting with 0 for the sub-contra octave and proceding up.
	 * In this notation, the octave starting at middle C has the number 4.
	 */
	private final int octave;

	/**
	 * The absolute pitch number, here defined as the number of semitones
	 * above C0 in scientific notation.
	 */
	private final transient int pitch;

	/** The number of semitones in an octave */
	private static final int SEMITONES = 12;

	private static final Comparator<Pitch> STRICT_COMPARATOR
	= new StrictComparator();

	private static final Comparator<Pitch> ENHARMONIC_COMPARATOR
			= new EnharmonicComparator();

	/**
	 * Pre-cached instances of most common pitches.
	 */
	private static final Map<BasePitchClass, List<List<Pitch>>> commonPitches;
	static {
		commonPitches = new EnumMap<>(BasePitchClass.class);
		for (BasePitchClass bpc : BasePitchClass.values()) {
			List<List<Pitch>> accidentals = new ArrayList<>(5);
			// Store accidentals in list: [bb, b, natural, #, x]
			for (int acc = 0; acc < 5; acc++) {
				PitchClass pc = PitchClass.of(bpc, Accidental.ofSteps(acc-2));
				List<Pitch> list = new ArrayList<>(9);
				for (int octave = 0; octave < 9; octave++) {
					list.add(octave, new Pitch(pc, octave));
				}
				accidentals.add(acc, list);
			}
			commonPitches.put(bpc, accidentals);
		}
	}

	/** The <em>C-double-flat</em> pitch in the 0th octave. */
	public static final Pitch CBB0 = Pitch.of(PitchClass.C_DBL_FLAT, 0);
	/** The <em>C-flat</em> pitch in the 0th octave. */
	public static final Pitch CB0 = Pitch.of(PitchClass.C_FLAT, 0);
	/** The <em>C</em> pitch in the 0th octave. */
	public static final Pitch C0 = Pitch.of(PitchClass.C, 0);
	/** The <em>C-sharp</em> pitch in the 0th octave. */
	public static final Pitch CS0 = Pitch.of(PitchClass.C_SHARP, 0);
	/** The <em>C-double-sharp</em> pitch in the 0th octave. */
	public static final Pitch CX0 = Pitch.of(PitchClass.C_DBL_SHARP, 0);

	/** The <em>D-double-flat</em> pitch in the 0th octave. */
	public static final Pitch DBB0 = Pitch.of(PitchClass.D_DBL_FLAT, 0);
	/** The <em>D-flat</em> pitch in the 0th octave. */
	public static final Pitch DB0 = Pitch.of(PitchClass.D_FLAT, 0);
	/** The <em>D</em> pitch in the 0th octave. */
	public static final Pitch D0 = Pitch.of(PitchClass.D, 0);
	/** The <em>D-sharp</em> pitch in the 0th octave. */
	public static final Pitch DS0 = Pitch.of(PitchClass.D_SHARP, 0);
	/** The <em>D-double-sharp</em> pitch in the 0th octave. */
	public static final Pitch DX0 = Pitch.of(PitchClass.D_DBL_SHARP, 0);

	/** The <em>E-double-flat</em> pitch in the 0th octave. */
	public static final Pitch EBB0 = Pitch.of(PitchClass.E_DBL_FLAT, 0);
	/** The <em>E-flat</em> pitch in the 0th octave. */
	public static final Pitch EB0 = Pitch.of(PitchClass.E_FLAT, 0);
	/** The <em>E</em> pitch in the 0th octave. */
	public static final Pitch E0 = Pitch.of(PitchClass.E, 0);
	/** The <em>E-sharp</em> pitch in the 0th octave. */
	public static final Pitch ES0 = Pitch.of(PitchClass.E_SHARP, 0);
	/** The <em>E-double-sharp</em> pitch in the 0th octave. */
	public static final Pitch EX0 = Pitch.of(PitchClass.E_DBL_SHARP, 0);

	/** The <em>F-double-flat</em> pitch in the 0th octave. */
	public static final Pitch FBB0 = Pitch.of(PitchClass.F_DBL_FLAT, 0);
	/** The <em>F-flat</em> pitch in the 0th octave. */
	public static final Pitch FB0 = Pitch.of(PitchClass.F_FLAT, 0);
	/** The <em>F</em> pitch in the 0th octave. */
	public static final Pitch F0 = Pitch.of(PitchClass.F, 0);
	/** The <em>F-sharp</em> pitch in the 0th octave. */
	public static final Pitch FS0 = Pitch.of(PitchClass.F_SHARP, 0);
	/** The <em>F-double-sharp</em> pitch in the 0th octave. */
	public static final Pitch FX0 = Pitch.of(PitchClass.F_DBL_SHARP, 0);

	/** The <em>G-double-flat</em> pitch in the 0th octave. */
	public static final Pitch GBB0 = Pitch.of(PitchClass.G_DBL_FLAT, 0);
	/** The <em>G-flat</em> pitch in the 0th octave. */
	public static final Pitch GB0 = Pitch.of(PitchClass.G_FLAT, 0);
	/** The <em>G</em> pitch in the 0th octave. */
	public static final Pitch G0 = Pitch.of(PitchClass.G, 0);
	/** The <em>G-sharp</em> pitch in the 0th octave. */
	public static final Pitch GS0 = Pitch.of(PitchClass.G_SHARP, 0);
	/** The <em>G-double-sharp</em> pitch in the 0th octave. */
	public static final Pitch GX0 = Pitch.of(PitchClass.G_DBL_SHARP, 0);

	/** The <em>A-double-flat</em> pitch in the 0th octave. */
	public static final Pitch ABB0 = Pitch.of(PitchClass.A_DBL_FLAT, 0);
	/** The <em>A-flat</em> pitch in the 0th octave. */
	public static final Pitch AB0 = Pitch.of(PitchClass.A_FLAT, 0);
	/** The <em>A</em> pitch in the 0th octave. */
	public static final Pitch A0 = Pitch.of(PitchClass.A, 0);
	/** The <em>A-sharp</em> pitch in the 0th octave. */
	public static final Pitch AS0 = Pitch.of(PitchClass.A_SHARP, 0);
	/** The <em>A-double-sharp</em> pitch in the 0th octave. */
	public static final Pitch AX0 = Pitch.of(PitchClass.A_DBL_SHARP, 0);

	/** The <em>B-double-flat</em> pitch in the 0th octave. */
	public static final Pitch BBB0 = Pitch.of(PitchClass.B_DBL_FLAT, 0);
	/** The <em>B-flat</em> pitch in the 0th octave. */
	public static final Pitch BB0 = Pitch.of(PitchClass.B_FLAT, 0);
	/** The <em>B</em> pitch in the 0th octave. */
	public static final Pitch B0 = Pitch.of(PitchClass.B, 0);
	/** The <em>B-sharp</em> pitch in the 0th octave. */
	public static final Pitch BS0 = Pitch.of(PitchClass.B_SHARP, 0);
	/** The <em>B-double-sharp</em> pitch in the 0th octave. */
	public static final Pitch BX0 = Pitch.of(PitchClass.B_DBL_SHARP, 0);

	/** The <em>C-double-flat</em> pitch in the 1st octave. */
	public static final Pitch CBB1 = Pitch.of(PitchClass.C_DBL_FLAT, 1);
	/** The <em>C-flat</em> pitch in the 1st octave. */
	public static final Pitch CB1 = Pitch.of(PitchClass.C_FLAT, 1);
	/** The <em>C</em> pitch in the 1st octave. */
	public static final Pitch C1 = Pitch.of(PitchClass.C, 1);
	/** The <em>C-sharp</em> pitch in the 1st octave. */
	public static final Pitch CS1 = Pitch.of(PitchClass.C_SHARP, 1);
	/** The <em>C-double-sharp</em> pitch in the 1st octave. */
	public static final Pitch CX1 = Pitch.of(PitchClass.C_DBL_SHARP, 1);

	/** The <em>D-double-flat</em> pitch in the 1st octave. */
	public static final Pitch DBB1 = Pitch.of(PitchClass.D_DBL_FLAT, 1);
	/** The <em>D-flat</em> pitch in the 1st octave. */
	public static final Pitch DB1 = Pitch.of(PitchClass.D_FLAT, 1);
	/** The <em>D</em> pitch in the 1st octave. */
	public static final Pitch D1 = Pitch.of(PitchClass.D, 1);
	/** The <em>D-sharp</em> pitch in the 1st octave. */
	public static final Pitch DS1 = Pitch.of(PitchClass.D_SHARP, 1);
	/** The <em>D-double-sharp</em> pitch in the 1st octave. */
	public static final Pitch DX1 = Pitch.of(PitchClass.D_DBL_SHARP, 1);

	/** The <em>E-double-flat</em> pitch in the 1st octave. */
	public static final Pitch EBB1 = Pitch.of(PitchClass.E_DBL_FLAT, 1);
	/** The <em>E-flat</em> pitch in the 1st octave. */
	public static final Pitch EB1 = Pitch.of(PitchClass.E_FLAT, 1);
	/** The <em>E</em> pitch in the 1st octave. */
	public static final Pitch E1 = Pitch.of(PitchClass.E, 1);
	/** The <em>E-sharp</em> pitch in the 1st octave. */
	public static final Pitch ES1 = Pitch.of(PitchClass.E_SHARP, 1);
	/** The <em>E-double-sharp</em> pitch in the 1st octave. */
	public static final Pitch EX1 = Pitch.of(PitchClass.E_DBL_SHARP, 1);

	/** The <em>F-double-flat</em> pitch in the 1st octave. */
	public static final Pitch FBB1 = Pitch.of(PitchClass.F_DBL_FLAT, 1);
	/** The <em>F-flat</em> pitch in the 1st octave. */
	public static final Pitch FB1 = Pitch.of(PitchClass.F_FLAT, 1);
	/** The <em>F</em> pitch in the 1st octave. */
	public static final Pitch F1 = Pitch.of(PitchClass.F, 1);
	/** The <em>F-sharp</em> pitch in the 1st octave. */
	public static final Pitch FS1 = Pitch.of(PitchClass.F_SHARP, 1);
	/** The <em>F-double-sharp</em> pitch in the 1st octave. */
	public static final Pitch FX1 = Pitch.of(PitchClass.F_DBL_SHARP, 1);

	/** The <em>G-double-flat</em> pitch in the 1st octave. */
	public static final Pitch GBB1 = Pitch.of(PitchClass.G_DBL_FLAT, 1);
	/** The <em>G-flat</em> pitch in the 1st octave. */
	public static final Pitch GB1 = Pitch.of(PitchClass.G_FLAT, 1);
	/** The <em>G</em> pitch in the 1st octave. */
	public static final Pitch G1 = Pitch.of(PitchClass.G, 1);
	/** The <em>G-sharp</em> pitch in the 1st octave. */
	public static final Pitch GS1 = Pitch.of(PitchClass.G_SHARP, 1);
	/** The <em>G-double-sharp</em> pitch in the 1st octave. */
	public static final Pitch GX1 = Pitch.of(PitchClass.G_DBL_SHARP, 1);

	/** The <em>A-double-flat</em> pitch in the 1st octave. */
	public static final Pitch ABB1 = Pitch.of(PitchClass.A_DBL_FLAT, 1);
	/** The <em>A-flat</em> pitch in the 1st octave. */
	public static final Pitch AB1 = Pitch.of(PitchClass.A_FLAT, 1);
	/** The <em>A</em> pitch in the 1st octave. */
	public static final Pitch A1 = Pitch.of(PitchClass.A, 1);
	/** The <em>A-sharp</em> pitch in the 1st octave. */
	public static final Pitch AS1 = Pitch.of(PitchClass.A_SHARP, 1);
	/** The <em>A-double-sharp</em> pitch in the 1st octave. */
	public static final Pitch AX1 = Pitch.of(PitchClass.A_DBL_SHARP, 1);

	/** The <em>B-double-flat</em> pitch in the 1st octave. */
	public static final Pitch BBB1 = Pitch.of(PitchClass.B_DBL_FLAT, 1);
	/** The <em>B-flat</em> pitch in the 1st octave. */
	public static final Pitch BB1 = Pitch.of(PitchClass.B_FLAT, 1);
	/** The <em>B</em> pitch in the 1st octave. */
	public static final Pitch B1 = Pitch.of(PitchClass.B, 1);
	/** The <em>B-sharp</em> pitch in the 1st octave. */
	public static final Pitch BS1 = Pitch.of(PitchClass.B_SHARP, 1);
	/** The <em>B-double-sharp</em> pitch in the 1st octave. */
	public static final Pitch BX1 = Pitch.of(PitchClass.B_DBL_SHARP, 1);

	/** The <em>C-double-flat</em> pitch in the 2nd octave. */
	public static final Pitch CBB2 = Pitch.of(PitchClass.C_DBL_FLAT, 2);
	/** The <em>C-flat</em> pitch in the 2nd octave. */
	public static final Pitch CB2 = Pitch.of(PitchClass.C_FLAT, 2);
	/** The <em>C</em> pitch in the 2nd octave. */
	public static final Pitch C2 = Pitch.of(PitchClass.C, 2);
	/** The <em>C-sharp</em> pitch in the 2nd octave. */
	public static final Pitch CS2 = Pitch.of(PitchClass.C_SHARP, 2);
	/** The <em>C-double-sharp</em> pitch in the 2nd octave. */
	public static final Pitch CX2 = Pitch.of(PitchClass.C_DBL_SHARP, 2);

	/** The <em>D-double-flat</em> pitch in the 2nd octave. */
	public static final Pitch DBB2 = Pitch.of(PitchClass.D_DBL_FLAT, 2);
	/** The <em>D-flat</em> pitch in the 2nd octave. */
	public static final Pitch DB2 = Pitch.of(PitchClass.D_FLAT, 2);
	/** The <em>D</em> pitch in the 2nd octave. */
	public static final Pitch D2 = Pitch.of(PitchClass.D, 2);
	/** The <em>D-sharp</em> pitch in the 2nd octave. */
	public static final Pitch DS2 = Pitch.of(PitchClass.D_SHARP, 2);
	/** The <em>D-double-sharp</em> pitch in the 2nd octave. */
	public static final Pitch DX2 = Pitch.of(PitchClass.D_DBL_SHARP, 2);

	/** The <em>E-double-flat</em> pitch in the 2nd octave. */
	public static final Pitch EBB2 = Pitch.of(PitchClass.E_DBL_FLAT, 2);
	/** The <em>E-flat</em> pitch in the 2nd octave. */
	public static final Pitch EB2 = Pitch.of(PitchClass.E_FLAT, 2);
	/** The <em>E</em> pitch in the 2nd octave. */
	public static final Pitch E2 = Pitch.of(PitchClass.E, 2);
	/** The <em>E-sharp</em> pitch in the 2nd octave. */
	public static final Pitch ES2 = Pitch.of(PitchClass.E_SHARP, 2);
	/** The <em>E-double-sharp</em> pitch in the 2nd octave. */
	public static final Pitch EX2 = Pitch.of(PitchClass.E_DBL_SHARP, 2);

	/** The <em>F-double-flat</em> pitch in the 2nd octave. */
	public static final Pitch FBB2 = Pitch.of(PitchClass.F_DBL_FLAT, 2);
	/** The <em>F-flat</em> pitch in the 2nd octave. */
	public static final Pitch FB2 = Pitch.of(PitchClass.F_FLAT, 2);
	/** The <em>F</em> pitch in the 2nd octave. */
	public static final Pitch F2 = Pitch.of(PitchClass.F, 2);
	/** The <em>F-sharp</em> pitch in the 2nd octave. */
	public static final Pitch FS2 = Pitch.of(PitchClass.F_SHARP, 2);
	/** The <em>F-double-sharp</em> pitch in the 2nd octave. */
	public static final Pitch FX2 = Pitch.of(PitchClass.F_DBL_SHARP, 2);

	/** The <em>G-double-flat</em> pitch in the 2nd octave. */
	public static final Pitch GBB2 = Pitch.of(PitchClass.G_DBL_FLAT, 2);
	/** The <em>G-flat</em> pitch in the 2nd octave. */
	public static final Pitch GB2 = Pitch.of(PitchClass.G_FLAT, 2);
	/** The <em>G</em> pitch in the 2nd octave. */
	public static final Pitch G2 = Pitch.of(PitchClass.G, 2);
	/** The <em>G-sharp</em> pitch in the 2nd octave. */
	public static final Pitch GS2 = Pitch.of(PitchClass.G_SHARP, 2);
	/** The <em>G-double-sharp</em> pitch in the 2nd octave. */
	public static final Pitch GX2 = Pitch.of(PitchClass.G_DBL_SHARP, 2);

	/** The <em>A-double-flat</em> pitch in the 2nd octave. */
	public static final Pitch ABB2 = Pitch.of(PitchClass.A_DBL_FLAT, 2);
	/** The <em>A-flat</em> pitch in the 2nd octave. */
	public static final Pitch AB2 = Pitch.of(PitchClass.A_FLAT, 2);
	/** The <em>A</em> pitch in the 2nd octave. */
	public static final Pitch A2 = Pitch.of(PitchClass.A, 2);
	/** The <em>A-sharp</em> pitch in the 2nd octave. */
	public static final Pitch AS2 = Pitch.of(PitchClass.A_SHARP, 2);
	/** The <em>A-double-sharp</em> pitch in the 2nd octave. */
	public static final Pitch AX2 = Pitch.of(PitchClass.A_DBL_SHARP, 2);

	/** The <em>B-double-flat</em> pitch in the 2nd octave. */
	public static final Pitch BBB2 = Pitch.of(PitchClass.B_DBL_FLAT, 2);
	/** The <em>B-flat</em> pitch in the 2nd octave. */
	public static final Pitch BB2 = Pitch.of(PitchClass.B_FLAT, 2);
	/** The <em>B</em> pitch in the 2nd octave. */
	public static final Pitch B2 = Pitch.of(PitchClass.B, 2);
	/** The <em>B-sharp</em> pitch in the 2nd octave. */
	public static final Pitch BS2 = Pitch.of(PitchClass.B_SHARP, 2);
	/** The <em>B-double-sharp</em> pitch in the 2nd octave. */
	public static final Pitch BX2 = Pitch.of(PitchClass.B_DBL_SHARP, 2);

	/** The <em>C-double-flat</em> pitch in the 3rd octave. */
	public static final Pitch CBB3 = Pitch.of(PitchClass.C_DBL_FLAT, 3);
	/** The <em>C-flat</em> pitch in the 3rd octave. */
	public static final Pitch CB3 = Pitch.of(PitchClass.C_FLAT, 3);
	/** The <em>C</em> pitch in the 3rd octave. */
	public static final Pitch C3 = Pitch.of(PitchClass.C, 3);
	/** The <em>C-sharp</em> pitch in the 3rd octave. */
	public static final Pitch CS3 = Pitch.of(PitchClass.C_SHARP, 3);
	/** The <em>C-double-sharp</em> pitch in the 3rd octave. */
	public static final Pitch CX3 = Pitch.of(PitchClass.C_DBL_SHARP, 3);

	/** The <em>D-double-flat</em> pitch in the 3rd octave. */
	public static final Pitch DBB3 = Pitch.of(PitchClass.D_DBL_FLAT, 3);
	/** The <em>D-flat</em> pitch in the 3rd octave. */
	public static final Pitch DB3 = Pitch.of(PitchClass.D_FLAT, 3);
	/** The <em>D</em> pitch in the 3rd octave. */
	public static final Pitch D3 = Pitch.of(PitchClass.D, 3);
	/** The <em>D-sharp</em> pitch in the 3rd octave. */
	public static final Pitch DS3 = Pitch.of(PitchClass.D_SHARP, 3);
	/** The <em>D-double-sharp</em> pitch in the 3rd octave. */
	public static final Pitch DX3 = Pitch.of(PitchClass.D_DBL_SHARP, 3);

	/** The <em>E-double-flat</em> pitch in the 3rd octave. */
	public static final Pitch EBB3 = Pitch.of(PitchClass.E_DBL_FLAT, 3);
	/** The <em>E-flat</em> pitch in the 3rd octave. */
	public static final Pitch EB3 = Pitch.of(PitchClass.E_FLAT, 3);
	/** The <em>E</em> pitch in the 3rd octave. */
	public static final Pitch E3 = Pitch.of(PitchClass.E, 3);
	/** The <em>E-sharp</em> pitch in the 3rd octave. */
	public static final Pitch ES3 = Pitch.of(PitchClass.E_SHARP, 3);
	/** The <em>E-double-sharp</em> pitch in the 3rd octave. */
	public static final Pitch EX3 = Pitch.of(PitchClass.E_DBL_SHARP, 3);

	/** The <em>F-double-flat</em> pitch in the 3rd octave. */
	public static final Pitch FBB3 = Pitch.of(PitchClass.F_DBL_FLAT, 3);
	/** The <em>F-flat</em> pitch in the 3rd octave. */
	public static final Pitch FB3 = Pitch.of(PitchClass.F_FLAT, 3);
	/** The <em>F</em> pitch in the 3rd octave. */
	public static final Pitch F3 = Pitch.of(PitchClass.F, 3);
	/** The <em>F-sharp</em> pitch in the 3rd octave. */
	public static final Pitch FS3 = Pitch.of(PitchClass.F_SHARP, 3);
	/** The <em>F-double-sharp</em> pitch in the 3rd octave. */
	public static final Pitch FX3 = Pitch.of(PitchClass.F_DBL_SHARP, 3);

	/** The <em>G-double-flat</em> pitch in the 3rd octave. */
	public static final Pitch GBB3 = Pitch.of(PitchClass.G_DBL_FLAT, 3);
	/** The <em>G-flat</em> pitch in the 3rd octave. */
	public static final Pitch GB3 = Pitch.of(PitchClass.G_FLAT, 3);
	/** The <em>G</em> pitch in the 3rd octave. */
	public static final Pitch G3 = Pitch.of(PitchClass.G, 3);
	/** The <em>G-sharp</em> pitch in the 3rd octave. */
	public static final Pitch GS3 = Pitch.of(PitchClass.G_SHARP, 3);
	/** The <em>G-double-sharp</em> pitch in the 3rd octave. */
	public static final Pitch GX3 = Pitch.of(PitchClass.G_DBL_SHARP, 3);

	/** The <em>A-double-flat</em> pitch in the 3rd octave. */
	public static final Pitch ABB3 = Pitch.of(PitchClass.A_DBL_FLAT, 3);
	/** The <em>A-flat</em> pitch in the 3rd octave. */
	public static final Pitch AB3 = Pitch.of(PitchClass.A_FLAT, 3);
	/** The <em>A</em> pitch in the 3rd octave. */
	public static final Pitch A3 = Pitch.of(PitchClass.A, 3);
	/** The <em>A-sharp</em> pitch in the 3rd octave. */
	public static final Pitch AS3 = Pitch.of(PitchClass.A_SHARP, 3);
	/** The <em>A-double-sharp</em> pitch in the 3rd octave. */
	public static final Pitch AX3 = Pitch.of(PitchClass.A_DBL_SHARP, 3);

	/** The <em>B-double-flat</em> pitch in the 3rd octave. */
	public static final Pitch BBB3 = Pitch.of(PitchClass.B_DBL_FLAT, 3);
	/** The <em>B-flat</em> pitch in the 3rd octave. */
	public static final Pitch BB3 = Pitch.of(PitchClass.B_FLAT, 3);
	/** The <em>B</em> pitch in the 3rd octave. */
	public static final Pitch B3 = Pitch.of(PitchClass.B, 3);
	/** The <em>B-sharp</em> pitch in the 3rd octave. */
	public static final Pitch BS3 = Pitch.of(PitchClass.B_SHARP, 3);
	/** The <em>B-double-sharp</em> pitch in the 3rd octave. */
	public static final Pitch BX3 = Pitch.of(PitchClass.B_DBL_SHARP, 3);

	/** The <em>C-double-flat</em> pitch in the 4th octave. */
	public static final Pitch CBB4 = Pitch.of(PitchClass.C_DBL_FLAT, 4);
	/** The <em>C-flat</em> pitch in the 4th octave. */
	public static final Pitch CB4 = Pitch.of(PitchClass.C_FLAT, 4);
	/** The <em>C</em> pitch in the 4th octave. */
	public static final Pitch C4 = Pitch.of(PitchClass.C, 4);
	/** The <em>C-sharp</em> pitch in the 4th octave. */
	public static final Pitch CS4 = Pitch.of(PitchClass.C_SHARP, 4);
	/** The <em>C-double-sharp</em> pitch in the 4th octave. */
	public static final Pitch CX4 = Pitch.of(PitchClass.C_DBL_SHARP, 4);

	/** The <em>D-double-flat</em> pitch in the 4th octave. */
	public static final Pitch DBB4 = Pitch.of(PitchClass.D_DBL_FLAT, 4);
	/** The <em>D-flat</em> pitch in the 4th octave. */
	public static final Pitch DB4 = Pitch.of(PitchClass.D_FLAT, 4);
	/** The <em>D</em> pitch in the 4th octave. */
	public static final Pitch D4 = Pitch.of(PitchClass.D, 4);
	/** The <em>D-sharp</em> pitch in the 4th octave. */
	public static final Pitch DS4 = Pitch.of(PitchClass.D_SHARP, 4);
	/** The <em>D-double-sharp</em> pitch in the 4th octave. */
	public static final Pitch DX4 = Pitch.of(PitchClass.D_DBL_SHARP, 4);

	/** The <em>E-double-flat</em> pitch in the 4th octave. */
	public static final Pitch EBB4 = Pitch.of(PitchClass.E_DBL_FLAT, 4);
	/** The <em>E-flat</em> pitch in the 4th octave. */
	public static final Pitch EB4 = Pitch.of(PitchClass.E_FLAT, 4);
	/** The <em>E</em> pitch in the 4th octave. */
	public static final Pitch E4 = Pitch.of(PitchClass.E, 4);
	/** The <em>E-sharp</em> pitch in the 4th octave. */
	public static final Pitch ES4 = Pitch.of(PitchClass.E_SHARP, 4);
	/** The <em>E-double-sharp</em> pitch in the 4th octave. */
	public static final Pitch EX4 = Pitch.of(PitchClass.E_DBL_SHARP, 4);

	/** The <em>F-double-flat</em> pitch in the 4th octave. */
	public static final Pitch FBB4 = Pitch.of(PitchClass.F_DBL_FLAT, 4);
	/** The <em>F-flat</em> pitch in the 4th octave. */
	public static final Pitch FB4 = Pitch.of(PitchClass.F_FLAT, 4);
	/** The <em>F</em> pitch in the 4th octave. */
	public static final Pitch F4 = Pitch.of(PitchClass.F, 4);
	/** The <em>F-sharp</em> pitch in the 4th octave. */
	public static final Pitch FS4 = Pitch.of(PitchClass.F_SHARP, 4);
	/** The <em>F-double-sharp</em> pitch in the 4th octave. */
	public static final Pitch FX4 = Pitch.of(PitchClass.F_DBL_SHARP, 4);

	/** The <em>G-double-flat</em> pitch in the 4th octave. */
	public static final Pitch GBB4 = Pitch.of(PitchClass.G_DBL_FLAT, 4);
	/** The <em>G-flat</em> pitch in the 4th octave. */
	public static final Pitch GB4 = Pitch.of(PitchClass.G_FLAT, 4);
	/** The <em>G</em> pitch in the 4th octave. */
	public static final Pitch G4 = Pitch.of(PitchClass.G, 4);
	/** The <em>G-sharp</em> pitch in the 4th octave. */
	public static final Pitch GS4 = Pitch.of(PitchClass.G_SHARP, 4);
	/** The <em>G-double-sharp</em> pitch in the 4th octave. */
	public static final Pitch GX4 = Pitch.of(PitchClass.G_DBL_SHARP, 4);

	/** The <em>A-double-flat</em> pitch in the 4th octave. */
	public static final Pitch ABB4 = Pitch.of(PitchClass.A_DBL_FLAT, 4);
	/** The <em>A-flat</em> pitch in the 4th octave. */
	public static final Pitch AB4 = Pitch.of(PitchClass.A_FLAT, 4);
	/** The <em>A</em> pitch in the 4th octave. */
	public static final Pitch A4 = Pitch.of(PitchClass.A, 4);
	/** The <em>A-sharp</em> pitch in the 4th octave. */
	public static final Pitch AS4 = Pitch.of(PitchClass.A_SHARP, 4);
	/** The <em>A-double-sharp</em> pitch in the 4th octave. */
	public static final Pitch AX4 = Pitch.of(PitchClass.A_DBL_SHARP, 4);

	/** The <em>B-double-flat</em> pitch in the 4th octave. */
	public static final Pitch BBB4 = Pitch.of(PitchClass.B_DBL_FLAT, 4);
	/** The <em>B-flat</em> pitch in the 4th octave. */
	public static final Pitch BB4 = Pitch.of(PitchClass.B_FLAT, 4);
	/** The <em>B</em> pitch in the 4th octave. */
	public static final Pitch B4 = Pitch.of(PitchClass.B, 4);
	/** The <em>B-sharp</em> pitch in the 4th octave. */
	public static final Pitch BS4 = Pitch.of(PitchClass.B_SHARP, 4);
	/** The <em>B-double-sharp</em> pitch in the 4th octave. */
	public static final Pitch BX4 = Pitch.of(PitchClass.B_DBL_SHARP, 4);

	/** The <em>C-double-flat</em> pitch in the 5th octave. */
	public static final Pitch CBB5 = Pitch.of(PitchClass.C_DBL_FLAT, 5);
	/** The <em>C-flat</em> pitch in the 5th octave. */
	public static final Pitch CB5 = Pitch.of(PitchClass.C_FLAT, 5);
	/** The <em>C</em> pitch in the 5th octave. */
	public static final Pitch C5 = Pitch.of(PitchClass.C, 5);
	/** The <em>C-sharp</em> pitch in the 5th octave. */
	public static final Pitch CS5 = Pitch.of(PitchClass.C_SHARP, 5);
	/** The <em>C-double-sharp</em> pitch in the 5th octave. */
	public static final Pitch CX5 = Pitch.of(PitchClass.C_DBL_SHARP, 5);

	/** The <em>D-double-flat</em> pitch in the 5th octave. */
	public static final Pitch DBB5 = Pitch.of(PitchClass.D_DBL_FLAT, 5);
	/** The <em>D-flat</em> pitch in the 5th octave. */
	public static final Pitch DB5 = Pitch.of(PitchClass.D_FLAT, 5);
	/** The <em>D</em> pitch in the 5th octave. */
	public static final Pitch D5 = Pitch.of(PitchClass.D, 5);
	/** The <em>D-sharp</em> pitch in the 5th octave. */
	public static final Pitch DS5 = Pitch.of(PitchClass.D_SHARP, 5);
	/** The <em>D-double-sharp</em> pitch in the 5th octave. */
	public static final Pitch DX5 = Pitch.of(PitchClass.D_DBL_SHARP, 5);

	/** The <em>E-double-flat</em> pitch in the 5th octave. */
	public static final Pitch EBB5 = Pitch.of(PitchClass.E_DBL_FLAT, 5);
	/** The <em>E-flat</em> pitch in the 5th octave. */
	public static final Pitch EB5 = Pitch.of(PitchClass.E_FLAT, 5);
	/** The <em>E</em> pitch in the 5th octave. */
	public static final Pitch E5 = Pitch.of(PitchClass.E, 5);
	/** The <em>E-sharp</em> pitch in the 5th octave. */
	public static final Pitch ES5 = Pitch.of(PitchClass.E_SHARP, 5);
	/** The <em>E-double-sharp</em> pitch in the 5th octave. */
	public static final Pitch EX5 = Pitch.of(PitchClass.E_DBL_SHARP, 5);

	/** The <em>F-double-flat</em> pitch in the 5th octave. */
	public static final Pitch FBB5 = Pitch.of(PitchClass.F_DBL_FLAT, 5);
	/** The <em>F-flat</em> pitch in the 5th octave. */
	public static final Pitch FB5 = Pitch.of(PitchClass.F_FLAT, 5);
	/** The <em>F</em> pitch in the 5th octave. */
	public static final Pitch F5 = Pitch.of(PitchClass.F, 5);
	/** The <em>F-sharp</em> pitch in the 5th octave. */
	public static final Pitch FS5 = Pitch.of(PitchClass.F_SHARP, 5);
	/** The <em>F-double-sharp</em> pitch in the 5th octave. */
	public static final Pitch FX5 = Pitch.of(PitchClass.F_DBL_SHARP, 5);

	/** The <em>G-double-flat</em> pitch in the 5th octave. */
	public static final Pitch GBB5 = Pitch.of(PitchClass.G_DBL_FLAT, 5);
	/** The <em>G-flat</em> pitch in the 5th octave. */
	public static final Pitch GB5 = Pitch.of(PitchClass.G_FLAT, 5);
	/** The <em>G</em> pitch in the 5th octave. */
	public static final Pitch G5 = Pitch.of(PitchClass.G, 5);
	/** The <em>G-sharp</em> pitch in the 5th octave. */
	public static final Pitch GS5 = Pitch.of(PitchClass.G_SHARP, 5);
	/** The <em>G-double-sharp</em> pitch in the 5th octave. */
	public static final Pitch GX5 = Pitch.of(PitchClass.G_DBL_SHARP, 5);

	/** The <em>A-double-flat</em> pitch in the 5th octave. */
	public static final Pitch ABB5 = Pitch.of(PitchClass.A_DBL_FLAT, 5);
	/** The <em>A-flat</em> pitch in the 5th octave. */
	public static final Pitch AB5 = Pitch.of(PitchClass.A_FLAT, 5);
	/** The <em>A</em> pitch in the 5th octave. */
	public static final Pitch A5 = Pitch.of(PitchClass.A, 5);
	/** The <em>A-sharp</em> pitch in the 5th octave. */
	public static final Pitch AS5 = Pitch.of(PitchClass.A_SHARP, 5);
	/** The <em>A-double-sharp</em> pitch in the 5th octave. */
	public static final Pitch AX5 = Pitch.of(PitchClass.A_DBL_SHARP, 5);

	/** The <em>B-double-flat</em> pitch in the 5th octave. */
	public static final Pitch BBB5 = Pitch.of(PitchClass.B_DBL_FLAT, 5);
	/** The <em>B-flat</em> pitch in the 5th octave. */
	public static final Pitch BB5 = Pitch.of(PitchClass.B_FLAT, 5);
	/** The <em>B</em> pitch in the 5th octave. */
	public static final Pitch B5 = Pitch.of(PitchClass.B, 5);
	/** The <em>B-sharp</em> pitch in the 5th octave. */
	public static final Pitch BS5 = Pitch.of(PitchClass.B_SHARP, 5);
	/** The <em>B-double-sharp</em> pitch in the 5th octave. */
	public static final Pitch BX5 = Pitch.of(PitchClass.B_DBL_SHARP, 5);

	/** The <em>C-double-flat</em> pitch in the 6th octave. */
	public static final Pitch CBB6 = Pitch.of(PitchClass.C_DBL_FLAT, 6);
	/** The <em>C-flat</em> pitch in the 6th octave. */
	public static final Pitch CB6 = Pitch.of(PitchClass.C_FLAT, 6);
	/** The <em>C</em> pitch in the 6th octave. */
	public static final Pitch C6 = Pitch.of(PitchClass.C, 6);
	/** The <em>C-sharp</em> pitch in the 6th octave. */
	public static final Pitch CS6 = Pitch.of(PitchClass.C_SHARP, 6);
	/** The <em>C-double-sharp</em> pitch in the 6th octave. */
	public static final Pitch CX6 = Pitch.of(PitchClass.C_DBL_SHARP, 6);

	/** The <em>D-double-flat</em> pitch in the 6th octave. */
	public static final Pitch DBB6 = Pitch.of(PitchClass.D_DBL_FLAT, 6);
	/** The <em>D-flat</em> pitch in the 6th octave. */
	public static final Pitch DB6 = Pitch.of(PitchClass.D_FLAT, 6);
	/** The <em>D</em> pitch in the 6th octave. */
	public static final Pitch D6 = Pitch.of(PitchClass.D, 6);
	/** The <em>D-sharp</em> pitch in the 6th octave. */
	public static final Pitch DS6 = Pitch.of(PitchClass.D_SHARP, 6);
	/** The <em>D-double-sharp</em> pitch in the 6th octave. */
	public static final Pitch DX6 = Pitch.of(PitchClass.D_DBL_SHARP, 6);

	/** The <em>E-double-flat</em> pitch in the 6th octave. */
	public static final Pitch EBB6 = Pitch.of(PitchClass.E_DBL_FLAT, 6);
	/** The <em>E-flat</em> pitch in the 6th octave. */
	public static final Pitch EB6 = Pitch.of(PitchClass.E_FLAT, 6);
	/** The <em>E</em> pitch in the 6th octave. */
	public static final Pitch E6 = Pitch.of(PitchClass.E, 6);
	/** The <em>E-sharp</em> pitch in the 6th octave. */
	public static final Pitch ES6 = Pitch.of(PitchClass.E_SHARP, 6);
	/** The <em>E-double-sharp</em> pitch in the 6th octave. */
	public static final Pitch EX6 = Pitch.of(PitchClass.E_DBL_SHARP, 6);

	/** The <em>F-double-flat</em> pitch in the 6th octave. */
	public static final Pitch FBB6 = Pitch.of(PitchClass.F_DBL_FLAT, 6);
	/** The <em>F-flat</em> pitch in the 6th octave. */
	public static final Pitch FB6 = Pitch.of(PitchClass.F_FLAT, 6);
	/** The <em>F</em> pitch in the 6th octave. */
	public static final Pitch F6 = Pitch.of(PitchClass.F, 6);
	/** The <em>F-sharp</em> pitch in the 6th octave. */
	public static final Pitch FS6 = Pitch.of(PitchClass.F_SHARP, 6);
	/** The <em>F-double-sharp</em> pitch in the 6th octave. */
	public static final Pitch FX6 = Pitch.of(PitchClass.F_DBL_SHARP, 6);

	/** The <em>G-double-flat</em> pitch in the 6th octave. */
	public static final Pitch GBB6 = Pitch.of(PitchClass.G_DBL_FLAT, 6);
	/** The <em>G-flat</em> pitch in the 6th octave. */
	public static final Pitch GB6 = Pitch.of(PitchClass.G_FLAT, 6);
	/** The <em>G</em> pitch in the 6th octave. */
	public static final Pitch G6 = Pitch.of(PitchClass.G, 6);
	/** The <em>G-sharp</em> pitch in the 6th octave. */
	public static final Pitch GS6 = Pitch.of(PitchClass.G_SHARP, 6);
	/** The <em>G-double-sharp</em> pitch in the 6th octave. */
	public static final Pitch GX6 = Pitch.of(PitchClass.G_DBL_SHARP, 6);

	/** The <em>A-double-flat</em> pitch in the 6th octave. */
	public static final Pitch ABB6 = Pitch.of(PitchClass.A_DBL_FLAT, 6);
	/** The <em>A-flat</em> pitch in the 6th octave. */
	public static final Pitch AB6 = Pitch.of(PitchClass.A_FLAT, 6);
	/** The <em>A</em> pitch in the 6th octave. */
	public static final Pitch A6 = Pitch.of(PitchClass.A, 6);
	/** The <em>A-sharp</em> pitch in the 6th octave. */
	public static final Pitch AS6 = Pitch.of(PitchClass.A_SHARP, 6);
	/** The <em>A-double-sharp</em> pitch in the 6th octave. */
	public static final Pitch AX6 = Pitch.of(PitchClass.A_DBL_SHARP, 6);

	/** The <em>B-double-flat</em> pitch in the 6th octave. */
	public static final Pitch BBB6 = Pitch.of(PitchClass.B_DBL_FLAT, 6);
	/** The <em>B-flat</em> pitch in the 6th octave. */
	public static final Pitch BB6 = Pitch.of(PitchClass.B_FLAT, 6);
	/** The <em>B</em> pitch in the 6th octave. */
	public static final Pitch B6 = Pitch.of(PitchClass.B, 6);
	/** The <em>B-sharp</em> pitch in the 6th octave. */
	public static final Pitch BS6 = Pitch.of(PitchClass.B_SHARP, 6);
	/** The <em>B-double-sharp</em> pitch in the 6th octave. */
	public static final Pitch BX6 = Pitch.of(PitchClass.B_DBL_SHARP, 6);

	/** The <em>C-double-flat</em> pitch in the 7th octave. */
	public static final Pitch CBB7 = Pitch.of(PitchClass.C_DBL_FLAT, 7);
	/** The <em>C-flat</em> pitch in the 7th octave. */
	public static final Pitch CB7 = Pitch.of(PitchClass.C_FLAT, 7);
	/** The <em>C</em> pitch in the 7th octave. */
	public static final Pitch C7 = Pitch.of(PitchClass.C, 7);
	/** The <em>C-sharp</em> pitch in the 7th octave. */
	public static final Pitch CS7 = Pitch.of(PitchClass.C_SHARP, 7);
	/** The <em>C-double-sharp</em> pitch in the 7th octave. */
	public static final Pitch CX7 = Pitch.of(PitchClass.C_DBL_SHARP, 7);

	/** The <em>D-double-flat</em> pitch in the 7th octave. */
	public static final Pitch DBB7 = Pitch.of(PitchClass.D_DBL_FLAT, 7);
	/** The <em>D-flat</em> pitch in the 7th octave. */
	public static final Pitch DB7 = Pitch.of(PitchClass.D_FLAT, 7);
	/** The <em>D</em> pitch in the 7th octave. */
	public static final Pitch D7 = Pitch.of(PitchClass.D, 7);
	/** The <em>D-sharp</em> pitch in the 7th octave. */
	public static final Pitch DS7 = Pitch.of(PitchClass.D_SHARP, 7);
	/** The <em>D-double-sharp</em> pitch in the 7th octave. */
	public static final Pitch DX7 = Pitch.of(PitchClass.D_DBL_SHARP, 7);

	/** The <em>E-double-flat</em> pitch in the 7th octave. */
	public static final Pitch EBB7 = Pitch.of(PitchClass.E_DBL_FLAT, 7);
	/** The <em>E-flat</em> pitch in the 7th octave. */
	public static final Pitch EB7 = Pitch.of(PitchClass.E_FLAT, 7);
	/** The <em>E</em> pitch in the 7th octave. */
	public static final Pitch E7 = Pitch.of(PitchClass.E, 7);
	/** The <em>E-sharp</em> pitch in the 7th octave. */
	public static final Pitch ES7 = Pitch.of(PitchClass.E_SHARP, 7);
	/** The <em>E-double-sharp</em> pitch in the 7th octave. */
	public static final Pitch EX7 = Pitch.of(PitchClass.E_DBL_SHARP, 7);

	/** The <em>F-double-flat</em> pitch in the 7th octave. */
	public static final Pitch FBB7 = Pitch.of(PitchClass.F_DBL_FLAT, 7);
	/** The <em>F-flat</em> pitch in the 7th octave. */
	public static final Pitch FB7 = Pitch.of(PitchClass.F_FLAT, 7);
	/** The <em>F</em> pitch in the 7th octave. */
	public static final Pitch F7 = Pitch.of(PitchClass.F, 7);
	/** The <em>F-sharp</em> pitch in the 7th octave. */
	public static final Pitch FS7 = Pitch.of(PitchClass.F_SHARP, 7);
	/** The <em>F-double-sharp</em> pitch in the 7th octave. */
	public static final Pitch FX7 = Pitch.of(PitchClass.F_DBL_SHARP, 7);

	/** The <em>G-double-flat</em> pitch in the 7th octave. */
	public static final Pitch GBB7 = Pitch.of(PitchClass.G_DBL_FLAT, 7);
	/** The <em>G-flat</em> pitch in the 7th octave. */
	public static final Pitch GB7 = Pitch.of(PitchClass.G_FLAT, 7);
	/** The <em>G</em> pitch in the 7th octave. */
	public static final Pitch G7 = Pitch.of(PitchClass.G, 7);
	/** The <em>G-sharp</em> pitch in the 7th octave. */
	public static final Pitch GS7 = Pitch.of(PitchClass.G_SHARP, 7);
	/** The <em>G-double-sharp</em> pitch in the 7th octave. */
	public static final Pitch GX7 = Pitch.of(PitchClass.G_DBL_SHARP, 7);

	/** The <em>A-double-flat</em> pitch in the 7th octave. */
	public static final Pitch ABB7 = Pitch.of(PitchClass.A_DBL_FLAT, 7);
	/** The <em>A-flat</em> pitch in the 7th octave. */
	public static final Pitch AB7 = Pitch.of(PitchClass.A_FLAT, 7);
	/** The <em>A</em> pitch in the 7th octave. */
	public static final Pitch A7 = Pitch.of(PitchClass.A, 7);
	/** The <em>A-sharp</em> pitch in the 7th octave. */
	public static final Pitch AS7 = Pitch.of(PitchClass.A_SHARP, 7);
	/** The <em>A-double-sharp</em> pitch in the 7th octave. */
	public static final Pitch AX7 = Pitch.of(PitchClass.A_DBL_SHARP, 7);

	/** The <em>B-double-flat</em> pitch in the 7th octave. */
	public static final Pitch BBB7 = Pitch.of(PitchClass.B_DBL_FLAT, 7);
	/** The <em>B-flat</em> pitch in the 7th octave. */
	public static final Pitch BB7 = Pitch.of(PitchClass.B_FLAT, 7);
	/** The <em>B</em> pitch in the 7th octave. */
	public static final Pitch B7 = Pitch.of(PitchClass.B, 7);
	/** The <em>B-sharp</em> pitch in the 7th octave. */
	public static final Pitch BS7 = Pitch.of(PitchClass.B_SHARP, 7);
	/** The <em>B-double-sharp</em> pitch in the 7th octave. */
	public static final Pitch BX7 = Pitch.of(PitchClass.B_DBL_SHARP, 7);

	/** The <em>C-double-flat</em> pitch in the 8th octave. */
	public static final Pitch CBB8 = Pitch.of(PitchClass.C_DBL_FLAT, 8);
	/** The <em>C-flat</em> pitch in the 8th octave. */
	public static final Pitch CB8 = Pitch.of(PitchClass.C_FLAT, 8);
	/** The <em>C</em> pitch in the 8th octave. */
	public static final Pitch C8 = Pitch.of(PitchClass.C, 8);
	/** The <em>C-sharp</em> pitch in the 8th octave. */
	public static final Pitch CS8 = Pitch.of(PitchClass.C_SHARP, 8);
	/** The <em>C-double-sharp</em> pitch in the 8th octave. */
	public static final Pitch CX8 = Pitch.of(PitchClass.C_DBL_SHARP, 8);

	/** The <em>D-double-flat</em> pitch in the 8th octave. */
	public static final Pitch DBB8 = Pitch.of(PitchClass.D_DBL_FLAT, 8);
	/** The <em>D-flat</em> pitch in the 8th octave. */
	public static final Pitch DB8 = Pitch.of(PitchClass.D_FLAT, 8);
	/** The <em>D</em> pitch in the 8th octave. */
	public static final Pitch D8 = Pitch.of(PitchClass.D, 8);
	/** The <em>D-sharp</em> pitch in the 8th octave. */
	public static final Pitch DS8 = Pitch.of(PitchClass.D_SHARP, 8);
	/** The <em>D-double-sharp</em> pitch in the 8th octave. */
	public static final Pitch DX8 = Pitch.of(PitchClass.D_DBL_SHARP, 8);

	/** The <em>E-double-flat</em> pitch in the 8th octave. */
	public static final Pitch EBB8 = Pitch.of(PitchClass.E_DBL_FLAT, 8);
	/** The <em>E-flat</em> pitch in the 8th octave. */
	public static final Pitch EB8 = Pitch.of(PitchClass.E_FLAT, 8);
	/** The <em>E</em> pitch in the 8th octave. */
	public static final Pitch E8 = Pitch.of(PitchClass.E, 8);
	/** The <em>E-sharp</em> pitch in the 8th octave. */
	public static final Pitch ES8 = Pitch.of(PitchClass.E_SHARP, 8);
	/** The <em>E-double-sharp</em> pitch in the 8th octave. */
	public static final Pitch EX8 = Pitch.of(PitchClass.E_DBL_SHARP, 8);

	/** The <em>F-double-flat</em> pitch in the 8th octave. */
	public static final Pitch FBB8 = Pitch.of(PitchClass.F_DBL_FLAT, 8);
	/** The <em>F-flat</em> pitch in the 8th octave. */
	public static final Pitch FB8 = Pitch.of(PitchClass.F_FLAT, 8);
	/** The <em>F</em> pitch in the 8th octave. */
	public static final Pitch F8 = Pitch.of(PitchClass.F, 8);
	/** The <em>F-sharp</em> pitch in the 8th octave. */
	public static final Pitch FS8 = Pitch.of(PitchClass.F_SHARP, 8);
	/** The <em>F-double-sharp</em> pitch in the 8th octave. */
	public static final Pitch FX8 = Pitch.of(PitchClass.F_DBL_SHARP, 8);

	/** The <em>G-double-flat</em> pitch in the 8th octave. */
	public static final Pitch GBB8 = Pitch.of(PitchClass.G_DBL_FLAT, 8);
	/** The <em>G-flat</em> pitch in the 8th octave. */
	public static final Pitch GB8 = Pitch.of(PitchClass.G_FLAT, 8);
	/** The <em>G</em> pitch in the 8th octave. */
	public static final Pitch G8 = Pitch.of(PitchClass.G, 8);
	/** The <em>G-sharp</em> pitch in the 8th octave. */
	public static final Pitch GS8 = Pitch.of(PitchClass.G_SHARP, 8);
	/** The <em>G-double-sharp</em> pitch in the 8th octave. */
	public static final Pitch GX8 = Pitch.of(PitchClass.G_DBL_SHARP, 8);

	/** The <em>A-double-flat</em> pitch in the 8th octave. */
	public static final Pitch ABB8 = Pitch.of(PitchClass.A_DBL_FLAT, 8);
	/** The <em>A-flat</em> pitch in the 8th octave. */
	public static final Pitch AB8 = Pitch.of(PitchClass.A_FLAT, 8);
	/** The <em>A</em> pitch in the 8th octave. */
	public static final Pitch A8 = Pitch.of(PitchClass.A, 8);
	/** The <em>A-sharp</em> pitch in the 8th octave. */
	public static final Pitch AS8 = Pitch.of(PitchClass.A_SHARP, 8);
	/** The <em>A-double-sharp</em> pitch in the 8th octave. */
	public static final Pitch AX8 = Pitch.of(PitchClass.A_DBL_SHARP, 8);

	/** The <em>B-double-flat</em> pitch in the 8th octave. */
	public static final Pitch BBB8 = Pitch.of(PitchClass.B_DBL_FLAT, 8);
	/** The <em>B-flat</em> pitch in the 8th octave. */
	public static final Pitch BB8 = Pitch.of(PitchClass.B_FLAT, 8);
	/** The <em>B</em> pitch in the 8th octave. */
	public static final Pitch B8 = Pitch.of(PitchClass.B, 8);
	/** The <em>B-sharp</em> pitch in the 8th octave. */
	public static final Pitch BS8 = Pitch.of(PitchClass.B_SHARP, 8);
	/** The <em>B-double-sharp</em> pitch in the 8th octave. */
	public static final Pitch BX8 = Pitch.of(PitchClass.B_DBL_SHARP, 8);

	private Pitch(PitchClass pitchClass, int octave) {
		this.pitchClass = pitchClass;
		this.octave = octave;
		this.pitch = (octave * SEMITONES) + pitchClass.stepsAboveReference();
	}

	/**
	 * Returns a pitch of the given pitch class and the given octave.
	 * This method returns a pre-cached value for the common pitch classes
	 * (C, D, E, F, G, A, B, and their single and double flats and sharps)
	 * in octaves between 0 and 8 (inclusive).
	 *
	 * @param pitchClass the pitch class of the pitch
	 * @param octave the octave of the pitch
	 * @return a pitch of {@code pitchClass} in {@code octave}
	 */
	public static Pitch of(PitchClass pitchClass, int octave) {
		int acc = pitchClass.accidental().stepsAboveNatural();
		if (acc >= -2 && acc <= 2 && octave >= 0 && octave < 9) {
			return commonPitches.get(pitchClass.basePitchClass())
					.get(acc + 2).get(octave);
		} else {
			return new Pitch(pitchClass, octave);
		}
	}

	/**
	 * Returns a pitch of the given pitch class and the given octave.
	 * This method returns a pre-cached value for the common pitch classes
	 * (C, D, E, F, G, A, B, and their single and double flats and sharps)
	 * in octaves between 0 and 8 (inclusive).
	 *
	 * @param base the base pitch class of the pitch
	 * @param accidental the accidental of the pitch
	 * @param octave the octave of the pitch
	 * @return a pitch of {@code pitchClass} in {@code octave}
	 */
	public static Pitch of(BasePitchClass base, Accidental accidental,
	                       int octave) {
		int acc = accidental.stepsAboveNatural();
		if (acc >= -2 && acc <= 2 && octave >= 0 && octave < 9) {
			return commonPitches.get(base).get(acc + 2).get(octave);
		} else {
			return new Pitch(PitchClass.of(base, accidental), octave);
		}
	}

	private static Pitch ofAbsolutePitch(PitchClass pitchClass,
	                                     int pitchNumber) {
		int difference = pitchNumber - pitchClass.stepsAboveReference();
		if (difference % SEMITONES != 0) {
			throw new IllegalArgumentException("The pitch class of "
					+ pitchNumber + " is not " + pitchClass + ", as desired");
		}
		int octave = difference / SEMITONES;	// No remainder
		return Pitch.of(pitchClass, octave);
	}

	/**
	 * Returns the lowest pitch of a given pitch class which is strictly
	 * higher than a specified pitch.
	 *
	 * @param target the target pitch class of the pitch to be returned
	 * @param lowerBound the pitch which must be exceeded by the
	 *        returned pitch
	 * @return the lowest higher pitch of {@code target} pitch class
	 */
	public static Pitch nearestAbove(PitchClass target, Pitch lowerBound) {
		int absDifference = target.stepsAboveReference()
				- lowerBound.pitchClass.stepsAboveReference();
		int modDifference = Util.floorMod(absDifference, SEMITONES);
		if (modDifference == 0) modDifference += SEMITONES;
		return Pitch.ofAbsolutePitch(target, lowerBound.pitch + modDifference);
	}

	/**
	 * Returns the lowest pitch of a given pitch class which is higher
	 * than or equal to a specified pitch.
	 *
	 * @param target the target pitch class of the pitch to be returned
	 * @param lowerBound the lowest allowable pitch to be returned
	 * @return the lowest higher or equal pitch of {@code target} pitch class
	 */
	public static Pitch nearestAboveOrEqual(PitchClass target, Pitch lowerBound) {
		if (lowerBound.pitchClass.equals(target)) {
			return lowerBound;
		} else {
			return nearestAbove(target, lowerBound);
		}
	}

	/**
	 * Returns the lowest pitch of a given pitch class which is higher
	 * than or enharmonic to a specified pitch.
	 *
	 * @param target the target pitch class of the pitch to be returned
	 * @param lowerBound the lowest allowable pitch to be returned
	 * @return the lowest higher or enharmonic pitch of {@code target} pitch class
	 */
	public static Pitch nearestAboveOrEnharmonic(PitchClass target, Pitch lowerBound) {
		if (lowerBound.pitchClass.isEnharmonicWith(target)) {
			return Pitch.ofAbsolutePitch(target, lowerBound.pitch);
		} else {
			return nearestAbove(target, lowerBound);
		}
	}

	/**
	 * Returns the highest pitch of a given pitch class which is strictly
	 * lower than a specified pitch.
	 *
	 * @param target the target pitch class of the pitch to be returned
	 * @param upperBound the pitch which must be higher than the
	 *        returned pitch
	 * @return the highest lower pitch of {@code target} pitch class
	 */
	public static Pitch nearestBelow(PitchClass target, Pitch upperBound) {
		int absDifference = upperBound.pitchClass.stepsAboveReference()
				- target.stepsAboveReference();
		int modDifference = Util.floorMod(absDifference, SEMITONES);
		if (modDifference == 0) modDifference += SEMITONES;
		return Pitch.ofAbsolutePitch(target, upperBound.pitch - modDifference);
	}

	/**
	 * Returns the highest pitch of a given pitch class which is lower
	 * than or equal to a specified pitch.
	 *
	 * @param target the target pitch class of the pitch to be returned
	 * @param upperBound the highest allowable pitch to be returned
	 * @return the highest lower or equal pitch of {@code target} pitch class
	 */
	public static Pitch nearestBelowOrEqual(PitchClass target, Pitch upperBound) {
		if (upperBound.pitchClass.equals(target)) {
			return upperBound;
		} else {
			return nearestBelow(target, upperBound);
		}
	}

	/**
	 * Returns the highest pitch of a given pitch class which is lower
	 * than or enharmonic to a specified pitch.
	 *
	 * @param target the target pitch class of the pitch to be returned
	 * @param upperBound the highest allowable pitch to be returned
	 * @return the highest lower or enharmonic pitch of {@code target} pitch class
	 */
	public static Pitch nearestBelowOrEnharmonic(PitchClass target, Pitch upperBound) {
		if (upperBound.pitchClass.isEnharmonicWith(target)) {
			return Pitch.ofAbsolutePitch(target, upperBound.pitch);
		} else {
			return nearestBelow(target, upperBound);
		}
	}

	/**
	 * Returns the pitch class of this pitch.
	 *
	 * @return the pitch class of this object
	 */
	public PitchClass pitchClass() {
		return pitchClass;
	}

	/**
	 * Returns the octave number of the natural of this pitch in scientific
	 * pitch notation. Note that this may not be the actual sounding octave,
	 * for example the pitch Bx3 (B double sharp in octave 3) returns 3,
	 * while the actual pitch is higher than C4.
	 * <p>
	 * In scientific pitch notation, the octaves are numbered in ascending
	 * order, starting with 0 for the sub-contra octave and proceeding up.
	 * The octave starting at middle C is assigned the number 4.
	 *
	 * @return the octave number
	 */
	public int octave() {
		return octave;
	}

	/**
	 * Returns the number of the octave (in scientific pitch notation)
	 * containing the absolute pitch of this pitch object.
	 * Exactly speaking, this method returns the octave of the highest
	 * C pitch which is lower than or equal to this pitch.
	 *
	 * @return the sounding octave number
	 */
//	int enharmonicOctave() {
//		return Math.floorDiv(pitch, SEMITONES);
//	}

	/**
	 * Returns the MIDI number of the pitch.
	 * The MIDI number of a pitch is the number of semitones above C-1
	 * in scientific notation. That is, C0 is assigned the MIDI number 12.
	 * @return a number representing this pitch in MIDI
	 */
	public int midiNumber() {
		return pitch + 12;
	}

	/**
	 * Checks whether the given pitch is the same number of semitones
	 * above the reference as this pitch.
	 *
	 * @param other the pitch to be compared with this one
	 * @return {@code true} if the difference of the semitones above
	 *         reference of the two pitches is 0
	 */
	public boolean isEnharmonicWith(Pitch other) {
		return this.pitch == other.pitch;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + octave;
		result = prime * result + ((pitchClass == null) ? 0 : pitchClass.hashCode());
		return result;
	}

	/**
	 * Indicates whether a given object is equal to this object.
	 * Two pitches are considered equal if and only if they are formed from
	 * the same base pitch class by the same accidental and have the same
	 * octave.
	 * Enharmonic pitches are <strong>not</strong> considered to be equal,
	 * for example D#4 is not equal to Eb4.
	 *
	 * @param obj {@inheritDoc}
	 * @return {@code true} if {@code obj} is also a {@code Pitch} and if
	 *         both {@code this} and {@code obj} have equal pitch class and
	 *         the same octave.
	 *         Pitch classes are only considered equal if they have equal
	 *         names; that is, enharmonic pitch classes are not necessarily
	 *         equal. For more information see {@link PitchClass#equals}.
	 * @see PitchClass#equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Pitch)) {
			return false;
		}
		Pitch other = (Pitch) obj;
		if (octave != other.octave) {
			return false;
		}
		if (pitchClass == null) {
			if (other.pitchClass != null) {
				return false;
			}
		} else if (!pitchClass.equals(other.pitchClass)) {
			return false;
		}
		return true;
	}

	/**
	 * Returns a pitch which is the specified amount above this pitch.
	 * The original object remains unchanged.
	 *
	 * @param interval the interval between this pitch and the (higher)
	 *        pitch to be returned
	 * @return the pitch resulting from transposing this pitch up by
	 *         {@code interval}
	 */
	public Pitch transposeUp(Interval interval) {
		PitchClass newPitchClass = pitchClass.transposeUp(interval);
		int targetPitch = pitch + interval.semitones();
		return ofAbsolutePitch(newPitchClass, targetPitch);
	}

	/**
	 * Returns a pitch which is the specified amount below this pitch.
	 * The original object remains unchanged.
	 *
	 * @param interval the interval between this pitch and the (lower)
	 *        pitch to be returned
	 * @return the pitch resulting from transposing this pitch down by
	 *         {@code interval}
	 */
	public Pitch transposeDown(Interval interval) {
		PitchClass newPitchClass = pitchClass.transposeDown(interval);
		int targetPitch = pitch - interval.semitones();
		return ofAbsolutePitch(newPitchClass, targetPitch);
	}

	@Override
	public String toString() {
		return pitchClass.toString() + octave;
	}

	/**
	 * Compares another pitch to this one.
	 * This method orders the pitches in ascending order (from lowest pitch
	 * to highest pitch), and further orders enharmonic pitches
	 * so that for example D#4 is sorted before Eb4.
	 * This ordering is consistent with {@code equals}.
	 *
	 * @param o {@inheritDoc}
	 * @return  {@inheritDoc}
	 * @throws  NullPointerException {@inheritDoc}
	 */
	@Override
	public int compareTo(Pitch o) {
		return STRICT_COMPARATOR.compare(this, o);
	}

	/**
	 * Returns a comparator which compares pitches based on both their
	 * absolute pitch and their pitch class.
	 * The comparator first orders the pitches in ascending order from lowest
	 * pitch to highest pitch, and then further orders enharmonic pitches
	 * so that for example D#4 is sorted before Eb4.
	 * <p>
	 * This ordering is equal to the natural ordering of {@code Pitch}
	 * and is consistent with {@code equals}.
	 * It does not permit {@code null} arguments.
	 *
	 * @return a {@code Comparator} which considers both absolute pitch
	 *         and pitch class
	 */
	public static Comparator<Pitch> strictComparator() {
		return STRICT_COMPARATOR;
	}

	/**
	 * Returns a comparator which compares pitches based on their absolute
	 * pitch only, treating enharmonic pitches as equal
	 * (that is, disregarding the pitch class completely).
	 * For example, comparing C#4 and Db4 will give the result {@code 0}.
	 * <p>
	 * This ordering is inconsistent with {@code equals}.
	 * It does not permit {@code null} arguments.
	 *
	 * @return a {@code Comparator} which considers the absolute pitch only
	 */
	public static Comparator<Pitch> enharmonicComparator() {
		return ENHARMONIC_COMPARATOR;
	}

	/**
	 * A comparator which compares pitches based on both their absolute
	 * pitch and their pitch class.
	 * This class first orders the pitches in ascending order from lowest
	 * pitch to highest pitch, and then further orders enharmonic pitches
	 * so that for example D#4 is sorted before Eb4.
	 * <p>
	 * This comparator does not permit null arguments.
	 */
	private static class StrictComparator implements Comparator<Pitch> {

		@Override
		public int compare(Pitch p1, Pitch p2) {
			if (p1 == null || p2 == null) {
				throw new NullPointerException
						("One of the pitches being compared is null");
			}

			int comparison = Integer.compare(p1.pitch, p2.pitch);
			if (comparison != 0) {
				return comparison;
			}
			/*
			 * The two pitches have equal value (the absolute pitch);
			 * so we proceed to comparing their base notes:
			 * We want e.g. C# to be ordered before Db,
			 * so we *reverse* the order of their respective accidentals.
			 */
			comparison = -p1.pitchClass.accidental()
			              .compareTo(p2.pitchClass.accidental());
			return comparison;
		}
	}

	/**
	 * A comparator which compares pitches based on their absolute
	 * pitch only, treating enharmonic pitches as equal
	 * (that is, disregarding the pitch class completely).
	 * For example, comparing C#4 and Db4 will give the result {@code 0}.
	 * <p>
	 * This comparator does not permit null arguments.
	 */
	private static class EnharmonicComparator implements Comparator<Pitch> {

		@Override
		public int compare(Pitch p1, Pitch p2) {
			if (p1 == null || p2 == null) {
				throw new NullPointerException
						("One of the pitches being compared is null");
			}
			return Integer.compare(p1.pitch, p2.pitch);
		}
	}
}
