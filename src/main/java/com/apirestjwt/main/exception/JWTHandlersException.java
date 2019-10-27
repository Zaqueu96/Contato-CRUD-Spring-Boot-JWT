package com.apirestjwt.main.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.apirestjwt.main.model.ResponseModel;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;

@RestControllerAdvice
public class JWTHandlersException {

	/**Em caso de Tokens*/
	@ExceptionHandler({JwtException.class,AccessDeniedException.class})
	public Object JWTExceptions( Exception ex) {
		ResponseModel erro = new ResponseModel(HttpStatus.UNAUTHORIZED);
		erro.setMensage("Token Invalid!");	 
		return new ResponseEntity<Object>(erro, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	/**Em caso de Tokens Quebrados*/
	@ExceptionHandler(MalformedJwtException.class)
	public Object handleMalFormedJWTExceptions(MalformedJwtException ex) {
		ResponseModel erro = new ResponseModel(HttpStatus.UNAUTHORIZED);
		erro.setMensage("Bad format Token!");	 
		return new ResponseEntity<Object>(erro, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	/** Em caso de senhas incorretas */
	@ExceptionHandler(BadCredentialsException.class)
	public Object handleJWTBadExceptions( BadCredentialsException ex) {
		ResponseModel erro = new ResponseModel(HttpStatus.UNAUTHORIZED);
		erro.setMensage(ex.getMessage());	 
		return new ResponseEntity<Object>(erro, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}
