package com.apirestjwt.main.model;

public class RequestModel {
	
	private Contato contato;
	private Telefone[] telefones;
	
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	public Telefone[] getTelefones() {
		return telefones;
	}
	public void setTelefones(Telefone[] telefones) {
		this.telefones = telefones;
	}
	
	
	
	
}
