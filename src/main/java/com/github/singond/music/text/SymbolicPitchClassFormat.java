package com.github.singond.music.text;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import com.github.singond.music.Accidental;
import com.github.singond.music.BasePitchClass;
import com.github.singond.music.PitchClass;

/**
 * A pitch class format which uses symbols to represent sharps and flats.
 *
 * Higher order flats or sharps are created by repeating the single
 * flat or sharp symbol, respectively, the necessary number of times.
 *
 * @author Singon
 */
class SymbolicPitchClassFormat implements PitchClassFormat {

	private final Map<BasePitchClass, String> baseNames;
	private final AccidentalSymbols accidentalSymbols;

	public static final Map<BasePitchClass, String> ENGLISH_NOTES;
	public static final Map<BasePitchClass, String> GERMAN_NOTES;
	public static final Map<BasePitchClass, String> ITALIAN_NOTES_SI;
	public static final Map<BasePitchClass, String> ITALIAN_NOTES_TI;
	static {
		Map<BasePitchClass, String> notes;
		notes = new EnumMap<>(BasePitchClass.class);
		notes.put(BasePitchClass.C, "C");
		notes.put(BasePitchClass.D, "D");
		notes.put(BasePitchClass.E, "E");
		notes.put(BasePitchClass.F, "F");
		notes.put(BasePitchClass.G, "G");
		notes.put(BasePitchClass.A, "A");
		notes.put(BasePitchClass.B, "B");
		ENGLISH_NOTES = Collections.unmodifiableMap(notes);

		notes = new EnumMap<>(BasePitchClass.class);
		notes.put(BasePitchClass.C, "C");
		notes.put(BasePitchClass.D, "D");
		notes.put(BasePitchClass.E, "E");
		notes.put(BasePitchClass.F, "F");
		notes.put(BasePitchClass.G, "G");
		notes.put(BasePitchClass.A, "A");
		notes.put(BasePitchClass.B, "H");
		GERMAN_NOTES = Collections.unmodifiableMap(notes);

		notes = new EnumMap<>(BasePitchClass.class);
		notes.put(BasePitchClass.C, "Do");
		notes.put(BasePitchClass.D, "Re");
		notes.put(BasePitchClass.E, "Mi");
		notes.put(BasePitchClass.F, "Fa");
		notes.put(BasePitchClass.G, "Sol");
		notes.put(BasePitchClass.A, "La");
		notes.put(BasePitchClass.B, "Si");
		ITALIAN_NOTES_SI = Collections.unmodifiableMap(notes);

		notes = new EnumMap<>(BasePitchClass.class);
		notes.put(BasePitchClass.C, "Do");
		notes.put(BasePitchClass.D, "Re");
		notes.put(BasePitchClass.E, "Mi");
		notes.put(BasePitchClass.F, "Fa");
		notes.put(BasePitchClass.G, "Sol");
		notes.put(BasePitchClass.A, "La");
		notes.put(BasePitchClass.B, "Ti");
		ITALIAN_NOTES_TI = Collections.unmodifiableMap(notes);
	}

	public SymbolicPitchClassFormat(Map<BasePitchClass, String> names,
			AccidentalSymbols accidentals) {
		this.baseNames = new EnumMap<>(names);
		this.accidentalSymbols = accidentals;
	}

	public SymbolicPitchClassFormat(AccidentalSymbols accidentals) {
		this(ENGLISH_NOTES, accidentals);
	}

	public SymbolicPitchClassFormat() {
		this(ENGLISH_NOTES, AccidentalSymbols.UNICODE);
	}

	@Override
	public CharSequence format(PitchClass pc) {
		StringBuilder sb = new StringBuilder(baseNames.get(pc.basePitchClass()));
		sb.append(accidentalSymbol(pc.accidental()));
		return sb;
	}

	/**
	 * Returns the textual symbol of an accidental.
	 *
	 * @param acc the accidental
	 * @return a string representation of the accidental
	 */
	private CharSequence accidentalSymbol(Accidental acc) {
		int steps = acc.stepsAboveNatural();
		if (steps == 0) {
			return accidentalSymbols.getNatural();
		} else if (steps == -1) {
			return accidentalSymbols.getFlat();
		} else if (steps == 1) {
			return accidentalSymbols.getSharp();
		} else if (steps == -2) {
			return accidentalSymbols.getDoubleFlat();
		} else if (steps == 2) {
			return accidentalSymbols.getDoubleSharp();
		} else {
			if (steps < 0) {
				return TextUtil.repeat(accidentalSymbols.getFlat(), -steps);
			} else {
				return TextUtil.repeat(accidentalSymbols.getSharp(), steps);
			}
		}
	}
}
