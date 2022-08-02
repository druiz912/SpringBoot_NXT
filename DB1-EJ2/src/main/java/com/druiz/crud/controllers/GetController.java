package com.druiz.crud.controllers;

import com.druiz.crud.Persona;
import com.druiz.crud.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GetController {



    @Autowired
    PersonaService pService;
    static List<Persona> listaPersonas = new ArrayList<Persona>();
    static {
        listaPersonas.add(new Persona(1, "Daniel", 22,"Huelva"));
    }

    @GetMapping(value = "/personas")
    public List<Persona> lista() {
        return listaPersonas;
    }

    @RequestMapping(value = "/persona/{id}", method = RequestMethod.GET)
    public Persona getInfoByID(@PathVariable(value = "id") int id) {
        return pService.getByID(id - 1);
    }

    @RequestMapping(value = "/persona/nombre/{nombre}", method = RequestMethod.GET)
    public Persona getInfoNombre(@PathVariable(value = "nombre")String nombre){
        return pService.getByName(nombre);
    }
}
