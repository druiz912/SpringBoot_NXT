package com.druiz.bp1.content.infrastructure.controller;

import com.druiz.bp1.content.application.port.PersonService;
import com.druiz.bp1.content.infrastructure.controller.dto.input.PersonInputDto;
import com.druiz.bp1.content.infrastructure.controller.dto.output.PersonOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/id/{id}")
    public PersonOutputDto getPersonById(@PathVariable("id") int id) throws Exception {
        return personService.findPersonById(id);
    }


    @GetMapping("/name/{name}")
    public List<PersonOutputDto> getPersonName(@PathVariable("name") String name) throws Exception {
        return personService.findPersonByName(name);
    }

    @GetMapping
    public List<PersonOutputDto> getAllPersons() {
        return personService.findAllPerson();
    }

    @PostMapping
    public PersonOutputDto addPerson(@RequestBody PersonInputDto personInputDTO) throws Exception {
        return personService.addPerson(personInputDTO);
    }

    @PutMapping("{id}")
    public PersonOutputDto updatePerson(@PathVariable int id, @RequestBody PersonInputDto personInputDto) {
        return personService.updatePerson(id, personInputDto);
    }

    @DeleteMapping("{id}")
    public void deletePerson(@PathVariable int id) throws Exception {
        personService.deletePerson(id);
    }
}
