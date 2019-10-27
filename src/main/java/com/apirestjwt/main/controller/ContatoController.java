package com.apirestjwt.main.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirestjwt.main.model.Contato;
import com.apirestjwt.main.model.RequestModel;
import com.apirestjwt.main.model.Telefone;
import com.apirestjwt.main.model.Usuario;
import com.apirestjwt.main.service.ContatoService;
import com.apirestjwt.main.service.TelefoneService;
import com.apirestjwt.main.service.UsuarioService;

@RestController
@RequestMapping(value = "/contatos", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class ContatoController {
	
	@Autowired
	private ContatoService service;
	@Autowired
	private TelefoneService telefoneDao;
	@Autowired
	private UsuarioService userDao;
	
	@GetMapping
	public ResponseEntity<Object> getALL(){
		return null;
	}
	@PostMapping
	public ResponseEntity<Contato> save(@Valid @RequestBody RequestModel requisicao){
		Usuario user = this.userDao.getOne(1l);
		Contato contato = requisicao.getContato();
		contato.setUsuario(user);
		Contato u = this.service.save(contato);
		if (u == null) return ResponseEntity.badRequest().build();
		for(Telefone telefone:requisicao.getTelefones()) {
			telefone.setContato(u);
			telefoneDao.save(telefone);			
		}
		if (u == null) return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(u);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Contato> update(@PathVariable Long id,@Valid @RequestBody Contato contato){
		if (!this.service.existeContato(id)) return ResponseEntity.badRequest().build();
		contato.setId(id);
		Contato  u = this.service.save(contato);
		if (u == null) return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(u);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Contato> delete(@PathVariable Long id){
		if (!this.service.existeContato(id)) return ResponseEntity.badRequest().build();
		this.service.delete(id);
		return ResponseEntity.accepted().build();
	}

}
