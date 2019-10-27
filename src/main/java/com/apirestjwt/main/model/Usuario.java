package com.apirestjwt.main.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Email(message = "Email Invalido!!")
	@NotNull(message = "Email é Requerido!!")
	@Column( unique = true)
	private String email;
	@NotNull(message = "Senha é Requerida!!")
	@NotBlank(message ="Senha é Requerida!!")
	@JsonIgnore
	@Column(nullable = false)
	private String senha;
	@Column( columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",insertable=false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date cadrasto;
	private String avatar;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "usuario")	
	@Fetch(value=FetchMode.SUBSELECT)
	@JsonManagedReference
	private Collection<Contato> contatos = new ArrayList<>();
	
	public static Usuario getInstance() {
		return new Usuario();
	}
	
	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getCadrasto() {
		return cadrasto;
	}
	
	public void SetCadrasto(Date cadrasto) {
		this.cadrasto= cadrasto;
	}


	public Collection<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}
	
	

}
