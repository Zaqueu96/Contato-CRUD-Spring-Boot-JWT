package com.apirestjwt.main.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.apirestjwt.main.model.Contato;
import com.apirestjwt.main.model.Usuario;
import com.apirestjwt.main.repository.UsuarioRepositoy;
import com.apirestjwt.main.security.jwt.JwtTokenUtil;




@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepositoy dao;
	
	@Autowired
	private PasswordEncoder passwordEncode;

	public Usuario register(Usuario user) {
		if (!this.verificarEmail(user.getEmail())) {
			new Throwable("Este Email já existe...");
		}
		user.setSenha(passwordEncode.encode(user.getSenha()));
		return dao.save(user);
	}
	
	// SENDO USADO PELO USERDETAILS
	public UsuarioRepositoy getRepository() {
		return this.dao;
	}
	
	
	public Usuario update(Usuario user) {
		return dao.save(user);
	}

	public void remove(Long user) {
		dao.deleteById(user);
	}

	public Usuario getByEmail(String email) {
		return dao.findbyEmail(email);
	}
	
	public Usuario getOne(Long user) {
		return dao.findById(user).get();
	}

	public Usuario validar(String email,String password) {
		Usuario u = dao.findbyEmailandSenha(email, password);
		if(u == null)
			new Exception().addSuppressed(new Throwable("Usuario Invalido!!"));
		return u;
	}
	
	public List<Contato> getContatos(Long id){
		Usuario u = this.dao.getOne(id);
		return (List<Contato>) u.getContatos();
	}

	private boolean verificarEmail(String email) {
		Usuario u = dao.findbyEmail(email);
		return (u == null) ? false : true;
	}


	
	
}
