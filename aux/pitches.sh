#!/bin/bash
rm -f pitches-octave.java
for pitch in C D E F G A B; do
	sed "s/Y/$pitch/g" pitches-template.java >> pitches-octave.java
done

rm -f pitches-all.java
for octave in 0 1 2 3 4 5 6 7 8; do
	sed "s/0/$octave/g" pitches-octave.java >> pitches-all.java
done

sed -i 's/1th/1st/g; s/2th/2nd/g; s/3th/3rd/g' pitches-all.java
rm pitches-octave.java
