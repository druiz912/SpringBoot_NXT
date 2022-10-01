package com.druiz.bosonit.webflux.config;

import com.druiz.bosonit.webflux.handlers.PersonHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class RoutesConfig {

    @Bean
    public RouterFunction<ServerResponse> router(PersonHandler personHandler) {

        return route()
                //Podemos añadir varias route()...
                .GET("/person",
                        personHandler::getAllPersons)
                .build();
    }
}
