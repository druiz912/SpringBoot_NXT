package com.druiz.crudvalidation;

import com.druiz.crudvalidation.persona.interfaces.IPersonaService;
import com.druiz.crudvalidation.persona.dtos.PersonaInputDto;
import com.druiz.crudvalidation.persona.dtos.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    IPersonaService personaService;

    @PostMapping("/add")
    public PersonaOutputDto addPersona(@RequestBody PersonaInputDto personInputDTO) throws Exception {
        return personaService.addPersona(personInputDTO);
    }

    @GetMapping("/id/{id}")
    public PersonaOutputDto getPersonaId(@PathVariable("id_persona") int id) throws Exception {
        return personaService.getPersonaId(id);
    }

    @GetMapping("/name/{name}")
    public List<PersonaOutputDto> getPersonaName(@PathVariable("name") String name) throws Exception {
        return personaService.getPersonaName(name);
    }

    @GetMapping("/all")
    public List<PersonaOutputDto> getPersons() {
        return personaService.getPersonas();
    }

    @PutMapping("/update/{id}")
    public void updatePersona(@PathVariable int id, @RequestBody PersonaInputDto p) {
        personaService.updatePersona(id, p);
        System.out.println("Usuario con id: "+id+" ha sido modificado");
    }

    @DeleteMapping("/eliminarPersona/{id}")
    public void deletePersona (@PathVariable int id) throws Exception{
        personaService.deletePersona(id);
        System.out.println("Usuario con id: "+id+" eliminado");
    }
}
