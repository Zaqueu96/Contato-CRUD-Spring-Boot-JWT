package com.apirestjwt.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apirestjwt.main.model.Telefone;
@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

}
