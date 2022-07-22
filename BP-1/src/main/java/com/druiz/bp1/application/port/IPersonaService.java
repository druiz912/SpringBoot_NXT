package com.druiz.bp1.application.port;

import com.druiz.bp1.infrastructure.controller.dto.input.PersonaInputDto;
import com.druiz.bp1.infrastructure.controller.dto.output.PersonaOutputDto;

import java.util.List;

public interface IPersonaService {
    PersonaOutputDto addPersona(PersonaInputDto personaInputDTO) throws Exception;

    PersonaOutputDto getPersonaId(int id) throws Exception;

    List<PersonaOutputDto> getPersonaName(String username) throws Exception;

    List<PersonaOutputDto> getPersonas();

    PersonaOutputDto updatePersona(int id, PersonaInputDto p);

    PersonaOutputDto deletePersona(int id) throws Exception;
}
