package com.rabo.assignment.exception;

import java.time.LocalDateTime;

public class ApiExceptionResponse {

	private String message;
	private String detail;
	private LocalDateTime timestamp;

	public ApiExceptionResponse(String message, String detail) {
		this.message = message;
		this.detail = detail;
		this.timestamp = LocalDateTime.now();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}