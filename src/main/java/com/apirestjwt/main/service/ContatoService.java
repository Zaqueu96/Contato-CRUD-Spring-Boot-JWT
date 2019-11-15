package com.apirestjwt.main.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.apirestjwt.main.model.Contato;
import com.apirestjwt.main.model.RequestModel;
import com.apirestjwt.main.model.Telefone;
import com.apirestjwt.main.model.Usuario;
import com.apirestjwt.main.repository.ContatoRepository;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository dao;

	@Autowired
	private UsuarioService userService;

	
	public List<Contato> getAll() {
		return this.userService.getContatos(1L);
	}

	public Contato getOne(Long id) {
		return this.dao.findById(id).get();
	}

	public Contato save(RequestModel request,Authentication auth) {	
		Contato contato = request.getContato();
		Contato u = this.dao.save(contato);
		u.setTelefones(contato.getTelefones());
		return this.dao.save(u);
	}

	public Contato Update(Contato contato) {
		return this.dao.save(contato);
	}

	public void delete(Long id) {
		this.dao.deleteById(id);
	}

	public boolean existeContato(Long id) {
		return (this.dao.findById(id).get() != null) ? true : false;
	}

	

}
