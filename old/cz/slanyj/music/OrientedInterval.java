package cz.slanyj.music;

import static cz.slanyj.music.Interval.*;

/**
 * An extension of the Interval interface, which keeps notion
 * of the interval direction.
 * 
 * @author Sorondil
 *
 */

public enum OrientedInterval {
	// Using Czech notation: C = perfect, M = minor, V = major; ZM = diminished, ZV = augmented.
		// Basic (perfect, minor, major)
		C1_UP (C1, Direction.UP), C1_DOWN (C1, Direction.DOWN),
		M2_UP (M2, Direction.UP), M2_DOWN (M2, Direction.DOWN),
		V2_UP (V2, Direction.UP), V2_DOWN (V2, Direction.DOWN),
		M3_UP (M3, Direction.UP), M3_DOWN (M3, Direction.DOWN),
		V3_UP (V3, Direction.UP), V3_DOWN (V3, Direction.DOWN),
		C4_UP (C4, Direction.UP), C4_DOWN (C4, Direction.DOWN),
		C5_UP (C5, Direction.UP), C5_DOWN (C5, Direction.DOWN),
		M6_UP (M6, Direction.UP), M6_DOWN (M6, Direction.DOWN),
		V6_UP (V6, Direction.UP), V6_DOWN (V6, Direction.DOWN),
		M7_UP (M7, Direction.UP), M7_DOWN (M7, Direction.DOWN),
		V7_UP (V7, Direction.UP), V7_DOWN (V7, Direction.DOWN),
		C8_UP (C8, Direction.UP), C8_DOWN (C8, Direction.DOWN),
		// Additional (diminished, augmented)
		ZM1_UP (ZM1, Direction.UP), ZM1_DOWN (ZM1, Direction.DOWN),
		ZV1_UP (ZV1, Direction.UP), ZV1_DOWN (ZV1, Direction.DOWN),
		ZM2_UP (ZM2, Direction.UP), ZM2_DOWN (ZM2, Direction.DOWN),
		ZV2_UP (ZV2, Direction.UP), ZV2_DOWN (ZV2, Direction.DOWN),
		ZM3_UP (ZM3, Direction.UP), ZM3_DOWN (ZM3, Direction.DOWN),
		ZV3_UP (ZV3, Direction.UP), ZV3_DOWN (ZV3, Direction.DOWN),
		ZM4_UP (ZM4, Direction.UP), ZM4_DOWN (ZM4, Direction.DOWN),
		ZV4_UP (ZV4, Direction.UP), ZV4_DOWN (ZV4, Direction.DOWN),
		ZM5_UP (ZM5, Direction.UP), ZM5_DOWN (ZM5, Direction.DOWN),
		ZV5_UP (ZV5, Direction.UP), ZV5_DOWN (ZV5, Direction.DOWN),
		ZM6_UP (ZM6, Direction.UP), ZM6_DOWN (ZM6, Direction.DOWN),
		ZV6_UP (ZV6, Direction.UP), ZV6_DOWN (ZV6, Direction.DOWN),
		ZM7_UP (ZM7, Direction.UP), ZM7_DOWN (ZM7, Direction.DOWN),
		ZV7_UP (ZV7, Direction.UP), ZV7_DOWN (ZV7, Direction.DOWN),
		ZM8_UP (ZM8, Direction.UP), ZM8_DOWN (ZM8, Direction.DOWN),
		ZV8_UP (ZV8, Direction.UP), ZV8_DOWN (ZV8, Direction.DOWN);
	
	final Interval interval;
	final Direction direction;
	
	private OrientedInterval(Interval i, OrientedInterval.Direction dir) {
		interval = i;
		direction = dir;
	}
	
	public enum Direction {
		UP, DOWN;
	}
}
