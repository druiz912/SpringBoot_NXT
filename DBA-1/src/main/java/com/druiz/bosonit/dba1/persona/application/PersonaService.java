package com.druiz.bosonit.dba1.persona.application;

import com.druiz.bosonit.dba1.persona.infrastructure.dto.PersonaInputDto;
import com.druiz.bosonit.dba1.persona.infrastructure.dto.PersonaOutputDto;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PersonaService {

    List<PersonaOutputDto> getAllPersonas(int pageNumber);

    boolean existsPersona(int id);

    PersonaOutputDto getPersonaByID(int id);

    List<PersonaOutputDto> getPersonasByName(String name);

    List<PersonaOutputDto> criteriaQuery(
            Optional<String> name,
            Optional<String> user,
            Optional<String> surname,
            Optional<String> address,
            Optional<Date> creation_date,
            String dateCondition,
            Optional<String> sorting);

    PersonaOutputDto postPersona(PersonaInputDto personInputDTO);

    PersonaOutputDto updatePersona(int id, PersonaInputDto personaInputDTO);

    PersonaOutputDto deletePersona(int id);
}
