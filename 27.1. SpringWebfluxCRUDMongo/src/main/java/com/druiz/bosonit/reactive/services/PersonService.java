package com.druiz.bosonit.reactive.services;

import com.druiz.bosonit.reactive.documents.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {

    // Flux es un publisher | puede manejar un flujo de eventos (varios elementos)
    Flux<Person> findAllPersons();
    // Mono es un publisher | solo maneja un flujo de eventos con un solo elemento
    Mono<Person> findById(int id);
    Mono<Person> save(Person person);
    Mono<Void> delete (Person person);

}
