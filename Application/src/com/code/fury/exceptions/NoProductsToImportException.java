package com.code.fury.exceptions;

import java.time.LocalDateTime;

public class NoProductsToImportException extends Exception {
	
private static final Long serialVersionId = 19L;
	
	private String message;
	private LocalDateTime time;
	private String errorCode;
	private String string;
	
	public NoProductsToImportException(String message, LocalDateTime time, String errorCode) {
		super();
		this.message = message;
		this.time = time;
		this.errorCode = errorCode;
	}
	
	public NoProductsToImportException(String string) {
	    this.string=string;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer(message).append(",").append(time).append(",").append(errorCode);
		return buffer.toString();
	}

}
