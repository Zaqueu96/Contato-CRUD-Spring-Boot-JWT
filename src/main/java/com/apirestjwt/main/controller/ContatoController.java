package com.apirestjwt.main.controller;

import com.apirestjwt.main.model.Contato;
import com.apirestjwt.main.model.RequestModel;
import com.apirestjwt.main.model.Telefone;
import com.apirestjwt.main.model.Usuario;
import com.apirestjwt.main.service.ContatoService;
import com.apirestjwt.main.service.TelefoneService;
import com.apirestjwt.main.service.UsuarioService;

import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/contatos", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
public class ContatoController extends BaseController {

	@Autowired
	private ContatoService service;
	@Autowired
	private UsuarioService userDao;

	@GetMapping
	public ResponseEntity<Object> getALL() throws Exception {
		return this.doResponse(HttpStatus.OK, "Lista de Contatos", this.userDao.getContatos(1L));
	}

	@PostMapping
	public ResponseEntity<Object> save(@Valid @RequestBody RequestModel requisicao) {		
		Contato c = this.service.save(requisicao);
		if(c == null) return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(c);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Contato> update(@PathVariable Long id, @Valid @RequestBody Contato contato) {
		if (!this.service.existeContato(id))
			return ResponseEntity.badRequest().build();
		contato.setId(id);
		Contato u = this.service.Update(contato);
		if (u == null)
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(u);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Contato> delete(@PathVariable Long id) {
		if (!this.service.existeContato(id))
			return ResponseEntity.badRequest().build();
		this.service.delete(id);
		return ResponseEntity.accepted().build();
	}

}
