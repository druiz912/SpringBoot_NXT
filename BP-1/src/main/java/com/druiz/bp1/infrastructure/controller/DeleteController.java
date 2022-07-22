package com.druiz.bp1.infrastructure.controller;

import com.druiz.bp1.application.port.IPersonaService;
import com.druiz.bp1.infrastructure.controller.dto.output.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteController {

    @Autowired
    IPersonaService personaService;

    @DeleteMapping("/eliminarPersona1/{id}")
    public ResponseEntity<PersonaOutputDto> deletePersona1 (@PathVariable int id) throws Exception{
        if(personaService==null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        PersonaOutputDto personaOutputDto = personaService.deletePersona(id);
        return new ResponseEntity<>(personaOutputDto, HttpStatus.OK) ;
        }

}
