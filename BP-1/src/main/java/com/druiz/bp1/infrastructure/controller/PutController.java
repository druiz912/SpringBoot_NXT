package com.druiz.bp1.infrastructure.controller;

import com.druiz.bp1.application.port.IPersonaService;
import com.druiz.bp1.infrastructure.controller.dto.input.PersonaInputDto;
import com.druiz.bp1.infrastructure.controller.dto.output.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PutController {

    @Autowired
    IPersonaService personaService;

    @PutMapping("/update/{id}")
    public ResponseEntity<PersonaOutputDto> updatePersona(@PathVariable int id, @RequestBody PersonaInputDto personaInputDto) {
        if(personaService==null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        PersonaOutputDto personaOutputDto = personaService.updatePersona(id, personaInputDto);
        return new ResponseEntity<>(personaOutputDto, HttpStatus.OK) ;
    }

}

