package com.druiz.bs9.persona.application;

import com.druiz.bs9.exceptions.NotFoundException;
import com.druiz.bs9.exceptions.UnprocesableException;
import com.druiz.bs9.persona.application.port.IPersonaService;
import com.druiz.bs9.persona.infrastructure.controller.dto.input.PersonaInputDto;
import com.druiz.bs9.persona.infrastructure.controller.dto.output.PersonaOutputDto;
import com.druiz.bs9.persona.infrastructure.controller.dto.output.PersonaProfesorOutputDto;
import com.druiz.bs9.persona.infrastructure.controller.dto.output.PersonaStudentOutputDto;
import com.druiz.bs9.persona.infrastructure.repository.IPersonaRepoJPA;
import com.druiz.bs9.persona.domain.PersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServiceImpl implements IPersonaService {

    @Autowired
    IPersonaRepoJPA repo;

    @Override
    public PersonaOutputDto addPersona(@Valid PersonaInputDto personaInputDto) throws Exception {
        try {
            PersonaEntity persona = new PersonaEntity(personaInputDto);
            repo.save(persona);
            return new PersonaOutputDto(persona);
        }catch (Exception e){
            throw new UnprocesableException(e.getMessage());
        }
    }

    //Segunda parte del ejercicio
    @Override
    public PersonaOutputDto getPersonaId(String id, String outputType) throws Exception {
        if (outputType.equalsIgnoreCase("persona")) {
            PersonaEntity personaEntity = repo.findById(id).orElseThrow(
                    () -> new NotFoundException("No se encuentra a la Persona con el id: " + id));
            return new PersonaOutputDto(personaEntity);
        } else if (outputType.equalsIgnoreCase("profesor")) {
            PersonaEntity personaEntity = repo.findById(id).orElseThrow(
                    () -> new NotFoundException("No se encuentra al Profesor con el id: " + id));
            return new PersonaProfesorOutputDto(personaEntity);
        } else if (outputType.equalsIgnoreCase("student")) {
            PersonaEntity personaEntity = repo.findById(id).orElseThrow(
                    () -> new NotFoundException("No se ha encontrado al Student con el id: " + id));
            return new PersonaStudentOutputDto(personaEntity);
        }
        return new PersonaOutputDto(new PersonaEntity());
    }

    @Override
    public List getPersonaName(String name, String outputType) {
        List<PersonaOutputDto> personaOutputDTOList = new ArrayList<>();
        List<PersonaProfesorOutputDto> personaProfesorOutputDTOS = new ArrayList<>();
        List<PersonaStudentOutputDto> personaStudentOutputDTOList = new ArrayList<>();

        if (repo.findByName(name) != null) {
            //CASO PERSONA
            if (outputType.equalsIgnoreCase("persona")) {
                repo.findByName(name).forEach(personaEntity -> personaOutputDTOList.add(new PersonaOutputDto(personaEntity)));
                return personaOutputDTOList;
                //CASO PROFESOR
            } else if (outputType.equalsIgnoreCase("profesor")) {
                repo.findByName("profesor").forEach(personaEntity -> personaOutputDTOList.add(new PersonaProfesorOutputDto(personaEntity)));
                return personaProfesorOutputDTOS;
                // CASO ESTUDIANTE
            } else if (outputType.equalsIgnoreCase("student")) {
                repo.findByName("student").forEach(personaEntity -> personaStudentOutputDTOList.add(new PersonaStudentOutputDto(personaEntity)));
                return personaStudentOutputDTOList;
            }
        }

        return new ArrayList<>();
    }

    @Override
    public List getPersonas(String outputType) {
        List<PersonaOutputDto> listPersonaOutputDto = new ArrayList<>();
        List<PersonaProfesorOutputDto> listPersonaProfesorOutputDto = new ArrayList<>();
        List<PersonaStudentOutputDto> listPersonaStudentOutputDto = new ArrayList<>();

        if (repo.findAll() != null) {
            //CASO PERSONA
            if (outputType.equalsIgnoreCase("persona")) {
                repo.findAll().forEach(
                        personaEntity -> listPersonaOutputDto.add(new PersonaOutputDto(personaEntity)));
                return listPersonaOutputDto;
                //CASO PROFESOR
            } else if (outputType.equalsIgnoreCase("profesor")) {
                repo.findAll().forEach((
                        personaEntity -> listPersonaProfesorOutputDto.add(new PersonaProfesorOutputDto(personaEntity))));
                return listPersonaProfesorOutputDto;
                //CASO ESTUDIANTE
            } else if (outputType.equalsIgnoreCase("student")) {
                repo.findAll().forEach(personaEntity -> listPersonaStudentOutputDto.add(new PersonaStudentOutputDto(personaEntity)));
                return listPersonaStudentOutputDto;
            }
        }
        return new ArrayList<>();
    }


    @Override
    public PersonaOutputDto updatePersona(String id, PersonaInputDto pInputDto) throws Exception {
        this.validate(pInputDto);
        PersonaEntity persona = repo.findById(pInputDto.getId_persona()).orElseThrow(
                () -> new NotFoundException("id " + pInputDto.getId_persona() + " not found."));
        persona.update(pInputDto);
        repo.save(persona);
        return new PersonaOutputDto(persona);
    }

    @Override
    public void deletePersona(String id) {
        PersonaEntity personaEnt = repo.findById(id).orElseThrow(
                () -> new NotFoundException("No se ha podido encontrar el id: " + id));
        repo.delete(personaEnt);
        repo.save(personaEnt);
    }


    private void validate(PersonaInputDto personaInputDTO) throws UnprocesableException {
        String usuario = personaInputDTO.getUsuario();
        if (usuario == null) throw new UnprocesableException("Error: user is null.");
        if (usuario.length() < 6 || usuario.length() > 10)
            throw new UnprocesableException("Error: user length must be between 6 and 10 characters");
        if (personaInputDTO.getPassword() == null) throw new UnprocesableException("Error: password is null.");
        if (personaInputDTO.getName() == null) throw new UnprocesableException("Error: name is null.");
        if (personaInputDTO.getCompany_email() == null)
            throw new UnprocesableException("Error: Company_email is null.");
        if (personaInputDTO.getPersonal_email() == null)
            throw new UnprocesableException("Error: Personal_email is null.");
        if (personaInputDTO.getCity() == null) throw new UnprocesableException("Error: City is null.");
        if (personaInputDTO.getCreated_date() == null) throw new UnprocesableException("Error: Created_date is null");
    }

}


