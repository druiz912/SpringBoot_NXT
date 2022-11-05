package com.druiz.bosonit.dba1.person.infrastructure.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.druiz.bosonit.dba1.person.application.PersonService;
import com.druiz.bosonit.dba1.person.infrastructure.controllers.dto.PersonInputDto;
import com.druiz.bosonit.dba1.person.infrastructure.controllers.dto.PersonOutputDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("person")
public class CustomController {
    private final PersonService personService;

    public CustomController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public PersonOutputDto postPersona(@RequestBody PersonInputDto dto){
        return personService.addPerson(dto);
    }

    @GetMapping
    public List<PersonOutputDto> getPersonas(@RequestParam int pageNumber){
        return personService.findAllPersons(pageNumber);
    }

    @GetMapping("/name/{name}")
    public List<PersonOutputDto> getPersonaByName(@PathVariable String name){
        return personService.findPersonByName(name);
    }

    @GetMapping("/criteriaQuery")
    public List<PersonOutputDto> criteriaQuery(
            @RequestParam(required = false) Optional<String> name,
            @RequestParam(required = false) Optional<String> user,
            @RequestParam(required = false) Optional<String> surname,
            @RequestParam(required = false) Optional<String> address,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Optional<Date> creation_date,
            @RequestParam(defaultValue = "equal") String dateCondition,
            @RequestParam(required = false) Optional<String> sorting
    ){
        return personService.criteriaQuery(name,user,surname,address,creation_date,dateCondition,sorting);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable int id){ //<?> --> Recoge cualquier objeto
        if(!personService.existsPerson(id)){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        PersonOutputDto personOutputDTO = personService.findPersonById(id);
        return new ResponseEntity<>(personOutputDTO, HttpStatus.OK);
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> updatePersona(@PathVariable int id, @RequestBody PersonInputDto personInputDTO){
        if(!personService.existsPerson(id)){
            return  new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        PersonOutputDto personOutputDTO = personService.updatePerson(id, personInputDTO);
        return new ResponseEntity<>(personOutputDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersonRoute(@PathVariable int id){
        //If ID doesnt exists then return 404
        if(!personService.existsPerson(id)){
            return  new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        PersonOutputDto personOutputDTO = personService.deletePerson(id);
        return new ResponseEntity<>(personOutputDTO, HttpStatus.OK);
    }
}
