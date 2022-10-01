package com.druiz.crudvalidation.persona.infrastructure.controller.dtos;

import com.druiz.crudvalidation.persona.domain.Persona;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PersonaOutputDto implements Serializable {
    private String usuario;
    private String name;
    private String surname;
    private String companyMail;
    private String personalMail;
    private String city;
    private boolean active;
    private Date createdDate;
    private String imageUrl;
    private Date terminationDate;

    public PersonaOutputDto(Persona persona){
        if (persona == null)
            return;

        usuario = persona.getUsuario();
        name = persona.getName();
        surname = persona.getSurname();
        companyMail = persona.getCompanyMail();
        personalMail = persona.getPersonalMail();
        city = persona.getCity();
        active = persona.isActive();
        createdDate = persona.getCreatedDate();
        imageUrl = persona.getImageUrl();
        terminationDate = persona.getTerminationDate();
    }

}
