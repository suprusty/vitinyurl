package com.virtualidentity.vitinyurl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserFoundExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<TinyURLErrorResponse> handleException(UserFoundException exc) {

		TinyURLErrorResponse error = new TinyURLErrorResponse();

		error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
