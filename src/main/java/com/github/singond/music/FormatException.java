/*
 * Copyright 2019 Jan Slany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
