package com.druiz.docker.persona.infrastructure.controller;

import com.druiz.docker.persona.infrastructure.controller.dto.input.PersonaInputDto;
import com.druiz.docker.persona.infrastructure.controller.dto.output.PersonaOutputDto;
import com.druiz.docker.persona.application.port.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping(value = "persona")
public class PersonaController {

    @Autowired
    IPersonaService personaService;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody @Valid PersonaInputDto personInputDTO) throws Exception {
        return new ResponseEntity<>(personaService.addPersona(personInputDTO),HttpStatus.OK);
    }

    @PutMapping("{id}")
    public PersonaOutputDto updatePersona(
            @PathVariable("id") String id,
            @RequestBody PersonaInputDto personInputDTO)
            throws Exception {
        return personaService.updatePersona(id, personInputDTO);
    }

    @GetMapping("/id/{id}")
    public PersonaOutputDto getPersonaId(
            @PathVariable(value = "id") String id,
            @RequestParam(defaultValue = "persona", required = false) String outputType) throws Exception {
        return personaService.getPersonaId(id, outputType); //--> Segunda parte del ejercicio
    }


    @GetMapping("/name/{name}")
    public List<PersonaOutputDto> getPersonaName(
            @PathVariable("name") String name,
            @RequestParam(defaultValue = "persona", required = false) String outputType) {
        return personaService.getPersonaName(name,outputType);
    }

    @GetMapping
    public List<PersonaOutputDto> getPersons(
            @RequestParam(defaultValue = "persona", required = false) String outputType) {
        return personaService.getPersonas(outputType);
    }

    @DeleteMapping("{id}")
    public void deletePersona(@PathVariable("id") String id) throws Exception {
        personaService.deletePersona(id);
    }

    //BS12
    @CrossOrigin(origins = "https://cdpn.io", methods = RequestMethod.POST)
    @PostMapping("/addperson")
    public ResponseEntity<PersonaOutputDto> addPersonaCORS(@RequestBody PersonaInputDto personaInputDto) throws Exception {
        return new ResponseEntity<>(personaService.addPersona(personaInputDto),HttpStatus.OK);
    }


    @CrossOrigin(origins = "https://cdpn.io", methods = RequestMethod.GET)
    @GetMapping("getall")
    public List<PersonaOutputDto> getAllUsuarios(@RequestParam(defaultValue = "persona", required = false) String outputType) {
        return personaService.getPersonas(outputType);
    }




}
