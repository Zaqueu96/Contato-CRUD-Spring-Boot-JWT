package com.apirestjwt.main.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.apirestjwt.main.model.Usuario;
import com.apirestjwt.main.repository.UsuarioRepositoy;

@RestController
@RequestMapping(value ="/users",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UsuarioController {
	@Autowired
	private UsuarioRepositoy dao;
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Usuario> save(@Valid @RequestBody Usuario user){
		Usuario u = dao.save(user);
		if(u == null)
			return ResponseEntity.status(HttpStatus.resolve(500)).build();
		return ResponseEntity.ok(u);		
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Usuario> update(@PathVariable Long id,@Valid @RequestBody Usuario user){
		user.setId(id);
		Usuario u = dao.save(user);
		if(u == null)
			return ResponseEntity.status(HttpStatus.resolve(500)).build();
		return ResponseEntity.ok(u);		
	}
}
