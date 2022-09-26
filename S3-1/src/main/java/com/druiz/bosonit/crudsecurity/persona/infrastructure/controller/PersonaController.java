package com.druiz.bosonit.crudsecurity.persona.infrastructure.controller;

import com.druiz.bosonit.crudsecurity.persona.application.PersonaService;
import com.druiz.bosonit.crudsecurity.persona.domain.PersonaEntity;
import com.druiz.bosonit.crudsecurity.persona.infrastructure.dtos.PersonaInputDto;
import com.druiz.bosonit.crudsecurity.persona.infrastructure.dtos.PersonaOutputDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/persona")
@Slf4j
public class PersonaController {
    @Autowired
    PersonaService personaService;


    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String userPage(Authentication authentication) {
        log.info("Username={}, roles={}", authentication.getPrincipal(), authentication.getAuthorities());
        return "User page.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminPage(Authentication authentication) {
        log.info("Username={}, roles={}", authentication.getPrincipal(), authentication.getAuthorities());
        return "Admin page";
    }


    @GetMapping
    public List<PersonaOutputDto> getPersonsRoute() {
        return personaService.getAllPersons();
    }

    @GetMapping("/name/{name}")
    public List<PersonaOutputDto> getPersonByNameRoute(@PathVariable String name) {
        return personaService.getPersonsByName(name);
    }


    @GetMapping("id/{id}")
    public ResponseEntity<?> getPersonByIDRoute(@PathVariable int id) {
        if (!personaService.existsPerson(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        PersonaOutputDto personaOutputDTO = personaService.getPersonByID(id);
        return new ResponseEntity<>(personaOutputDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public PersonaOutputDto postPersonRoute(@RequestBody PersonaInputDto personaInputDTO) {
        return personaService.postPerson(personaInputDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePersonRoute(@PathVariable int id, @RequestBody PersonaInputDto personaInputDTO) {
        //If ID doesnt exists then return 404
        if (!personaService.existsPerson(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        PersonaOutputDto personaOutputDTO = personaService.updatePerson(id, personaInputDTO);
        return new ResponseEntity<>(personaOutputDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersonRoute(@PathVariable int id) {
        //If ID doesnt exists then return 404
        if (!personaService.existsPerson(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        PersonaOutputDto personaOutputDTO = personaService.deletePerson(id);
        return new ResponseEntity<>(personaOutputDTO, HttpStatus.OK);
    }


}