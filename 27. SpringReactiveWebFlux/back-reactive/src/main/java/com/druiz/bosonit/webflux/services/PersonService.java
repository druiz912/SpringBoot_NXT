package com.druiz.bosonit.webflux.services;

import com.druiz.bosonit.webflux.model.Person;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PersonService {

    //Devolver todos los productos --> trabajamos con el stream events
    public Flux<Person> getAllProducts() {
        //Instanciamos los objetos
        var person1 = new Person();
        var person2 = new Person();
        var person3 = new Person();
        var person4 = new Person();
        var person5 = new Person();
        final String GMAIL = "@gmail.com";
        // Daniel Ruiz
        person1.setId(1);
        person1.setName("Daniel");
        person1.setSurname("Ruiz");
        person1.setMail((person1.getName()+person1.getSurname()).toLowerCase() + GMAIL);
        // José Velez
        person2.setId(2);
        person2.setName("Jose");
        person2.setSurname("Velez");
        person2.setMail((person2.getName()+person2.getSurname()).toLowerCase() + GMAIL);
        // Juan Molina
        person3.setId(3);
        person3.setName("Juan");
        person3.setSurname("Molina");
        person3.setMail((person3.getName()+person3.getSurname()).toLowerCase() + GMAIL);
        // Alicia Romero
        person4.setId(4);
        person4.setName("Alicia");
        person4.setSurname("Romero");
        person4.setMail((person4.getName()+person4.getSurname()).toLowerCase() + GMAIL);
        // Carlos Gómez
        person5.setId(5);
        person5.setName("Carlos");
        person5.setSurname("Gómez");
        person5.setMail((person5.getName()+person5.getSurname()).toLowerCase() + GMAIL);

        return Flux.fromStream(Stream.of(person1, person2,person3,person4,person5))
                .delayElements(Duration.ofSeconds(3)); // Simulamos un atraso en la respuesta para ver mas claro lo de reactivo
    }
}


