package com.druiz.bs8.content.application;

import com.druiz.bs8.content.application.port.IPersonaService;
import com.druiz.bs8.content.domain.PersonaEntity;
import com.druiz.bs8.content.infrastructure.controller.dto.input.PersonaInputDto;
import com.druiz.bs8.content.infrastructure.controller.dto.output.PersonaOutputDto;
import com.druiz.bs8.content.infrastructure.repository.IPersonaRepoJPA;
import com.druiz.bs8.exceptions.NotFoundException;
import com.druiz.bs8.exceptions.UnprocessableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServiceImpl implements IPersonaService {

    @Autowired
    IPersonaRepoJPA repo;

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto personaInputDto) throws Exception {
        PersonaEntity persona = new PersonaEntity(personaInputDto);
        repo.save(persona);
        return new PersonaOutputDto(persona);
    }


    @Override
    public PersonaOutputDto getPersonaId(int id) throws Exception {
        PersonaEntity personaEntity = null;
        try {
            personaEntity = repo.findById(id).orElseThrow(() -> new Exception("No se encuentra la persona con ese id"));
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
        return new PersonaOutputDto(personaEntity);
    }

    @Override
    public List<PersonaOutputDto> getPersonaName(String username) {
        List<PersonaOutputDto> listaTemporal = new ArrayList<>();
        repo.findByName(username).forEach(personaEntity -> {
            listaTemporal.add(new PersonaOutputDto(personaEntity));
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
    public PersonaOutputDto updatePersona(int id, PersonaInputDto pInputDto) throws Exception {
        try {
            PersonaEntity foundPersona = repo.findById(id).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY , "No user found with ID " + id));
            foundPersona.update(pInputDto);
            repo.save(foundPersona);
            return new PersonaOutputDto(foundPersona);
        } catch (Exception e){
            throw new UnprocessableException(e.getMessage());
        }


    }
     /*  PersonaEntity personaEntity = null;
        try {
            personaEntity = repo.findById(id).orElseThrow(() -> new Exception("No se encuentra la persona con ese id"));
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
        return new PersonaOutputDto(personaEntity); */

    @Override
    public void deletePersona(int id) throws Exception{
        PersonaEntity persona = repo.findById(id)
                .orElseThrow(() -> new HttpMediaTypeNotAcceptableException("No user found with ID " + id));
        repo.delete(persona);
    }

    private void validate(PersonaInputDto personaInputDTO) throws UnprocessableException {
        String usuario = personaInputDTO.getUsuario();
        if (usuario == null) throw new UnprocessableException("Error: user is null.");
        if (usuario.length() < 6 || usuario.length() > 10)
            throw new UnprocessableException("Error: user length must be between 6 and 10 characters");
        if (personaInputDTO.getPassword() == null) throw new UnprocessableException("Error: password is null.");
        if (personaInputDTO.getName() == null) throw new UnprocessableException("Error: name is null.");
        if (personaInputDTO.getCompany_email() == null)
            throw new UnprocessableException("Error: Company_email is null.");
        if (personaInputDTO.getPersonal_email() == null)
            throw new UnprocessableException("Error: Personal_email is null.");
        if (personaInputDTO.getCity() == null) throw new UnprocessableException("Error: City is null.");
        if (personaInputDTO.getCreated_date() == null) throw new UnprocessableException("Error: Created_date is null");
    }

}


