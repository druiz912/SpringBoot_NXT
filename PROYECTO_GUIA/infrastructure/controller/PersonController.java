package com.bosonit.exercises.bs8.infrastructure.controller;

import com.bosonit.exercises.bs8.application.port.IAddPersonPort;
import com.bosonit.exercises.bs8.application.port.IDeletePersonPort;
import com.bosonit.exercises.bs8.application.port.IGetPersonPort;
import com.bosonit.exercises.bs8.application.port.IUpdatePersonPort;
import com.bosonit.exercises.bs8.infrastructure.dto.input.PersonInputDTO;
import com.bosonit.exercises.bs8.infrastructure.dto.output.PersonOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    IAddPersonPort iAddPersonPort;
    @Autowired
    IGetPersonPort iGetPersonPort;
    @Autowired
    IUpdatePersonPort iUpdatePersonPort;
    @Autowired
    IDeletePersonPort iDeletePersonPort;

    @PostMapping
    public PersonOutputDTO addPerson(@RequestBody @Valid PersonInputDTO personInputDTO) throws Exception {
        return iAddPersonPort.addPerson(personInputDTO);
    }

    @GetMapping("/{id}")
    public PersonOutputDTO getPersonId(@PathVariable("id") String id) throws Exception {
        return iGetPersonPort.getPersonId(Integer.parseInt(id));
    }

    @GetMapping("/name/{name}")
    public List<PersonOutputDTO> getPersonName(@PathVariable("name") String user) throws Exception {
        return iGetPersonPort.getPersonName(user);
    }
    // GET http://localhost:8080/person/PEPE

    @GetMapping
    public List<PersonOutputDTO> getPersons() {
        return iGetPersonPort.getPersons();
    }

    @PutMapping("/{id}")
    public PersonOutputDTO updatePerson(@PathVariable("id") int id, @RequestBody PersonInputDTO personInputDTO)
            throws Exception {
        return iUpdatePersonPort.updatePerson(id, personInputDTO);
    }

    @DeleteMapping("{id}")
    public void deletePerson(@PathVariable("id") int id) throws Exception {
        iDeletePersonPort.deletePerson(id);
    }
}
