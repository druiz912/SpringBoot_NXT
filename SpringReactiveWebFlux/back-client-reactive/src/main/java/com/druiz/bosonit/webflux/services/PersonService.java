package com.druiz.bosonit.webflux.services;

import com.druiz.bosonit.webflux.model.Person;
import com.druiz.bosonit.webflux.proxy.PersonProxy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class PersonService {

    private final PersonProxy personProxy;

    public PersonService(PersonProxy personProxy) {
        this.personProxy = personProxy;
    }

    public Flux<Person> getAllPersons(){
        return personProxy.getAllPersons();
    }

}
