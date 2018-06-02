package com.ilm.lms.course.model;

import java.time.LocalDateTime;

public class ExceptionData {
	
	private String errorCode;
	private String errorMessage;
	private LocalDateTime time;
	
	public ExceptionData() {
		super();
	}
	
	public ExceptionData(String errorCode, String errorMessage, LocalDateTime time) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.time = time;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "ExceptionData [errorCode=" + errorCode + ", errorMessage=" + errorMessage + ", time=" + time + "]";
	}
	
}
