package com.druiz.bosonit.crudsecurity.persona.application;

import com.druiz.bosonit.crudsecurity.persona.domain.PersonaEntity;
import com.druiz.bosonit.crudsecurity.persona.infrastructure.dtos.PersonaInputDto;
import com.druiz.bosonit.crudsecurity.persona.infrastructure.dtos.PersonaOutputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;

public interface PersonaService {


    List<PersonaOutputDto> getAllPersons();

    boolean existsPerson(int id);

    PersonaOutputDto getPersonByID(int id);

    List<PersonaOutputDto> getPersonsByName(String name);

    PersonaEntity getPersonaByUser(String username);

    PersonaOutputDto postPerson(PersonaInputDto personInputDTO);

    PersonaOutputDto updatePerson(int id, PersonaInputDto personaInputDTO);

    PersonaOutputDto deletePerson(int id);

    UserDetails loadPersonaByUser(String username) throws UsernameNotFoundException;
}
