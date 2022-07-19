package com.druiz.crud.controllers;

import com.druiz.crud.Persona;
import com.druiz.crud.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class PostController {
    AtomicInteger atomicInteger = new AtomicInteger();
    @Autowired
    @Qualifier("bean1")
    PersonaService personaService;

    @RequestMapping(value = "/persona", method = RequestMethod.POST)
    public PersonaService addPersona(@RequestBody Persona per) {
        per.setId(atomicInteger.incrementAndGet());
        personaService.addPersona(per);
        return personaService;
    }
}
