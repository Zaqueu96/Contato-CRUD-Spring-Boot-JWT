package com.apirestjwt.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apirestjwt.main.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

}