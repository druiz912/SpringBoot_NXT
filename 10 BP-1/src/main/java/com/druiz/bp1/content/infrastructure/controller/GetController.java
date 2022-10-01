package com.druiz.bp1.content.infrastructure.controller;

import com.druiz.bp1.content.application.port.IPersonaService;
import com.druiz.bp1.content.infrastructure.controller.dto.output.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetController {

    @Autowired
    IPersonaService personaService;

    @GetMapping("/id/{id}")
    public ResponseEntity<PersonaOutputDto> getPersonaId(@PathVariable("id") int id) throws Exception {
        if(personaService==null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        PersonaOutputDto personaOutputDto = personaService.getPersonaId(id);
        return new ResponseEntity<>(personaOutputDto, HttpStatus.OK) ;
    }


    @GetMapping("/name/{name}")
    public List<PersonaOutputDto> getPersonaName(@PathVariable("name") String name) throws Exception {
        return personaService.getPersonaName(name);
    }

    @GetMapping("/all")
    public List<PersonaOutputDto> getPersons() {
        return personaService.getPersonas();
    }
}
