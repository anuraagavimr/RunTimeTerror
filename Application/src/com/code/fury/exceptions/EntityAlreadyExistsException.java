package com.code.fury.exceptions;

import java.time.LocalDateTime;

public class EntityAlreadyExistsException extends Exception {
	
private static final Long serialVersionId = 13L;
	
	private String message;
	private LocalDateTime time;
	private String errorCode;
	
	
	public EntityAlreadyExistsException(String message, LocalDateTime time, String errorCode) {
		super();
		this.message = message;
		this.time = time;
		this.errorCode = errorCode;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer(message).append(",").append(time).append(",").append(errorCode);
		return buffer.toString();
	}
	

}
