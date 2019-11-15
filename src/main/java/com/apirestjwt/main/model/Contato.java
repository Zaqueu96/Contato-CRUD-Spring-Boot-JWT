package com.apirestjwt.main.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Contato {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade= CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonBackReference
	private Usuario usuario;
	
	@NotBlank
	@Column(nullable = false,unique = true)
	private String nome;
	
	@NotBlank
	@Column(nullable = false,unique = true)
	private String email;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "contato")	
	@Fetch(value=FetchMode.SUBSELECT)
	private Collection<Telefone> telefones = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Telefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(Collection<Telefone> lista) {
		this.telefones =  lista;
	}
	
	
	
	
	
}
