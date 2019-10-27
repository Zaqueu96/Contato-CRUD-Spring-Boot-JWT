package com.apirestjwt.main.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirestjwt.main.model.Telefone;
import com.apirestjwt.main.service.TelefoneService;

@RestController
@RequestMapping(value="/telefone",produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
public class TelefoneController {

	@Autowired
	private TelefoneService service;
	
	
	@PostMapping
	public ResponseEntity<Telefone> save(@Valid @RequestBody Telefone telefone){
		Telefone  u = this.service.save(telefone);
		if (u == null) return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(u);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Telefone> update(@PathVariable Long id,@Valid @RequestBody Telefone telefone){
		if (!this.service.existeTelefone(id)) return ResponseEntity.badRequest().build();
		telefone.setId(id);
		Telefone  u = this.service.save(telefone);
		if (u == null) return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(u);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Telefone> delete(@PathVariable Long id){
		if (!this.service.existeTelefone(id)) return ResponseEntity.badRequest().build();
		this.service.delete(id);
		return ResponseEntity.accepted().build();
	}
}
