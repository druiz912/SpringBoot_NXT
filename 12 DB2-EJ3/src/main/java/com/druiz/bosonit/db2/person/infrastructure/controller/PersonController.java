package com.druiz.bosonit.db2.person.infrastructure.controller;

import com.druiz.bosonit.db2.person.application.port.PersonFeign;
import com.druiz.bosonit.db2.person.infrastructure.controller.dto.output.PersonOutputDto;
import com.druiz.bosonit.db2.person.infrastructure.controller.dto.input.PersonInputDto;
import com.druiz.bosonit.db2.person.application.port.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "person")
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    PersonFeign personFeign;

    @PostMapping
    public ResponseEntity<PersonOutputDto> addPersona(@RequestBody @Valid PersonInputDto personInputDTO) throws Exception {
        return new ResponseEntity<>(personService.addPerson(personInputDTO),HttpStatus.OK);
    }

    @PutMapping("{id}")
    public PersonOutputDto updatePersona(@PathVariable("id") String id, @RequestBody @Valid PersonInputDto personInputDTO)
    {
        return personService.updatePerson(id, personInputDTO);
    }

    @GetMapping("/id/{id}")
    public PersonOutputDto getPersonaId(
            @PathVariable(value = "id") String id){
        return personService.findPersonById(id); //--> Segunda parte del ejercicio
    }


    @GetMapping("/name/{name}")
    public List<PersonOutputDto> getPersonaName(
            @PathVariable("name") String name,
            @RequestParam(defaultValue = "person", required = false) String outputType) {
        return personService.findPersonByName(name);
    }

    @GetMapping("/criteria")
    public List<PersonOutputDto> getPersonWithCriteriaQueryRoute(
            @RequestParam(required = false) Optional<String> name,
            @RequestParam(required = false) Optional<String>  surname,
            @RequestParam(required = false) Optional<String>  company,
            @RequestParam(required = false) Optional<String>  teacherName
    ){
        return personService.getPersonWithCriteriaQuery(name, surname, company, teacherName);
    }

    @DeleteMapping("{id}")
    public void deletePersona(@PathVariable("id") String id) throws Exception {
        personService.deletePerson(id);
    }

    @GetMapping("rt/teacher/{id}")
    public ResponseEntity<?> getTeacherWithTemplateRoute(@PathVariable String id){
        try {
            return personService.getTeacherWithTemplate(id);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("feign/teacher/{id}")
    public ResponseEntity<?> getTeacherWithFeignRoute(@PathVariable String id){
        try {
            return personFeign.getTeacher(id);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}
