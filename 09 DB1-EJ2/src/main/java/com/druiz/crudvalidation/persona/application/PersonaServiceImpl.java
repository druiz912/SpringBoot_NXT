package com.druiz.crudvalidation.persona.application;

import com.druiz.crudvalidation.persona.domain.Persona;
import com.druiz.crudvalidation.persona.infrastructure.controller.dtos.PersonaInputDto;
import com.druiz.crudvalidation.persona.infrastructure.controller.dtos.PersonaOutputDto;
import com.druiz.crudvalidation.persona.infrastructure.repo.IPersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    IPersonaRepo repo;

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto personaInputDTO) throws Exception {
        Persona persona = new Persona(personaInputDTO);
        repo.save(persona);
        return new PersonaOutputDto(persona);
    }

    @Override
    public PersonaOutputDto getPersonaId(int id) throws Exception {
        Persona persona = repo.findById(id).orElseThrow(() -> new Exception("No person found with ID"));
        return new PersonaOutputDto(persona);
    }

    @Override
    public List<PersonaOutputDto> getPersonaName(String username) throws Exception {
        List<PersonaOutputDto> listaTemporal = new ArrayList<>();
        repo.findByName(username).forEach(persona -> {
            listaTemporal.add(new PersonaOutputDto(persona));
        });
        return listaTemporal;
    }

    @Override
    public List<PersonaOutputDto> getPersonas() {
        List<PersonaOutputDto> listaTemporal = new ArrayList<>();
        repo.findAll().forEach(persona -> {
            PersonaOutputDto personOutputDTO = new PersonaOutputDto(persona);
            listaTemporal.add(personOutputDTO);
        });
        return listaTemporal;
    }


    @Override
    public PersonaOutputDto updatePersona(int id, PersonaInputDto p) {
        Persona personInDB = repo.findById(id).orElse(null);
        personInDB.updateEntity(p);
        repo.save(personInDB);

        PersonaOutputDto personaOutputDTO = new PersonaOutputDto(personInDB);
        return personaOutputDTO;
    }

    @Override
    public void deletePersona(int id) throws Exception {
        PersonaOutputDto persona;
        persona = getPersonaId(id);
        if(persona == null){
            System.out.println("No se encuentra el identificador: " + id);
        }else{
            repo.deleteById(id);
        }

    }


}
