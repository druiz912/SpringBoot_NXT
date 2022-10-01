package com.druiz.bs2.controllers;


import com.druiz.bs2.services.city.CiudadService;
import com.druiz.bs2.services.person.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("controller2")
public class Controller2 {
    @Autowired
    @Qualifier("getDataPersona")
    PersonaService personaService;

    @Autowired
    CiudadService ciudadService;

    @GetMapping("getPersona")
    public PersonaService getEdadPersona() {
        personaService.setEdad(personaService.getEdad() * 2);
        return personaService;
    }

    @GetMapping("getCiudad")
    public CiudadService getCiudad(){
        ciudadService.getCiudades();
        return ciudadService;
    }

}
