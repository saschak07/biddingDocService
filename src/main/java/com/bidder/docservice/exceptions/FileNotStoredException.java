package com.bidder.docservice.exceptions;

public class FileNotStoredException extends RuntimeException {
	private String message;

	public FileNotStoredException(String message) {
		super();
		this.message = message;
	}

}
