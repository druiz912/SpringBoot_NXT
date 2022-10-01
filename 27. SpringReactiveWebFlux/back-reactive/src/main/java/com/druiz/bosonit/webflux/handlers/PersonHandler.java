package com.druiz.bosonit.webflux.handlers;

import com.druiz.bosonit.webflux.services.PersonService;
import com.druiz.bosonit.webflux.model.Person;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class PersonHandler {

    private final PersonService personService;

    public PersonHandler(PersonService personService) {
        this.personService = personService;
    }

    public Mono<ServerResponse> getAllPersons(ServerRequest
                                                       req) {
        //req.headers()
        // sin el contentType(MediaType.TEXT_EVENT_STREAM) -> No estaríamos estableciendo un cliente web reactivo
        //Entonces por mucho que nuestro back sea reactivo si el cliente no lo es no sirve de nada.
        return ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(personService.getAllProducts(), Person.class);
    }
}
