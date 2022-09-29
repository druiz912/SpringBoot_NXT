package com.druiz.bosonit.reactive.services;

import com.druiz.bosonit.reactive.documents.Person;
import com.druiz.bosonit.reactive.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepo personRepo;

    @Override
    public Flux<Person> findAllPersons() {
        return personRepo.findAll();
    }

    @Override
    public Mono<Person> findById(int id) {
        return personRepo.findById(id);
    }

    @Override
    public Mono<Person> save(Person person) {
        return personRepo.save(person);
    }

    @Override
    public Mono<Void> delete(Person person) {
        return personRepo.delete(person);
    }
}
