package com.druiz.bs8.content.application.port;

import com.druiz.bs8.content.infrastructure.controller.dto.input.PersonaInputDto;
import com.druiz.bs8.content.infrastructure.controller.dto.output.PersonaOutputDto;

import java.util.List;

public interface IPersonaService {
    //Crear una persona
    PersonaOutputDto addPersona(PersonaInputDto personaInputDTO) throws Exception;

    //Obtener una persona x su id
    PersonaOutputDto getPersonaId(int id) throws Exception;

    //Obtener una persona por su nombre
    List<PersonaOutputDto> getPersonaName(String name) throws Exception;

    List<PersonaOutputDto> getPersonas();

    PersonaOutputDto updatePersona(int id, PersonaInputDto personaInputDto) throws Exception;

    void deletePersona(int id) throws Exception;
}
