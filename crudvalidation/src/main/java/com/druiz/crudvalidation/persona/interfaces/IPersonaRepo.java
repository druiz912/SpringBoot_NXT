package com.druiz.crudvalidation.persona.interfaces;

import com.druiz.crudvalidation.persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface IPersonaRepo extends JpaRepository<Persona, Integer> {
    List<Persona> findByName(String username);

}
