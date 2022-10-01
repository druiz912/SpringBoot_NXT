package com.druiz.rs1.services;

import com.druiz.rs1.models.Persona;

import java.util.List;

public interface PersonaService {

    List<Persona> getPersonas();

    Persona getPersonaById(Integer id);

    List<Persona> getPersonaByName(String name);

    Persona updatePersona (Integer id, Persona persona);

    Persona deletePersona (Integer id);

    void addPersona(Persona persona);
}
