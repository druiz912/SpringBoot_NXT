package com.druiz.crud.controllers;

import com.druiz.crud.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class DeleteController {

    protected int i;
    @Autowired
    @Qualifier("bean1")
    PersonaService personaService;

    @RequestMapping(value = "/persona/{id}", method = RequestMethod.DELETE)
    public PersonaService deletePersonaByID(@PathVariable(value = "id") int id) {
        for (i = 0; i < personaService.getListaPersonas().size(); i++) {
            if (personaService.getListaPersonas().get(i).getId() == id) {
                personaService.getListaPersonas().remove(id - 1);
            }
        }
        return personaService;
    }
}
