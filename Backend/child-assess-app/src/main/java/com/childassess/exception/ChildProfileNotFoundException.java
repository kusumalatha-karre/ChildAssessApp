package com.childassess.exception;

public class ChildProfileNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ChildProfileNotFoundException(final String message) {
		super(message);
	}

	@Override
	public String toString() {
		return "ChildProfileNotFoundException [message=" + getMessage() + "]";
	}
}
