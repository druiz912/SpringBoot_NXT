package com.druiz.bp1.content.application.port;

import com.druiz.bp1.content.infrastructure.controller.dto.input.PersonInputDto;
import com.druiz.bp1.content.infrastructure.controller.dto.output.PersonOutputDto;
import com.druiz.bp1.exceptions.NotFoundException;
import com.druiz.bp1.exceptions.UnprocesableException;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PersonService {
    PersonOutputDto addPerson(PersonInputDto personInputDTO) throws NotFoundException, UnprocesableException;

    PersonOutputDto findPersonById(int id) throws Exception;

    List<PersonOutputDto> findPersonByName(String name);

    List<PersonOutputDto> findAllPerson();

    PersonOutputDto updatePerson(@PathVariable(value = "id") int id, PersonInputDto p) throws NotFoundException, UnprocesableException;

    void deletePerson(int id) throws Exception;
}
