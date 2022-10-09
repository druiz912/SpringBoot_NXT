package com.druiz.bosonit.db2.person.application.port;

import com.druiz.bosonit.db2.person.infrastructure.controller.dto.input.PersonInputDto;
import com.druiz.bosonit.db2.person.infrastructure.controller.dto.output.PersonOutputDto;

import java.util.List;

public interface PersonService {
    //Crear una person
    PersonOutputDto addPersona(PersonInputDto personInputDTO);

    //Obtener una person x su id
    PersonOutputDto getPersonaId(String id, String outputType);

    //Obtener una person por su nombre
    List<PersonOutputDto> getPersonaName(String name, String outputType);

    List<PersonOutputDto> getPersonas(String outputType);

    PersonOutputDto updatePersona(String id, PersonInputDto pInputDto);

    void deletePersona(String id) ;
}
