package com.childassess.exception;

public class ChildProfileAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	public ChildProfileAlreadyExistsException(final String message) {
		super(message);
	}

	@Override
	public String toString() {
		return "ChildProfileAlreadyExistsException [message=" + getMessage() + "]";
	}
}
