package com.druiz.bosonit.crudsecurity.persona.infrastructure.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class PersonaInputDto {
    @NotBlank
    private String user;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    private String surname;
    @NotBlank
    private String email;
    @NotBlank
    private String city;

}
