package com.druiz.bosonit.webflux.proxy;

import com.druiz.bosonit.webflux.model.Person;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class PersonProxy {

    private final WebClient webClient;

    public PersonProxy(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<Person> getAllPersons(){
        return webClient.get().uri("/person") //path
                .exchangeToFlux(res -> res.bodyToFlux(Person.class));
    }

}
