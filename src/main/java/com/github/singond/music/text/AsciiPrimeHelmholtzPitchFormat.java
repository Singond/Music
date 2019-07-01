package com.github.singond.music.text;

class AsciiPrimeHelmholtzPitchFormat extends BasicHelmholtzPitchFormat {

	private static final CharSequence lowOctaveMark = ",";
	private static final CharSequence highOctaveMark = "'";

	public AsciiPrimeHelmholtzPitchFormat(PitchClassFormat pcFmt,
			boolean prefix) {
		super(pcFmt, "", "", prefix);
	}

	@Override
	protected CharSequence lowOctaveMark(int number) {
		return TextUtil.repeat(lowOctaveMark, number);
	}

	@Override
	protected CharSequence highOctaveMark(int number) {
		return TextUtil.repeat(highOctaveMark, number);
	}

}
