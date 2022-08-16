package com.druiz.bosonit.mongodb.persona.infrastructure.dto.output;


import com.druiz.bosonit.mongodb.persona.domain.PersonaEntity;

public record PersonaOutputDto (
        int id,
        String name,
        String surname,
        String address,
        int age) {


    public PersonaOutputDto(PersonaEntity persona) {
        this(
                persona.getId(),
                persona.getName(),
                persona.getSurname(),
                persona.getAddress(),
                persona.getAge()
        );
    }

}
