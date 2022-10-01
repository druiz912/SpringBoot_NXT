package com.druiz.bs8.content.infrastructure.controller;

import com.druiz.bs8.content.application.port.IPersonaService;
import com.druiz.bs8.content.infrastructure.controller.dto.input.PersonaInputDto;
import com.druiz.bs8.content.infrastructure.controller.dto.output.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/persona")
public class PersonaController {

    @Autowired
    IPersonaService personaService;


    @PostMapping /* http://localhost:8080/persona */
    public PersonaOutputDto addPersona(@RequestBody @Valid PersonaInputDto pInputDto) throws Exception {
        return personaService.addPersona(pInputDto);
    }

    @GetMapping("/{id}")  /* http://localhost:8080/persona/1 */
    public PersonaOutputDto getPersonaId(@PathVariable("id") String id) throws Exception {
        return personaService.getPersonaId(Integer.parseInt(id));
    }

    @GetMapping("/name/{name}") /* http://localhost:8080/persona/name/Daniel */
    public List<PersonaOutputDto> getPersonaName(@PathVariable("name") String usuario) throws Exception {
        return personaService.getPersonaName(usuario);
    }
    // GET http://localhost:8080/person/PEPE

    @GetMapping /* https://localhost::8080/persona */
    public List<PersonaOutputDto> getPersons() {
        return personaService.getPersonas();
    }

    @PutMapping("/{id}") /* https://localhost::8080/persona/1 */
    public PersonaOutputDto updatePersona(@PathVariable("id") int id, @RequestBody PersonaInputDto personInputDTO)
            throws Exception {
        return personaService.updatePersona(id, personInputDTO);
    }

    @DeleteMapping("{id}") /* https://localhost::8080/persona/1 */
    public void deletePersona(@PathVariable("id") int id) throws Exception {
        personaService.deletePersona(id);
    }

/*
    @PostMapping /* ..8080/persona
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody @Valid PersonaInputDto personInputDTO) throws Exception {
        return new ResponseEntity<>(personaService.addPersona(personInputDTO),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public PersonaOutputDto updatePersona(@PathVariable("id") int id, @RequestBody PersonaInputDto personInputDTO)
            throws Exception {
        return personaService.updatePersona(id, personInputDTO);
    }

    @GetMapping("/id/{id}")
    public PersonaOutputDto getPersonId(@PathVariable("id") String id) throws Exception {
        return personaService.getPersonaId(Integer.parseInt(id));
    }


    @GetMapping("/name/{name}")
    public List<PersonaOutputDto> getPersonaName(@PathVariable("name") String name) throws Exception {
        return personaService.getPersonaName(name);
    }

    @GetMapping("/all")
    public List<PersonaOutputDto> getPersons() {
        return personaService.getPersonas();
    }

    @DeleteMapping("{id}")
    public void deletePersona(@PathVariable("id") int id) throws Exception {
        personaService.deletePersona(id);
    }*/


}
