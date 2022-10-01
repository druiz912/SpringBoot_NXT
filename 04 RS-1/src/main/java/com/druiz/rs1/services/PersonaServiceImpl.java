package com.druiz.rs1.services;

import com.druiz.rs1.models.Persona;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImpl implements PersonaService{

    Map<Integer, Persona> personMap = new HashMap<>();

    @Override
    public List<Persona> getPersonas() {
        return new ArrayList<>(personMap.values());
    }

    @Override
    public Persona getPersonaById(Integer id) {
        return personMap.get(id);

    }

    @Override
    public List<Persona> getPersonaByName(String name) {
        return getPersonas().stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public Persona updatePersona(Integer id, Persona persona) {
        Persona auxPersona = getPersonaById(id);

        if(auxPersona !=null){
            if(persona.getName() != null)
                auxPersona.setName(persona.getName());
            if(persona.getCity() != null)
                auxPersona.setCity(persona.getCity());
            if(persona.getAge() != null)
                auxPersona.setAge(persona.getAge());
        }

        personMap.replace(id, auxPersona);
        return auxPersona;
    }

    @Override
    public Persona deletePersona(Integer id) {
        Persona deletedPerson = getPersonaById(id);
        personMap.remove(id);
        return deletedPerson;
    }

    @Override
    public void addPersona(Persona persona) {
        personMap.put(persona.getId(), persona);
    }
}
