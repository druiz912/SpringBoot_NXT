package com.druiz.bosonit.db2.person.application.port;

import com.druiz.bosonit.db2.person.infrastructure.controller.dto.input.PersonInputDto;
import com.druiz.bosonit.db2.person.infrastructure.controller.dto.output.PersonOutputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<PersonOutputDto> findAllPersons();

    boolean existsPerson(int id);

    PersonOutputDto findPersonById(String id);

    List<PersonOutputDto> findPersonByName(String name);

    List<PersonOutputDto> getPersonWithCriteriaQuery (
            Optional<String> name,
            Optional<String>  surname,
            Optional<String>  company,
            Optional<String>  teacherName);

    PersonOutputDto addPerson(PersonInputDto personInputDTO) throws MethodArgumentNotValidException;

    PersonOutputDto updatePerson(String id, PersonInputDto personaInputDTO);

    PersonOutputDto deletePerson(String id);

    ResponseEntity<?> getTeacherWithTemplate(String id);
}
