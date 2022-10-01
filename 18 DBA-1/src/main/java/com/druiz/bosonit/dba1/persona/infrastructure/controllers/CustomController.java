package com.druiz.bosonit.dba1.persona.infrastructure.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.druiz.bosonit.dba1.persona.application.PersonaService;
import com.druiz.bosonit.dba1.persona.domain.PersonaEntity;
import com.druiz.bosonit.dba1.persona.infrastructure.dto.PersonaInputDto;
import com.druiz.bosonit.dba1.persona.infrastructure.dto.PersonaOutputDto;
import com.druiz.bosonit.dba1.persona.infrastructure.repo.PersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("persona")
public class CustomController {
    @Autowired
    PersonaService personaService;

    @PostMapping
    public PersonaOutputDto postPersona(@RequestBody PersonaInputDto personaInputDTO){
        return personaService.postPersona(personaInputDTO);
    }

    @GetMapping
    public List<PersonaOutputDto> getPersonas(@RequestParam int pageNumber){
        List<PersonaOutputDto> listaPersonasDto = personaService.getAllPersonas(pageNumber);
        return listaPersonasDto;
    }

    @GetMapping("/name/{name}")
    public List<PersonaOutputDto> getPersonaByName(@PathVariable String name){
        return personaService.getPersonasByName(name);
    }

    @GetMapping("/criteriaQuery")
    public List<PersonaOutputDto> criteriaQuery(
            @RequestParam(required = false) Optional<String> name,
            @RequestParam(required = false) Optional<String> user,
            @RequestParam(required = false) Optional<String> surname,
            @RequestParam(required = false) Optional<String> address,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Optional<Date> creation_date,
            @RequestParam(defaultValue = "equal") String dateCondition,
            @RequestParam(required = false) Optional<String> sorting
    ){
        return personaService.criteriaQuery(name,user,surname,address,creation_date,dateCondition,sorting);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable int id){ //<?> --> Recoge cualquier objeto
        if(!personaService.existsPersona(id)){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        PersonaOutputDto personaOutputDTO = personaService.getPersonaByID(id);
        return new ResponseEntity<>(personaOutputDTO, HttpStatus.OK);
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> updatePersona(@PathVariable int id, @RequestBody PersonaInputDto personaInputDTO){
        if(!personaService.existsPersona(id)){
            return  new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        PersonaOutputDto personaOutputDTO = personaService.updatePersona(id, personaInputDTO);
        return new ResponseEntity<>(personaOutputDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersonRoute(@PathVariable int id){
        //If ID doesnt exists then return 404
        if(!personaService.existsPersona(id)){
            return  new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        PersonaOutputDto personaOutputDTO = personaService.deletePersona(id);
        return new ResponseEntity<>(personaOutputDTO, HttpStatus.OK);
    }
}
