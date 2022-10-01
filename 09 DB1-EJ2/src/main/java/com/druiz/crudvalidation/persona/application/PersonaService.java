package com.druiz.crudvalidation.persona.application;

import com.druiz.crudvalidation.persona.infrastructure.controller.dtos.PersonaInputDto;
import com.druiz.crudvalidation.persona.infrastructure.controller.dtos.PersonaOutputDto;

import java.util.List;

public interface PersonaService {
    PersonaOutputDto addPersona(PersonaInputDto personaInputDTO) throws Exception;
    PersonaOutputDto getPersonaId(int id) throws Exception;
    List<PersonaOutputDto> getPersonaName(String username) throws Exception;

    List<PersonaOutputDto> getPersonas();

    PersonaOutputDto updatePersona(int id, PersonaInputDto p);

    void deletePersona(int id) throws Exception;
}
