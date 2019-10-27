package com.apirestjwt.main.model;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseModel extends ResponseEntity<Object> {

	public ResponseModel(HttpStatus status) {
		super(status);
		this.status = status;
	}
	private boolean success;
	private HttpStatus status;
	private String mensage;
	private Object data;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Instant getDate() {
		return Instant.now();
	}
	public String getMensage() {
		return mensage;
	}
	public void setMensage(String mensage) {
		this.mensage = mensage;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	public  ResponseModel  finalizar(Object data) {
		this.data = data;
		return this;
	}
	
	public static ResponseModel getInstance() {
		return new ResponseModel(HttpStatus.ACCEPTED);
	}
	
	
}
