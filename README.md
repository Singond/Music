Music Theory for Java
=====================
A Java library providing implementation of the Western music theory.

The library implements the widely used concepts in the Western music theory
like pitches, pitch classes, intervals, keys, scales, chords etc. in a type-safe
manner, using immutable value objects wherever possible.

Basics
======
The core types implementing music concepts are the following:
- `Pitch`: The pitch of a musical note, consisting of a _pitch class_ and an
  octave number, like “C4” (C in the fourth octave) or “F#5” (F sharp in
  the fifth octave).
- `PitchClass`: The group of all pitches an octave apart, like “C” or “Ab”
  (A flat). This is like `Pitch` with octave information erased.
- `Interval`: An interval between two pitches, like “minor third”.
- `Key`: A musical key, like “E major” or “F sharp minor”.
- `KeyType`: A type of a musical key, like “major” or “minor”.
- `Chord`: A group of _pitch classes_, like “major triad at A” (A, C#, E).
- `ChordVoicing`: A group of _pitches_, like “major triad at A4” (A4, C#5, E5).
- `ChordType`: A type of a chord, like “major triad” or “diminished seventh”.

There are also some utility classes for manipulating the objects:
- `Pitches`: for manipulating instances of `Pitch` and `PitchClass`,
- `Intervals`: for manipulating instances of `Interval`,
- `Keys`: for working with instances of `Key`.

Examples
========
For brevity, the following examples don't show the `System.out.println(var)`.

Create pitch from pitch class and octave:

```
Pitch p = Pitch.of(PitchClass.D, 4);
>> D4
```

Equality and enharmonicity:

```
Pitch dSharp = Pitch.DS4;
Pitch eFlat = Pitch.EB4;
boolean equal = dSharp.equals(eFlat);
>> false
boolean enharm = dSharp.isEnharmonicWith(eFlat);
>> true
```

Get the major third above A4:

```
Pitch p = Pitch.A4.transposeUp(SimpleInterval.MAJOR_THIRD);
>> C#5
```
Get the pitch classes in the key of A major and get the second raised degree:

```
Key key = Keys.A_MAJOR;
>> A major

List<PitchClass> scale = key.degrees();
>> [A, B, C#, D, E, F#, G#]

PitchClass tonic = key.tonic();
>> A

PitchClass secondRaised = key.degree(Degree.II_RAISED);
>> B#
```

Generate the E major scale between A3 and D5:

```
Set<PitchClass> pcs = Keys.E_MAJOR.pitchClasses();
List<Pitch> range = Pitches.allBetween(Pitch.A3, Pitch.D5, pcs);
>> [A3, B3, C#4, D#4, E4, F#4, G#4, A4, B4, C#5]
```

