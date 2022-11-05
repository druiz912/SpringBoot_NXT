package com.druiz.bosonit.docker.person.application.port;

import com.druiz.bosonit.docker.person.infrastructure.controller.dto.input.PersonaInputDto;
import com.druiz.bosonit.docker.person.infrastructure.controller.dto.output.PersonaOutputDto;

import java.util.List;

public interface PersonService {
    //Crear una person
    PersonaOutputDto addPersona(PersonaInputDto personaInputDTO) throws Exception;

    //Obtener una person x su id
    PersonaOutputDto getPersonaId(String id, String outputType) throws Exception;

    //Obtener una person por su nombre
    List<PersonaOutputDto> getPersonaName(String name, String outputType);

    List<PersonaOutputDto> getPersonas(String outputType);

    PersonaOutputDto updatePersona(String id, PersonaInputDto pInputDto) throws Exception;

    void deletePersona(String id) throws Exception;
}
