package com.druiz.bosonit.mongodb.persona.infrastructure.controller;

import com.druiz.bosonit.mongodb.persona.application.port.PersonaService;
import com.druiz.bosonit.mongodb.persona.infrastructure.dto.input.PersonaInputDto;
import com.druiz.bosonit.mongodb.persona.infrastructure.dto.output.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("persona")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @PostMapping
    public PersonaOutputDto addPersona(@RequestBody PersonaInputDto personaInputDto) {
            return personaService.addPersona(personaInputDto);
    }

    /////////////GET//////////////
    @GetMapping("/id/{id}")
    public List<PersonaOutputDto> getPersonaByID(@PathVariable(name = "id") int id) {
        return personaService.getPersonaByID(id);
    }

    @GetMapping("/all/all")
    public List<PersonaOutputDto> getAllPersonas() {

        return personaService.getAllPersonas();
    }

    @GetMapping("/name/{name}")
    public List<PersonaOutputDto> findByName(@PathVariable("name") String name) {
        return personaService.getPersonaByName(name);
    }
    /////////////////////////

    @DeleteMapping("{id}")
    public void deleteByID(@PathVariable(value = "id") int id) throws Exception {
        personaService.deleteByID(id);
    }

    @PutMapping("{id}")
    public PersonaOutputDto updatePersona(@PathVariable(value = "id") int id, @RequestBody PersonaInputDto personaInputDTO) throws Exception {
        return personaService.updatePersona(id, personaInputDTO);
    }


}
