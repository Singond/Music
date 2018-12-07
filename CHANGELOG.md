Changelog
=========

[Unreleased]
------------
### Added
- Added this changelog. Logged preceding versions based on information
  in repository.
### Changed
- Changed published artifact name from `com.github.singond.music` to simply
  `music`. The group name remains `com.github.singond`.

[0.4.0] - 2018-08-04
--------------------
### Added
- Added an interface for _key type_, which is a generalization of a key.
  It is basically the key minus the absolute pitch information, only relative
  pitch positions remain.

[0.3.1] - 2018-08-02
--------------------
### Changed
- Commonly used values of `Pitch` are now cached.

[0.3.0] - 2018-07-28
--------------------
### Changed
- Changed compatibility to Java 1.7.

[0.2.2] - 2018-07-15
--------------------
### Fixed
- Fixed the visibility of `Pitches.allBetween` method.

[0.2.1] - 2018-07-14
--------------------
### Fixed
- Removed errors in javadoc.

[0.2.0] - 2018-07-14
--------------------
Converted to Gradle, started versioning and publishing
### Added
- Implemented compound intervals.
- Exposed a utility method for generating a range of pitches.