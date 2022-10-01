package com.druiz.rs1.controllers;

import com.druiz.rs1.models.Persona;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller1 {

    @GetMapping("user/{id}")
    public Persona getID(@RequestBody Persona per, @PathVariable(value = "id") int id){
        per.setId(id);
        return per;
    }

    @PostMapping("/infoUser")
    public Persona getInfoPersona(@RequestBody Persona per, @RequestParam(value = "name") String name){
        per.setName(name);
        return per;
    }


    @PutMapping("/post")
    public String putVar(@RequestParam(value = "var1") int var1,
                          @RequestParam(value = "var2") int var2
    )
    {
        return "Var1: " + var1 + " Var2: " + var2;
    }

}
