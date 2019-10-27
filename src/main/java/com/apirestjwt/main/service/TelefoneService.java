package com.apirestjwt.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirestjwt.main.model.Telefone;
import com.apirestjwt.main.repository.TelefoneRepository;

@Service
public class TelefoneService {

	@Autowired
	private TelefoneRepository dao;
	
	public Telefone save(Telefone telefone) {
		return this.dao.save(telefone);
	}
	
	public Telefone update(Telefone telefone) {
		return this.dao.save(telefone);
	}
	
	public Telefone getOne(Long id) {
		return this.dao.findById(id).get();
	}
	
	public void delete(Long id) {
		 this.dao.deleteById(id);
	}
	
	public boolean existeTelefone(Long id) {
		return (this.dao.findById(id).get() != null)? true:false;
	}
	
}
