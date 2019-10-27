package com.apirestjwt.main.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.apirestjwt.main.model.ResponseModel;

/**
 * Controller Basic in All
 * @author zaqueu
 *
 */
public abstract class BaseController {
	
	public String user;
	/**
	 * Retornar dados do controller com base em parametros
	 * assim tirando responsabilidade de criação de cada controller de realizar
	 * ações repetitivas.
	 * @param status
	 * @param data
	 * @param mensagem
	 * @return {@link ResponseEntity}
	 * @throws Exception
	 */
	public ResponseEntity<Object> doResponse(HttpStatus status,String mensagem,Object data) throws Exception{
		ResponseModel response = ResponseModel.getInstance();
		response.setStatus(status);
		response.setMensage(mensagem);
		response.setData(data);
		response.setSuccess(true);
		return ResponseEntity.ok(response);		
	}
}
