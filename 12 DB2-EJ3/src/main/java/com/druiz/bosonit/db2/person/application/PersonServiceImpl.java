package com.druiz.bosonit.db2.person.application;

import com.druiz.bosonit.db2.person.application.port.PersonService;
import com.druiz.bosonit.db2.person.domain.Person;
import com.druiz.bosonit.db2.person.infrastructure.controller.dto.input.PersonInputDto;
import com.druiz.bosonit.db2.person.infrastructure.controller.dto.output.PersonOutputDto;
import com.druiz.bosonit.db2.person.infrastructure.controller.dto.output.PersonProfesorOutputDto;
import com.druiz.bosonit.db2.person.infrastructure.controller.dto.output.PersonStudentOutputDto;
import com.druiz.bosonit.db2.person.infrastructure.repository.PersonRepo;
import com.druiz.bosonit.db2.utils.exceptions.NotFoundException;
import com.druiz.bosonit.db2.utils.exceptions.UnprocesableException;
import com.druiz.bosonit.db2.utils.mappers.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepo repo;

    @Autowired
    PersonMapper personMapper;

    @Override
    public PersonOutputDto addPersona(@Valid PersonInputDto personInputDto) {
        try {
            Person persona = personMapper.toEntity(personInputDto);
            repo.save(persona);
            return personMapper.toOutput(persona);
        } catch (Exception e) {
            throw new UnprocesableException(e.getMessage());
        }
    }

    //Segunda parte del ejercicio
    @Override
    public PersonOutputDto getPersonaId(String id, String outputType) {
        if (outputType.equalsIgnoreCase("person")) {
            Person person = repo.findById(id).orElseThrow(
                    () -> new NotFoundException("No se encuentra a la Persona con el id: " + id));
            return new PersonOutputDto(person);
        } else if (outputType.equalsIgnoreCase("teacher")) {
            Person person = repo.findById(id).orElseThrow(
                    () -> new NotFoundException("No se encuentra al Profesor con el id: " + id));
            return new PersonProfesorOutputDto(person);
        } else if (outputType.equalsIgnoreCase("student")) {
            Person person = repo.findById(id).orElseThrow(
                    () -> new NotFoundException("No se ha encontrado al Student con el id: " + id));
            return new PersonStudentOutputDto(person);
        }
        return new PersonOutputDto(new Person());
    }

    @Override
    public List getPersonaName(String name, String outputType) {
        List<PersonOutputDto> personOutputDTOList = new ArrayList<>();
        List<PersonProfesorOutputDto> personaProfesorOutputDTOS = new ArrayList<>();
        List<PersonStudentOutputDto> personaStudentOutputDTOList = new ArrayList<>();

        if (repo.findByName(name) != null) {
            //CASO PERSONA
            if (outputType.equalsIgnoreCase("person")) {
                repo.findByName(name).forEach(personaEntity -> personOutputDTOList.add(personMapper.toOutput(personaEntity)));
                return personOutputDTOList;
                //CASO PROFESOR
            } else if (outputType.equalsIgnoreCase("teacher")) {
                repo.findByName("teacher").forEach(personaEntity -> personOutputDTOList.add(new PersonProfesorOutputDto(personaEntity)));
                return personaProfesorOutputDTOS;
                // CASO ESTUDIANTE
            } else if (outputType.equalsIgnoreCase("student")) {
                repo.findByName("student").forEach(personaEntity -> personaStudentOutputDTOList.add(new PersonStudentOutputDto(personaEntity)));
                return personaStudentOutputDTOList;
            }
        }

        return new ArrayList<>();
    }

    @Override
    public List getPersonas(String outputType) {
        List<PersonOutputDto> listPersonOutputDto = new ArrayList<>();
        List<PersonProfesorOutputDto> listPersonaProfesorOutputDto = new ArrayList<>();
        List<PersonStudentOutputDto> listPersonaStudentOutputDto = new ArrayList<>();

        repo.findAll();
        //CASO PERSONA
        if (outputType.equalsIgnoreCase("person")) {
            repo.findAll().forEach(
                    personaEntity -> listPersonOutputDto.add(new PersonOutputDto(personaEntity)));
            return listPersonOutputDto;
            //CASO PROFESOR
        } else if (outputType.equalsIgnoreCase("teacher")) {
            repo.findAll().forEach((
                    personaEntity -> listPersonaProfesorOutputDto.add(new PersonProfesorOutputDto(personaEntity))));
            return listPersonaProfesorOutputDto;
            //CASO ESTUDIANTE
        } else if (outputType.equalsIgnoreCase("student")) {
            repo.findAll().forEach(personaEntity -> listPersonaStudentOutputDto.add(new PersonStudentOutputDto(personaEntity)));
            return listPersonaStudentOutputDto;
        }
        return new ArrayList<>();
    }


    @Override
    public PersonOutputDto updatePersona(String id, PersonInputDto pInputDto) {
        Person persona = repo.findById(id).orElseThrow(
                () -> new NotFoundException("id " + id + " not found."));
        persona.update(pInputDto);
        repo.save(persona);
        return personMapper.toOutput(persona);
    }

    @Override
    public void deletePersona(String id) {
        Person personaEnt = repo.findById(id).orElseThrow(
                () -> new NotFoundException("No se ha podido encontrar el id: " + id));
        repo.delete(personaEnt);
        repo.save(personaEnt);
    }


}


