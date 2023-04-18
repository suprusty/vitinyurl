package com.virtualidentity.vitinyurl.exception;

public class TinyURLNotFoundException extends RuntimeException {

    public TinyURLNotFoundException(String message) {
        super(message);
    }

    public TinyURLNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TinyURLNotFoundException(Throwable cause) {
        super(cause);
    }
}
