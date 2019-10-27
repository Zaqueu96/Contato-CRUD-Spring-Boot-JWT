package com.apirestjwt.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirestjwt.main.model.Contato;
import com.apirestjwt.main.repository.ContatoRepository;

@Service
public class ContatoService {
	
	@Autowired
	private ContatoRepository dao;
	

	
	
	public Contato getOne(Long id) {
		return this.dao.findById(id).get();
	}
	
	public Contato save(Contato contato) {
		return this.dao.save(contato);
	}
	
	public Contato Update(Contato contato) {
		return this.dao.save(contato);
	}
	
	public void delete(Long id) {
		this.dao.deleteById(id);
	}
	
	public boolean existeContato(Long id) {
		return (this.dao.findById(id).get() != null)? true:false;
	}
	
}
