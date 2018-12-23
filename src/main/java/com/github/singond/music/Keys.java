package com.github.singond.music;

/**
 * A utility class for working with musical keys.
 *
 * @author Singon
 */
public final class Keys {

	public static final KeyType MAJOR = MajorKey.TYPE;
	public static final KeyType MINOR = MinorKey.TYPE;

	/** The key of <em>Cb major</em>. */
	public static final Key C_FLAT_MAJOR  = MajorKey.C_FLAT;
	/** The key of <em>Gb major</em>. */
	public static final Key G_FLAT_MAJOR  = MajorKey.G_FLAT;
	/** The key of <em>Db major</em>. */
	public static final Key D_FLAT_MAJOR  = MajorKey.D_FLAT;
	/** The key of <em>Ab major</em>. */
	public static final Key A_FLAT_MAJOR  = MajorKey.A_FLAT;
	/** The key of <em>Eb major</em>. */
	public static final Key E_FLAT_MAJOR  = MajorKey.E_FLAT;
	/** The key of <em>Bb major</em>. */
	public static final Key B_FLAT_MAJOR  = MajorKey.B_FLAT;
	/** The key of <em>F major</em>. */
	public static final Key F_MAJOR       = MajorKey.F;
	/** The key of <em>C major</em>. */
	public static final Key C_MAJOR       = MajorKey.C;
	/** The key of <em>G major</em>. */
	public static final Key G_MAJOR       = MajorKey.G;
	/** The key of <em>D major</em>. */
	public static final Key D_MAJOR       = MajorKey.D;
	/** The key of <em>A major</em>. */
	public static final Key A_MAJOR       = MajorKey.A;
	/** The key of <em>E major</em>. */
	public static final Key E_MAJOR       = MajorKey.E;
	/** The key of <em>B major</em>. */
	public static final Key B_MAJOR       = MajorKey.B;
	/** The key of <em>F# major</em>. */
	public static final Key F_SHARP_MAJOR = MajorKey.F_SHARP;
	/** The key of <em>C# major</em>. */
	public static final Key C_SHARP_MAJOR = MajorKey.C_SHARP;

	/** The key of <em>Ab minor</em>. */
	public static final Key A_FLAT_MINOR  = MinorKey.A_FLAT;
	/** The key of <em>Eb minor</em>. */
	public static final Key E_FLAT_MINOR  = MinorKey.E_FLAT;
	/** The key of <em>Bb minor</em>. */
	public static final Key B_FLAT_MINOR  = MinorKey.B_FLAT;
	/** The key of <em>F minor</em>. */
	public static final Key F_MINOR       = MinorKey.F;
	/** The key of <em>C minor</em>. */
	public static final Key C_MINOR       = MinorKey.C;
	/** The key of <em>G minor</em>. */
	public static final Key G_MINOR       = MinorKey.G;
	/** The key of <em>D minor</em>. */
	public static final Key D_MINOR       = MinorKey.D;
	/** The key of <em>A minor</em>. */
	public static final Key A_MINOR       = MinorKey.A;
	/** The key of <em>E minor</em>. */
	public static final Key E_MINOR       = MinorKey.E;
	/** The key of <em>B minor</em>. */
	public static final Key B_MINOR       = MinorKey.B;
	/** The key of <em>F# minor</em>. */
	public static final Key F_SHARP_MINOR = MinorKey.F_SHARP;
	/** The key of <em>C# minor</em>. */
	public static final Key C_SHARP_MINOR = MinorKey.C_SHARP;
	/** The key of <em>G# minor</em>. */
	public static final Key G_SHARP_MINOR = MinorKey.G_SHARP;
	/** The key of <em>D# minor</em>. */
	public static final Key D_SHARP_MINOR = MinorKey.D_SHARP;
	/** The key of <em>A# minor</em>. */
	public static final Key A_SHARP_MINOR = MinorKey.A_SHARP;

	private Keys() {
    	throw new UnsupportedOperationException("Non-instantiable class");
    }
}
