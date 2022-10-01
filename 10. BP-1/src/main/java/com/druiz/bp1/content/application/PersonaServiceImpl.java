package com.druiz.bp1.content.application;

import com.druiz.bp1.content.application.port.IPersonaService;
import com.druiz.bp1.content.domain.PersonaEntity;
import com.druiz.bp1.content.infrastructure.controller.dto.input.PersonaInputDto;
import com.druiz.bp1.content.infrastructure.controller.dto.output.PersonaOutputDto;
import com.druiz.bp1.exceptions.NotFoundException;
import com.druiz.bp1.exceptions.UnprocesableException;
import com.druiz.bp1.content.infrastructure.repository.IPersonaRepoJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServiceImpl implements IPersonaService {

    @Autowired
    IPersonaRepoJPA repo;

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto pInputDto) throws NotFoundException, UnprocesableException{
        this.validate(pInputDto);
        PersonaEntity persona = this.updatePersona();
        repo.save(persona);
        return new PersonaOutputDto(persona);
    }

    @Override
    public PersonaOutputDto getPersonaId(int id) throws NotFoundException {
        PersonaEntity personaEntity = null;
        try {
            personaEntity = repo.findById(id).orElseThrow(() -> new Exception("No se encuentra la persona con ese id"));
        } catch (Exception e) {
            throw new RuntimeException(e);
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
        repo.findAll().forEach(personaEntity -> {
            PersonaOutputDto personOutputDTO = new PersonaOutputDto(personaEntity);
            listaTemporal.add(personOutputDTO);
        });
        return listaTemporal;
    }


    @Override
    public PersonaOutputDto updatePersona(@PathVariable(value = "id") int id, PersonaInputDto pInputDto) throws NotFoundException,UnprocesableException{
        this.validate(pInputDto);
        PersonaEntity persona = repo.findById(pInputDto.getId_persona()).orElseThrow(()->new NotFoundException("id "+pInputDto.getId_persona()+" not found."));
        persona.setPassword(pInputDto.getPassword());
        persona.setUsuario(pInputDto.getUsuario());
        persona.setName(pInputDto.getName());
        persona.setUsuario(pInputDto.getUsuario());
        persona.setName(pInputDto.getName());
        persona.setSurname(pInputDto.getSurname());
        persona.setCompany_email(pInputDto.getCompany_email());
        persona.setPersonal_email(pInputDto.getPersonal_email());
        persona.setCity(pInputDto.getCity());
        persona.setActive(pInputDto.isActive());
        persona.setCreated_date(pInputDto.getCreated_date());
        persona.setImagen_url(pInputDto.getImagen_url());
        persona.setTermination_date(pInputDto.getTermination_date());
        repo.save(persona);
        return new PersonaOutputDto(persona);
    }
    @Override
    public void deletePersona(int id) {
        PersonaEntity personaEnt = repo.findById(id).orElseThrow(() -> new NotFoundException("No se ha encontrado el ID"));
        repo.delete(personaEnt);
        repo.save(personaEnt);
    }

    private void validate(PersonaInputDto personaInputDTO) throws UnprocesableException{
        String usuario = personaInputDTO.getUsuario();
        if (usuario==null) throw new UnprocesableException("Error: user is null.");
        if (usuario.length()<6 || usuario.length()>10) throw new UnprocesableException("Error: user length must be between 6 and 10 characters");
        if (personaInputDTO.getPassword()==null) throw new UnprocesableException("Error: password is null.");
        if (personaInputDTO.getName()==null) throw new UnprocesableException("Error: name is null.");
        if (personaInputDTO.getCompany_email()==null) throw new UnprocesableException("Error: Company_email is null.");
        if (personaInputDTO.getPersonal_email()==null) throw new UnprocesableException("Error: Personal_email is null.");
        if (personaInputDTO.getCity()==null) throw new UnprocesableException("Error: City is null.");
        if (personaInputDTO.getCreated_date()==null) throw new UnprocesableException("Error: Created_date is null");
    }

    }


