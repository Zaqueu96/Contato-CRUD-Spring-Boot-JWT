package com.apirestjwt.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apirestjwt.main.model.Usuario;

@Repository
public interface UsuarioRepositoy extends JpaRepository<Usuario, Long> {
	@Query("Select u from Usuario u  where u.email = :email")
	public Usuario findbyEmail(String email);
	
	@Query("Select u from Usuario u  where u.email = :email and u.senha = :password")
	public Usuario findbyEmailandSenha(String email,String password);

}
