package com.kalyan.expenses.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8393975927899658159L;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}

	public ResourceNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}

}
