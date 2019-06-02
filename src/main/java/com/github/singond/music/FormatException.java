package com.github.singond.music;

/**
 * Thrown to indicate that the application has attempted to convert
 * a string to an object, but that the string does not have the appropriate
 * format.
 *
 * @author Singon
 */
public class FormatException extends IllegalArgumentException {

	private static final long serialVersionUID = 1114246192603992158L;

	public FormatException() {
		super();
	}

	public FormatException(String s) {
		super(s);
	}

	public FormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public FormatException(Throwable cause) {
		super(cause);
	}

}
