package com.example.personapp.controller.resp;

import java.util.List;

import org.springframework.http.HttpStatus;

public abstract class BaseResponse<T> {
	private boolean isSuccess;
	private String message;
	private HttpStatus status;
	
	public BaseResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public BaseResponse(HttpStatus status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String error) {
		this.message = error;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public abstract List<T> getData();
}
