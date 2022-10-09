package com.druiz.bosonit.db2.person.infrastructure.controller;

import com.druiz.bosonit.db2.person.infrastructure.controller.dto.output.PersonOutputDto;
import com.druiz.bosonit.db2.person.infrastructure.controller.dto.input.PersonInputDto;
import com.druiz.bosonit.db2.person.application.port.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity<PersonOutputDto> addPersona(@RequestBody @Valid PersonInputDto personInputDTO) throws Exception {
        return new ResponseEntity<>(personService.addPersona(personInputDTO),HttpStatus.OK);
    }

    @PutMapping("{id}")
    public PersonOutputDto updatePersona(
            @PathVariable("id") String id,
            @RequestBody @Valid PersonInputDto personInputDTO)
            {
        return personService.updatePersona(id, personInputDTO);
    }

    @GetMapping("/id/{id}")
    public PersonOutputDto getPersonaId(
            @PathVariable(value = "id") String id,
            @RequestParam(defaultValue = "person", required = false) String outputType) throws Exception {
        return personService.getPersonaId(id, outputType); //--> Segunda parte del ejercicio
    }


    @GetMapping("/name/{name}")
    public List<PersonOutputDto> getPersonaName(
            @PathVariable("name") String name,
            @RequestParam(defaultValue = "person", required = false) String outputType) {
        return personService.getPersonaName(name,outputType);
    }

    @GetMapping
    public List<PersonOutputDto> getPersons(
            @RequestParam(defaultValue = "person", required = false) String outputType) {
        return personService.getPersonas(outputType);
    }

    @DeleteMapping("{id}")
    public void deletePersona(@PathVariable("id") String id) throws Exception {
        personService.deletePersona(id);
    }
}
