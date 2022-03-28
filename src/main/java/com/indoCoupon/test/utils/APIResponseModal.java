package com.indoCoupon.test.utils;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class APIResponseModal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String data;
	private HttpStatus status;
	private String message;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "{\"data\":\"" + data + "\", \"status\":\"" + status + "\", \"message\":\"" + message + "\"}";
	}
	
}
