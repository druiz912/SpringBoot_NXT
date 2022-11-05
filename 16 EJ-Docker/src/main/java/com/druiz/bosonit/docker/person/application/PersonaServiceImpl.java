package com.druiz.bosonit.docker.person.application;

import com.druiz.bosonit.docker.utils.exceptions.NotFoundException;
import com.druiz.bosonit.docker.utils.exceptions.UnprocesableException;
import com.druiz.bosonit.docker.person.application.port.PersonService;
import com.druiz.bosonit.docker.person.domain.Person;
import com.druiz.bosonit.docker.person.infrastructure.controller.dto.input.PersonaInputDto;
import com.druiz.bosonit.docker.person.infrastructure.controller.dto.output.PersonaOutputDto;
import com.druiz.bosonit.docker.person.infrastructure.controller.dto.output.PersonaProfesorOutputDto;
import com.druiz.bosonit.docker.person.infrastructure.controller.dto.output.PersonaStudentOutputDto;
import com.druiz.bosonit.docker.person.infrastructure.repository.PersonRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonService {


    private final PersonRepo repo;

    public PersonaServiceImpl(PersonRepo repo) {
        this.repo = repo;
    }

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto dto) {
            if(!dto.getName().isEmpty()){
                Person persona = new Person(dto);
                repo.save(persona);
                return new PersonaOutputDto(persona);
            } else {
               throw new UnprocesableException("Couldn't add persona");
            }

    }

    //Segunda parte del ejercicio
    @Override
    public PersonaOutputDto getPersonaId(String id, String outputType) throws Exception {
        if (outputType.equalsIgnoreCase("person")) {
            Person person = repo.findById(id).orElseThrow(
                    () -> new NotFoundException("No se encuentra a la Persona con el id: " + id));
            return new PersonaOutputDto(person);
        } else if (outputType.equalsIgnoreCase("teacher")) {
            Person person = repo.findById(id).orElseThrow(
                    () -> new NotFoundException("No se encuentra al Profesor con el id: " + id));
            return new PersonaProfesorOutputDto(person);
        } else if (outputType.equalsIgnoreCase("student")) {
            Person person = repo.findById(id).orElseThrow(
                    () -> new NotFoundException("No se ha encontrado al Student con el id: " + id));
            return new PersonaStudentOutputDto(person);
        }
        return new PersonaOutputDto(new Person());
    }

    @Override
    public List getPersonaName(String name, String outputType) {
        List<PersonaOutputDto> personaOutputDTOList = new ArrayList<>();
        List<PersonaProfesorOutputDto> personaProfesorOutputDTOS = new ArrayList<>();
        List<PersonaStudentOutputDto> personaStudentOutputDTOList = new ArrayList<>();

        if (repo.findByName(name) != null) {
            //CASO PERSONA
            if (outputType.equalsIgnoreCase("person")) {
                repo.findByName(name)
                        .forEach( p -> personaOutputDTOList.add(new PersonaOutputDto(p)));
                return personaOutputDTOList;
                //CASO PROFESOR
            } else if (outputType.equalsIgnoreCase("teacher")) {
                repo.findByName("teacher")
                        .forEach(p -> personaOutputDTOList.add(new PersonaProfesorOutputDto(p)));
                return personaProfesorOutputDTOS;
                // CASO ESTUDIANTE
            } else if (outputType.equalsIgnoreCase("student")) {
                repo.findByName("student")
                        .forEach(p -> personaStudentOutputDTOList.add(new PersonaStudentOutputDto(p)));
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

        repo.findAll();
        //CASO PERSONA
        if (outputType.equalsIgnoreCase("person")) {
            repo.findAll().forEach(
                    personaEntity -> listPersonaOutputDto.add(new PersonaOutputDto(personaEntity)));
            return listPersonaOutputDto;
            //CASO PROFESOR
        } else if (outputType.equalsIgnoreCase("teacher")) {
            repo.findAll().forEach((
                    personaEntity -> listPersonaProfesorOutputDto.add(new PersonaProfesorOutputDto(personaEntity))));
            return listPersonaProfesorOutputDto;
            //CASO ESTUDIANTE
        } else if (outputType.equalsIgnoreCase("student")) {
            repo.findAll().forEach(personaEntity -> listPersonaStudentOutputDto.add(new PersonaStudentOutputDto(personaEntity)));
            return listPersonaStudentOutputDto;
        }
        return new ArrayList<>();
    }


    @Override
    public PersonaOutputDto updatePersona(String id, PersonaInputDto pInputDto) throws Exception {
        this.validate(pInputDto);
        Person person = repo.findById(id).orElseThrow(
                () -> new NotFoundException("id " + id + " not found."));
        person.update(pInputDto);
        repo.save(person);
        return new PersonaOutputDto(person);
    }

    @Override
    public void deletePersona(String id) {
        Person personaEnt = repo.findById(id).orElseThrow(
                () -> new NotFoundException("No se ha podido encontrar el id: " + id));
        repo.delete(personaEnt);
        repo.save(personaEnt);
    }


    private void validate(PersonaInputDto dto) throws UnprocesableException {
        String user = dto.getUser();
        if (user == null) throw new UnprocesableException("Error: user is null.");
        if (user.length() < 6 || user.length() > 10)
            throw new UnprocesableException("Error: user length must be between 6 and 10 characters");
        if (dto.getPassword() == null) throw new UnprocesableException("Error: password is null.");
        if (dto.getName() == null) throw new UnprocesableException("Error: name is null.");
        if (dto.getCompanyMail() == null)
            throw new UnprocesableException("Error: Company_email is null.");
        if (dto.getPersonalMail() == null)
            throw new UnprocesableException("Error: Personal_email is null.");
        if (dto.getCity() == null) throw new UnprocesableException("Error: City is null.");
        if (dto.getCreatedDate() == null) throw new UnprocesableException("Error: Created_date is null");
    }

}


