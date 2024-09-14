package com.example.LibrarySystem.Exception;

import org.springframework.http.HttpStatus;



public class NoElementFoundException {

	private final String message;
	private final Throwable cause;
	private final HttpStatus httpStatus;
	
	public NoElementFoundException(String message, Throwable cause, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.cause = cause;
		this.httpStatus = httpStatus;
	}
	public String getMessage() {
		return message;
	}

	public Throwable getCause() {
		return cause;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
}
