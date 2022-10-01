package com.druiz.crudvalidation.persona.interfaces;

import com.druiz.crudvalidation.persona.dtos.PersonaInputDto;
import com.druiz.crudvalidation.persona.dtos.PersonaOutputDto;

import java.util.List;

public interface IPersonaService {
    PersonaOutputDto addPersona(PersonaInputDto personaInputDTO) throws Exception;

    PersonaOutputDto getPersonaId(int id) throws Exception;

    List<PersonaOutputDto> getPersonaName(String username) throws Exception;

    List<PersonaOutputDto> getPersonas();

    void updatePersona(int id, PersonaInputDto p);

    void deletePersona(int id) throws Exception;
}
