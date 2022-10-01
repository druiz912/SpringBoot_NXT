package com.druiz.crudvalidation.persona.infrastructure.repo;

import com.druiz.crudvalidation.persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPersonaRepo extends JpaRepository<Persona, Integer> { List<Persona> findByName(String username);}
