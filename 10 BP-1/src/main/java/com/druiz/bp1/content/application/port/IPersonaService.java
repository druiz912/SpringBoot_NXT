package com.druiz.bp1.content.application.port;

import com.druiz.bp1.content.infrastructure.controller.dto.input.PersonaInputDto;
import com.druiz.bp1.content.infrastructure.controller.dto.output.PersonaOutputDto;
import com.druiz.bp1.exceptions.NotFoundException;
import com.druiz.bp1.exceptions.UnprocesableException;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IPersonaService {
    PersonaOutputDto addPersona(PersonaInputDto personaInputDTO) throws NotFoundException, UnprocesableException;

    PersonaOutputDto getPersonaId(int id) throws Exception;

    List<PersonaOutputDto> getPersonaName(String name);

    List<PersonaOutputDto> getPersonas();

    PersonaOutputDto updatePersona(@PathVariable(value = "id") int id, PersonaInputDto p) throws NotFoundException, UnprocesableException;

    void deletePersona(int id) throws Exception;
}
