package com.inventory.management.exception;

public class BadRequestBodyException extends Exception {

	private static final long serialVersionUID = 3114584028613545506L;

	public BadRequestBodyException(String message) {
		super(message);
	}
}
