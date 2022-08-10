package com.druiz.bosonit.dba1.persona.infrastructure.dto;

import com.druiz.bosonit.dba1.persona.domain.PersonaEntity;

import java.util.Date;

public record PersonaOutputDto(
        String user_persona,
        String name,
        String surname,
        String address,
        String email,
        Date created
) {


    public PersonaOutputDto(PersonaEntity p){
        this(
                p.getUser_persona(),
                p.getName(),
                p.getSurname(),
                p.getAddress(),
                p.getEmail(),
                p.getCreated()
                );
    }
}
