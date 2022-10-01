package com.druiz.bosonit.mongodb.persona.application.port;

import com.druiz.bosonit.mongodb.persona.infrastructure.dto.input.PersonaInputDto;
import com.druiz.bosonit.mongodb.persona.infrastructure.dto.output.PersonaOutputDto;

import java.util.List;

public interface PersonaService {
    //Añadir
    PersonaOutputDto addPersona(PersonaInputDto pInputDto);
    //Obtener
    List<PersonaOutputDto> getPersonaByID(int id);

    List<PersonaOutputDto> getAllPersonas();
    List<PersonaOutputDto> getPersonaByName(String name);
    //BORRAR
    void deleteByID(int id) throws Exception;
    //ACTUALIZAR
    PersonaOutputDto updatePersona(int id, PersonaInputDto personaInputDTO) throws Exception;




}
