package com.apirestjwt.main.exception;

import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.apirestjwt.main.model.ResponseModel;

@RestControllerAdvice
public class ResponseEntityException {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public Object handlerNotFoundEntity(EntityNotFoundException ex){
		ResponseModel response = ResponseModel.getInstance();
		response.setStatus(HttpStatus.BAD_REQUEST);
		response.setMensage("Usuario Não Encontrado!");
		response.setData(ex.getMessage());
		return ResponseEntity.ok(response);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public Object handlerNotSuchElementEntity(NoSuchElementException ex) throws Exception {
		ResponseModel response = ResponseModel.getInstance();
		response.setStatus(HttpStatus.BAD_REQUEST);
		response.setMensage("Item Não Encontrado!");
		response.setData(ex.getMessage());
		return ResponseEntity.ok(response);
	}

	
	
	
	
}
