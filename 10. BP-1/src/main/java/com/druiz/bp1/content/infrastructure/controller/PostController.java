package com.druiz.bp1.content.infrastructure.controller;

import com.druiz.bp1.content.application.port.IPersonaService;
import com.druiz.bp1.content.infrastructure.controller.dto.input.PersonaInputDto;
import com.druiz.bp1.content.infrastructure.controller.dto.output.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    IPersonaService personaService;

    @PostMapping("/add")
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto personInputDTO) throws Exception {
        if (personaService == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        PersonaOutputDto personaOutputDto = personaService.addPersona(personInputDTO);
        return new ResponseEntity<>(personaOutputDto, HttpStatus.OK);
    }
}


