package com.druiz.bosonit;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "person")
public class Controller {

    @GetMapping("/{name}")
    String hello(@PathVariable String name){
        return "Hello " + name + "!";
    }

    @PostMapping
    public Persona createPerson(@RequestBody ObjectNode requestObject) {
        Persona newPerson =
                new Persona(
                        requestObject.get("nombre").asText(),
                        requestObject.get("ciudad").asText(),
                        requestObject.get("edad").asText());
        newPerson.addOneAge();
        return newPerson;
    }
}

