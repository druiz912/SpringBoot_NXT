package com.druiz.bosonit.dba1.person.application;

import com.druiz.bosonit.dba1.person.infrastructure.controllers.dto.PersonInputDto;
import com.druiz.bosonit.dba1.person.infrastructure.controllers.dto.PersonOutputDto;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<PersonOutputDto> findAllPersons(int pageNumber);

    boolean existsPerson(int id);

    PersonOutputDto findPersonById(int id);

    List<PersonOutputDto> findPersonByName(String name);

    List<PersonOutputDto> criteriaQuery(
            Optional<String> name,
            Optional<String> user,
            Optional<String> surname,
            Optional<String> address,
            Optional<Date> creation_date,
            String dateCondition,
            Optional<String> sorting);

    PersonOutputDto addPerson(PersonInputDto personInputDTO);

    PersonOutputDto updatePerson(int id, PersonInputDto personInputDTO);

    PersonOutputDto deletePerson(int id);
}
