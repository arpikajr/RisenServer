package com.risen.exception;

@SuppressWarnings("serial")
public class SpringRisenException extends RuntimeException {

	public SpringRisenException(String exMessage, Exception exception) {
		super(exMessage, exception);
	}

	public SpringRisenException(String exMessage) {
		super(exMessage);
	}
}