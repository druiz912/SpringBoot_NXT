package com.druiz.bosonit.mongodb.persona.infrastructure.dto.input;


public record PersonaInputDto(
        int id,
        String name,
        String surname,
        String address,
        int age){

}
