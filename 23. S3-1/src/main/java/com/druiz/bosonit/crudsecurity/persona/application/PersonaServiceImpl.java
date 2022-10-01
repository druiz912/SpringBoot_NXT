package com.druiz.bosonit.crudsecurity.persona.application;

import com.druiz.bosonit.crudsecurity.persona.domain.PersonaEntity;
import com.druiz.bosonit.crudsecurity.persona.infrastructure.dtos.PersonaInputDto;
import com.druiz.bosonit.crudsecurity.persona.infrastructure.dtos.PersonaOutputDto;
import com.druiz.bosonit.crudsecurity.persona.infrastructure.repo.PersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepo repoPersona;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<PersonaOutputDto> getAllPersons() {
        List<PersonaOutputDto> personaOutputDTOList = new ArrayList<>();

        for (PersonaEntity persona : repoPersona.findAll()){
            PersonaOutputDto outputDTO = new PersonaOutputDto(persona);
            personaOutputDTOList.add(outputDTO);
        }
        return personaOutputDTOList;
    }

    @Override
    public boolean existsPerson(int id) {
        return repoPersona.existsById(id);
    }

    @Override
    public PersonaOutputDto getPersonByID(int id) {
        PersonaEntity persona = repoPersona.findById(id).orElse(null);
        return new PersonaOutputDto(persona);
    }

    @Override
    public List<PersonaOutputDto> getPersonsByName(String name) {
        List<PersonaOutputDto> personaOutputDTOList = new ArrayList<>();

        for (PersonaEntity persona : repoPersona.findByName(name)){
            PersonaOutputDto outputDTO = new PersonaOutputDto(persona);
            personaOutputDTOList.add(outputDTO);
        }
        return personaOutputDTOList;
    }

    @Override
    public PersonaEntity getPersonaByUser(String username){
        return repoPersona.findByUserPersona(username);
    }

    @Override
    public PersonaOutputDto postPerson(PersonaInputDto personaInputDto) {
        personaInputDto.setPassword(passwordEncoder.encode(personaInputDto.getPassword()));
        PersonaEntity personEntity = new PersonaEntity(personaInputDto);
        repoPersona.save(personEntity);
        return new PersonaOutputDto(personEntity);
    }

    @Override
    public PersonaOutputDto updatePerson(int id, PersonaInputDto personaInputDTO) {
        PersonaEntity personInDB = repoPersona.findById(id).orElseThrow(
                ()-> new RuntimeException("Persona not found with id: " + id));
        if (personInDB != null) {
            personInDB.updateEntity(personaInputDTO);
        }
        repoPersona.save(personInDB);

        return new PersonaOutputDto(personInDB);
    }

    @Override
    public PersonaOutputDto deletePerson(int id) {
        PersonaOutputDto personaOutputDTO = getPersonByID(id);
        repoPersona.deleteById(id);
        return personaOutputDTO;
    }
    //SECURITY TOKEN Y LOGIN

    @Override
    public UserDetails loadPersonaByUser(String username) throws UsernameNotFoundException {
        PersonaEntity persona = repoPersona.findByUserPersona(username);
        if(persona == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(Objects.equals(persona.getRol(), "ADMIN")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return new User(persona.getUserPersona(), persona.getPassword(),authorities);

    }

}
