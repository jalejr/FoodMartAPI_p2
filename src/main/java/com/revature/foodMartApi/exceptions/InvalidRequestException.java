package com.revature.foodMartApi.exceptions;

@SuppressWarnings("serial")
public class InvalidRequestException extends RuntimeException {

	public InvalidRequestException(String message) {
		super(message);
	}
		
}
