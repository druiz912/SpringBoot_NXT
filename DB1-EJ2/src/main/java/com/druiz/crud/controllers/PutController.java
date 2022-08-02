package com.druiz.crud.controllers;

import com.druiz.crud.Persona;
import com.druiz.crud.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PutController {

    @Autowired
    PersonaService pService;

    @RequestMapping(value = "/persona/{id}", method = RequestMethod.PUT)
    public PersonaService updatePersona(@RequestBody Persona person,
                                        @PathVariable(value = "id") int id) {
        for (int i = 0; i < pService.getListaPersonas().size(); i++) {
            if (pService.getListaPersonas().get(i).getId() == id) {
                pService.getListaPersonas().get(id - 1).setNombre(person.getNombre());
                pService.getListaPersonas().get(id - 1).setEdad(person.getEdad());
                pService.getListaPersonas().get(id - 1).setPoblacion(person.getPoblacion());
            }

        }
        return pService;
    }
}

