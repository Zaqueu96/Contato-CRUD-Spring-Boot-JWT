package com.apirestjwt.main.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.apirestjwt.main.model.Usuario;
import com.apirestjwt.main.service.UsuarioService;

import io.jsonwebtoken.MalformedJwtException;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UsuarioController extends BaseController{


	@Autowired
	private UsuarioService service;
	public static final String path = "/upload/users/";
	public static final String uploadingDir = System.getProperty("user.dir") + path;

	/**Carregando dado de determinado Usuario */
	@GetMapping("/{id}")
	public Object getOne(@PathVariable Long id,@RequestHeader Map<String,String> req)  throws Exception,MalformedJwtException,AccessDeniedException {
		System.out.println("Request:->  Chegou aki"+req);
			return this.doResponse(HttpStatus.ACCEPTED,"Usuario encontrado", this.service.getOne(id));
	
	}

	/**
	 * Cadrasto de Usuario
	 * @throws Exception 
	 */
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public ResponseEntity<Object> save(@Valid @RequestBody Usuario user) throws Exception {
		return this.doResponse(HttpStatus.ACCEPTED,"Usuario encontrado", this.service.register(user));
	}

	/**
	 * Atualizar Usuario
	 */
	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Usuario> update(@PathVariable Long id, @Valid @RequestBody Usuario user) {
		try {
			user.setId(id);
			Usuario u = service.update(user);
			return (u != null)? ResponseEntity.ok(u): ResponseEntity.status(HttpStatus.resolve(500)).build();
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * Mudar Foto
	 */
	@PutMapping
	@ResponseBody
	public ResponseEntity<Usuario> uploadImage(@RequestPart("foto") MultipartFile img) {
		File file = new File(uploadingDir + img.getOriginalFilename());
		Usuario user =  Usuario.getInstance();
		try {
			img.transferTo(file);
			user.setAvatar(path + img.getOriginalFilename());
			return ResponseEntity.ok(user);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return ResponseEntity.badRequest().build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}

	}
}
