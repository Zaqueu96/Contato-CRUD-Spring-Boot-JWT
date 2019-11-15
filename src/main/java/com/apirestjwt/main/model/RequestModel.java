package com.apirestjwt.main.model;

import java.util.List;

public class RequestModel {
	
	private Contato contato;
	private List<Telefone> telefones;
	
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	public List<Telefone> getTelefones() {
		return telefones;
	}
	
	
	
	
}
