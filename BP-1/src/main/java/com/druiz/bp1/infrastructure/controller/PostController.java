package com.druiz.bp1.infrastructure.controller;

import com.druiz.bp1.application.port.IPersonaService;
import com.druiz.bp1.infrastructure.controller.dto.input.PersonaInputDto;
import com.druiz.bp1.infrastructure.controller.dto.output.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    IPersonaService personaService;

    @PostMapping("/add")
    public PersonaOutputDto addPersona(@RequestBody PersonaInputDto personInputDTO) throws Exception {
        return personaService.addPersona(personInputDTO);
    }

}
