package com.apirestjwt.main.security.jwt;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apirestjwt.main.model.Usuario;
import com.apirestjwt.main.service.UsuarioService;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	
	@Autowired
	private UsuarioService dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user  = this.dao.getRepository().findbyEmail(username);
		if(user == null) {
			new UsernameNotFoundException("User not found with username : "+username);
		}
		return new User(user.getEmail(),user.getSenha(),new ArrayList<>());
	}
	
	public Usuario register(Usuario u) {
		return this.dao.register(u);
	}

}
