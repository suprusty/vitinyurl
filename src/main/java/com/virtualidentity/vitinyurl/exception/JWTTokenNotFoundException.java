package com.virtualidentity.vitinyurl.exception;

public class JWTTokenNotFoundException extends RuntimeException {

	public JWTTokenNotFoundException(String message) {
		super(message);
	}

	public JWTTokenNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public JWTTokenNotFoundException(Throwable cause) {
		super(cause);
	}
}
