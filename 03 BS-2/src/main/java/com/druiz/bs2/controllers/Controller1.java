package com.druiz.bs2.controllers;

import com.druiz.bs2.models.Ciudad;
import com.druiz.bs2.services.city.CiudadService;
import com.druiz.bs2.services.person.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("controller1")
public class Controller1 {
    @Autowired
    @Qualifier("getDataPersona")
    PersonaService personaService;

    @Autowired
    @Qualifier("bean1")
    PersonaService personaServiceBean1;

    @Autowired
    @Qualifier("bean2")
    PersonaService personaServiceBean2;

    @Autowired
    @Qualifier("bean3")
    PersonaService personaServiceBean3;

    @Autowired
    CiudadService ciudadService;

    @GetMapping("addPersona")
    public PersonaService getPersonaServiceInfo
            (@RequestHeader Map<String, String> headers) {
        personaService.setNombre(headers.get("name"));
        personaService.setCiudad(headers.get("city"));
        personaService.setEdad(Integer.parseInt(headers.get("age")));
        return personaService;
    }

    @PostMapping("addCiudad")
    public CiudadService addCiudad(@RequestBody Ciudad c) {
        ciudadService.addCiudad(c);
        return ciudadService;
    }

    @GetMapping("/bean/{bean}")
    public PersonaService chooseBean(@PathVariable String bean) {
        PersonaService auxPerson;
        switch (bean) {
            case "bean1":
                auxPerson = personaServiceBean1;
                break;
            case "bean2":
                auxPerson = personaServiceBean2;
                break;
            case "bean3":
                auxPerson = personaServiceBean3;
                break;
            default:
                auxPerson = personaService;
        }

        return auxPerson;

    }
}
