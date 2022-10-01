package com.druiz.bosonit.crudsecurity.persona.infrastructure.dtos;

import com.druiz.bosonit.crudsecurity.persona.domain.PersonaEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PersonaOutputDto {
    private Integer id;
    private String user;
    private String name;

    // La contraseña no hombre...
    //private String password;
    private String surname;
    private String email;
    private String city;
    private String rol;

    private Date created_date;
    private Date last_modified;

    public PersonaOutputDto(PersonaEntity persona){
        if (persona == null)
            return;
        id = persona.getId();
        user = persona.getUserPersona();
        name = persona.getName();
        surname = persona.getSurname();
        email = persona.getEmail();
        city = persona.getCity();
        rol = persona.getRol();
        created_date = persona.getCreatedDate();
        last_modified = persona.getLastModified();
    }
}
