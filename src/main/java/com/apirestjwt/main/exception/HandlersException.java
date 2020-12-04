package com.apirestjwt.main.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import com.apirestjwt.main.model.ResponseModel;


@RestControllerAdvice
public class HandlersException {

	/** Valindando erros de validação */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Object handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
		ResponseModel erro = new ResponseModel(HttpStatus.BAD_REQUEST);
		erro.setMensage("Erro!! Dados invalidos !!");
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField().toLowerCase();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);	       
	    });;
	    erro.setData(errors);
		return new ResponseEntity<Object>(erro, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	/** Erros de exceptions e tratamentos*/
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Object> handlerNullException(HttpServletRequest request, NullPointerException ex) {
		ResponseModel erro = new ResponseModel(HttpStatus.BAD_REQUEST);
		erro.setMensage(ex.getMessage());
		erro.setData(ex.getCause().getMessage());
		return new ResponseEntity<Object>(erro, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	
	/** Erros de exceptions e tratamentos*/
	@ExceptionHandler(MultipartException.class)
	public ResponseEntity<Object> handlerConstraintViolationException(HttpServletRequest request, ConstraintViolationException ex) {
		ResponseModel erro = new ResponseModel(HttpStatus.BAD_REQUEST);
		erro.setMensage("Informe o Arquivo!");
		erro.setData(ex.getCause().getMessage());
		return new ResponseEntity<Object>(erro, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	/** Erros de exceptions e tratamentos*/
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handlerException(HttpServletRequest request, Exception ex) {
		ResponseModel erro = new ResponseModel(HttpStatus.BAD_REQUEST);
		erro.setMensage(ex.getMessage());
		erro.setData(ex.getCause().getMessage());
		return new ResponseEntity<Object>(erro, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	
}
