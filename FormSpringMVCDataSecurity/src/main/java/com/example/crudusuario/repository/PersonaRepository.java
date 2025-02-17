package com.example.crudusuario.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crudusuario.model.Persona;


public interface PersonaRepository extends JpaRepository<Persona, Long> {}

