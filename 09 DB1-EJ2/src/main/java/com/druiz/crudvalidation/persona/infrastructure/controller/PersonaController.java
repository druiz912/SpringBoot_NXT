package com.druiz.crudvalidation.persona.infrastructure.controller;

import com.druiz.crudvalidation.persona.application.PersonaService;
import com.druiz.crudvalidation.persona.infrastructure.controller.dtos.PersonaInputDto;
import com.druiz.crudvalidation.persona.infrastructure.controller.dtos.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("persona")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @PostMapping
    public PersonaOutputDto addPersona(@RequestBody PersonaInputDto personInputDTO) throws Exception {
        return personaService.addPersona(personInputDTO);
    }

    @GetMapping("/id/{id}")
    public PersonaOutputDto getPersonaId(@PathVariable("id") int id) throws Exception {
        return personaService.getPersonaId(id);
    }

    @GetMapping("/name/{name}")
    public List<PersonaOutputDto> getPersonaName(@PathVariable("name") String name) throws Exception {
        return personaService.getPersonaName(name);
    }

    @GetMapping
    public List<PersonaOutputDto> getPersons() {
        return personaService.getPersonas();
    }

    @PutMapping("/update/{id}")
    public void updatePersona(@PathVariable int id, @RequestBody PersonaInputDto p) {
        personaService.updatePersona(id, p);
        System.out.println("Usuario con id: "+id+" ha sido modificado");
    }

    @DeleteMapping("{id}")
    public void deletePersona (@PathVariable int id) throws Exception{
        personaService.deletePersona(id);
        System.out.println("Usuario con id: "+id+" eliminado");
    }
}
