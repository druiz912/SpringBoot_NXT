package com.druiz.bosonit.reactive.controller;

import com.druiz.bosonit.reactive.documents.Person;
import com.druiz.bosonit.reactive.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.io.File;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("person")
public class PersonController {


    @Autowired
    private PersonService personService;

    @Value("${config.uploads.path}")
    private String path;

    //TODO: cambiar a handlercontroller

    @PostMapping
    public Mono<ResponseEntity<Person>> addPersonPhoto(Person person, @RequestPart FilePart file) {
        person.setPhoto(file.filename()
                .replace(" ", "")
                .replace(":", "")
                .replace("//", ""));

        return file.transferTo(new File(path + person.getPhoto()))
                .then(personService.save(person))
                .map(c -> ResponseEntity.created(URI.create("/person".concat(String.valueOf(c.getId()))))
                        .contentType(MediaType.APPLICATION_JSON).body(c));

    }

    @PostMapping("upload/{id}")
    public Mono<ResponseEntity<Person>> uploadPhoto(@PathVariable("id") int id, @RequestPart FilePart file) {
        return personService.findById(id).flatMap(c -> {
            c.setPhoto(file.filename()
                    .replace(" ", "")
                    .replace(":", "")
                    .replace("//", ""));

            return file.transferTo(new File(path + c.getPhoto())).then(personService.save(c));
        }).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Mono<ResponseEntity<Flux<Person>>> allPersons() {
        return Mono.just(
                ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(personService.findAllPersons())
        );
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Person>> findPersonDetails(@PathVariable int id) {
        return personService.findById(id).map(
                p -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p)).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> savePerson(@Valid @RequestBody Mono<Person> monoPerson) {
        Map<String, Object> response = new HashMap<>();
        return monoPerson.flatMap(person -> personService.save(person)
                .map(p -> {
                    response.put("person", p);
                    response.put("message", "Person saved successfully");
                    response.put("timestamp", new Date());
                    return ResponseEntity.created(URI.create("/person".concat(String.valueOf(p.getId()))))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(response);
                })).onErrorResume(e -> Mono.just(e).cast(WebExchangeBindException.class)
                .flatMap(b -> Mono.just(b.getFieldErrors()))
                .flatMapMany(Flux::fromIterable)
                .map(
                        fieldError -> "El campo : " + fieldError.getField() + " " + fieldError.getDefaultMessage())
                .collectList()
                .flatMap(list -> {
                    response.put("errors", list);
                    response.put("status", HttpStatus.BAD_REQUEST.value());
                    response.put("timestamp", new Date());

                    return Mono.just(ResponseEntity.badRequest().body(response));
                }));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Person>> updatePerson(@RequestBody Person person, @PathVariable int id) {
        return personService.findById(id).flatMap(p -> {
                    p.setName(person.getName());
                    p.setSurname(person.getSurname());
                    p.setMail(person.getMail());
                    p.setAge(person.getAge());
                    p.setPhone(person.getPhone());
                    return personService.save(p);
                }).map(person1 -> ResponseEntity.created(URI.create("/person".concat(String.valueOf(person1.getId()))))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(person1))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deletePerson(@PathVariable int id) {
        return personService.findById(id).flatMap(p -> personService.delete(p)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))))
                .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

    }

}
