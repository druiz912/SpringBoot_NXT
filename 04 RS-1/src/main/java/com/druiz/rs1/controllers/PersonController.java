package com.druiz.rs1.controllers;

import com.druiz.rs1.models.Persona;
import com.druiz.rs1.services.PersonaService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    PersonaService personaService;

    @GetMapping
    public List<Persona> getPersonas() {
        return personaService.getPersonas();
    }

    @GetMapping("{id}")
    public Persona getPersonaById(@PathVariable Integer id) {
        return personaService.getPersonaById(id);
    }

    @GetMapping("name/{name}")
    public List<Persona> getPersonByName(@PathVariable String name) {
        return personaService.getPersonaByName(name);
    }

    @PostMapping
    public Persona addPerson(@RequestBody ObjectNode requestPerson) {
        String personName = requestPerson.get("name").asText();
        String personCity = requestPerson.get("city").asText();
        String personAge = requestPerson.get("age").asText();

        Persona newPerson = new Persona(personName, personCity, personAge);
        personaService.addPersona(newPerson);
        return newPerson;
    }

    @PutMapping("{id}")
    public Persona updatePerson(@RequestBody ObjectNode requestPerson, @PathVariable Integer id) {
        String personName;
        String personCity;
        String personAge;

        if (requestPerson.has("name"))
            personName = requestPerson.get("name").asText();
        else personName = "";

        if (requestPerson.has("city"))
            personCity = requestPerson.get("city").asText();
        else personCity = "";

        if (requestPerson.has("age"))
            personAge = requestPerson.get("age").asText();
        else personAge = "";


        Persona updatePerson = new Persona(personName, personCity, personAge);
        return personaService.updatePersona(id, updatePerson);
    }

    @DeleteMapping("{id}")
    public Persona deletePerson(@PathVariable Integer id) {
        return personaService.deletePersona(id);
    }
}