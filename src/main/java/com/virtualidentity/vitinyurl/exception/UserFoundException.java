package com.virtualidentity.vitinyurl.exception;

public class UserFoundException extends RuntimeException {

    public UserFoundException(String message) {
        super(message);
    }

    public UserFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserFoundException(Throwable cause) {
        super(cause);
    }
}
