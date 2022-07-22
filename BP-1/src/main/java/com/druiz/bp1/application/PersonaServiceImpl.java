package com.druiz.bp1.application;

import com.druiz.bp1.application.port.IPersonaService;
import com.druiz.bp1.domain.PersonaEntity;
import com.druiz.bp1.infrastructure.controller.dto.input.PersonaInputDto;
import com.druiz.bp1.infrastructure.controller.dto.output.PersonaOutputDto;
import com.druiz.bp1.infrastructure.repository.IPersonaRepoJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServiceImpl implements IPersonaService {

    @Autowired
    IPersonaRepoJPA repo;

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto personaInputDTO) throws Exception {
        PersonaEntity personaEntity = new PersonaEntity(personaInputDTO);
        repo.save(personaEntity);
        return new PersonaOutputDto(personaEntity);
    }

    @Override
    public PersonaOutputDto getPersonaId(int id) throws Exception {
        PersonaEntity personaEntity = repo.findById(id).orElseThrow(() -> new Exception("No se encuentra la persona con ese id"));
        return new PersonaOutputDto(personaEntity);
    }

    @Override
    public List<PersonaOutputDto> getPersonaName(String username) throws Exception {
        List<PersonaOutputDto> listaTemporal = new ArrayList<>();
        repo.findByName(username).forEach(personaEntity -> {
            listaTemporal.add(new PersonaOutputDto(personaEntity));
        });
        return listaTemporal;
    }

    @Override
    public List<PersonaOutputDto> getPersonas() {
        List<PersonaOutputDto> listaTemporal = new ArrayList<>();
        repo.findAll().forEach(personaEntity -> {
            PersonaOutputDto personOutputDTO = new PersonaOutputDto(personaEntity);
            listaTemporal.add(personOutputDTO);
        });
        return listaTemporal;
    }


    @Override
    public PersonaOutputDto updatePersona(int id, PersonaInputDto p) {

        repo.findAll().forEach(personaEntity -> {
            if (personaEntity.getId_persona() == id) {
                personaEntity.setId_persona(p.getId_persona());
                personaEntity.setName(p.getName());
                personaEntity.setSurname(p.getSurname());
                personaEntity.setUsuario(p.getUsuario());
                personaEntity.setPassword(p.getPassword());
                personaEntity.setPersonal_email(p.getPersonal_email());
                personaEntity.setCompany_email(p.getCompany_email());
                personaEntity.setCity(p.getCity());
                personaEntity.setImagen_url(p.getImagen_url());
                personaEntity.setCreated_date(p.getCreated_date());
                personaEntity.setTermination_date(p.getTermination_date());
                personaEntity.setActive(p.isActive());

                repo.saveAndFlush(personaEntity);
            }
        });
        return null;
    }

    @Override
    public PersonaOutputDto deletePersona(int id) throws Error, Exception {
        PersonaOutputDto persona;
        persona = getPersonaId(id);
        try {
            repo.deleteById(id);
        } catch (Error e1) {
            if (persona == null) { //Comprobación
                System.out.println("No se encuentra el identificador: " + id);
            }

        }


        return persona;
    }
}

