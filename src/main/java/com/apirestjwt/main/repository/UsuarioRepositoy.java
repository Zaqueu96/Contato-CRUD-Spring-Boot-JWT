package com.apirestjwt.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apirestjwt.main.model.Usuario;

@Repository
public interface UsuarioRepositoy extends JpaRepository<Usuario, Long> {

}
