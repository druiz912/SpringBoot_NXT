package com.druiz.bosonit.dba1.person.infrastructure.controllers.dto;

import com.druiz.bosonit.dba1.person.domain.Person;

import java.time.LocalDate;

public record PersonOutputDto(
        String user_persona,
        String name,
        String surname,
        String address,
        String email,
        LocalDate created
) {


    public PersonOutputDto(Person p){
        this(
                p.getUser(),
                p.getName(),
                p.getSurname(),
                p.getAddress(),
                p.getEmail(),
                p.getCreated()
                );
    }
}
