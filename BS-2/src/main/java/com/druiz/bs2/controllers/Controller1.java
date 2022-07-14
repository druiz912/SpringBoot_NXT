package com.druiz.bs2.controllers;

import com.druiz.bs2.models.Ciudad;
import com.druiz.bs2.models.Persona;
import com.druiz.bs2.services.CiudadService;
import com.druiz.bs2.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

@RestController
@Configuration
public class Controller1 {
        @Autowired
        @Qualifier("getDataPersona")
        PersonaService personaService;

        @Autowired
        @Qualifier("b1Qualifier")
        PersonaService personaServiceBean1;

        @Autowired
        @Qualifier("b2Qualifier")
        PersonaService personaServiceBean2;

        @Autowired
        @Qualifier("b3Qualifier")
        PersonaService personaServiceBean3;

        @Autowired
        @Qualifier("b4Qualifier")
        PersonaService personaServiceBean4;

        @Autowired
        CiudadService ciudadService;

        @GetMapping("/controller1/addPersona")
        public PersonaService getPersonaServiceInfo
                (@RequestHeader("nombre") String nombre,
                 @RequestHeader("poblacion") String poblacion,
                 @RequestHeader("edad") Integer edad) {
            personaService.setNombre(nombre);
            personaService.setPoblacion(poblacion);
            personaService.setEdad(edad);

            return personaService;
        }

        @PostMapping("/controller1/addCiudad")
        public CiudadService addCiudad(@RequestBody Ciudad c) {
            ciudadService.addCiudad(c);
            return ciudadService;
        }

        @GetMapping("/controller1/bean/{bean}")
        public PersonaService getNombreSer(@PathVariable String bean) {

            if (bean.equalsIgnoreCase("bean1")) {
                return personaServiceBean1;

            } else if (bean.equalsIgnoreCase("bean2")) {
                return personaServiceBean2;

            } else if (bean.equalsIgnoreCase("bean3")) {
                return personaServiceBean3;

            } else {
                return personaServiceBean4;
            }

        }
}
